public class Main {

    public static void main(String[] args)
    {
        Runner runner = new Runner();
        runner.insertValue(1);
        runner.insertValue(2);
        runner.insertValue(3);
        runner.insertValue(4);
        runner.insertValue(5);
        runner.insertValue(6);
        runner.insertValue(7);
        runner.insertValue(8);
        runner.insertValue(9);

        runner.print();
        runner.run();
        runner.print();
    }
}

class Node
{
    private Integer value;
    private Node next;

    public Node()
    {
        value = null;
        next = null;
    }

    public Node(Integer value) {
        this.value = value;
        this.next = null;
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