package etapa_tres;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class PlantGoal extends GoalTest {

	@Override
	public boolean isGoalState(AgentState agentState) {
		PlantAgentState state = (PlantAgentState)agentState;
		if(state.getEnergia()>0 && state.getZombiesRestantes()<=0&&!state.hayZombiesVistos()) {
			System.out.println(state.getZombiesRestantes());
			return true;
		}
		else {
			//if(!state.hayZombiesVistos() && state.exploreTodo() && state.getEnergia()>0&&state.filaGirasoles()) {
			if(!state.hayZombiesVistos() && state.exploreCasiTodo() && state.getEnergia()>0 && state.filaGirasoles()) {
				System.out.println(state.getZombiesRestantes());
				return true;
			}
			else return false;
		}
	}

}

