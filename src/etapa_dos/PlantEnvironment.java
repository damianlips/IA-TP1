package etapa_dos;

import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;

public class PlantEnvironment extends Environment {
	public PlantEnvironment() {
		this.environmentState = new PlantEnvironmentState();
	}

	@Override
	public Perception getPercept() {
		// TODO Auto-generated method stub
		PlantPerception perception = new PlantPerception();
		return null;
	}
	
	@Override
    public PlantEnvironmentState getEnvironmentState() {
        return (PlantEnvironmentState) super.getEnvironmentState();
    }
	@Override
    public String toString() {
        return environmentState.toString();
    }
	@Override
    public boolean agentFailed(Action actionReturned) {
		return false;
	}
	 

}
