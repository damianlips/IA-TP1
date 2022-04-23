package etapa_tres;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class Heuristic implements IEstimatedCostFunction {

	@Override
	public double getEstimatedCost(NTree node) {
		PlantAgentState state = ((PlantAgentState) node.getAgentState());
		return state.getZombiesRestantes();
	}

}
