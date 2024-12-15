package fp.dam.psp.EXAMENES.Ev1_ex2_NereaZJ.globosexamen.src.fp.dam.psp.globos;

import java.util.concurrent.Semaphore;

public abstract class HiloPausable extends Thread{

//    protected boolean pausado;

    private  Semaphore pausado;
    private volatile boolean ej;

    public HiloPausable(String nombre) {
        super(nombre);
        this.pausado = new Semaphore(1);
        this.ej = false;

    }

    public synchronized void pausaOnOff() {
        ej = !ej;
        if (ej){
            try {
                pausado.acquire();
            }catch (InterruptedException e){
                e.getMessage();
                Thread.interrupted();
            }
        }



        //        pausado.acquire();


//        try {
//            pausado.acquire();
//            ej = !ej;
//            if (ej){
//                notifyAll();
//            }
//            pausado.release();
//
//        }catch (InterruptedException e){
//            interrupt();
//            e.getMessage();
//        }
//        pausado = !pausado;
//        if (!pausado)
//            notifyAll();
//        pausado.release();
    }

    protected synchronized void checkPausa() {
        try {
            pausado.acquire();
            pausado.release();

        }catch (InterruptedException e){
            Thread.interrupted();
            e.getMessage();
        }

        /*
        if (pausado) {
            try {
                wait();
            } catch (InterruptedException e) {
                interrupt();
            }
        }

         */
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
