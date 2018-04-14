package Philosophers;

public class Philosopher implements Runnable {

    private Fork fork_1;
    private Fork fork_2;
    private int eaten;
    private int thought;
    private int timeDinning = 0;
    private int timeThinking = 0;

    public Philosopher(Fork fork1, Fork fork2) {
        fork_1 = fork1;
        fork_2 = fork2;
        eaten = 0;
        thought = 0;
    }

    public void run() {
        while(true) {
            try {
                eat(this.fork_1,this.fork_2);
                finish();
                think();
            } catch (InterruptedException e) {
                return;
            }
            //System.out.println(Thread.currentThread().getName()+" eaten "+eaten);
            this.eaten++;
            this.thought++;
            //System.out.println(Thread.currentThread().getName()+" thought " +thought);

        }
    }

    public boolean eat(Fork fork1, Fork fork2) throws InterruptedException {
        fork_1.get(1);
        if(!fork_2.get(2)){
            fork_1.put();
            //Thread.sleep(0,1);
            eat(fork_2,fork_1);
        }else {
                Thread.sleep(timeDinning);
        }
            return true;
    }

    public void finish() throws InterruptedException {
        fork_1.put();
        fork_2.put();
    }

    public synchronized void think() throws InterruptedException {


            Thread.sleep(timeThinking);
    }

    public String stat () {
        String str = "" + eaten;
        return str;
    }
}
