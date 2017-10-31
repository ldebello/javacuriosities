package ar.com.javacuriosities.threads.spurios_wake_up;


import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.util.concurrent.TimeUnit;

public class SpuriosWakeUp {

	public static void main(String[] args) {
		try {
			SpuriosWakeUpHandler spuriosWakeUpHandler = new SpuriosWakeUpHandler();
			Signal.handle(new Signal("TRAP"), spuriosWakeUpHandler);

			Meeting meeting = new Meeting();

			Employee worker01 = new Employee("Homero Simpson", meeting);
			Employee worker02 = new Employee("Pablo Marmol", meeting);
			Employee worker03 = new Employee("Don Quijote", meeting);
			Boss boss = new Boss("Cosme Fulanito", meeting);

			// Start employees
			worker01.start();
			worker02.start();
			worker03.start();

			Thread.sleep(TimeUnit.MINUTES.toMillis(1));

			// Start boss
			boss.start();
		} catch (InterruptedException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}

	private static final class Meeting {

		public Meeting() {
		}

		public synchronized void waitBoss(String name) {
			try {
				System.out.println(name + " is waiting");
				wait();
				System.out.println(name + ": Good Morning boss");
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		public synchronized void startMeeting(String name) {
			System.out.println(name + " -: Good Morning slaves :)");
			notifyAll();
		}
	}

	private static final class Employee extends Thread {

		private Meeting meeting;

		public Employee(String name, Meeting meeting) {
			super(name);
			this.meeting = meeting;
		}

		@Override
		public void run() {
			System.out.println(getName() + " arrives at the office.");
			meeting.waitBoss(getName());
		}
	}

	private static final class Boss extends Thread {

		private Meeting meeting;

		public Boss(String name, Meeting meeting) {
			super(name);
			this.meeting = meeting;
		}

		@Override
		public void run() {
			System.out.println(getName() + " arrives at the office.");
			meeting.startMeeting(getName());
		}
	}

	private static class SpuriosWakeUpHandler implements SignalHandler {
		public SpuriosWakeUpHandler() {
		}

		@Override
		public void handle(Signal signal) {
			System.out.println("Trying to generate a spurious wake up");
		}
	}
}