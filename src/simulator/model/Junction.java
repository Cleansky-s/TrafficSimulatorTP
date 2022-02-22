package simulator.model;

import java.util.*;

import org.json.JSONObject;

public class Junction extends SimulatedObject{
	private List<Road> listRoadEnter;
	private Map<Junction,Road> MapRoadOut; //Key = Junction, Value = Road
	private List<List<Vehicle>> listCola;
	private int indexGreenLight;
	private int lastLightChange;
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
			this.MapRoadOut = new HashMap<Junction,Road>();
			this.listRoadEnter = new ArrayList<Road>();
			this.listCola = new ArrayList<List<Vehicle>>();
		}
		else {
			throw new IllegalArgumentException("Argument can`t be null / Position can`t be negative");
		}
	}
	
	void addIncommingRoad(Road r) {
		List<Vehicle> cola = new LinkedList<Vehicle>();
		//cola.addAll(r.getVehicle());
		this.listRoadEnter.add(r);
		this.listCola.add(cola);
	}
	
	void addOutGoingRoad(Road r) {
		if(MapRoadOut.get(r.getDestJunc())!=null){
			throw new IllegalArgumentException("There is other road connect to r");
		}else if(!this.equals(r.getSrcJunc())){
			throw new IllegalArgumentException("This is not src junction for road");
		}
		MapRoadOut.put(r.getDestJunc(),r);
	}
	
	void enter(Vehicle v) {
		  listRoadEnter.get(listRoadEnter.size()-1).enter(v);
	}
	
	void exit(Vehicle v) {
		  listRoadEnter.get(listRoadEnter.size()-1).exit(v);
	}
	
	
	Road roadTo(Junction j) {

		return MapRoadOut.get(j);
	}

	
	@Override
	void advance(int time) {
		for(int i = 0;i<listRoadEnter.size();i++){
			for(int j = 0;j<extractStrategy.dequeue(listCola.get(i)).size();j++)
			extractStrategy.dequeue(listCola.get(i)).get(j).moveToNextRoad();
		}
	}

	@Override
	public JSONObject report() {
		JSONObject o = new JSONObject();
		o.put("id", this._id);
		if(this.indexGreenLight == -1) {
			o.put("none", "");
		}
		else {
			o.put("green", this.listRoadEnter.get(this.indexGreenLight).getId());
		}
		for(int i = 0; i < this.listRoadEnter.size(); i++) {
			JSONObject r = new JSONObject();
			r.put("road", this.listRoadEnter.get(i).getId());
			for(int j = 0; j < this.listRoadEnter.get(i).getVehicle().size(); j++) {
				r.accumulate("vehicles", this.listRoadEnter.get(i).getVehicle().get(j).getId());
			}
			o.accumulate("queues", r);
		}
		return o;
	}



	
}
