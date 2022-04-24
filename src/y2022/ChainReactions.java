package y2022;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * To review, WA
 * 
 * @author jeremypetit-jean
 *
 */
public class ChainReactions {
	
	private static Map<Integer, Node> nodes = new HashMap<>();
	private static Map<Node, Integer> depths = new HashMap<>();
	
	private static class Node {
		public int id;
		public int f;
		public Node parent;
		public List<Node> childs = new ArrayList<>();
		public boolean triggered = false;
		
		// Create root abyss
		public Node() {
			id = 0;
			f = 0;
		}
		
		public Node(int id, int f) {
			this.id = id;
			this.f = f;
		}
	}
	
	public static void main(String[] args) {
		try(Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)))) {
			int t = in.nextInt();
			for(int i = 1; i <= t; i++) {
				System.out.println("Case #"+i+": "+solveCase(in));
				nodes.clear();
				depths.clear();
			}
			
		}
	}


	private static int solveCase(Scanner in) {
		int n = in.nextInt();
		List<Integer> funFactors = new ArrayList<>();
		List<Integer> pointers = new ArrayList<>();
		for(int i = 0; i < n; i++)
			funFactors.add(in.nextInt());
		for(int i = 0; i < n; i++)
			pointers.add(in.nextInt());
		
		Node abyss = buildTree(funFactors, pointers);
		List<Node> leaves = getLeaves(abyss, 0);
		// Sort according to depths first, and then to the fun amount
		leaves.sort((n1, n2) -> {
			if(depths.get(n1) < depths.get(n2)) return -1;
			else if(depths.get(n1) > depths.get(n2)) return 1;
			else 
				return Integer.compare(n1.f, n2.f);
		});
		
		int maxFun = computeMaxFun(leaves);
		
		return maxFun;
	}


	private static int computeMaxFun(List<Node> leaves) {
		return leaves.stream().map(l -> computeMaxFun(l)).reduce(0, (s, e) -> s + e);
	}

	private static int computeMaxFun(Node leaf) {
		Node current = leaf;
		int max = Integer.MIN_VALUE;
		do {
			current.triggered = true;
			max = Math.max(max, current.f);
			current = current.parent;
		} while(current != null && !current.triggered);
		return max;
	}


	private static List<Node> getLeaves(Node root, int depth) {
		List<Node> leaves = new ArrayList<>();
		if(root.childs.isEmpty()) {
			leaves.add(root);
			depths.put(root, depth + 1);
		}
		else 
			for(Node subChild : root.childs) 
				leaves.addAll(getLeaves(subChild, depth + 1));
		return leaves;
	}

	private static Node buildTree(List<Integer> funFactors,
			List<Integer> pointers) {
		Node abyss = new Node();
		nodes.put(abyss.id, abyss);
		for(int i = 0; i < funFactors.size(); i++) {
			Node node = new Node(i + 1, funFactors.get(i));
			nodes.put(node.id, node);
			Node parent = nodes.get(pointers.get(i));
			node.parent = parent;
			parent.childs.add(node);
		}
		return abyss;
	}
}