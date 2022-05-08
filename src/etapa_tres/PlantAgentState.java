package etapa_tres;

import java.util.Random;

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
	private Integer[] ultimoExplorado;
	private Boolean perdi;
	private Boolean avanzan;
	
	public final static int DESCONOCIDO = -1; 
	
	

	
	public PlantAgentState(Integer energia, Integer posX, Integer posY, Integer[][] matrizZombies,
			Integer[][] matrizGirasoles, Integer zombiesRestantes, Integer energiaGastada, Integer movimientos, Integer[] ultimoExplorado, Boolean perdi, Boolean avanzan) {
		super();
		this.energia = energia;
		this.posX = posX;
		this.posY = posY;
		this.matrizZombies = matrizZombies;
		this.matrizGirasoles = matrizGirasoles;
		this.zombiesRestantes = zombiesRestantes;
		this.energiaGastada = energiaGastada;
		this.movimientos = movimientos;
		this.ultimoExplorado = ultimoExplorado;
		this.setPerdi(perdi);
		this.avanzan=avanzan;
	}
	
	
	public PlantAgentState() {
		this.initState();
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
		if(otro.getPerdi()!=this.getPerdi()) return false;
		
		for(int i = 0; i<5 ; ++i) {
			if(this.ultimoExplorado[i]!= otro.getUltimoExplorado()[i]) return false;
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
		Integer[] nuevoUltimoExplorado = new Integer[5];
		for(int i = 0; i<5 ; ++i) {
			nuevoUltimoExplorado[i]=this.ultimoExplorado[i];
			for(int j= 0 ; j<9 ; ++j) {
				nuevaMatrizGirasoles[i][j] = this.matrizGirasoles[i][j];
				nuevaMatrizZombies[i][j] = this.matrizZombies[i][j];
			}
		}		
		return new PlantAgentState(energia, posX, posY, nuevaMatrizZombies, nuevaMatrizGirasoles, zombiesRestantes,energiaGastada, movimientos,nuevoUltimoExplorado, getPerdi(),avanzan);
	}

	@Override
	public void updateState(Perception p) {
		for(int i = 0; i<5 ; ++i) {
			if(i!=this.getPosY())
				++ultimoExplorado[i];
		}	
		
		PlantPerception per = (PlantPerception) p;
		//Sensor donde estoy parado
		if(per.zombie>0) {
			matrizZombies[posY][posX]= per.zombie;
			matrizGirasoles[posY][posX] = -1;				
		}
		
		//Sensor a la izquierda
		for(int i=1 ; i<=per.izquierda.distancia ; ++i ) {
			matrizZombies[posY][posX-i] = 0;
			matrizGirasoles[posY][posX-i] = -1;						
		}
		switch(per.izquierda.tipo) {
		case Sensor.GIRASOL:
			matrizGirasoles[posY][posX-per.izquierda.distancia]=per.izquierda.energia;
			break;
		case Sensor.ZOMBIE:
			matrizZombies[posY][posX-per.izquierda.distancia]=per.izquierda.energia;
		}
		
		//Derecha
		for(int i=1 ; i<=per.derecha.distancia ; ++i ) {
			matrizZombies[posY][posX+i] = 0;
			matrizGirasoles[posY][posX+i] = -1;						
		}
		switch(per.derecha.tipo) {
		case Sensor.GIRASOL:
			matrizGirasoles[posY][posX+per.derecha.distancia]=per.derecha.energia;
			break;
		case Sensor.ZOMBIE:
			matrizZombies[posY][posX+per.derecha.distancia]=per.derecha.energia;
		}
		//No sabemos si vinieron mas zombies atras
		for(int i=posX+per.derecha.distancia+1 ; i<9 ; ++i ) {
			matrizZombies[posY][i] = this.DESCONOCIDO;
		}
		
		
		//Arriba
		for(int i=1 ; i<=per.arriba.distancia ; ++i) {
			matrizZombies[posY-i][posX] = 0;
			matrizGirasoles[posY-i][posX] = -1;
			switch(per.arriba.tipo) {
			case Sensor.GIRASOL:
				matrizGirasoles[posY-per.arriba.distancia][posX]=per.arriba.energia;
				break;
			case Sensor.ZOMBIE:
				matrizZombies[posY-per.arriba.distancia][posX]=per.arriba.energia;
			}
		}
		
		//Abajo
		for(int i=1 ; i<=per.abajo.distancia ; ++i) {
			matrizZombies[posY+i][posX] = 0;
			matrizGirasoles[posY+i][posX] = -1;
			switch(per.abajo.tipo) {
			case Sensor.GIRASOL:
				matrizGirasoles[posY+per.abajo.distancia][posX]=per.abajo.energia;
				break;
			case Sensor.ZOMBIE:
				matrizZombies[posY+per.abajo.distancia][posX]=per.abajo.energia;
			}
		}
		//Asumimos que los zombies fuera de vista se mueven
		if(avanzan)
		for(int i=posX+2 ; i<9 ; ++i) {
			for(int j=0; j<5; ++j) {
				if(j!=posY) {
					if(matrizZombies[j][i]>0) {
						matrizZombies[j][i-1]= matrizZombies[j][i];
						matrizZombies[j][i]=this.DESCONOCIDO;
					}
				}
			}
		}
		avanzan=!avanzan;
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		String[][] matriz = new String[5][9];
		StringBuilder str = new StringBuilder();
		//System.out.println("Energia: " + this.energia);
		str.append(System.getProperty("line.separator"));
		str.append("Energia: " + this.energia);
		str.append(System.getProperty("line.separator"));
		for(int i=0; i<5; i++) 	for(int j=0; j<9; j++) matriz[i][j] = "XX";
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<9; j++) {
				if(matrizZombies[i][j] > 0) {
					matriz[i][j] = "Z" + matrizZombies[i][j];
 				}
				else 
				if(matrizGirasoles[i][j] >= 0) {
					matriz[i][j] = "G" + matrizGirasoles[i][j];
 				}
				else if (matrizZombies[i][j]==0) matriz[i][j] = "VV";
			}
		}
		matriz[posY][posX] = "Pl";
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<9; j++) {
				//System.out.print(matriz[i][j] + " ");
				str.append(matriz[i][j] + " ");
			}
			//System.out.println();
			str.append(System.getProperty("line.separator"));
		}
		str.append("Ultimo explorado: ");
		for(int i=0; i<5; i++) {
			str.append("[ " + ultimoExplorado[i] + "] ");
		}
		str.append(System.getProperty("line.separator"));
		
		return str.toString();
	}
	
	
	@Override
	public void initState() {
		matrizZombies = new Integer[5][9];
		matrizGirasoles = new Integer[5][9];
		ultimoExplorado = new Integer[5];
		perdi=false;
		for(int i = 0; i<5 ; ++i) {
			ultimoExplorado[i]=6;
			for(int j=0; j<9; j++) {
				matrizZombies[i][j]= DESCONOCIDO;
				matrizGirasoles[i][j] = DESCONOCIDO;
			}
		}
		ultimoExplorado[2]=0;
		Random rand = new Random();
		energia=rand.nextInt(19)+2; //Genero random entre 0 y (19-1) y sumo 2 para tener entre 2 y 20
		posX=0;
		posY=2;
		zombiesRestantes= rand.nextInt(16)+5;
		energiaGastada=0;
		movimientos=0;
		avanzan=false;
		
		
	}
	
	public void percepcionFalsa() {
		for(int i =0;i<5;i++) {
			if(matrizZombies[i][0]>0 && avanzan) setPerdi(true);
			for(int j=0;j<9;j++) {
				if((i==posY||j==posX)&& matrizZombies[i][j]==PlantAgentState.DESCONOCIDO) matrizZombies[i][j]=Sensor.VACIO;
				if(matrizGirasoles[i][j]>=0) matrizGirasoles[i][j]+=1;
				if(matrizZombies[i][j]>0&&j>0 && avanzan) {
					matrizZombies[i][j-1]=matrizZombies[i][j];
					matrizZombies[i][j]=PlantAgentState.DESCONOCIDO;
				}
			}
		}
		avanzan=!avanzan;
	}
	
	public boolean exploreTodo() {
		for(int i=0; i<5; i++) {
			if(ultimoExplorado[i]>3) return false;
		}
		return true;
	}
	
	public boolean hayZombiesVistos() {
		for(int i=0; i<5; i++) {
			for(int j=0; j<9; j++) {
				if (matrizZombies[i][j]>0) return true;
			}
		}
		return false;
	}

	public void setCero() {
		ultimoExplorado[posY]=0;
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
		this.setCero();
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


	public Integer[] getUltimoExplorado() {
		return ultimoExplorado;
	}


	public void setUltimoExplorado(Integer[] ultimoExplorado) {
		this.ultimoExplorado = ultimoExplorado;
	}


	public Boolean getPerdi() {
		return perdi;
	}


	public void setPerdi(Boolean perdi) {
		this.perdi = perdi;
	}


	public Boolean getAvanzan() {
		return avanzan;
	}


	public void setAvanzan(Boolean avanzan) {
		this.avanzan = avanzan;
	}
	
	
	

}
