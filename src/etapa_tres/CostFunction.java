package etapa_tres;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class CostFunction implements IStepCostFunction {

	@Override
	public double calculateCost(NTree node) {
		PlantAgentState state = ((PlantAgentState) node.getAgentState());
		return state.getEnergiaGastada() + state.getMovimientos();
	}

}
