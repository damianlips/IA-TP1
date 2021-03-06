package actions;

import etapa_dos.Girasol;
import etapa_dos.PlantEnvironmentState;
import etapa_tres.PlantAgentState;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class PlantarGirasol extends SearchAction{

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        PlantAgentState p = (PlantAgentState) s;
        if(p.getPerdi()) return null;
        if(p.getEnergia()<=0) return null;
        if(p.getMatrizZombies()[p.getPosY()][p.getPosX()]>0) return null;
        if(p.getPosX()>0) return null;
        if(p.getMatrizGirasoles()[p.getPosY()][p.getPosX()]>=0) return null;
        if(p.getEnergia()<1) return null;
        p.setEnergia(p.getEnergia()-1);
        p.getMatrizGirasoles()[p.getPosY()][p.getPosX()]=0;
        //p.getMatrizGirasoles()[p.getPosY()][p.getPosX()]=2;
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
        PlantAgentState p = (PlantAgentState) ast;
        p.setMate(false);
        if(p.getPerdi()) return null;
        if(p.getEnergia()<=0) return null;
        PlantEnvironmentState e = (PlantEnvironmentState) est;
        if(p.getMatrizZombies()[p.getPosY()][p.getPosX()]>0) return null;
        if(p.getPosX()>0) return null;
        if(p.getMatrizGirasoles()[p.getPosY()][p.getPosX()]>=0) return null;
        if(p.getEnergia()<1) return null;
        p.setEnergia(p.getEnergia()-1);
        p.getMatrizGirasoles()[p.getPosY()][p.getPosX()]=0;
        e.getMapa()[e.getAgentY()][e.getAgentX()]= new Girasol();
        e.setEnergiaAgente(p.getEnergia());
        e.setMate(false);
        return e;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Plantar girasol";
    }

}