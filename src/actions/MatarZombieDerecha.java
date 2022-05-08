package actions;

import etapa_dos.PlantEnvironmentState;
import etapa_tres.PlantAgentState;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class MatarZombieDerecha extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        PlantAgentState p = (PlantAgentState) s;
        if(p.getEnergia()<=0) return null;
        if(p.getMatrizZombies()[p.getPosY()][p.getPosX()]>0) return null;
        if(p.getPosX()==8) return null;
        if(p.getMatrizZombies()[p.getPosY()][p.getPosX()+1]<=0) return null;
        p.setEnergia(p.getEnergia()-p.getMatrizZombies()[p.getPosY()][p.getPosX()+1]);
        p.getMatrizZombies()[p.getPosY()][p.getPosX()+1]=0;
        p.setZombiesRestantes(p.getZombiesRestantes()-1);
        p.percepcionFalsa();
        return p;
    }

    @Override
    public Double getCost() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        // TODO Auto-generated method stub
        PlantAgentState p = (PlantAgentState) ast;
        if(p.getEnergia()<=0) return null;
        PlantEnvironmentState e = (PlantEnvironmentState) est;
        if(p.getMatrizZombies()[p.getPosY()][p.getPosX()]>0) return null;
        if(p.getPosX()==8) return null;
        if(p.getMatrizZombies()[p.getPosY()][p.getPosX()+1]<=0) return null;
        p.setEnergia(p.getEnergia()-p.getMatrizZombies()[p.getPosY()][p.getPosX()+1]);
        p.getMatrizZombies()[p.getPosY()][p.getPosX()+1]=0;
        p.setZombiesRestantes(p.getZombiesRestantes()-1);
        e.getMapa()[e.getAgentY()][e.getAgentX()+1]=0;
        e.setEnergiaAgente(p.getEnergia());
        return e;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Matar zombie a la derecha";
    }

}