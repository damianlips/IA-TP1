package etapa_dos;

import java.util.Random;

public class Zombie {
    private int proxMovimiento;
    private int tipoZombie;
    public Zombie() {
    	Random random = new Random();
        proxMovimiento=random.nextInt(3) + 1;
        tipoZombie=random.nextInt(5) + 1;
    }
    public Boolean puedeMoverse() {
        return proxMovimiento==0;
    }
    public void setProxMovimiento(int i) {
        proxMovimiento=i;
    }
    public void decrementarProxMovimiento() {
        proxMovimiento--;
    }
    public int getTipoZombie() {
        return tipoZombie;
    }
    public int getProxMovimiento() {
        return proxMovimiento;
    }
}
