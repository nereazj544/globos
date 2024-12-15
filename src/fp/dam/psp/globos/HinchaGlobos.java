package fp.dam.psp.EXAMENES.Ev1_ex2_NereaZJ.globosexamen.src.fp.dam.psp.globos;

import static fp.dam.psp.EXAMENES.Ev1_ex2_NereaZJ.globosexamen.src.fp.dam.psp.globos.EstadoGlobo.*;

public class HinchaGlobos extends HiloPausable {

    private Deposito deposito;

    public HinchaGlobos(int id, Deposito deposito) {
        super("HG " + id);
        this.deposito = deposito;
    }

    @Override
    protected void tarea() {
        Globo globo = deposito.getDeshinchado();
        if (globo != null)
        while (globo.getEstado() == HINCHANDO) {
            checkPausa();
            globo.hinchar();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                interrupt();
                return;
            }
        }
        deposito.retirar(globo);
    }
}
