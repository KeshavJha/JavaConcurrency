package recursion;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Josephus {
	public static void main(String[] args) {
//		System.out.println(josephus(50,4));
//		System.out.println(josephus(50,9));
//
//		System.out.println(josephusIterative(50,4));
//		System.out.println(josephusIterative(50,9));
		
		usingDifferentMaps();
	}

	public static int josephus(int n, int k) {
		if (n == 1) {
			return 1;
		} else {
			return (josephus(n - 1, k) + k - 1) % n + 1;
		}
	}

	private static int josephusIterative(int total, int pos) {
		if(total < 0 ) { System.out.println("Can return living prisoner");}
		if (total == 1) { return 1;}
		Queue<Integer> prisoners = // new ArrayDeque<Integer>();
				IntStream.rangeClosed(1, total).boxed().collect(Collectors.toCollection(ArrayDeque::new));

		//		Iterator<Integer> guard = prisoners.iterator();
		//		Iterator<Integer> auxiliary = new Iterator<Integer>() {  --- // @NOTE : Iterator ka copy() ya clone() kaise karoge ??

		//			@Override
		//			public Integer next() {
		//				// TODO Auto-generated method stub
		//				return null;
		//			}
		//			
		//			@Override
		//			public boolean hasNext() {
		//				// TODO Auto-generated method stub
		//				return false;
		//			}
		//		};
		System.out.println(prisoners);

		int i = 1;
		while (prisoners.size() > 1) {
			if(i == pos) { System.out.println("prisoner killed :: " + prisoners.poll()); i = 1;}
			else {
				prisoners.offer(prisoners.poll());
				i++ ;

			}
		}
		return prisoners.poll();
	}

	public static void usingDifferentMaps() {
		Map<Integer, Integer> squares = new HashMap<>();
		IntStream.rangeClosed(1,150).forEach(x -> squares.put(x, x*x));
		for( Integer x:squares.values()) { System.out.print(x + ", "); }
		System.out.println();

		Map<Integer, Integer> cubes =  new LinkedHashMap<>();
		IntStream.rangeClosed(1,100).forEach(x -> cubes.put(x, x*x*x));
		for( Integer x:cubes.values()) { System.out.print(x + ", "); }
		System.out.println();
	}

}
