package etapa_tres;

import actions.*;
import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class CostFunction implements IStepCostFunction {

	private final Double[] aux = {2d,4d,8d,16d,32d,64d,128d,256d,512d,1024d, 2048d};
	@Override
	public double calculateCost(NTree node) {
		PlantAgentState state = ((PlantAgentState) node.getAgentState());
		Integer[][] zombies = state.getMatrizZombies();
		Double costo=0d;
		//System.out.println(state.getPosY());
		if(node.getAction() instanceof MoverseDerecha && state.getMatrizZombies()[state.getPosY()][state.getPosX()]<=0) return 200;
		
		if(node.getAction() instanceof MatarZombieAbajo
				|| node.getAction() instanceof MatarZombieArriba
				|| node.getAction() instanceof MatarZombieIzquierda
				|| node.getAction() instanceof MatarZombieDerecha				
				) {
			//costo+= Math.pow(2,state.getPosX()+1);		
			costo+= aux[state.getPosX()+1];		
		}
		else if(node.getAction() instanceof MoverseAbajo && state.getMatrizZombies()[state.getPosY()][state.getPosX()]>0
				|| node.getAction() instanceof MoverseArriba && state.getMatrizZombies()[state.getPosY()][state.getPosX()]>0	
				|| node.getAction() instanceof MoverseIzquierda && state.getMatrizZombies()[state.getPosY()][state.getPosX()]>0
				|| node.getAction() instanceof MoverseDerecha && state.getMatrizZombies()[state.getPosY()][state.getPosX()]>0
				|| node.getAction() instanceof MatarZombieEnMismaCasilla && state.getMatrizZombies()[state.getPosY()][state.getPosX()]>0
				) {
			//costo+= Math.pow(2,state.getPosX()+2);	
			costo+= aux[state.getPosX()+2];
		}
				
		/*
		for(int i=0; i<5; i++) {
			for(int j=0; j<9; j++) {
				if(zombies[i][j]>0) {
					costo+= aux[8-j];
				}
				else if(zombies[i][j]==PlantAgentState.DESCONOCIDO) costo+=state.getUltimoExplorado()[i];
			}
		}
		*/
		/*
		for(int i=0; i<5; i++) {
			for(int j=0; j<9; j++) {
				if(zombies[i][j]==PlantAgentState.DESCONOCIDO) {
					//costo+= Math.pow(2,j+1);
					costo+=state.getUltimoExplorado()[i];
				}
			}
		}*/
		/*
		if(state.getPosX()<8) {
			if(state.getPosY()<4 && 
					zombies[state.getPosY()+1][state.getPosX()+1]>0
					&& (node.getAction() instanceof MoverseDerecha || 
							node.getAction() instanceof MoverseArriba)
					)
				return 300d;
			else if(state.getPosY()>0 && 
					zombies[state.getPosY()-1][state.getPosX()+1]>0
					&& (node.getAction() instanceof MoverseDerecha || 
							node.getAction() instanceof MoverseAbajo)
					)
				return 300d;
				
		}
			*/	
		
		return costo;
		
		
	}

}

