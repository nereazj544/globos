package fp.dam.psp.globos;

public abstract class HiloPausable extends Thread{

    protected boolean pausado;

    public HiloPausable(String nombre) {
        super(nombre);
    }

    public synchronized void pausaOnOff() {
        pausado = !pausado;
        if (!pausado)
            notifyAll();
    }

    protected synchronized void checkPausa() {
        if (pausado) {
            try {
                wait();
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            checkPausa();
            if (!isInterrupted())
                tarea();
        }
    }

    protected abstract void tarea();
}
