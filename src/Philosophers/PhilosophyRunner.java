package Philosophers;

public class PhilosophyRunner {
    public static void main(String[] args) throws InterruptedException {
        int countPh = 5;

        Fork f[] = new Fork[countPh];
        for (int i = 0; i < countPh; i++) {
            f[i] = new Fork();
        }

        Thread t[] = new Thread[countPh];
        final Philosopher p[] = new Philosopher[countPh];
        for (int i = 0; i < countPh; i++) {
            if (i == 0) {
                p[i] = new Philosopher(f[countPh-1],f[i]);
            }else {
                p[i] = new Philosopher(f[i-1],f[i]);
            }
            t[i] = new Thread(p[i], "Philosopher-" + (i+1));
        }

        for (int i = 0; i< countPh;i++) {

            t[i].start();
        }

        /*Thread t1 = new Thread(new Philosopher(f[6], f[0]), "Philosopher-1");
        Thread t2 = new Thread(new Philosopher(f[0], f[1]), "Philosopher-2");
        Thread t3 = new Thread(new Philosopher(f[2], f[1]), "Philosopher-3");
        Thread t4 = new Thread(new Philosopher(f[2], f[3]), "Philosopher-4");
        Thread t5 = new Thread(new Philosopher(f[4], f[3]), "Philosopher-5");
        Thread t6 = new Thread(new Philosopher(f[4], f[5]), "Philosopher-6");
        Thread t7 = new Thread(new Philosopher(f[6], f[5]), "Philosopher-7");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();*/

        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) { }

        /*t1.stop();
        t2.stop();
        t3.stop();
        t4.stop();
        t5.stop();
        t6.stop();
        t7.stop();*/
        for (int i = 0; i < countPh; i++) {
            t[i].interrupt();
            t[i].join();
        }

        for (int i = 0; i < countPh; i++) {
            System.out.println("Philosopher-" + (i+1) + " eaten " + p[i].stat());
        }

        System.out.println("Main thread finished!");
    }
}
