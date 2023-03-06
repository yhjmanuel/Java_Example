package main;

public class Railway {

	public DoubleLinkedList<Station> railway;
	public String[] stationNames;
	public static final int NUM_STATIONS = 18;
	
	public Railway() {
		this.stationNames = new String[NUM_STATIONS];
		this.railway = new DoubleLinkedList<Station>();
	}
	
	public void addStation(Station s) {
		for (int i=0; i<NUM_STATIONS; i++) {
			if (this.stationNames[i] == null) {
				this.stationNames[i] = s.name;
				i = NUM_STATIONS;
			}
		}
		railway.insert(s);
	}
	
	public void addRider(Rider r) throws Exception {
		this.setRiderDirection(r);
		Node<Station> curr = this.railway.head;
		while (curr != null) {
			if (curr.data.name.equals(r.getStarting())) {
				curr.data.addRider(r);
			}
			curr = curr.next;
		}
	}
	
	public void addTrain(Train t) throws Exception {
		Node<Station> curr = this.railway.head;
		String disembarkInfo = "";
		while (curr != null) {
			if (curr.data.name.equals(t.currentStation)) {
				disembarkInfo += curr.data.addTrain(t);
			}
			curr = curr.next;
		}
	}
	
	public void setRiderDirection(Rider r) {
		int start = -1;
		int end = -1;
		for (int i=0; i<this.stationNames.length; i++) {
			if (this.stationNames[i].equals(r.getStarting())) {
				start = i;
			} else if (this.stationNames[i].equals(r.getDestination())) {
				end = i;
			}
		}
		if (start < end) {
			if (r.goingNorth()) {
				r.swapDirection();
			}
		} else {
			if (!r.goingNorth()) {
				r.swapDirection();
			}
		}
		
	}
	
	public String simulate() throws Exception {
		String log = "";
		Node<Station> curr = this.railway.head;
		while (curr != null) {
			//for stations that are not the first or the last
			log = log + curr.data + "\n\n";
			String tempSouthLog = "";
			String tempNorthLog = "";
			if (curr.data.name.equals("Alewife")) {
				//cannot go to further north
				if (curr.data.southBoundTrains.size() > 0) {
					tempSouthLog += curr.next.data.name + " disembarking passengers: \n";
					curr.data.southBoundTrains.front().currentStation = curr.data.name;
					tempSouthLog += curr.next.data.addTrain(curr.data.southBoardTrain());
					tempSouthLog += "Direction: Southbound\n";
					tempSouthLog += "Passengers: \n";
					tempSouthLog += curr.next.data.southBoundTrains.front().currentPassengers();
					tempSouthLog += "Current station: "+ curr.next.data.name + "\n\n";
				}
				if (curr.data.northBoundTrains.size() > 0) {
					curr.data.northBoundTrains.front().swapDirection();
					curr.data.addTrain(curr.data.northBoardTrain());
				}
			} else if (curr.data.name.equals("Braintree")){
				//cannot go to further south
				
				if (curr.data.northBoundTrains.size() > 0) {
					tempNorthLog += curr.prev.data.name + " disembarking passengers: \n";
					curr.data.northBoundTrains.front().currentStation = curr.data.name;
					tempNorthLog += curr.prev.data.addTrain(curr.data.northBoardTrain());
					tempNorthLog += "Direction: Northbound\n";
					tempNorthLog += "Passengers: \n";
					tempNorthLog += curr.prev.data.northBoundTrains.front().currentPassengers();
					tempNorthLog += "Current station: "+ curr.prev.data.name + "\n\n";
				}
				if (curr.data.southBoundTrains.size() > 0) {
					curr.data.southBoundTrains.front().swapDirection();
					curr.data.addTrain(curr.data.southBoardTrain());
				}
			} else {
				
				if (curr.data.southBoundTrains.size() > 0) {
					tempSouthLog += curr.next.data.name + " disembarking passengers: \n";
					curr.data.southBoundTrains.front().currentStation = curr.data.name;
					tempSouthLog += curr.next.data.addTrain(curr.data.southBoardTrain());
					tempSouthLog += "Direction: Southbound\n";
					tempSouthLog += "Passengers: \n";
					tempSouthLog += curr.next.data.southBoundTrains.front().currentPassengers();
					tempSouthLog += "Current station: "+ curr.next.data.name + "\n\n";
				}
				if (curr.data.northBoundTrains.size() > 0) {
					tempNorthLog += curr.prev.data.name + " disembarking passengers: \n";
					curr.data.northBoundTrains.front().currentStation = curr.data.name;
					tempNorthLog += curr.prev.data.addTrain(curr.data.northBoardTrain());
					tempNorthLog += "Direction: Northbound\n";
					tempNorthLog += "Passengers: \n";
					tempNorthLog += curr.prev.data.northBoundTrains.front().currentPassengers();
					tempNorthLog += "Current station: "+ curr.prev.data.name + "\n\n";
				}
			}
			
			log = log + tempNorthLog;
			log = log + tempSouthLog;

			curr = curr.next;
		}
		
		return log;
	}
	
	@Override
	public String toString() {
		String rep = "Station list:\n";
		for (int i=0; i<this.stationNames.length; i++) {
			if (this.stationNames[i] != null) {
				rep = rep + stationNames[i] + "->";
			}
		}
		return rep.substring(0, rep.length()-2);
	}
	
}
