package actions;

import etapa_dos.PlantEnvironmentState;
import etapa_tres.PlantAgentState;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class MoverseDerecha extends SearchAction{

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        PlantAgentState p= (PlantAgentState) s;
        if(p.getPosX()==8) return null;
        p.setPosX(p.getPosX()+1);
        if(p.getMatrizZombies()[p.getPosY()][p.getPosX()]>0) p.setEnergia(p.getEnergia()-p.getMatrizZombies()[p.getPosY()][p.getPosX()]*2);
        return p;
    }

    @Override
    public Double getCost() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        PlantAgentState p= (PlantAgentState) ast;
        PlantEnvironmentState e = (PlantEnvironmentState) est;
        if(p.getPosX()==8) return null;
        p.setPosX(p.getPosX()+1);
        if(p.getMatrizZombies()[p.getPosY()][p.getPosX()]>0) p.setEnergia(p.getEnergia()-p.getMatrizZombies()[p.getPosY()][p.getPosX()]*2);
        e.setAgentX(e.getAgentX()+1);
        e.setEnergiaAgente(p.getEnergia());
        return e;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

}