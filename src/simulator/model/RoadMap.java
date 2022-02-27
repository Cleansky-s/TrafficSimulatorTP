package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class RoadMap {
	
	private List<Junction> junctionList;
	private List<Road> roadList;
	private List<Vehicle> vehicleList;
	private Map<String, Junction> juctionMap;
	private Map<String, Road> roadMap;
	private Map<String, Vehicle> vehicleMap;

	RoadMap() {
		this.junctionList = new ArrayList<Junction>();
		this.roadList = new ArrayList<Road>();
		this.vehicleList = new ArrayList<Vehicle>() ;
		this.juctionMap = new HashMap<String, Junction>();
		this.roadMap = new HashMap<String, Road>();
		this.vehicleMap = new HashMap<String, Vehicle>();

	}

	void addJunction(Junction j) {
		if(!this.juctionMap.containsKey(j.getId())) {
			junctionList.add(j);
			juctionMap.put(j.getId(), j);
		}
	}
	
	void addRoad(Road r) {
		if(!this.roadMap.containsKey(r.getId()) && this.juctionMap.containsKey(r.getDestJunc().getId()) && this.juctionMap.containsKey(r.getSrcJunc().getId())) {
			roadList.add(r);
			roadMap.put(r.getId(), r);
			
			
		}
		else
			throw new IllegalArgumentException("Error while adding road");
	}
	
	void addVehicle(Vehicle v) {
		boolean ok = true;
		for(int i = 0; i < v.getItinerary().size()-1; i++) {
			if(!this.roadMap.containsKey(v.getItinerary().get(i).roadTo(v.getItinerary().get(i+1)).getId())) {
				ok = false;
			}
		}
		if(!this.vehicleMap.containsKey(v.getId()) && ok) {
		vehicleList.add(v);
		vehicleMap.put(v.getId(), v);
		v.moveToNextRoad();
		}
		else
			throw new IllegalArgumentException("Error while adding vehicle");
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
		return Collections.unmodifiableList(new ArrayList<>(junctionList));
	}

	public List<Road> getRoads() {
		return Collections.unmodifiableList(new ArrayList<>(roadList));
	}

	public List<Vehicle> getVehicles() {
		return Collections.unmodifiableList(new ArrayList<>(vehicleList));
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
		JSONArray a = new JSONArray();
		JSONArray b = new JSONArray();
		JSONArray c = new JSONArray();
		JSONObject o = new JSONObject();
		for(int i = 0; i < this.roadList.size(); i++) {
			a.put(this.roadMap.get(roadList.get(i).getId()).report());
		}
			o.put("roads",a);
		for(int i = 0; i < this.junctionList.size(); i++) {
			b.put(this.juctionMap.get(junctionList.get(i).getId()).report());
		}
		o.put("junctions",b);
		for(int i = 0; i < this.vehicleList.size(); i++) {
			c.put(this.vehicleMap.get(vehicleList.get(i).getId()).report());
		}
		o.put("vehicles",c);
		return o;
	}
}
