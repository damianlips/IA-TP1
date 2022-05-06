package etapa_tres;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class PlantGoal extends GoalTest {

	@Override
	public boolean isGoalState(AgentState agentState) {
		PlantAgentState state = (PlantAgentState)agentState;
		//System.out.print(state.getZombiesVistos());
		//System.out.print(state.matrizExplorada());
		//System.out.println(state.getEnergia());
		//System.out.println(state.getZombiesRestantes());
		if(state.getEnergia()>0 && state.getZombiesRestantes()==0)
			return true;
		else if (state.isSimulacion()&&state.getZombiesVistos()==0&&!state.isExplorando())
			return true;
		else if (state.isSimulacion()&&state.isExplorando()&&state.matrizExplorada()&&state.getPosX()<=3&&state.filaGirasoles())
			return true;
		else return false;
	}

}
