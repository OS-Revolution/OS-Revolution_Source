package ethos.scaperune.entity.node;

public class Node {



    public int getId() {
        return id;
    }

    protected Node(int id) {
        this.id = id;
    }

    protected final int id;
}
