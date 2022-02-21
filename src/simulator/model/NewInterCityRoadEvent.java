package simulator.model;

public class NewInterCityRoadEvent extends NewRoadEvent{ // HAY QUE CREAR UNA SUPERCLASE: NewRoadEvent
	
	private String srcJunc,destJunc; //dest and origin junction
	private int length;
	private String id;
	private int maxSpeed;
	private int contLimit;
	private Weather weather;
	
	public NewInterCityRoadEvent(int time, String id, String srcJun, String
			destJunc, int length, int co2Limit, int maxSpeed, Weather weather)
			{
		super(time,  id,  srcJun, 
			destJunc,  length,  co2Limit,  maxSpeed,  weather);
			}

	@Override
	void execute(RoadMap map) {
		Road nr = new InterCityRoad(id, map.getJunction(srcJunc), map.getJunction(destJunc), maxSpeed, contLimit, length, weather);
		map.addRoad(nr);
		
	}

}
