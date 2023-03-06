package main;

public class Train {

	public static final int TOTAL_PASSENGERS = 10;
	public Rider[] passengers;
	public int passengerIndex;
	public String currentStation;
	public int direction;
	
	public Train(String currentStation, int direction) {
		this.currentStation = currentStation;
		this.direction = direction;
		this.passengers = new Rider[TOTAL_PASSENGERS];
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
	
	public String currentPassengers() {
		String psgInfo = "";
		for (int i=0; i<passengers.length; i++) {
			if (passengers[i] != null) {
				psgInfo = psgInfo + passengers[i].riderID + ", " + passengers[i].destinationStation + "\n";
			}
		}
		return psgInfo;
	}
	
	public boolean addPassenger(Rider r) {
		
		if (r.direction == this.direction && r.startingStation.equals(this.currentStation) && this.hasSpaceForPassengers()) {
			for (int i=0; i<TOTAL_PASSENGERS; i++) {
				if (this.passengers[i] == null) {
					this.passengers[i] = r;
					i = TOTAL_PASSENGERS;
				}
			}
			this.passengerIndex++;
			return true;
		} 
		return false;
		
	}
	
	public boolean hasSpaceForPassengers() {
		return this.passengerIndex < TOTAL_PASSENGERS;
	}
	
	public String disembarkPassengers() {
		String psgInfo = "";
		for (int i=0; i<TOTAL_PASSENGERS; i++) {
			if (this.passengers[i] != null && this.passengers[i].getDestination().equals(this.currentStation)) {
				psgInfo = psgInfo + passengers[i].riderID + ", " + currentStation + "\n";
				this.passengers[i] = null;
				this.passengerIndex--;
			} 
		}
		return psgInfo;
	}
	
	public void updateStation(String s) {
		this.currentStation = s;
	}
	
	public String getStation() {
		return this.currentStation;
	}
	
	@Override
	public String toString() {
		String psgInfo = "There are " + this.passengerIndex + " passengers, maximum "+TOTAL_PASSENGERS + "\n";
		String dirInfo = "It's direction is to the ";
		if (this.direction == 0) {
			dirInfo = dirInfo + "North" + "\n";
		} else {
			dirInfo = dirInfo + "South" + "\n";
		}
		String currStationInfo = "Current station: "+this.currentStation + "\n";
		
		return psgInfo + dirInfo + currStationInfo;
	}
}
