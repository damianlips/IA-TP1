package etapa_dos;

import javax.swing.JFrame;
import javax.swing.JPanel;

import etapa_tres.Graficador;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class PlantEnvironmentState extends EnvironmentState {
	//Matriz objeto del mapa
	private Object[][] mapa;
	//Cantidad de zombies que quedan
	private int cantZombies;
	//Energia que tiene el agente
	private int energiaAgente;
	//Boolean que se vuelve verdadero cuando un zombie llego a la casa
	private Boolean llegoZombie;
	//Tiempo hasta que aparezca el proximo zombie
	private int siguienteZombie;
	//Posicion del agente
	private int agentX;
	private int agentY;
	private Graficador graficador = new Graficador();
	private JFrame frame = new JFrame("Image display");
	
	
	@Override
	public void initState() {
		// TODO Auto-generated method stub
		mapa= new Object[5][9];
		for(int i=0;i<9;i++) {
			for(int j=0;j<5;j++) {
				mapa[j][i]=0;
			}
		}
		energiaAgente=2+(int)(Math.random()*18);
		cantZombies=5+(int)(Math.random()*15);
		llegoZombie=false;
		siguienteZombie=3+(int)(Math.random()*4);
		agentX=0;
		agentY=2;
		
	}
	
	public void zombiesDeInicio() {
		for(int i=0 ; i<5 ; ++i) {
			int rand = (int)(Math.random()*13);
			if(rand<2 && cantZombies>0) {
				mapa[i][8]=new Zombie();
				--cantZombies;
			}
		}
	}
	
	
	//Funcion que se ejecuta en cada ciclo accion-percepcion
	public void update() {
		//Recorre la matriz y updatea los zombies y girasoles
		for(int i=0;i<9;i++) {
			for(int j=0;j<5;j++) {
				if(mapa[j][i] instanceof Zombie) {
					((Zombie)mapa[j][i]).decrementarProxMovimiento();
					if(((Zombie)mapa[j][i]).puedeMoverse()) {
						if(i==0) {
							llegoZombie=true;
							mapa[j][i]=0;
						}
						else {
							if(mapa[j][i-1] instanceof Zombie) {
								((Zombie)mapa[j][i]).setProxMovimiento(1);
							}
							else {
								mapa[j][i-1]=mapa[j][i];
								mapa[j][i]=0;
								((Zombie)mapa[j][i-1]).setProxMovimiento(1+(int)(Math.random()*2));
							}						
							
						}
					}
				}
				else if(mapa[j][i] instanceof Girasol) {
					((Girasol)mapa[j][i]).agregarSoles(1+(int)(Math.random()*2));
				}
			}
		}
		//Generacion de nuevos zombies
		if(cantZombies>0) {
			if(siguienteZombie<=0) {
				int posicion=(int)(Math.random()*4);
				mapa[posicion][8]=new Zombie();
				siguienteZombie=3+(int)(Math.random()*4);
				--cantZombies;
			}
			else siguienteZombie--;
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		String[][] matriz = new String[5][9];

		StringBuilder str = new StringBuilder();
		str.append(System.getProperty("line.separator"));
		str.append("Zombies restantes: "+cantZombies);
		str.append(System.getProperty("line.separator"));
		for(int i=0; i<5; i++) 	for(int j=0; j<9; j++) matriz[i][j] = "VV";
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<9; j++) {
				if(mapa[i][j] instanceof Zombie) {
					matriz[i][j] = "Z" + ((Zombie)mapa[i][j]).getTipoZombie();
				}
				else if(mapa[i][j] instanceof Girasol) {
					matriz[i][j] = "G" + ((Girasol)mapa[i][j]).getCantSoles();
				}
			}
		}
		matriz[agentY][agentX] = "Pl";
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<9; j++) {
				//System.out.print(matriz[i][j] + " ");
				str.append(matriz[i][j] + " ");
			}
			//System.out.println();
			str.append(System.getProperty("line.separator"));
		}
		JPanel panel = this.graficador.graficar(matriz);
//		frame.add(panel);
//        frame.setSize(900,550);
//        frame.setVisible(true);
		return str.toString();
	}
	public Boolean getLlegoZombie() {
		return llegoZombie;
	}
	public void setLlegoZombie(Boolean llegoZombie) {
		this.llegoZombie = llegoZombie;
	}
	public int getAgentX() {
		return agentX;
	}
	public void setAgentX(int agentX) {
		this.agentX = agentX;
	}
	public int getAgentY() {
		return agentY;
	}
	public void setAgentY(int agentY) {
		this.agentY = agentY;
	}
	
	public Sensor getIzquierda(int x, int y){
		Sensor sensor = new Sensor();
		sensor.tipo= Sensor.VACIO;
		sensor.distancia=x;
		for(int i=(x-1) ; i>=0 ; --i) {
			if(mapa[y][i] instanceof Zombie) {
				sensor.tipo=1;
				sensor.distancia=(x-i);
				sensor.energia = ((Zombie)mapa[y][i]).getTipoZombie();
				i=-1;
			}
			else if(mapa[y][i] instanceof Girasol) {
				sensor.tipo=2;
				sensor.distancia=(x-i);
				sensor.energia = ((Girasol)mapa[y][i]).getCantSoles();
				i=-1;
			}
		}
		return sensor;
	}
	
	public Sensor getDerecha(int x, int y){
		Sensor sensor = new Sensor();
		sensor.tipo= Sensor.VACIO;
		sensor.distancia=8-x;
		for(int i=(x+1) ; i<9 ; ++i) {
			if(mapa[y][i] instanceof Zombie) {
				sensor.tipo=1;
				sensor.distancia=(i-x);
				sensor.energia = ((Zombie)mapa[y][i]).getTipoZombie();
				i=10;
			}
			else if(mapa[y][i] instanceof Girasol) {
				sensor.tipo=2;
				sensor.distancia=(i-x);
				sensor.energia = ((Girasol)mapa[y][i]).getCantSoles();
				i=10;
			}
		}
		return sensor;
	}
	
	public Sensor getArriba(int x, int y){
		Sensor sensor = new Sensor();
		sensor.tipo= Sensor.VACIO;
		sensor.distancia=y;
		for(int i=(y-1) ; i>=0 ; --i) {
			if(mapa[i][x] instanceof Zombie) {
				sensor.tipo=1;
				sensor.distancia=(y-i);
				sensor.energia = ((Zombie)mapa[i][x]).getTipoZombie();
				i=-1;
			}
			else if(mapa[i][x] instanceof Girasol) {
				sensor.tipo=2;
				sensor.distancia=(y-i);
				sensor.energia = ((Girasol)mapa[i][x]).getCantSoles();
				i=-1;
			}
		}
		return sensor;
	}
	
	public Sensor getAbajo(int x, int y){
		Sensor sensor = new Sensor();
		sensor.tipo= Sensor.VACIO;
		sensor.distancia=4-y;
		for(int i=(y+1) ; i<5 ; ++i) {
			if(mapa[i][x] instanceof Zombie) {
				sensor.tipo=Sensor.ZOMBIE;
				sensor.distancia=(i-y);
				sensor.energia = ((Zombie)mapa[i][x]).getTipoZombie();
				i=6;
			}
			else if(mapa[i][x] instanceof Girasol) {
				sensor.tipo=Sensor.GIRASOL;
				sensor.distancia=(i-y);
				sensor.energia = ((Girasol)mapa[i][x]).getCantSoles();
				i=6;
			}
		}
		return sensor;
	}
	public int getEnergiaAgente() {
		return energiaAgente;
	}
	public void setEnergiaAgente(int energiaAgente) {
		this.energiaAgente = energiaAgente;
	}
	public Object[][] getMapa() {
		return mapa;
	}
	public void setMapa(Object[][] mapa) {
		this.mapa = mapa;
	}
	public int getCantZombies() {
		return cantZombies;
	}
	public void setCantZombies(int cantZombies) {
		this.cantZombies = cantZombies;
	}
	public int getSiguienteZombie() {
		return siguienteZombie;
	}
	public void setSiguienteZombie(int siguienteZombie) {
		this.siguienteZombie = siguienteZombie;
	}
	

}
