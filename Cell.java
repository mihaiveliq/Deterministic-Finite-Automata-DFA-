import java.util.*;

public class Cell {
    String name;
    List<Neighbour> neighbours;
    int index;
    boolean isAccessible;
    boolean isProductive;
    boolean isUseful;
    boolean isFinal;

    Cell(String name) {
        this.name = name;
        neighbours = new ArrayList<>();
        this.isAccessible = false;
        this.isProductive = false;
        this.isUseful = false;
        this.isFinal = false;
    }
}
