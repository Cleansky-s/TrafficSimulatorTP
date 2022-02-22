package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

public abstract class Road extends SimulatedObject{
	
	
	private Junction srcJunc,destJunc; //dest and origin junction
	private int length;
	private int maxSpeed;
	private int contLimit;
	private int limitSpeed;
	private Weather weather;
	private int totalPoll;
	private List<Vehicle> vehicle;

	Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed,
			int contLimit, int length, Weather weather) {
		super(id);
		
		if(maxSpeed > 0 && contLimit >= 0 && length > 0 && srcJunc != null && destJunc != null && weather != null) {
			this.srcJunc = srcJunc;
			this.destJunc = destJunc;
			this.maxSpeed = maxSpeed;
			this.contLimit = contLimit;
			this.length = length;
			this.weather = weather;
			this.limitSpeed = maxSpeed;
			srcJunc.addOutGoingRoad(this);
			destJunc.addIncommingRoad(this);
			vehicle = new ArrayList<>();
		}else throw new IllegalArgumentException("Road's argument ERROR");
	}

	@Override
	void advance(int time) {
		this.reduceTotalContamination();
		this.updateSpeedLimit();
		for(int i = 0; i < vehicle.size(); i++) {
			vehicle.get(i).setSpeed(calculateVehicleSpeed(this.vehicle.get(i)));
			vehicle.get(i).advance(time);
		}
		for(int i = 0;i < vehicle.size();i++){
			int pos = i;
			while((pos > 0)&&(this.vehicle.get(pos-1).getLocal() < this.vehicle.get(pos).getLocal())) {
				Vehicle ve = this.vehicle.get(pos);
				this.vehicle.add(pos-1,ve);
				this.vehicle.remove(pos+1);
				pos--;

			}

		}
		//ORDDENAR LA LISTA DE VEHICULOS POR SU LOCALIZACION
	}

	@Override
	public JSONObject report() {
		JSONObject o = new JSONObject();
		o.put("id", this._id);
		o.put("speedlimit", this.limitSpeed);
		o.put("weather", this.weather);
		o.put("co2", this.contLimit);
		o.put("vehicles", this.vehicle);
		return o;
	}

	
	public void addContam(int contaminacion) {
		this.totalPoll += contaminacion;
		
	}

	void enter(Vehicle v) {
		if(v.getNowSpeed() == 0 && v.getLocal() == 0) {
			this.vehicle.add(v);
		}
		else throw new IllegalArgumentException("Vehicle Speed can`t be 0 / Vehicle Location can`t be 0");
		
	}
	
	void exit(Vehicle v) {
		this.vehicle.remove(v);
	}
	
	void setWeather(Weather w) {
		if(w != null) {
			this.weather = w;
		}
		else throw new IllegalArgumentException("Weather can`t be NULL");
	}
	
	public Junction getSrcJunc() {
		return srcJunc;
	}

	public Junction getDestJunc() {
		return destJunc;
	}

	public int getLength() {
		return length;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public int getContLimit() {
		return contLimit;
	}
	
	public int getLimitSpeed() {
		return this.limitSpeed;
	}

	public Weather getWeather() {
		return weather;
	}

	public int getTotalPoll() {
		return totalPoll;
	}

	public List<Vehicle> getVehicle() {
		return Collections.unmodifiableList(this.vehicle);
	}

	public void setSpeedLimit(int v) {
		this.limitSpeed = v;
	}
	
	abstract void reduceTotalContamination();
	
	abstract void updateSpeedLimit();
	
	abstract int calculateVehicleSpeed(Vehicle v);
	
	
	
}
