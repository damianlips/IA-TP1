package etapa_tres;

import actions.*;
import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class CostFunction implements IStepCostFunction {

	@Override
	public double calculateCost(NTree node) {
		PlantAgentState state = ((PlantAgentState) node.getAgentState());
		//System.out.println(state.getPosY());
		if(node.getAction() instanceof MatarZombieAbajo
				|| node.getAction() instanceof MatarZombieArriba
				|| node.getAction() instanceof MatarZombieIzquierda
				|| node.getAction() instanceof MatarZombieDerecha				
				) {
			return Math.pow(2,state.getPosX()+1);			
		}
		else if(node.getAction() instanceof MoverseAbajo && state.getMatrizZombies()[state.getPosY()][state.getPosX()]>0
				|| node.getAction() instanceof MoverseArriba && state.getMatrizZombies()[state.getPosY()][state.getPosX()]>0	
				|| node.getAction() instanceof MoverseIzquierda && state.getMatrizZombies()[state.getPosY()][state.getPosX()]>0
				|| node.getAction() instanceof MoverseDerecha && state.getMatrizZombies()[state.getPosY()][state.getPosX()]>0
				|| node.getAction() instanceof MatarZombieEnMismaCasilla && state.getMatrizZombies()[state.getPosY()][state.getPosX()]>0
				) {
			return Math.pow(2,state.getPosX()+2);			
		}
		
		else return 0;
	}

}
