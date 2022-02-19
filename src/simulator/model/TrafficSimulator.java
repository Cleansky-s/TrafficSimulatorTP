package simulator.model;

import java.util.List;

import org.json.JSONObject;

public class TrafficSimulator{
	private RoadMap roadMap;
	private List<Event> eventList;
	private int time;

	public TrafficSimulator(RoadMap roadMap, List<Event> eventList) {
		this.roadMap = roadMap;
		this.eventList = eventList;
		this.time = 0;
	}
	
	public void addEvent(Event e) {
			eventList.add(e);
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
