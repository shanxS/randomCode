public class Main {

    public static void main(String[] args)
    {
        NodeList nodeList = new NodeList();
        nodeList.insert(1);
        nodeList.insert(2);
        nodeList.insert(3);
        nodeList.insert(4);
        nodeList.insert(5);

        Remover remover = new Remover(nodeList);
        remover.remove(1).print();
    }
}

class Node
{
    private Integer value;
    private Node next;

    public Node() {
        value = null;
        next = null;
    }

    public Node(Integer value) {
        this.value = value;
        next = null;
    }

    public Node(Integer value, Node next) {
        this.value = value;
        this.next = next;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}