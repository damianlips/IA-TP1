package etapa_dos;

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
	@Override
	public void initState() {
		// TODO Auto-generated method stub
		mapa= new Object[5][9];
		for(int i=0;i<9;i++) {
			for(int j=0;j<5;j++) {
				mapa[j][i]=0;
			}
		}
		energiaAgente=2+(int)Math.random()*18;
		cantZombies=5+(int)Math.random()*15;
		llegoZombie=false;
		siguienteZombie=3+(int)Math.random()*4;
	}
	//Funcion que se ejecuta en cada ciclo accion-percepcion
	public void update() {
		//Recorre la matriz y updatea los zombies y girasoles
		for(int i=0;i<9;i++) {
			for(int j=0;j<5;j++) {
				if(mapa[j][i].getClass()==Zombie.class) {
					((Zombie)mapa[j][i]).decrementarProxMovimiento();
					if(((Zombie)mapa[j][i]).puedeMoverse()) {
						if(i==0) {
							llegoZombie=true;
						}
						else {
							mapa[j][i-1]=mapa[j][i];
							((Zombie)mapa[j][i-1]).setProxMovimiento(1+(int)Math.random()*2);
						}
						mapa[j][i]=0;
					}
				}
				else if(mapa[j][i].getClass()==Girasol.class) {
					((Girasol)mapa[j][i]).agregarSoles(1+(int)Math.random()*2);
				}
			}
		}
		//Generacion de nuevos zombies
		if(cantZombies>0) {
			if(siguienteZombie<=0) {
				int posicion=(int)Math.random()*4;
				mapa[posicion][8]=new Zombie();
				siguienteZombie=3+(int)Math.random()*4;
			}
			else siguienteZombie--;
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
