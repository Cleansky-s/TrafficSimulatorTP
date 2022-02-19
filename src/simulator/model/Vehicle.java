package simulator.model;

import java.util.List;
import java.util.Random;

import org.json.JSONObject;

public class Vehicle extends SimulatedObject{

	private List<Junction> itinerary;
	private int maxSpeed;
	private int nowSpeed;
	private VehicleStatus state;
	private Road road;
	private int local;
	private int contClass;
	private int totalPollution;
	private int distancia_total_recorrida;
	private int index = 0;
	private Random x = new Random();

	Vehicle(String id, int maxSpeed, int contClass,List<Junction> itinerary) {
		super(id);
		this.maxSpeed = maxSpeed;
		this.contClass = contClass;
		this.itinerary = itinerary;
		this.local = 0;
		this.distancia_total_recorrida = 0;
	}
	
	
	void setSpeed(int s) {
		int m = x.nextInt(this.maxSpeed-s)+s;
		if(s < 0) {
			throw new IllegalArgumentException("Speed cant be negative :" + m + "s"); 
		}else {
			this.nowSpeed = m;
		}
	}
	void setContaminationClass(int c) {
		if(c>10||c<0) {
			throw new IllegalArgumentException("Grade must between 0 - 10: " + c); 
		}else {
		this.contClass = c;
		}
	}

	@Override
	void advance(int time) {
		if(state != VehicleStatus.TRAVELING) {
			
			int m = x.nextInt(road.getLength() - (this.local + this.nowSpeed) ) + this.local + this.nowSpeed;
			int pollut = (m-local) * this.contClass;
			this.local = m;
			this.totalPollution += pollut;
			road.addContam(pollut);
			if(this.local >= road.getLength()) {
				//Llama al junction
				this.state = VehicleStatus.WAITING;
				// ESTABLECER nowSpeed = 0;
			}
		}
		
	}
	
	void moveToNextRoad() {
		if(state!=VehicleStatus.PENDING&&state !=VehicleStatus.WAITING) {
			throw new IllegalArgumentException("State must be Waiting or Pending :" + this._id); 
		}
		
		if(state == VehicleStatus.PENDING) {
			this.itinerary.get(index).enter(this);
			state = VehicleStatus.TRAVELING;
			this.local = 0;
			index++;
		}
		else if(state == VehicleStatus.WAITING&&index<itinerary.size()){
			this.itinerary.get(index-1).exit(this);
			this.itinerary.get(index).enter(this);
			state = VehicleStatus.TRAVELING;
			this.local = 0;
			index++;
		}
		else if(index >= itinerary.size()){
			state = VehicleStatus.ARRIVED;
		}
		
		
	}

	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}


	public VehicleStatus getState() {
		return state;
		
	}
	
	public List<Junction> getItinerary() {
		return itinerary;
	}


	public int getMaxSpeed() {
		return maxSpeed;
	}


	public int getNowSpeed() {
		return nowSpeed;
	}


	public Road getRoad() {
		return road;
	}


	public int getLocal() {
		return local;
	}


	public int getContClass() {
		return contClass;
	}


	public int getTotalPollution() {
		return totalPollution;
	}


}
