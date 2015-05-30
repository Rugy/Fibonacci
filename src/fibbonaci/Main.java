package fibbonaci;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

	private static final int STARTINGNUMBER = 1;
	private static final int MINIMALSEQUENCELENGTH = 5;
	private static List<Integer> fibbonaciSequenceRecursive = new LinkedList<>();

	public static void main(String[] args) {

		int sequenceLength = MINIMALSEQUENCELENGTH;
		if (validNumber(args)) {
			sequenceLength = Integer.valueOf(args[0]);
		}
		List<Integer> fibbonaciSequenceIterative = new LinkedList<>();

		// recursive takes longer, initiate first in separate Thread
		Thread recursiveCalculation = new Thread(new Runnable() {
			public void run() {
				fibbonaciSequenceRecursive = calculateFibbonaciRecursive(Integer
						.valueOf(args[0]));
			}
		});
		recursiveCalculation.start();

		fibbonaciSequenceIterative = calculateFibbonaciIterative(sequenceLength);
		System.out
				.println("Fibonacci sequence with iterative calculation up to "
						+ sequenceLength + ": "
						+ Arrays.toString(fibbonaciSequenceIterative.toArray()));

		while (recursiveCalculation.isAlive()) {
			try {
				recursiveCalculation.join();
			} catch (InterruptedException ignore) {
			}
		}
		System.out
				.println("Fibonacci sequence with recursive calculation up to "
						+ sequenceLength + ": "
						+ Arrays.toString(fibbonaciSequenceRecursive.toArray()));

	}

	private static boolean validNumber(String[] args) {
		return Integer.valueOf(args[0]) != null;
	}

	private static List<Integer> calculateFibbonaciRecursive(int sequenceLength) {
		List<Integer> currentSequence = new LinkedList<>();
		if (sequenceLength == 2) {
			currentSequence.add(STARTINGNUMBER);
			currentSequence.add(STARTINGNUMBER);
		} else if (sequenceLength > 2) {
			currentSequence
					.addAll(calculateFibbonaciRecursive(sequenceLength - 1));
			currentSequence.add(currentSequence.get(sequenceLength - 2)
					+ currentSequence.get(sequenceLength - 3));
		}
		return currentSequence;
	}

	private static List<Integer> calculateFibbonaciIterative(int sequenceLength) {
		List<Integer> currentSequence = new LinkedList<>();
		int firstNumber = STARTINGNUMBER;
		int secondNumber = STARTINGNUMBER;
		currentSequence.add(firstNumber);
		currentSequence.add(secondNumber);
		sequenceLength -= 2;
		while (sequenceLength != 0) {
			currentSequence.add(firstNumber + secondNumber);
			secondNumber = firstNumber + secondNumber;
			firstNumber = secondNumber - firstNumber;
			sequenceLength--;
		}
		return currentSequence;
	}

}
