package etapa_dos;

public class Sensor {
	public Sensor() {
	}
	
	public final static int VACIO = 0;
	public final static int ZOMBIE = 1;
	public final static int GIRASOL = 2; 

	public int tipo; 
	public int energia;
	public int distancia;
	@Override
	public String toString() {
		return "Sensor [tipo=" + tipo + ", energia=" + energia + ", distancia=" + distancia + "]";
	}
}
