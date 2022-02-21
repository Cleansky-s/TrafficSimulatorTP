package simulator.model;

import java.util.List;
import java.util.Map;

public class RoadMap {
	
	private List<Junction> junctionList;
	private List<Road> roadList;
	private List<Vehicle> vehicleList;
	private Map<String, Junction> juctionMap;
	private Map<String, Road> roadMap;
	private Map<String, Vehicle> vehicleMap;
	
	RoadMap(List<Junction> junctionList, List<Road> roadList, List<Vehicle> vehicleList,
			Map<String, Junction> juctionMap, Map<String, Road> roadMap, Map<String, Vehicle> vehicleMap) {
		super();
		this.junctionList = junctionList;
		this.roadList = roadList;
		this.vehicleList = vehicleList;
		this.juctionMap = juctionMap;
		this.roadMap = roadMap;
		this.vehicleMap = vehicleMap;
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
}
