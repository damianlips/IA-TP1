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
		((PlantAgentState)agent.getAgentState()).setEnergia(10);
		((PlantAgentState)agent.getAgentState()).setZombiesRestantes(5);
		state.setEnergiaAgente(((PlantAgentState)agent.getAgentState()).getEnergia());
		state.setCantZombies(((PlantAgentState)agent.getAgentState()).getZombiesRestantes());
		state.zombiesDeInicio();
		PlantSimulator simulator =
                new PlantSimulator(environment, agent);
        simulator.start();
		
      

	}

}
