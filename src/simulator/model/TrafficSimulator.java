package simulator.model;

import java.util.List;

import org.json.JSONObject;

public class TrafficSimulator{

	public TrafficSimulator(RoadMap roadMap, List<Event> eventList) {
		this.roadMap = roadMap;
		this.eventList = eventList;
		this.time = 0;
	}
	private RoadMap roadMap;
	private List<Event> eventList;
	private int time;
	
	public void addEvent(Event e) {
		
	}
	
	public void advance() {
		
	}

	public void reset() {
		
	}

	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}
}
