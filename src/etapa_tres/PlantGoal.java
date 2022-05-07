package etapa_tres;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class PlantGoal extends GoalTest {

	@Override
	public boolean isGoalState(AgentState agentState) {
		PlantAgentState state = (PlantAgentState)agentState;
		if(state.getEnergia()>0 && state.getZombiesRestantes()==0)
			return true;
		else {
			if(state.getEnergia()>1 && !state.hayZombiesVistos() && state.exploreTodo()) return true;
			else return false;
		}
	}

}

