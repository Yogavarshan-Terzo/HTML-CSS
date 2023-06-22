class Q{
    int num;

    boolean valueSet = false;
    public synchronized void put(int i){
        while (valueSet){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        num = i;
        System.out.println("Put : " + num);
        valueSet = true;
        notify();
    }

    public synchronized void get(){
        while (!valueSet){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Get : " + num);
        valueSet = false;
        notify();
    }
}

class Producer implements Runnable{
    Q q = new Q();

    public Producer(Q q) {
        this.q = q;
        Thread t = new Thread(this);
        t.start();
    }

    public void run(){
        int i = 0;
        while(true){
            q.put(i++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumer implements Runnable{
    Q q = new Q();

    public Consumer(Q q) {
        this.q = q;
        Thread t = new Thread(this);
        t.start();
    }

    public void run(){
        while (true){
            q.get();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
public class InterThreadComm {

    public static void main(String[] args) {
        Q q = new Q();
        Producer p = new Producer(q);
        Consumer c = new Consumer(q);
    }

}
