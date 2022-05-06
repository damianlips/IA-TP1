package etapa_dos;

import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;

public class PlantEnvironment extends Environment {
	public PlantEnvironment() {
		this.environmentState = new PlantEnvironmentState();
		this.environmentState.initState();
	}

	@Override
	public Perception getPercept() {
		// TODO Auto-generated method stub
		PlantPerception perception = new PlantPerception();
		int x = ((PlantEnvironmentState)environmentState).getAgentX();
		int y = ((PlantEnvironmentState)environmentState).getAgentY();
		
		perception.arriba=this.getArriba(x, y);
		perception.abajo=this.getAbajo(x, y);
		perception.izquierda=this.getIzquierda(x, y);
		perception.derecha=this.getDerecha(x, y);
		return perception;
	}

	@Override
	public PlantEnvironmentState getEnvironmentState() {
		return (PlantEnvironmentState) super.getEnvironmentState();
	}
	@Override
	public void updateState(AgentState ast,Action action) {
		super.updateState(ast, action);
		System.out.println(environmentState);
		((PlantEnvironmentState)environmentState).update();
	}
	@Override
	public String toString() {
		return environmentState.toString();
	}
	@Override
	public boolean agentFailed(Action actionReturned) {
		PlantEnvironmentState state = ((PlantEnvironmentState)environmentState);
		
		return (state.getLlegoZombie() || state.getEnergiaAgente()<=0);
	}
	
	public Sensor getArriba(int x, int y) {
		return ((PlantEnvironmentState)this.environmentState).getArriba(x, y);
	}
	public Sensor getAbajo(int x, int y) {
		return ((PlantEnvironmentState)this.environmentState).getAbajo(x, y);
	}
	public Sensor getIzquierda(int x, int y) {
		return ((PlantEnvironmentState)this.environmentState).getIzquierda(x, y);
	}
	public Sensor getDerecha(int x, int y) {
		return ((PlantEnvironmentState)this.environmentState).getDerecha(x, y);
	}


}
