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
	private Random x = new Random();

	Vehicle(String id, int maxSpeed, int contClass,List<Junction> itinerary) {
		super(id);
		this.maxSpeed = maxSpeed;
		this.contClass = contClass;
		this.itinerary = itinerary;
		this.local = 0;
		this.distancia_total_recorrida = 0;
		// TODO Auto-generated constructor stub
	}
	
	
	void setSpeed(int s) {
		int m = x.nextInt(this.maxSpeed-s)+s;
		if(m < 0) {
			throw new IllegalArgumentException("Speed cant be negative :" + m + "s"); 
		}else {
			this.nowSpeed = m;
		}
	}
	void setContaminationClass(int c) {
		if(c>10||c<0) {
			throw new IllegalArgumentException("Grade must between 0-10: " + c); 
		}else {
		this.contClass = c;
		}
	}

	@Override
	void advance(int time) {
		if(state != VehicleStatus.TRAVELING) {
			
			int m = x.nextInt(road.getlength() - (this.local + this.nowSpeed) ) + this.local + this.nowSpeed;
			int contaminacion = (m-local) * this.contClass;
			this.local = m;
			this.totalPollution += contaminacion;
			road.addCon(contaminacion);
			if(this.local >= road.getlength()) {
				//Llama al junction
			}
		}
		
	}
	
	void moveToNextRoad() {
		
	}

	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}

}
