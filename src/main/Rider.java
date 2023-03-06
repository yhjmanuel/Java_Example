package main;

public class Rider {
	public String riderID;
	public String startingStation;
	public String destinationStation;
	public int direction;
	

	public Rider(String riderID, String startingStation, String destinationStation) {
		this.riderID = riderID;
		this.startingStation = startingStation;
		this.destinationStation = destinationStation;
		this.direction = 1;
	}
	
	public String getStarting() {
		return this.startingStation;
	}
	
	public String getDestination() {
		return this.destinationStation;
	}
	
	public String getRiderID() {
		return this.riderID;
	}
	
	public boolean goingNorth() {	
		return this.direction == 0;
	}
	
	public void swapDirection() {
		if (this.direction == 1) {
			this.direction = 0;
		} else {
			this.direction = 1;
		}
	}
	
	@Override
	public String toString() {
		String idInfo = "The rider's id: " + this.riderID + "\n";
		String stationInfo = "Starting station: " + this.startingStation + "Destination: " + this.destinationStation + "\n";
		String dir = "Direction: ";
		if (this.direction == 0) {
			dir += "North";
		} else {
			dir += "South";
		}
		return idInfo + stationInfo + dir;
	}
	
	@Override
	public boolean equals(Object s) {
		if (s instanceof Rider) {
			return ((Rider) s).riderID == this.riderID;
		}
		return false;
	}
}
