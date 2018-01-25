package com.lokesh.multithreading;

import java.util.logging.Logger;

public class BankCounter implements Runnable {
	volatile static int customernumber = 0;
	int sleeptime;
	Logger logger = Logger.getLogger(BankCounter.class.getName());

	public BankCounter(int sleeptime) {
		this.sleeptime = sleeptime;
	}

	public synchronized int getCustomerNumber() throws InterruptedException {
		customernumber++;
		Thread.sleep((long) Math.random() * 216);
		return customernumber;
	}

	public String getCounterName(String threadname) {
		switch (threadname) {
		case "pool-1-thread-1":
			return "Counter-1";
		case "pool-1-thread-2":
			return "Counter-2";
		case "pool-1-thread-3":
			return "Counter-3";
		}
		return null;
	}

	@Override
	public void run() {
		try {
			logger.info("Customer-" + getCustomerNumber() + " is in " + getCounterName(Thread.currentThread().getName())
					+ "\nSleeping for : " + sleeptime);
			Thread.sleep(sleeptime);
		} catch (Exception e) {
			logger.warning("Problem while executing the thread");
		}

	}
}
