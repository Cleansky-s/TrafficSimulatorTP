package simulator.model;

public class InterCityRoad extends Road{

	InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length,
			Weather weather) {
		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
	}

	@Override
	void reduceTotalContamination() {
		int x = 0;
		switch(this.getWeather()) {
		case SUNNY:
			x = 2;
			break;
		case CLOUDY:
			x = 3;
			break;
		case RAINY:
			x = 10;
			break;
		case WINDY:
			x = 15;
			break;
		case STORM:
			x = 20;
			break;
		}
		int c = ((100 - x)*this.getTotalPoll())/100;
		c = this.getTotalPoll() - c;
		this.addContam(-c);
	}

	@Override
	void updateSpeedLimit() {
		if(this.getTotalPoll() > this.getContLimit()){
			this.setSpeedLimit(this.getMaxSpeed()/2);
		}
		else {
			this.setSpeedLimit(this.getMaxSpeed());
		}
	}
	
	@Override
	int calculateVehicleSpeed(Vehicle v) {
		if(this.getWeather() != Weather.STORM) {
			if(v.getMaxSpeed() > this.getLimitSpeed()) {
				return this.getLimitSpeed();
			}
			else
				return v.getMaxSpeed();
		}
		else		
			return (this.getLimitSpeed()*8)/10;
	}

}
