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
		this.state = VehicleStatus.PENDING;
		this.distancia_total_recorrida = 0;
	}
	
	
	void setSpeed(int s) {
		if(s < 0) {
			throw new IllegalArgumentException("Speed cant be negative :" + s + "s");
		}
		else if(this.state!=VehicleStatus.TRAVELING){

		}
		else if(s > this.maxSpeed){
			this.nowSpeed = maxSpeed;
		}
		else this.nowSpeed = s;
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
		if(state == VehicleStatus.TRAVELING) {
			int pollut = (local+this.nowSpeed-local) * this.contClass;
			this.local += this.nowSpeed;
			distancia_total_recorrida += nowSpeed;
			int m = this.nowSpeed - (this.local-this.road.getLength());
			this.totalPollution += pollut;
			road.addContam(pollut);
			if(this.local >= road.getLength()) {
				this.getRoad().getDestJunc().enter(this);
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
			if(this.road == null){
			this.road =  this.itinerary.get(0).roadTo(this.itinerary.get(1));
			this.road.enter(this);
			this.local = 0;
			}
			else {
				this.road.exit(this);
				this.road = this.itinerary.get(0).roadTo(this.itinerary.get(1));
				this.road.enter(this);
				this.local = 0;
				this.itinerary.remove(0);
			}
			this.state = VehicleStatus.TRAVELING;
		}
		else if(state == VehicleStatus.WAITING&&index<itinerary.size()){
			this.itinerary.get(index-1).exit(this); //exit wrong
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
		JSONObject o = new JSONObject();
		o.put("id", this._id);
		o.put("speed", this.nowSpeed);
		o.put("distance", this.distancia_total_recorrida);
		o.put("co2", this.totalPollution);
		o.put("class", this.contClass);
		o.put("status", this.state);
		o.put("road", this.road.getId());
		o.put("location", this.local);
		return o;
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
