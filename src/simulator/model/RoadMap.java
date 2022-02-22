package simulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class RoadMap {
	
	private List<Junction> junctionList;
	private List<Road> roadList;
	private List<Vehicle> vehicleList;
	private Map<String, Junction> juctionMap;
	private Map<String, Road> roadMap;
	private Map<String, Vehicle> vehicleMap;
	
	RoadMap(List<Junction> junctionList, List<Road> roadList, List<Vehicle> vehicleList,
			Map<String, Junction> juctionMap, Map<String, Road> roadMap, Map<String, Vehicle> vehicleMap) {
		this.junctionList = junctionList;
		this.roadList = roadList;
		this.vehicleList = vehicleList;
		this.juctionMap = juctionMap;
		this.roadMap = roadMap;
		this.vehicleMap = vehicleMap;
	}

	public RoadMap() {
		this.junctionList = new ArrayList<Junction>();
		this.roadList = new ArrayList<Road>();
		this.vehicleList = new ArrayList<Vehicle>() ;
		this.juctionMap = new HashMap<String, Junction>();
		this.roadMap = new HashMap<String, Road>();
		this.vehicleMap = new HashMap<String, Vehicle>();

	}

	void addJunction(Junction j) {
		junctionList.add(j);
		juctionMap.put(j.getId(), j);
	}
	
	void addRoad(Road r) {
		roadList.add(r);
		roadMap.put(r.getId(), r);
	}
	
	void addVehicle(Vehicle v) {
		vehicleList.add(v);
		vehicleMap.put(v.getId(), v);
	}
	
	public Junction getJunction(String id) {
		
		return juctionMap.getOrDefault(id, null);
		
	}
	
	public Road getRoad(String id) {
		return roadMap.getOrDefault(id, null);
		
	}
	
	public Vehicle getVehicle(String id) {
		return vehicleMap.getOrDefault(id, null);
		
	}


	public List<Junction> getJunctions() {
		return junctionList;
	}

	public List<Road> getRoads() {
		return roadList;
	}

	public List<Vehicle> getVehicles() {
		return vehicleList;
	}
	
	void reset() {
		this.juctionMap.clear();
		this.junctionList.clear();
		this.roadList.clear();
		this.roadMap.clear();
		this.vehicleList.clear();
		this.vehicleMap.clear();
	}
	
	public JSONObject report() {
		JSONObject o = new JSONObject();
		for(int i = 0; i < this.juctionMap.size(); i++) {
			o.accumulate("junctions", this.juctionMap.get(i).report());
		}
		for(int i = 0; i < this.roadMap.size(); i++) {
			o.accumulate("road", this.roadMap.get(i).report());
		}
		for(int i = 0; i < this.vehicleMap.size(); i++) {
			o.accumulate("vehicles", this.vehicleMap.get(i).report());		
		}
		return o;
		
	}
}
