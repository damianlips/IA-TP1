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
        if(p.getMatrizZombies()[p.getPosY()][p.getPosX()]>0) return null;
        if(p.getPosX()>=8) return null;
        p.setPosX(p.getPosX()+1);
        if(p.getMatrizZombies()[p.getPosY()][p.getPosX()]>0) {
        	p.setEnergia(p.getEnergia()-p.getMatrizZombies()[p.getPosY()][p.getPosX()]*2);
        	p.getMatrizZombies()[p.getPosY()][p.getPosX()] = 0;
        	p.setZombiesRestantes(p.getZombiesRestantes()-1);
        }
        else if(p.getMatrizGirasoles()[p.getPosY()][p.getPosX()]>0) p.setEnergia(p.getEnergia()+p.getMatrizGirasoles()[p.getPosY()][p.getPosX()]);
        return p;
    }

    @Override
    public Double getCost() {
        return 0d;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        PlantAgentState p= (PlantAgentState) ast;
        PlantEnvironmentState e = (PlantEnvironmentState) est;
        if(p.getMatrizZombies()[p.getPosY()][p.getPosX()]>0) return null;
        if(p.getPosX()>=8) return null;
        p.setPosX(p.getPosX()+1);
        if(p.getMatrizZombies()[p.getPosY()][p.getPosX()]>0) {
        	p.setEnergia(p.getEnergia()-p.getMatrizZombies()[p.getPosY()][p.getPosX()]*2);
        	p.getMatrizZombies()[p.getPosY()][p.getPosX()]=0;
        	e.getMapa()[p.getPosY()][p.getPosX()]=0;
        	e.setCantZombies(e.getCantZombies()-1);
        }
        else 
            if(p.getMatrizGirasoles()[p.getPosY()][p.getPosX()]>0) p.setEnergia(p.getEnergia()+p.getMatrizGirasoles()[p.getPosY()][p.getPosX()]);
        e.setAgentX(e.getAgentX()+1);
        e.setEnergiaAgente(p.getEnergia());
        return e;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Moverse derecha";
    }

}