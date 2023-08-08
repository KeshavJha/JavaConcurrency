package concurrency.lowLevel.deadLock;

import java.util.stream.IntStream;

public class DeadlockUsingSynchronized {
	Object lock1 = new Object(), lock2 = new Object();
	
	public static void main(String[] args) {
		DeadlockUsingSynchronized dl = new DeadlockUsingSynchronized();
		
		dl.letSleepyThreadSleep();
		System.out.println("\n********\n");
		
		new DeadlockUsingSynchronized().dontLetSleepyThreadSleep(); // NOTE: Thread.start() will work only if invoked on a separate Parent object
		// For the same object, after the Thread has completed/ interrupted / waiting / etc , IT just can't be start()ed again . 
		// If multiple Thread.start() are needed use Runnable, and create multiple Threads with this Runnable as parameter , and start each one afresh --- See the need of Executors here ?
		System.out.println("\n********\n");
		
		dl.playSeetaAurGeeta();
		System.out.println("\n********\n");
		
	}
	
	public void letSleepyThreadSleep() {
		sleepyThread.start();
		try {
			Thread.sleep(500);
			sleepyThread.join();   // NOTE: current thread waits on the thread on which join() is called, to complete before proceeding to next statement
		} catch (InterruptedException e) {
			System.out.println("How dare !!! ... I was waiting for sleepy  :-1 ");
			e.printStackTrace();
		}
		System.out.println("Finally !!! ... Sleepy is up :+1 ");
	}
	
	public void dontLetSleepyThreadSleep() {
		sleepyThread.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("who dared !!! ... To disturb MAIN's sleep  :/ ");
			e.printStackTrace();
		}
		sleepyThread.interrupt();  // NOTE: Thread.interrupt() interrupts the sleep() of thread
	}
	
	private Thread sleepyThread = new Thread(() -> {
		System.out.println("sleeping .. Good night");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("oh no !!! ... Youuu didn't let me sleep  :'(");
			e.printStackTrace();
		}
		System.out.println("Awake now ... Good morning !!");  // NOTE: even when Thread.interrupt() interrupts the sleep() of thread , everything after catch will be executed. If you don't handle the exception then it will return with the exception
		});

	private void playSeetaAurGeeta() {

		IntStream.range(1, 11).forEach(i -> {
			Thread geeta = new Thread(SeetaAurGeetaKiGeeta);
			Thread seeta = new Thread(SeetaAurGeetaKiSeeta);
			
			seeta.start();geeta.start();
			
			try {
				seeta.join(); 
				geeta.join();
			} catch (InterruptedException e) {
				System.out.println("Seeta and Geeta got deadLocked , in " + i + "th turn");
				e.printStackTrace();
			}
			System.out.println("Seeta and Geeta both were successful , " + i + "times");
			
			System.out.println("\n=====\n");
		});
	}
	private Runnable SeetaAurGeetaKiSeeta = () -> { 
		templateOfLockingThreads("Seeta coming","Seeta has come !!","Now Seeta going ... ","Seeta on the way .... OR is she locked ??? ", "Impossible .... but Seeta is gone :(", lock1,lock2);
		
		
	};

	private Runnable SeetaAurGeetaKiGeeta = () -> { 
		templateOfLockingThreads("Geeta aati hai","Geeta aa gayi !!","Ab Geeta jaaegi ... ","Geeta jaa rahi hai .... LEKIN kya woh jaa paayegi ??? ", "Asambhav .... but Geeta bhi gayi :( ", lock2,lock1);
		
		};
		
		private void templateOfLockingThreads(String trailer, String firstHalf, String interval, String secondHalf, String climax, Object l1, Object l2) {
			
			System.out.println(trailer);		
			synchronized (l1) {               // NOTE: If order of locks is same in both threads , they WILL pass always ( because the thread that gets first thread is guaranteed to get the second one also ) ;  
//				try {
//					Thread.sleep(200);
//				} catch (InterruptedException e) {
//					System.out.println("Not even possible !!  ... no one interrupting you here");
//					e.printStackTrace();
//				}
				System.out.println(firstHalf);
				synchronized (l2) {          // NOTE contd. : REVERSE the order of locks - and deadlock rate is significantly higher ( maybe one or two loops successful ) ; SLEEP after taking first lock in reverse order - and not even run successful.
					System.out.println(interval);
				}
				System.out.println(secondHalf);

			}
			System.out.println(climax);
			
		}
}
