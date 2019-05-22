package org.paumard.locks.semaphore;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class Test implements Runnable {
	Semaphore mSemaphore;
	String mThreadName;

	public Test(Semaphore sem, String threadName) {
		mSemaphore = sem;
		mThreadName = threadName;
	}

	@Override
	public void run() {
		// First, get a permit.
		System.out.println(mThreadName + " is waiting for a permit.");

		try {
			// acquire method
			mSemaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(mThreadName + " gets a permit");

		// Now, critical section
		// other waiting threads will wait, until this
		// thread release the lock
		for (int i = 0; i < 2; i++) {
			// hasQueuedThreads() methods
			boolean b = mSemaphore.hasQueuedThreads();
			if (b)
				// getQueuedLength() methods
				System.out.println("Length of Queue : " + mSemaphore.getQueueLength());

			// Now, allowing a context switch -- if possible.
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Release the permit.
		System.out.println(mThreadName + " releases the permit.");

		// release() method
		mSemaphore.release();

	}

}

//Driver class 
public class SemaphoreDemoRunnable {

	@SuppressWarnings("serial")
	public static void main(String args[]) throws InterruptedException {
//		// creating a Semaphore object
//		// with number of permits 3 and fairness true
//		Semaphore sem = new Semaphore(3, true);

		// creating a Semaphore object
		// with number of permits 3 and fairness true
		Semaphore sem = new Semaphore(3);
		// isFair() method
		System.out.println("is Fairness enabled : " + sem.isFair());

		// Main thread try to acquire 2 permits
		// tryAcquire(int permits) method
		sem.tryAcquire(3);

		// availablePermits() method
		System.out.println("Available permits : " + sem.availablePermits());

		// drainPermits() method
		System.out.println("number of permits drain by Main thread : " + sem.drainPermits());

		// permit released by Main thread
		sem.release(2);
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		ArrayList<Runnable> tasks = new ArrayList<Runnable>() {
			{
				add(new Test(sem, "A"));
				add(new Test(sem, "B"));
				add(new Test(sem, "C"));
				add(new Test(sem, "D"));
				add(new Test(sem, "E"));
				add(new Test(sem, "F"));
			}
		};
		try {
			for (Runnable runnable : tasks) {
				executorService.execute(runnable);
			}
		} finally {
			executorService.shutdown();
			System.out.println("Executor service shut down");
		}

		// toString method
		System.out.println(sem.toString());
	}
}
