package etapa_dos;

public class Girasol {
    private int cantSoles;
    public Girasol() {
        cantSoles=0;
    }
    public void agregarSoles(int i) {
        cantSoles+=i;
    }
    public void setCantSoles(int i) {
        cantSoles=i;
    }
    public int getCantSoles() {
        return cantSoles;
    }
}