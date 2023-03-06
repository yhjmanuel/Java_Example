package main;

public class Station {

	public Queue<Rider> northBoundRiders;
	public Queue<Rider> southBoundRiders;
	public Queue<Train> northBoundTrains;
	public Queue<Train> southBoundTrains;
	public static final int MAX_CAPACITY = 20;
	public String name;
	
	public Station(String name) {
		this.name = name;
		this.northBoundRiders = new Queue<Rider>(MAX_CAPACITY);
		this.southBoundRiders = new Queue<Rider>(MAX_CAPACITY);
		this.northBoundTrains = new Queue<Train>(MAX_CAPACITY);
		this.southBoundTrains = new Queue<Train>(MAX_CAPACITY);
	}
	
	public boolean addRider(Rider r) throws Exception { 
		if (r.direction == 0) {
			if (this.northBoundRiders.size() == MAX_CAPACITY) {
				return false;
			}
			Rider rCopy = new Rider(r.riderID, r.startingStation, r.destinationStation);
			rCopy.direction = r.direction;
			this.northBoundRiders.enqueue(r);
			return true;
		} 
		if (this.southBoundRiders.size() == MAX_CAPACITY) {
			return false;
		}
		Rider rCopy = new Rider(r.riderID, r.startingStation, r.destinationStation);
		rCopy.direction = r.direction;
		this.southBoundRiders.enqueue(r);
		return true;
	}
	
	public String addTrain(Train t) throws Exception {
		if (t.direction == 0) {
			this.northBoundTrains.enqueue(t);
			t.currentStation = this.name;
		} else {
			this.southBoundTrains.enqueue(t);
			t.currentStation = this.name;
		}
		return t.disembarkPassengers();
	}
	
	public Train southBoardTrain() {
		if (this.southBoundTrains.size() == 0) {
			return null;
		} 
		Train train = this.southBoundTrains.front();
		this.southBoundTrains.dequeue();
		while (train.hasSpaceForPassengers() && this.southBoundRiders.size() > 0) {
			Rider tempRider = this.southBoundRiders.front();
			train.addPassenger(tempRider);
			this.southBoundRiders.dequeue();
		}
		return train;
	}
	
	public Train northBoardTrain() {
		if (this.northBoundTrains.size() == 0) {
			return null;
		} 
		Train train = this.northBoundTrains.front();
		this.northBoundTrains.dequeue();
		while (train.hasSpaceForPassengers() && this.northBoundRiders.size() > 0) {
			train.addPassenger(this.northBoundRiders.front());
			this.northBoundRiders.dequeue();
		}
		return train;
	}
	
	public void moveTrainNorthToSouth() throws Exception {
		this.northBoundTrains.front().swapDirection();
		this.addTrain(this.northBoundTrains.front());
		this.northBoundTrains.dequeue();
	}
	
	public void moveTrainSouthToNorth() throws Exception {
		this.southBoundTrains.front().swapDirection();
		this.addTrain(this.southBoundTrains.front());
		this.southBoundTrains.dequeue();
	}
	
	@Override
	public String toString() {
		String rep = "Station: " + this.name +"\n";
		rep = rep + this.northBoundTrains.size() + " north-bound trains waiting" + "\n";
		rep = rep + this.southBoundTrains.size() + " south-bound trains waiting" + "\n";
		rep = rep + this.northBoundRiders.size() + " north-bound passengers waiting" + "\n";
		rep = rep + this.southBoundRiders.size() + " south-bound passengers waiting";
		return rep;
	}
	
	public String stationName() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Station) {
			return ((Station)o).name == this.name;
		}
		return false;
	}
}
