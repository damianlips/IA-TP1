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
	private Integer zombiesVistos;
	private Boolean simulacion= false;
	private Boolean explorando= true;
	public final static int DESCONOCIDO = -1; 
	
	

	
	public PlantAgentState(Integer energia, Integer posX, Integer posY, Integer[][] matrizZombies,
			Integer[][] matrizGirasoles, Integer zombiesRestantes, Integer energiaGastada, Integer movimientos, Integer zombiesVistos) {
		super();
		this.energia = energia;
		this.posX = posX;
		this.posY = posY;
		this.matrizZombies = matrizZombies;
		this.matrizGirasoles = matrizGirasoles;
		this.zombiesRestantes = zombiesRestantes;
		this.energiaGastada = energiaGastada;
		this.movimientos = movimientos;
		this.zombiesVistos= zombiesVistos;
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
		
		return new PlantAgentState(energia, posX, posY, nuevaMatrizZombies, nuevaMatrizGirasoles, zombiesRestantes,energiaGastada, movimientos, zombiesVistos);
	}

	@Override
	public void updateState(Perception p) {
		PlantPerception per = (PlantPerception) p;
		//Primero sensor a la izquierda
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
			zombiesVistos++;
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
			zombiesVistos++;
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
				zombiesVistos++;
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
				zombiesVistos++;
			}
		}
		Boolean[] primerZombie= new Boolean[5];
		for(int i=0;i<5;i++) primerZombie[i]=true;
		for(int i=0;i<5;i++) {
			for(int j=0;j<9;j++) {
				if(i!=posY&&j!=posX) {
					if(matrizZombies[i][j]==Sensor.VACIO) {
					if(j==8) matrizZombies[i][j]=PlantAgentState.DESCONOCIDO;
					else if(matrizZombies[i][j+1]==PlantAgentState.DESCONOCIDO||matrizZombies[i][j+1]>0) 
						matrizZombies[i][j]=PlantAgentState.DESCONOCIDO; 
					}
					else if(j<7) 
						if(j+1==posX&&(matrizZombies[i][j+2]==PlantAgentState.DESCONOCIDO||matrizZombies[i][j+2]>0)) {
						matrizZombies[i][j]=PlantAgentState.DESCONOCIDO; 
						}
				}
				if(!primerZombie[i])
					matrizZombies[i][j]=PlantAgentState.DESCONOCIDO;
				if(primerZombie[i]&&matrizZombies[i][j]>0)
					primerZombie[i]=false;
			}
		}
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String[][] matriz = new String[5][9];
		String string="Energia: " + this.energia+"\n"
		+"Zombies restantes: "+this.getZombiesRestantes()+"\n"
		+"Zombies vistos: "+this.getZombiesVistos()+"\n";
		
		
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
				string+=matriz[i][j] + " ";
			}
			string+="\n";
		}
		
		
		return string;
	}
	
	
	@Override
	public void initState() {
		matrizZombies = new Integer[5][9];
		matrizGirasoles = new Integer[5][9];
		for(int i=0; i<5; i++) {
			for(int j=0; j<9; j++) {
				matrizZombies[i][j]= DESCONOCIDO;
				matrizGirasoles[i][j] = DESCONOCIDO;
			}
		}
		Random rand = new Random();
		energia=rand.nextInt(19)+2; //Genero random entre 0 y (19-1) y sumo 2 para tener entre 2 y 20
		posX=0;
		posY=2;
		zombiesRestantes= rand.nextInt(16)+5;
		energiaGastada=0;
		movimientos=0;
		zombiesVistos=0;
		
		
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
	
	public void setZombiesVistos(Integer zombiesVistos) {
		this.zombiesVistos=zombiesVistos;
	}
	
	public Integer getZombiesVistos() {
		return zombiesVistos;
	}
	
	public Boolean matrizExplorada() {
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				if(matrizZombies[i][j]!=Sensor.VACIO) return false;
			}
		}
		return true;
	}
	public void percepcionFalsa() {
		for(int i =0;i<5;i++) {
			for(int j=0;j<9;j++) {
				if((i==posY||j==posX)&& matrizZombies[i][j]==PlantAgentState.DESCONOCIDO) matrizZombies[i][j]=Sensor.VACIO;
				if(matrizGirasoles[i][j]>=0) matrizGirasoles[i][j]+=1;
				if(matrizZombies[i][j]>0&&j>0) {
					matrizZombies[i][j-1]=matrizZombies[i][j];
					matrizZombies[i][j]=PlantAgentState.DESCONOCIDO;
				}
				}
		}
	}
	public void actualizarZombiesVistos() {
		int zombies=0;
		for (int i=0;i<5;i++) {
			for(int j=0;j<9;j++) {
				if(matrizZombies[i][j]>0)zombies++;
			}
		}
		zombiesVistos=zombies;
	}
	
	public Boolean filaGirasoles() {
		for(int i=0;i<5;i++) {
			if(matrizGirasoles[i][0]<0) return false;
		}
		return true;
	}
	public Boolean isSimulacion() {
		return simulacion;
	}
	public void setSimulacion(Boolean simulacion) {
		this.simulacion= simulacion;
	}
	public void setExplorando(Boolean explorando) {
		this.explorando=explorando;
	}
	public Boolean isExplorando() {
		return explorando;
	}

}
