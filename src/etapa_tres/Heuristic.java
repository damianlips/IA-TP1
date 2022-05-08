package etapa_tres;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class Heuristic implements IEstimatedCostFunction {

	@Override
	public double getEstimatedCost(NTree node) {
		PlantAgentState state = ((PlantAgentState) node.getAgentState());
		Integer[][] zombies = state.getMatrizZombies();
		Integer cant=0;
		Double costo=0d;
		/*
		 * for(int i=0; i<5; i++) {
			for(int j=0; j<9; j++) {
				//System.out.print(matriz[i][j] + " ");
				str.append(matriz[i][j] + " ");
		 */
		for(int i=0; i<5; i++) {
			for(int j=0; j<9; j++) {
				if(zombies[i][j]>0) {
					costo+= Math.pow(2,j+1);
					++cant;
				}
			}
		}
		
		return state.getZombiesRestantes()-cant*0.5;
	}

}
