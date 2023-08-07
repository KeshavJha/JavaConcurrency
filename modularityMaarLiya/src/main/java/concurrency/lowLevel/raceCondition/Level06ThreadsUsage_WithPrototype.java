package concurrency.lowLevel.raceCondition;

import concurrency.lowLevel.ThreadUsage_WithPrototype;

public class Level06ThreadsUsage_WithPrototype extends ThreadUsage_WithPrototype {
	class Task1 extends ThreadUsage_WithPrototype.Task1 {
		public void run() {
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			synchronized(Level05ThreadsUsage.class) {
				System.out.println("counter " + counter);
			}
		}
	}
	class Task2 extends ThreadUsage_WithPrototype.Task2 {
		public void run() {
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			synchronized(Level05ThreadsUsage.class) {
				System.out.println("Increasing counter " + counter++); // counter++ here is deliberate to see if Task1 in competingThreadsWithJoin ever sees 1 value. Which it does .
				 // @NOTE POINT 1 :  But not if this method is synchronized. --- Nakko ji , tried making run() synchronized -- as well as this block as synchronized , but Still value 1 is visible . REASON - counter to nahi hai na synchronized ; two separate operations on counter value which means probably counter itself should be synchronized.
				 // @NOTE POINT 2 :  Continued from Point 1. --- synchronized modifier is not valid for a variable.
				 // @NOTE POINT 3 :  Continued from Point 1 & 2 --- synchronized modifier instead of applying on counter variable, apply it on both run() methods and it still doesn't work, REASON - Synchronized on respective classes.
				 // @NOTE POINT 4 :  Continued from Point 3 --- synchronized blocks on common parent class ---- WOOHAAHAAAHAA == This worked. Now the Value is either 0 or 2.
				counter++;
			}
		}
	}
	
	@Override  // NOTE : Can't override static methods  -- can do if method is not static
	public ThreadUsage_WithPrototype.Task1 getTask1() {
		return new Task1();
	}

	@Override  // NOTE : Can't override static methods
	public ThreadUsage_WithPrototype.Task2 getTask2() {
		return new Task2();
	}	

	public static void main(String[] args) {
		Level06ThreadsUsage_WithPrototype p1 = new Level06ThreadsUsage_WithPrototype();
		p1.invokeDriver();
	}
	
	// 
}
