package simulator.model;

public class NewRoadEvent extends Event{
	private String srcJunc,destJunc; //dest and origin junction
	private int length;
	private String id;
	private int maxSpeed;
	private int contLimit;
	private int limitSpeed;
	private Weather weather;
	NewRoadEvent(int time, String id, String srcJun, String
			destJunc, int length, int co2Limit, int maxSpeed, Weather weather) {
		super(time);
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
		// TODO Auto-generated method stub
		
	}

}
