package simulator.model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Junction extends SimulatedObject{
	private List<Road> listRoadEnter;
	private Map<Junction,Road> MapRoadOut; //Key = Junction, Value = Road
	private List<List<Vehicle>> listCola;
	private int indexGreenLight;
	private int lastSwitchLightTime;
	private LightSwitchingStrategy lightSwitchStrategy;
	private DequeuingStrategy extractStrategy;
	private int xCo,yCo;
	
	Junction(String id, LightSwitchingStrategy lightSwitchStrategy, DequeuingStrategy extractStrategy, int xCoor, int yCoor) {
		super(id);
		if(lightSwitchStrategy != null &&extractStrategy != null &&xCoor >= 0 &&yCoor >= 0) {
			this.lightSwitchStrategy = lightSwitchStrategy;
			this.extractStrategy = extractStrategy;
			this.xCo = xCoor;
			this.yCo = yCoor;
			this.listRoadEnter = new ArrayList<Road>();
			this.MapRoadOut = new HashMap<Junction,Road>();
			this.listCola = new ArrayList<List<Vehicle>>();
			this.indexGreenLight = -1;
			this.lastSwitchLightTime = 0;
			
		}
		else {
			throw new IllegalArgumentException("Argument can`t be null / Position can`t be negative");
		}
	}
	
	void addIncommingRoad(Road r) {
		if(r.getDestJunc() == this) {
			this.listRoadEnter.add(r);
			this.listCola.add(new ArrayList<Vehicle>());
		}
		else
			throw new IllegalArgumentException("Error: road's DestJunction is not the same");
	}
	
	void addOutGoingRoad(Road r) {
		if(MapRoadOut.get(r.getDestJunc())!=null){
			throw new IllegalArgumentException("There is other road connect to road");
		}else if(!this.equals(r.getSrcJunc())){
			throw new IllegalArgumentException("This is not src junction for road");
		}
		else {
			MapRoadOut.put(r.getDestJunc(),r);
		}

	}
	
	void enter(Vehicle v) {
		listCola.get(this.listRoadEnter.indexOf(v.getRoad())).add(v);
	}
	Road roadTo(Junction j) {
		return MapRoadOut.get(j);
	}

	
	@Override
	void advance(int time) {
		List<Vehicle> listOut;
		if(indexGreenLight > -1&&indexGreenLight < listCola.size()) {
			if(listCola.get(indexGreenLight).size() > 0){
				listOut = extractStrategy.dequeue(listCola.get(indexGreenLight));
				listCola.get(indexGreenLight).removeAll(listOut);
				for(Vehicle v : listOut) { 
					v.moveToNextRoad();
					if(v.getRoad() != null) {
						v.getRoad().enter(v);
					}
				}
				
			}
		}
		int nextGreen = lightSwitchStrategy.chooseNextGreen(this.listRoadEnter,listCola,indexGreenLight,lastSwitchLightTime,time);
		if(this.indexGreenLight != nextGreen) {
			indexGreenLight = nextGreen;
			this.lastSwitchLightTime = time;
		}
	}

	@Override
	public JSONObject report() {
		JSONObject o = new JSONObject();
		
		JSONArray r = new JSONArray();
		
		o.put("id", this._id);
		if(this.indexGreenLight == -1) {
			o.put("green", "none");
			o.put("queues", r);
		}else if(this.listRoadEnter.size() == 0){
			o.put("green", "none");
			o.put("queues", r);
		}
		else {
			o.put("green", this.listRoadEnter.get(this.indexGreenLight).getId());
		}
		for(int i = 0; i < this.listRoadEnter.size(); i++) {
			JSONObject om = new JSONObject();
			JSONArray v = new JSONArray();
			om.put("road", this.listRoadEnter.get(i).getId());
			for(int j = 0; j < this.listRoadEnter.get(i).getVehicle().size(); j++) {
				if(this.listRoadEnter.get(i).getVehicle().get(j).getState()==VehicleStatus.WAITING){
					v.put(this.listRoadEnter.get(i).getVehicle().get(j).getId());
				}
			}
			om.put("vehicles", v);
			r.put(om);
			o.put("queues", r);
		}
		
		return o;
	}



	
}
