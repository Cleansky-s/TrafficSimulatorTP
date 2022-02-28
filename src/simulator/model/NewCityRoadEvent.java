package simulator.model;


public class NewCityRoadEvent extends NewRoadEvent{

	private String srcJunc,destJunc; //dest and origin junction
	private int length;
	private String id;
	private int maxSpeed;
	private int contLimit;
	private Weather weather;

	public NewCityRoadEvent(int time, String id, String srcJun, String
			destJunc, int length, int co2Limit, int maxSpeed, Weather weather)
			{
				super(time,  id,  srcJun,
				destJunc,  length,  co2Limit,  maxSpeed,  weather);
				this.id = id;
				this.srcJunc = srcJun;
				this.destJunc = destJunc;
				this.length = length;
				this.contLimit = co2Limit;
				this.maxSpeed = maxSpeed;
				this.weather = weather;
			}

	@Override
	void execute(RoadMap map) {
		Road nr = new CityRoad(id, map.getJunction(srcJunc), map.getJunction(destJunc), maxSpeed, contLimit, length, weather);
		map.addRoad(nr);
	}


}
