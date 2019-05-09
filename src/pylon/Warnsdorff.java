package pylon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Warnsdorf algo, Does not work...
 * @author jeremypetit-jean
 *
 */
public class Warnsdorff {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.

		int startC = 1;
		int startR = 1;

		for (int i = 1; i <= t; ++i) {
			int r = in.nextInt();
			int c = in.nextInt();

			boolean solutionFound = false;

			Set<Pair<Integer, Integer>> cells = new LinkedHashSet<>();
			LinkedHashSet<Pair<Integer, Integer>> visited = new LinkedHashSet<>();

			for(int rc = 1 ; rc <= r; rc++) {
				for(int cc = 1 ; cc <= c; cc++) {
					cells.add(Pair.of(rc, cc));
				}
			}

			Pair<Integer, Integer> current = Pair.of(startR, startC);
			cells.remove(current);
			visited.add(current);

			while(true) {
				Optional<Pair<Integer, Integer>> nextMove = getNextMove(cells, visited, current, r, c);
				if(nextMove.isPresent()) {
					current = nextMove.get();
					visited.add(current);
				} else break;
			}

			if(cells.size() == visited.size()) {
				// Finished
				System.out.println("Case #" + i + ": POSSIBLE");
				for(Pair<Integer, Integer> cell : visited) {
					System.out.println(cell.first + " " + cell.second);
				}
			} else {
				// Impossible
				System.out.println("Case #" + i + ": IMPOSSIBLE");
				for(Pair<Integer, Integer> cell : visited) {
					System.out.println(cell.first + " " + cell.second);
				}
			}
		}
	}

	private static Optional<Pair<Integer,Integer>> getNextMove(Set<Pair<Integer, Integer>> cells, Set<Pair<Integer, Integer>> visited, Pair<Integer, Integer> cell, int r, int c) {
		List<Pair<Integer,Integer>> moves = getPossibleMoves(cell, r, c).stream().filter(m -> !visited.contains(m)).collect(Collectors.toList());
		
		if(!moves.isEmpty()) {
			Pair<Integer, Integer> best = moves.get(0);
			long bestDegree = getDegree(cells, visited, best, r, c);
			for(Pair<Integer, Integer> m : moves) {
				long degree = getDegree(cells, visited, m, r, c);
				if(degree < bestDegree) {
					best = m;
					bestDegree = degree;
				}
			}
			
			return Optional.of(best);
		}
		return Optional.empty();
	}	
	
	private static long getDegree(Set<Pair<Integer, Integer>> cells, Set<Pair<Integer, Integer>> visited,
			Pair<Integer, Integer> cell, int r, int c) {
		List<Pair<Integer, Integer>> adjs = getAdjacants(cell, r, c);
		return adjs.stream().filter(adj -> visited.contains(adj)).count();
	}

	private static List<Pair<Integer, Integer>> getAdjacants(Pair<Integer, Integer> cell, int r, int c) {
		List<Pair<Integer,Integer>> moves = new ArrayList<>();
		
		moves.add(Pair.of(cell.first + 0, cell.second + 1));
		moves.add(Pair.of(cell.first + 0, cell.second - 1));
		moves.add(Pair.of(cell.first + 1, cell.second + 1));
		moves.add(Pair.of(cell.first + 1, cell.second + 0));
		moves.add(Pair.of(cell.first + 1, cell.second - 1));
		moves.add(Pair.of(cell.first - 1, cell.second + 1));
		moves.add(Pair.of(cell.first - 1, cell.second + 0));
		moves.add(Pair.of(cell.first - 1, cell.second - 1));
		
		return moves.stream().filter(o -> o.first > 0 && o.second > 0 && o.first <= r && o.second <= c).collect(Collectors.toList());
		
	}

	private static List<Pair<Integer,Integer>> getPossibleMoves(Pair<Integer, Integer> cell, int r, int c) {
		List<Pair<Integer,Integer>> moves = new ArrayList<>();
		
		moves.add(Pair.of(cell.first + 1, cell.second + 2));
		moves.add(Pair.of(cell.first + 2, cell.second + 1));
		moves.add(Pair.of(cell.first - 1, cell.second - 2));
		moves.add(Pair.of(cell.first - 2, cell.second - 1));
		moves.add(Pair.of(cell.first - 1, cell.second + 2));
		moves.add(Pair.of(cell.first - 2, cell.second + 1));
		moves.add(Pair.of(cell.first + 1, cell.second - 2));
		moves.add(Pair.of(cell.first + 2, cell.second - 1));
		
		return moves.stream().filter(o -> o.first > 0 && o.second > 0 && o.first <= r && o.second <= c).collect(Collectors.toList());
	}
	
	/**
	 * Rules defined to tell if it's possible to travel to cell...
	 * (Different row, cell and not on diagonals)
	 * @param from
	 * @param to
	 * @return boolean 
	 */
	private static boolean isTravelPossible(Pair<Integer, Integer> from,
			Pair<Integer, Integer> to) {
		return !from.first.equals(to.first) 
				&& !from.second.equals(to.second) 
				&& (from.first + from.second) != (to.first + to.second)  
				&& (from.first - from.second) != (to.first - to.second);
	}

	static class Pair<T, U>
	{
		T first;
		U second;

		public Pair(T first, U second) {
			this.first = first;
			this.second = second;
		}

		// Return a map entry (key-value pair) from the specified values
		public static <T, U> Pair<T, U> of(T first, U second)
		{
			return new Pair<T, U>(first, second);
		}

		public T getFirst() {
			return first;
		}

		public U getSecond() {
			return second;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((first == null) ? 0 : first.hashCode());
			result = prime * result
					+ ((second == null) ? 0 : second.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair<?, ?> other = (Pair<?, ?>) obj;
			if (first == null) {
				if (other.first != null)
					return false;
			} else if (!first.equals(other.first))
				return false;
			if (second == null) {
				if (other.second != null)
					return false;
			} else if (!second.equals(other.second))
				return false;
			return true;
		}


	}
}
