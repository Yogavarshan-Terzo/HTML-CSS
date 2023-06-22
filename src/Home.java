import java.io.Serializable;

public class Home implements Serializable {
    int chairCount;
    int tableCount;
    int cotCount;

    public Home(int chairCount, int tableCount, int cotCount) {
        this.chairCount = chairCount;
        this.tableCount = tableCount;
        this.cotCount = cotCount;
    }

    @Override
    public String toString() {
        return "Home{" +
                "chairCount=" + chairCount +
                ", tableCount=" + tableCount +
                ", cotCount=" + cotCount +
                '}';
    }
}
