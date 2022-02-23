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
			this.listCola = new ArrayList<List<Vehicle>>();
		}
		else {
			throw new IllegalArgumentException("Argument can`t be null / Position can`t be negative");
		}
	}
	
	void addIncommingRoad(Road r) {
		if(listRoadEnter == null){
		this.listRoadEnter = new ArrayList<Road>();}
		this.listRoadEnter.add(r);
		listCola.add(r.getVehicle());
	}
	
	void addOutGoingRoad(Road r) {
		if(MapRoadOut.get(r.getDestJunc().getId())!=null){
			throw new IllegalArgumentException("There is other road connect to road");
		}else if(!this.equals(r.getSrcJunc())){
			throw new IllegalArgumentException("This is not src junction for road");
		}
		MapRoadOut.put(r.getDestJunc(),r);
		listCola.add(r.getVehicle());

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
		if(listCola.size()!=0){
			if(listCola.get(0).size() > 0){
			extractStrategy.dequeue(listCola.get(0));
			}

		}
		else if(listCola.size()>1){
			for(int i = 0;i<listCola.size();i++){
				if(listCola.get(0).size() > 0){
					extractStrategy.dequeue(listCola.get(i));
				}
			}
		}

		indexGreenLight = lightSwitchStrategy.chooseNextGreen(this.listRoadEnter,listCola,indexGreenLight,lastLightChange,time);
	}

	@Override
	public JSONObject report() {
		JSONObject o = new JSONObject();
		o.put("id", this._id);
		if(this.indexGreenLight == -1) {
			o.put("green", "none");
		}
		else {
			o.put("green", this.listRoadEnter.get(this.indexGreenLight).getId());

		for(int i = 0; i < this.listRoadEnter.size(); i++) {
			JSONObject r = new JSONObject();
			r.append("road", this.listRoadEnter.get(i).getId());
			for(int j = 0; j < this.listRoadEnter.get(i).getVehicle().size(); j++) {
				if(this.listRoadEnter.get(i).getVehicle().get(j).getState()==VehicleStatus.WAITING){
				r.append("vehicles", this.listRoadEnter.get(i).getVehicle().get(j).getId());}
			}
			o.append("queues", r);
		}
		}
		return o;
	}



	
}
