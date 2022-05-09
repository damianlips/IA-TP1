package etapa_tres;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class Heuristic implements IEstimatedCostFunction {

	private final Double[] aux = {2d,4d,8d,16d,32d,64d,128d,256d,512d,1024d};
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
					costo+= Math.pow(2,8-j)*Math.abs(state.getPosY()-i);
					costo+= aux[8-j]*Math.abs(state.getPosY()-i);
					++cant;
				}
				else if (zombies[i][j]==PlantAgentState.DESCONOCIDO) {
					//costo+= Math.pow(2,j+1)*Math.abs(state.getPosY()-i);
					costo+= ((double)state.getUltimoExplorado()[i])*Math.abs(state.getPosY()-i);					
				}
			}
		}/*
		for(int i=0; i<5; i++) {
			for(int j=0; j<9; j++) {
				if(zombies[i][j]==PlantAgentState.DESCONOCIDO) {
					costo+= Math.pow(2,j+1);
				}
			}
		}
		*/
		
		return costo + ((state.getZombiesRestantes()-cant)*2);
		//return state.getZombiesRestantes()-cant*0.5;
	}

}
