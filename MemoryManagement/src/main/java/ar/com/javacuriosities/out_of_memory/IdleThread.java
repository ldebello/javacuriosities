package ar.com.javacuriosities.out_of_memory;

/*
 * Creamos un Thread que solo espera
 */
public class IdleThread extends Thread {

    private Object task = null;

    public IdleThread() {
    }

    @Override
    public void run() {
        // Esperamos indefinidamente
        waitForTask();
    }

    private synchronized void waitForTask() {
        while (task == null) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
    }

    public Object getTask() {
        return task;
    }

    public void setTask(Object task) {
        this.task = task;
    }
}
