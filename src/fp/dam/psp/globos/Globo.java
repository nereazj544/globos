package fp.dam.psp.EXAMENES.Ev1_ex2_NereaZJ.globosexamen.src.fp.dam.psp.globos;
import static fp.dam.psp.EXAMENES.Ev1_ex2_NereaZJ.globosexamen.src.fp.dam.psp.globos.EstadoGlobo.*;

public class Globo {

    private EstadoGlobo estado = DESHINCHADO;
    private int volMax;
    private int volumen;
    private Consola consola;
    private String nombre;

    public Globo(int id, int volMax, Consola consola) {
        nombre = "GLOBO " + id;
        this.volMax = volMax;
        this.consola = consola;
    }

    public String getNombre() {
        return nombre;
    }

    public EstadoGlobo getEstado() {
        return estado;
    }

    public synchronized void hinchar() {
        volumen++;
        if (volumen > volMax) {
            estado = EXPLOTADO;
            consola.println(nombre + " " + estado);
            consola.actualizarGlobosExplotados();
        }
        else
            consola.println(nombre + " " + estado + " " + volumen);
    }

    public synchronized void pinchar() {
        volumen = 0;
        estado = PINCHADO;
        consola.println(nombre + " " + estado + " POR " + Thread.currentThread().getName());
        consola.actualizarGlobosPinchados();
    }

    public void setHinchando() {
        estado = HINCHANDO;
    }
}
