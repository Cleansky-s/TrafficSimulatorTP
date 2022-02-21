package simulator.model;

public class NewJunctionEvent extends Event{
	
	
	private String id;
	private LightSwitchingStrategy lightSwitchStrategy;
	private DequeuingStrategy extractStrategy;
	private int xCo,yCo;
	
	public NewJunctionEvent(int time, String id, LightSwitchingStrategy
			lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
			super(time);
			this.id = id;
			this.lightSwitchStrategy = lsStrategy;
			this.extractStrategy = dqStrategy;
			this.xCo = xCoor;
			this.yCo = yCoor;
			}

	@Override
	void execute(RoadMap map) {
		Junction jc= new Junction(id,lightSwitchStrategy,extractStrategy,xCo,yCo);
		map.addJunction(jc);		
	}

}
