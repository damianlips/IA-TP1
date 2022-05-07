package etapa_tres;


import etapa_dos.PlantEnvironment;
import etapa_dos.PlantEnvironmentState;
import etapa_dos.Zombie;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class PlantSearchMain {

	public static void main(String[] args) {
		
	    
		PlantAgent agent = new PlantAgent();
		PlantEnvironment environment = new PlantEnvironment();
		PlantEnvironmentState state = (PlantEnvironmentState)environment.getEnvironmentState();
		state.setEnergiaAgente(((PlantAgentState)agent.getAgentState()).getEnergia());
		state.setCantZombies(((PlantAgentState)agent.getAgentState()).getZombiesRestantes());
		PlantSimulator simulator =
                new PlantSimulator(environment, agent);
        simulator.start();
		
      

	}

}
