package com.lokesh.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class ThreadPooling {
	static Logger logger = Logger.getLogger(ThreadPooling.class.getName());

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Integer> timetaken = new ArrayList<>();
		logger.info("Enter the number of customers : ");
		int customers = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < customers; i++) {
			logger.info("Enter the time taken by customer-" + (i + 1) + "(in seconds) : ");
			timetaken.add(Integer.parseInt(scanner.nextLine()));
		}

		ExecutorService executor = Executors.newFixedThreadPool(3);
		for (Integer temp : timetaken) {
			BankCounter counter = new BankCounter(temp * 1000);
			executor.submit(counter);
		}
		executor.shutdown();
		logger.info("In the last line of main method");

	}
}
