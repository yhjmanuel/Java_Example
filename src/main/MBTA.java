package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MBTA {

	public static final int SOUTHBOUND = 1;
	public static final int NORTHBOUND = 0;
	
	static final int TIMES = 3;
	static Railway r;
	
	public static void main(String[] args) throws Exception {
		r = new Railway();
		initStations("redLine.txt");
		initTrains("trains.txt");
		initRiders("riders.txt");
		runSimulation();
	}
	
	public static void runSimulation() throws Exception {
		System.out.println("BEGINNING RED LINE SIMULATION\n");
		for (int i=0; i<TIMES; i++) {
			int times = i + 1;
			System.out.println(" ------ " + times + " ------ ");
			System.out.println(r.simulate());
		}
	}
	
	public static void initTrains(String trainsFile) throws Exception {
		Scanner s = new Scanner(new File(trainsFile));
		while (s.hasNextLine()) {
			String dest = s.nextLine();
			String dir = s.nextLine();
			if (dir.equals("0")) {
				r.addTrain(new Train(dest, 0));
			} else {
				r.addTrain(new Train(dest, 1));
			}
		}
	}
	
	public static void initRiders(String ridersFile) throws Exception {
		Scanner s = new Scanner(new File(ridersFile));
		while (s.hasNextLine()) {
			String id = s.nextLine();
			String start = s.nextLine();
			String dest = s.nextLine();
			r.addRider(new Rider(id, start, dest));
		}
	}
	
	public static void initStations(String stationsFile) throws Exception {
		
		Scanner s = new Scanner(new File(stationsFile));
		while (s.hasNextLine()) {
			String station = s.nextLine();
			r.addStation(new Station(station));
		}
	}
}
