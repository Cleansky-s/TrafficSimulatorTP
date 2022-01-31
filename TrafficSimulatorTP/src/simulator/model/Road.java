package simulator.model;

import java.util.List;

import org.json.JSONObject;

public class Road extends SimulatedObject{
	
	private Junction srcJunc,destJunc;
	private int length;
	private int maxSpeed;
	private int contLimit;
	private int pollution;
	private Weather condition;
	private int totalPoll;
	private List<Vehicle> vehicle;

	Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed,
			int contLimit, int length, Weather weather) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	void advance(int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getlength() {
		// TODO Auto-generated method stub
		return length;
	}

	public void addCon(int contaminacion) {
		this.totalPoll += contaminacion;
		
	}

}
