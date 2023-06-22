import java.io.*;

class Main{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/terzo/IdeaProjects/java-streams/src/Home");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        Home yogaHome = new Home(4,4,2);
        objectOutputStream.writeObject(yogaHome);


        FileInputStream fileInputStream = new FileInputStream("/Users/terzo/IdeaProjects/java-streams/src/Home");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);


        yogaHome = (Home) objectInputStream.readObject();
        System.out.println(yogaHome.toString());

    }
}