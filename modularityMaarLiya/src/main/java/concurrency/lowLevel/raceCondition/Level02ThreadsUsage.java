package concurrency.lowLevel.raceCondition;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;


public class Level02ThreadsUsage {
	static int counter = 0;
	static class Task1 implements Runnable {
		public void run() {
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			System.out.println("counter " + counter);
		}
	}
	static class Task2 implements Runnable {
		public void run() {
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			System.out.println("Increasing counter " + counter++); // counter++ here is deliberate to see if Task1 ever sees 1 value. Whi
			counter++;
		}
	}
	

	public static void main(String[] args) {
		//		Task1 t1 = new Task1(); // TODO @NOTE : See and resolve the error , when Task1 and Task2 are not static
		//		Thread th1 = new Thread(new Task1()); // TODO @NOTE : Ye dono baahar rahega , and start() andar to java.lang.IllegalThreadStateException aata hai because thread is already started
		//		Thread th2 = new Thread(new Task2());
		
		IntStream.rangeClosed(1, 100).forEach(x -> {competingThreadsWithoutJoin.accept(x); counter = 0; System.out.println("******");}); // Without Join it is all haphazard -- waat hi lagaa diye hain teeno threads mil ke
		// Value went up to 82 also in one run
		// Thread.sleep() lagaa do th1 and th2 mein tab to aur puraa *** print kar dega apna Main thread , and then dono threads ka kaam start hoga
		// counter reset forEach mein rakho ya accept() mein batiyaa is the same
//		IntStream.rangeClosed(1, 100).forEach(x -> competingThreadsWithJoin.accept(x)); // With Join there is atleast some order in the disorder .. Kabhi 0 , kabhi 1 , kabhi 2 ... bas 3 states hi.
	}

	static IntConsumer competingThreadsWithoutJoin = new IntConsumer() {
		
		@Override
		public void accept(int value) {

			Thread th1 = new Thread(new Task1());
			Thread th2 = new Thread(new Task2());
			th1.start(); 
			th2.start(); 
			
		}
	};

	static IntConsumer competingThreadsWithJoin = new IntConsumer() {
		
		@Override
		public void accept(int value) {

			Thread th1 = new Thread(new Task1());
			Thread th2 = new Thread(new Task2());
			th1.start(); 
			th2.start(); 

//			competingThreadsWithoutJoin.accept(value); // th1 aur th2 yadi reference idhar mil gaya to we can use this statement instead;

			try {
				th1.join();
				th2.join();
			} catch (InterruptedException e) {
				System.out.println("Waiting to see if Interrupted Exception is ever thrown");
				e.printStackTrace();
			}
			counter = 0; 
			System.out.println("******");
			
		}
	};
}
