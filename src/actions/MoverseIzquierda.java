package actions;

import etapa_dos.Girasol;
import etapa_dos.PlantEnvironmentState;
import etapa_tres.PlantAgentState;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class MoverseIzquierda extends SearchAction{

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        PlantAgentState p= (PlantAgentState) s;
        if(p.getEnergia()<=0) return null;
        if(p.getPosX()==0) return null;
        p.setPosX(p.getPosX()-1);
        if(p.getMatrizZombies()[p.getPosY()][p.getPosX()]>0) p.setEnergia(p.getEnergia()-p.getMatrizZombies()[p.getPosY()][p.getPosX()]*2);
        else if(p.getMatrizGirasoles()[p.getPosY()][p.getPosX()]>0) {
        	p.setEnergia(p.getEnergia()+p.getMatrizGirasoles()[p.getPosY()][p.getPosX()]);
        	p.getMatrizGirasoles()[p.getPosY()][p.getPosX()]=0;
        }
        p.percepcionFalsa();
        p.setMovimientos(p.getMovimientos()+1);
        if(!p.isSimulacion()&&p.getZombiesVistos()==0)p.setExplorando(true);
        p.setSimulacion(true);
        return p;
    }

    @Override
    public Double getCost() {
        return 1d;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        PlantAgentState p= (PlantAgentState) ast;
        PlantEnvironmentState e = (PlantEnvironmentState) est;
        if(p.getPosX()==0) return null;
        p.setPosX(p.getPosX()-1);
        if(p.getMatrizZombies()[p.getPosY()][p.getPosX()]>0) p.setEnergia(p.getEnergia()-p.getMatrizZombies()[p.getPosY()][p.getPosX()]*2);
        else if(p.getMatrizGirasoles()[p.getPosY()][p.getPosX()]>0) p.setEnergia(p.getEnergia()+p.getMatrizGirasoles()[p.getPosY()][p.getPosX()]);
        e.setAgentX(e.getAgentX()-1);
        e.setEnergiaAgente(p.getEnergia());
        if(e.getMapa()[e.getAgentY()][e.getAgentX()] instanceof Girasol)((Girasol)e.getMapa()[e.getAgentY()][e.getAgentX()]).setCantSoles(0);
        return e;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "MoverseIzquierda";
    }

}
