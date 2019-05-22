package org.paumard.executors;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PlayingWithCallablesAndFutures {

	public static void main(String[] args) throws ExecutionException, InterruptedException {

		Callable<String> taskString = () -> {
			return "I throw an exception in thread " + Thread.currentThread().getName();
//			throw new IllegalStateException("I throw an exception in thread " + Thread.currentThread().getName());
		};
		Callable<Integer> taskInt = () -> {
			return 1;
//			throw new IllegalStateException("I throw an exception in thread " + Thread.currentThread().getName());
		};


		ExecutorService executor = Executors.newFixedThreadPool(4);

		try {
			for (int i = 0; i < 10; i++) {
				Future<String> future = executor.submit(taskString);
//				Future<Integer> future = executor.submit(taskInt);
				Object string = future.get();
				System.out.println(MessageFormat.format("I get: {0} Object type {1}", string, string instanceof String) );
			}
		} finally {
			executor.shutdown();
		}
	}
}
