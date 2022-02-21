package simulator.model;

public class CityRoad extends Road{

	CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {  //id must be non-empty String
		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
		// TODO Auto-generated constructor stub
	}

	@Override
	void reduceTotalContamination() {
		int x = 0;
		if(this.getWeather() == Weather.WINDY || this.getWeather() == Weather.STORM) {
			x = 10;
		}
		else {
			x = 2;
		}
		if(x > this.getTotalPoll()) {
			x = this.getTotalPoll();
		}
		this.addContam(-x);
	}

	@Override
	void updateSpeedLimit() { //Velocidad limite no cambia
		
	}

	@Override
	int calculateVehicleSpeed(Vehicle v) {
		int vel;
		vel = ((11 - v.getContClass())*this.getLimitSpeed())/11;
		return vel;
	}

}
