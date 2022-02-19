package simulator.model;

import java.util.List;
import java.util.Map;

public class RoadMap {
	
	

	private List<Junction> junctionList;
	private List<Road> roadList;
	private List<Vehicle> vehicleList;
	private Map<String, Road> juctionMap;
	private Map<String, Road> roadMap;
	private Map<String, Vehicle> vehicleMap;
	
	RoadMap(List<Junction> junctionList, List<Road> roadList, List<Vehicle> vehicleList,
			Map<String, Road> juctionMap, Map<String, Road> roadMap, Map<String, Vehicle> vehicleMap) {
		super();
		this.junctionList = junctionList;
		this.roadList = roadList;
		this.vehicleList = vehicleList;
		this.juctionMap = juctionMap;
		this.roadMap = roadMap;
		this.vehicleMap = vehicleMap;
	}
	
	void addJunction(Junction j) {
		
	}
	
	void addRoad(Road r) {
		
	}
	
	void addVehicle(Vehicle v) {
		
	}
	
	public Junction getJunction(String id) {
		return null;
		
	}
	
	public Road getRoad(String id) {
		return null;
		
	}
	
	public Vehicle getVehicle(String id) {
		return null;
		
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
