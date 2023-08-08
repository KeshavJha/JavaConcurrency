package recursion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;


public class WaysBetweenPoints {
	public static void main(String[] args) {
		hanoiDriver(7);
	}
	
	private static void hanoiDriver( int size ) {
		HanoiTower a = new HanoiTower(size);
		System.out.println(a);
//		a.movePart(2, a.getSource(), a.getTarget());

		List<Integer> source = a.getSource();
		List<Integer> intermediate = a.getIntermediate();
		List<Integer> target = a.getTarget();
		
		moveRecursive(a , size , source, intermediate, target);
	}

	private void waysBetweenPoints(int height, int width, int zeroIndexStartX, int zeroIndexStartY, int zeroIndexEndX, int zeroIndexEndY) {
		if( (zeroIndexStartX > zeroIndexEndX) || (zeroIndexStartY > zeroIndexEndY) ) { return ;}
		else {
//			if 
		}
	}
	
	private List<List<Point>> pathsFromSourceToDestination( int[][] maze, Point start, Point end) {
		return null;
	}
	
	private static void moveRecursive ( HanoiTower ht, int size , List<Integer> source ,List<Integer> intermediate ,List<Integer> target ) {
		if(size > 1 ) moveRecursive(ht, size - 1, source, target, intermediate);
		ht.movePart(1, source, target);
		if ( size > 1 ) moveRecursive(ht, size - 1, intermediate, source, target );
	}
}

//@Sett
class Point {
	int x ;
	int y ;
}

class HanoiTower {
	private ArrayList<Integer> source, target , intermediate;

	HanoiTower(int size) {
		source = new ArrayList<>(size);
		target = new ArrayList<>(size);
		intermediate = new ArrayList<>(size);
		
		IntStream.rangeClosed(1,size).boxed().sorted(Comparator.reverseOrder()).forEach(x -> source.add(x)); // @NOTE :: Note Generation of a range of Integers
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("\nstart:%s \nintermediate:%s, \n end:%s \n*****\n", source.toString(), intermediate.toString(), target.toString());
	}
	
	
	public void movePart(int toMove, List<Integer> from, List<Integer> to) {
		List<Integer> listToMove = from.subList(from.size()- toMove, from.size());
		System.out.println("Moving list :: " + listToMove.toString());
		to.addAll(listToMove);
		from.removeAll(listToMove);
		
		System.out.println("state after move :: "+ toString()); 
	}
	
	public ArrayList<Integer> getSource() {
		return source;
	}

	public ArrayList<Integer> getTarget() {
		return target;
	}

	public ArrayList<Integer> getIntermediate() {
		return intermediate;
	}
	
	
}
