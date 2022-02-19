package simulator.model;

public class NewCityRoadEvent extends Event{ // HAY QUE CREAR UNA SUPERCLASE: NewRoadEvent
	public NewCityRoadEvent(int time, String id, String srcJun, String
			destJunc, int length, int co2Limit, int maxSpeed, Weather weather)
			{
			super(time);
			// ...
			}

	@Override
	void execute(RoadMap map) {
		// TODO Auto-generated method stub
		
	}


}
