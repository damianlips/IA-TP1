package etapa_tres;

import actions.*;
import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class CostFunction implements IStepCostFunction {

	@Override
	public double calculateCost(NTree node) {
		PlantAgentState state = ((PlantAgentState) node.getAgentState());
		Integer[][] zombies = state.getMatrizZombies();
		Double costo=0d;
		//System.out.println(state.getPosY());
		/*
		if(node.getAction() instanceof MatarZombieAbajo
				|| node.getAction() instanceof MatarZombieArriba
				|| node.getAction() instanceof MatarZombieIzquierda
				|| node.getAction() instanceof MatarZombieDerecha				
				) {
			costo+= Math.pow(2,state.getPosX()+1);			
		}
		else if(node.getAction() instanceof MoverseAbajo && state.getMatrizZombies()[state.getPosY()][state.getPosX()]>0
				|| node.getAction() instanceof MoverseArriba && state.getMatrizZombies()[state.getPosY()][state.getPosX()]>0	
				|| node.getAction() instanceof MoverseIzquierda && state.getMatrizZombies()[state.getPosY()][state.getPosX()]>0
				|| node.getAction() instanceof MoverseDerecha && state.getMatrizZombies()[state.getPosY()][state.getPosX()]>0
				|| node.getAction() instanceof MatarZombieEnMismaCasilla && state.getMatrizZombies()[state.getPosY()][state.getPosX()]>0
				) {
			costo+= Math.pow(2,state.getPosX()+2);			
		}
			*/	
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<9; j++) {
				if(zombies[i][j]>0) {
					costo+= Math.pow(2,j+1);
				}
				else if(zombies[i][j]==PlantAgentState.DESCONOCIDO) ++costo;
			}
		}
		
		return costo;
		
		
	}

}
