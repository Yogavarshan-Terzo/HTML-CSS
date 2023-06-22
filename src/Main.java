class Counter{
    int count = 0;
    public synchronized void increment(){
        count++;
    }
}
class Main{

    public static void main(String[] args) throws Exception {
        Counter c = new Counter();
        Runnable obj1 = () -> {
            for(int i = 0; i < 1000; i++){
                c.increment();
            }
        };
        Runnable obj2 = ()->{
            for(int i = 0; i < 1000; i++){
                c.increment();
            }
        };

        Thread t1 = new Thread(obj1);
        Thread t2 = new Thread(obj2);


        t1.start();
        t2.start();


        t1.join();
        t2.join();

        System.out.println(c.count);




    }
}
//class A{
//    public void run(){
//        for(int i = 0; i < 10; i++){
//            System.out.println("Hi");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}
//class B implements Runnable{
//    public void run(){
//        for(int i = 0; i < 10; i++){
//            System.out.println("Hello");
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}