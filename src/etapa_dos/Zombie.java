package etapa_dos;

public class Zombie {
    private int proxMovimiento;
    private int tipoZombie;
    public Zombie() {
        proxMovimiento=1+(int)(Math.random()*2);
        tipoZombie=1+(int)(Math.random()*4);
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
