package etapa_tres;

import etapa_dos.PlantEnvironment;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class PlantSearchMain {

	public static void main(String[] args) {
		PlantAgent agent = new PlantAgent();
		PlantEnvironment environment = new PlantEnvironment();
		
		SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
		
	}

}
