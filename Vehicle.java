package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	private int index;

	Vehicle(String id, int maxSpeed, int contClass,List<Junction> itinerary) {
		super(id);
		if(maxSpeed > 0 && (contClass >= 0 || contClass <= 10) && itinerary.size() >= 2) {
			this.maxSpeed = maxSpeed;
			this.contClass = contClass;
			this.itinerary = Collections.unmodifiableList(new ArrayList<>(itinerary));;
			this.local = 0;
			this.state = VehicleStatus.PENDING;
			this.distancia_total_recorrida = 0;
			this.totalPollution = 0;
			this.road = null;
		}
		else throw new IllegalArgumentException("Vehicle argument error");
			
	}
	
	
	void setSpeed(int s) {
		if(s < 0) {
			throw new IllegalArgumentException("Speed cant be negative :" + s + "s");
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
			//(a)
			int i1 = this.local + this.nowSpeed;
			int i2 = this.road.getLength();
			int aux = this.local;
			if(i1 < i2) {
				this.local = i1;
			}
			else
				this.local = i2;
			// (b)
			int c = this.contClass * (this.local - aux);
			this.totalPollution += c;
			this.road.addContam(c);
			//(c)
			if(this.local >= this.road.getLength()) {
				this.road.getDestJunc().enter(this);
				this.distancia_total_recorrida += this.local;
				this.state = VehicleStatus.WAITING;
			}
			
		}
		
	}
	
	void moveToNextRoad() {
		if(state!=VehicleStatus.PENDING && state !=VehicleStatus.WAITING) {
			throw new IllegalArgumentException("VehicleStatus must be Waiting or Pending for move road:" + this._id);
		}
		
		if(state == VehicleStatus.PENDING) {
			this.road = this.itinerary.get(0).roadTo(this.itinerary.get(1));
			this.road.enter(this);
			index = 1;
			this.state = VehicleStatus.TRAVELING;
			this.local = 0;
		}
		else if(state == VehicleStatus.WAITING&&index<itinerary.size()-1){
			this.road.exit(this);
			this.road = this.itinerary.get(index).roadTo(this.itinerary.get(index+1));
			index++;
			state = VehicleStatus.TRAVELING;
			this.local = 0;
		
		}
		else if(index >= itinerary.size()-1){
			state = VehicleStatus.ARRIVED;
			this.road.exit(this);
			this.road = null;
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
		o.put("status", this.state.toString());
		if(this.road != null)
			o.put("road", this.road.getId());
		if(this.local != 0)
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
