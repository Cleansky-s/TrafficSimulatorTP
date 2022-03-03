package simulator.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class TrafficSimulator{
	private RoadMap roadMap;
	private List<Event> eventList;
	private int time;

	public TrafficSimulator() {
		this.roadMap = new RoadMap();
		eventList = new ArrayList<Event>();
		this.time = 0;
	}

	public void addEvent(Event e) {
			eventList.add(e);
	}
	
	public void advance() {
		this.time++;
		for(int i = 0;i<eventList.size();i++){
			if(eventList.get(i).getTime() == this.time){
				eventList.get(i).execute(roadMap);
				eventList.remove(i);
				i--;
			}
		}

		for(int i = 0;i < roadMap.getJunctions().size();i++){
			roadMap.getJunctions().get(i).advance(time);
		}
		for(int i = 0;i < roadMap.getRoads().size();i++){
			roadMap.getRoads().get(i).advance(time);
		}
	}

	public void reset() {
		this.time = 0;
		this.roadMap.reset();
		this.eventList.clear();
	}

	public JSONObject report() {
		JSONObject o = new JSONObject();
		o.put("time",this.time);
		o.put("state", this.roadMap.report());
		return o;
	}
}
