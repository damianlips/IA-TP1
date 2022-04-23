package etapa_tres;

import etapa_dos.PlantPerception;
import etapa_dos.Sensor;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class PlantAgentState extends SearchBasedAgentState {
	
	private Integer energia;
	private Integer posX;
	private Integer posY;
	private Integer[][] matrizZombies;
	private Integer[][] matrizGirasoles;
	private Integer zombiesRestantes;
	private Integer energiaGastada;
	private Integer movimientos;
	
	public final static int DESCONOCIDO = -1; 
	
	

	
	public PlantAgentState(Integer energia, Integer posX, Integer posY, Integer[][] matrizZombies,
			Integer[][] matrizGirasoles, Integer zombiesRestantes, Integer energiaGastada, Integer movimientos) {
		super();
		this.energia = energia;
		this.posX = posX;
		this.posY = posY;
		this.matrizZombies = matrizZombies;
		this.matrizGirasoles = matrizGirasoles;
		this.zombiesRestantes = zombiesRestantes;
		this.energiaGastada = energiaGastada;
		this.movimientos = movimientos;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PlantAgentState))
            return false;
		PlantAgentState otro = ((PlantAgentState)obj);
		
		if(otro.getEnergia()!=this.energia) return false;
		if(otro.getPosX()!=this.posX) return false;
		if(otro.getPosY()!=this.posY) return false;
		if(otro.getZombiesRestantes()!=this.zombiesRestantes) return false;
		
		for(int i = 0; i<5 ; ++i) {
			for(int j= 0 ; j<9 ; ++j) {
				if(this.matrizGirasoles[i][j] != otro.getMatrizGirasoles()[i][j]) return false;
				if(this.matrizZombies[i][j] != otro.getMatrizZombies()[i][j]) return false;
			}
		}
		
		return true;
		
	}

	@Override
	public SearchBasedAgentState clone() {
		Integer[][] nuevaMatrizZombies = new Integer[5][9];
		Integer[][] nuevaMatrizGirasoles = new Integer[5][9];
		for(int i = 0; i<5 ; ++i) {
			for(int j= 0 ; j<9 ; ++j) {
				nuevaMatrizGirasoles[i][j] = this.matrizGirasoles[i][j];
				nuevaMatrizZombies[i][j] = this.matrizZombies[i][j];
			}
		}
		
		return new PlantAgentState(energia, posX, posY, nuevaMatrizZombies, nuevaMatrizGirasoles, zombiesRestantes,energiaGastada, movimientos);
	}

	@Override
	public void updateState(Perception p) {
		PlantPerception per = (PlantPerception) p;
		//Primero sensor a la izquierda
		for(int i=1 ; i<per.izquierda.distancia ; ++i ) {
			matrizZombies[posY][posX-i] = 0;
			matrizGirasoles[posY][posX-i] = 0;						
		}
		switch(per.izquierda.tipo) {
		case Sensor.GIRASOL:
			matrizGirasoles[posY][posX-per.izquierda.distancia]=per.izquierda.energia;
			break;
		case Sensor.ZOMBIE:
			matrizZombies[posY][posX-per.izquierda.distancia]=per.izquierda.energia;
		}
		
		//Derecha
		for(int i=1 ; i<per.derecha.distancia ; ++i ) {
			matrizZombies[posY][posX+i] = 0;
			matrizGirasoles[posY][posX+i] = 0;						
		}
		switch(per.derecha.tipo) {
		case Sensor.GIRASOL:
			matrizGirasoles[posY][posX+per.derecha.distancia]=per.derecha.energia;
			break;
		case Sensor.ZOMBIE:
			matrizZombies[posY][posX+per.derecha.distancia]=per.derecha.energia;
		}
		
		
		//Arriba
		for(int i=1 ; i<per.arriba.distancia ; ++i) {
			matrizZombies[posY-i][posX] = 0;
			matrizGirasoles[posY-i][posX] = 0;
			switch(per.arriba.tipo) {
			case Sensor.GIRASOL:
				matrizGirasoles[posY-per.arriba.distancia][posX]=per.arriba.energia;
				break;
			case Sensor.ZOMBIE:
				matrizZombies[posY-per.arriba.distancia][posX]=per.arriba.energia;
			}
		}
		
		//Abajo
		for(int i=1 ; i<per.abajo.distancia ; ++i) {
			matrizZombies[posY+i][posX] = 0;
			matrizGirasoles[posY+i][posX] = 0;
			switch(per.abajo.tipo) {
			case Sensor.GIRASOL:
				matrizGirasoles[posY+per.abajo.distancia][posX]=per.abajo.energia;
				break;
			case Sensor.ZOMBIE:
				matrizZombies[posY+per.abajo.distancia][posX]=per.abajo.energia;
			}
		}
		
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initState() {
		// TODO Auto-generated method stub
		
	}


	public Integer getEnergia() {
		return energia;
	}


	public void setEnergia(Integer energia) {
		this.energia = energia;
	}


	public Integer getPosX() {
		return posX;
	}


	public void setPosX(Integer posX) {
		this.posX = posX;
	}


	public Integer getPosY() {
		return posY;
	}


	public void setPosY(Integer posY) {
		this.posY = posY;
	}


	public Integer[][] getMatrizZombies() {
		return matrizZombies;
	}


	public void setMatrizZombies(Integer[][] matrizZombies) {
		this.matrizZombies = matrizZombies;
	}


	public Integer[][] getMatrizGirasoles() {
		return matrizGirasoles;
	}


	public void setMatrizGirasoles(Integer[][] matrizGirasoles) {
		this.matrizGirasoles = matrizGirasoles;
	}


	public Integer getZombiesRestantes() {
		return zombiesRestantes;
	}


	public void setZombiesRestantes(Integer zombiesRestantes) {
		this.zombiesRestantes = zombiesRestantes;
	}


	public Integer getEnergiaGastada() {
		return energiaGastada;
	}


	public void setEnergiaGastada(Integer energiaGastada) {
		this.energiaGastada = energiaGastada;
	}


	public Integer getMovimientos() {
		return movimientos;
	}


	public void setMovimientos(Integer movimientos) {
		this.movimientos = movimientos;
	}
	
	
	

}
