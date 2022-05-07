package etapa_tres;

import etapa_dos.PlantEnvironmentState;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import frsf.cidisi.faia.state.AgentState;

public class PlantSimulator extends SearchBasedAgentSimulator {

	public PlantSimulator(Environment environment, Agent agent) {
		super(environment, agent);
		// TODO Auto-generated constructor stub
	}
	@Override
    public boolean agentSucceeded(Action actionReturned) {
        
        PlantAgentState state = (PlantAgentState) ((PlantAgent)getAgents().firstElement()).getAgentState();
        PlantEnvironmentState envState = (PlantEnvironmentState) this.getEnvironment().getEnvironmentState();
		if(state.getEnergia()>0 && envState.getCantZombies()==0)
			return true;
		else return false;
        
    }

}
