package etapa_dos;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PlantPerception extends Perception {
	
	public Sensor arriba,abajo,izquierda,derecha;
	
	
	@Override
	public void initPerception(Agent agent, Environment environment) {
		// TODO Auto-generated method stub
		PlantEnvironment plantEnvironment = (PlantEnvironment)environment;
		PlantEnvironmentState envState = (PlantEnvironmentState)(environment.getEnvironmentState());
		int x = envState.getAgentX();
		int y = envState.getAgentY();
		
		this.arriba=envState.getArriba(x, y);
		this.abajo=envState.getAbajo(x, y);
		this.izquierda=envState.getIzquierda(x, y);
		this.derecha=envState.getDerecha(x, y);
		
		

	}

}
