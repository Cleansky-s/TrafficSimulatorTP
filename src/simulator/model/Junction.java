package simulator.model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Junction extends SimulatedObject{
	private List<Road> listRoadEnter;
	private Map<Junction,Road> MapRoadOut; //Key = Junction, Value = Road
	private List<List<Vehicle>> listCola;
	private int indexGreenLight = -1;
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
			this.listRoadEnter = new ArrayList<Road>();
			this.MapRoadOut = new HashMap<Junction,Road>();
			this.listCola = new ArrayList<List<Vehicle>>();
			this.listCola.add(new ArrayList<Vehicle>());
		}
		else {
			throw new IllegalArgumentException("Argument can`t be null / Position can`t be negative");
		}
	}
	
	void addIncommingRoad(Road r) {
		if(r.getDestJunc() == this) {
			this.listRoadEnter.add(r);
		}
		else
			throw new IllegalArgumentException("Error: road's DestJunction is not the same");
	}
	
	void addOutGoingRoad(Road r) {
		if(MapRoadOut.get(r.getDestJunc())!=null){
			throw new IllegalArgumentException("There is other road connect to road");
		}else if(!this.equals(r.getSrcJunc())){
			throw new IllegalArgumentException("This is not src junction for road");
		}
		else {
			MapRoadOut.put(r.getDestJunc(),r);
		}

	}
	
	void enter(Vehicle v) {
		listCola.get(this.listRoadEnter.indexOf(v.getRoad())).add(v);
	}
	Road roadTo(Junction j) {
		return MapRoadOut.get(j);
	}

	
	@Override
	void advance(int time) {
		List<List<Vehicle>> listOut = new ArrayList<List<Vehicle>>();
		if(indexGreenLight > -1&&indexGreenLight < listCola.size()) {
		if(listCola.size()!=0){
			if(listCola.get(indexGreenLight).size() > 0){
				listOut.add(extractStrategy.dequeue(listCola.get(0)));
			}

		}
		else if(listCola.size()>1){
			for(int i = 0;i<listCola.size();i++){
				if(listCola.get(indexGreenLight).size() > 0){
					listOut.add(extractStrategy.dequeue(listCola.get(i)));
				}
			}
		}
		for(int i = 0;i<listOut.size();i++){
			for(int j = 0;j<listOut.get(i).size();j++){
				listOut.get(i).get(j).moveToNextRoad();
				listCola.get(indexGreenLight).remove(listOut.get(i).get(j));
				listOut.get(i).remove(j);
			}
		}
		}
		indexGreenLight = lightSwitchStrategy.chooseNextGreen(this.listRoadEnter,listCola,indexGreenLight,lastLightChange,time);
	}

	@Override
	public JSONObject report() {
		JSONObject o = new JSONObject();
		JSONObject om = new JSONObject();
		JSONArray r = new JSONArray();
		JSONArray v = new JSONArray();
		o.put("id", this._id);
		if(this.indexGreenLight == -1) {
			o.put("green", "none");
			o.put("queues", r);
		}else if(this.listRoadEnter.size() == 0){
			o.put("green", "none");
			o.put("queues", r);
		}
		else {
			o.put("green", this.listRoadEnter.get(this.indexGreenLight).getId());
		}
		for(int i = 0; i < this.listRoadEnter.size(); i++) {
			om.put("road", this.listRoadEnter.get(i).getId());
			for(int j = 0; j < this.listRoadEnter.get(i).getVehicle().size(); j++) {
				if(this.listRoadEnter.get(i).getVehicle().get(j).getState()==VehicleStatus.WAITING){
					v.put(this.listRoadEnter.get(i).getVehicle().get(j).getId());
				}
			}
			om.put("vehicles", v);
			r.put(om);
			o.put("queues", r);
		}
		
		return o;
	}



	
}
