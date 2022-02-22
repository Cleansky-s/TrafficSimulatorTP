package simulator.model;

import java.util.ArrayList;
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

	public TrafficSimulator() {
		this.roadMap = new RoadMap();
		eventList = new ArrayList<Event>();
		this.time = 0;
	}

	public void addEvent(Event e) {
			eventList.add(e);
	}
	
	public void advance() {
		
	}

	public void reset() {
		this.time = 0;
		this.roadMap.reset();
		this.eventList.clear();
	}

	public JSONObject report() {
		JSONObject o = new JSONObject();
		o.put("time", this.time);
		o.put("state", this.roadMap.report());
		return o;
	}
}
