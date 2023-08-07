import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface IsPrime {
	public List<Integer> primeNumbersInRange(Integer start, Integer end);
 }
 
 class PrimeCalculator implements IsPrime {
    List<Worker> workers = new ArrayList<>(10);  // TODO : Dynamic addition / removal  of workers
    
	public List<Integer> primeNumbersInRange(Integer start, Integer end) {
     Map<Integer, List<Integer> > collating = new HashMap<>(10);
	    int perPart = (end-start)/workers.size();
		for ( int i = 0 ; i < workers.size(); i++ ) {
			collating.put(i, workers.get(i).primesInSubRange(request_id, i, start+ i*perPart , start+ (i+1)*perPart ) );
		}
	 List<Integer> allNumbers = new ArrayList<>();
	 for ( int i = 0 ; i < workers.size(); i++ ) {
	 	allNumbers.addAll(collating.get(i));
	 }
	 return allNumbers;
	}
 }
 
 interface Worker {
 	public primesInSubRange(String request_id, int part_id , Integer start, Integer end);
 }
 
 class ParWorker implements Worker {
 	
 	 public List<Integer> primesInSubRange(String request_id, int part_id , Integer start, Integer end) {
 	 List<Integer> primesInSubPart = new ArrayList<>();
 		for ( int i = start ; i < end ; i++ ) {
 			if ( primeTester.isPrime(i) { primesInSubPart.add(i); } 
 		}
 	return primesInSubPart;
 	}
 }