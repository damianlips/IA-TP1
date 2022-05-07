package etapa_dos;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PlantPerception extends Perception {
	
	public Sensor arriba,abajo,izquierda,derecha;
	public int zombie;
	
	
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
		if(envState.getMapa()[y][x] instanceof Zombie)
			zombie = ((Zombie)envState.getMapa()[y][x]).getTipoZombie();
		else zombie=0;
		

	}


	@Override
	public String toString() {
		return "PlantPerception [arriba=" + arriba + ", abajo=" + abajo + ", izquierda=" + izquierda + ", derecha="
				+ derecha + ", zombie=" + zombie + "]";
	}


	
	

}
