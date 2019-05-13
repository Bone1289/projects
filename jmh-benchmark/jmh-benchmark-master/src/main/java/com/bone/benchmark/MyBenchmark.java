package com.bone.benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1, jvmArgs = { "-Xms2G", "-Xmx2G" })
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@State(Scope.Benchmark)
public class MyBenchmark {

	public void testMethod() {
		int a = 1;
		int b = 2;
		int sum = 0;
		for (int i = 0; i < 1000; i++) {
			sum += (i * (a + b));
		}
	}

	public static void main(String[] args) throws RunnerException {

		Options opt = new OptionsBuilder().include(MyBenchmark.class.getSimpleName()).forks(1).build();

		new Runner(opt).run();
	}

//	@Benchmark @BenchmarkMode(Mode.Throughput) @OutputTimeUnit(TimeUnit.MINUTES)
//    public void testMethod(MyState state) {
//        state.sum = state.a + state.b;
//    }
}
