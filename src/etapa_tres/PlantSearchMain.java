package etapa_tres;


import etapa_dos.PlantEnvironment;
import etapa_dos.PlantEnvironmentState;
import etapa_dos.Zombie;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class PlantSearchMain {
	

	public static void main(String[] args) {
		
		int cont = 0;
		int win = 0;
		long acumT = 0;
	    while(cont < 10) {
	    	long tstart = System.currentTimeMillis();
	    	cont++;
	    	PlantSearchMain psm = new PlantSearchMain();
	    	if(psm.ejecutar()) win++;
	    	long tfinish = System.currentTimeMillis();
	    	System.out.println("Tardo " + (tfinish-tstart));
	    	acumT += (tfinish-tstart);
	    	System.out.println("Total " + acumT);
	    	System.out.println("Actual " + cont + " " + win);
	    	System.out.println("Actual " + cont + " " + win);
	    	System.out.println("Actual " + cont + " " + win);
	    }
		System.out.println(cont + " " + win);
		
      

	}
	
	public  Boolean ejecutar() {
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
        return simulator.start();
		
	}

}
