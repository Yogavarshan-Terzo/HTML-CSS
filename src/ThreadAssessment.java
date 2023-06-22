class TrainServices{
    int id = 0;
    boolean bookingStarted = false;
    public synchronized void bookTicket(){
        while (bookingStarted){
            try{wait();}catch (Exception e){}
        }
        bookingStarted = true;
        System.out.println(Thread.currentThread().getName() + ": Ticket booked with id :" + id++);
        notify();
        bookingStarted = false;
    }
}
class User implements Runnable{
    String name;
    int age;
    TrainServices trainServices = new TrainServices();

    public User(String name, int age, TrainServices trainServices) {
        this.name = name;
        this.age = age;
        this.trainServices = trainServices;
    }

    public void bookTicket(){
        Thread T = new Thread(this);
        T.start();
    }
    public void run(){
        trainServices.bookTicket();
    }

}
public class ThreadAssessment {
    public static void main(String[] args) {
        TrainServices trainServices = new TrainServices();
        User person1 = new User("yoga",20,trainServices);
        User person2 = new User("yuswanth",20,trainServices);
        person1.bookTicket();
        person2.bookTicket();
    }

}
