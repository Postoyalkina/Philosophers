package Philosophers;

public class Fork {
    private static int I = 0;
    private int id = ++I;
    private boolean inUse;
    private boolean write = false;
    private static boolean[] mas = new boolean[5];
    private int a;

    public Fork(){
        inUse = false;
        for(int i = 0; i< mas.length;i++) {
            mas[i] = false;
        }

    }

    public synchronized boolean get (int n) throws InterruptedException {
        a = 0;
        for (int i = 0; i < mas.length; i++) {
            if (mas[i]) {
                a += 1;
            }
        }
        if(a == mas.length) {
            return false;
        }else {
            while (inUse) {
                wait();
            }
            if(n==1) {
                mas[id - 1] = true;
                inUse = mas[id - 1];
            }
            if(n==2) {
                inUse = true;
            }
            if (write && id == 1) {
                System.out.println(Thread.currentThread().getName() + " get fork " + id);
            }
            notify();
            return true;
        }
    }

    public synchronized void put () throws InterruptedException {

        while (!inUse) {
            wait();
        }
        mas[id-1] = false;
        inUse = mas[id-1];
        if(write && id == 1) {
            System.out.println(Thread.currentThread().getName() + " put fork " + id);
        }
        notify();

    }
}
