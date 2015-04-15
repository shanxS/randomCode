public class Main {

    public static void main(String[] args)
    {
        INormalNumber one = new Number();
        one.insert(9);
        one.insert(9);
        one.insert(3);
        one.insert(4);
        one.insert(5);
        one.print();

        INormalNumber two = new Number();
        two.insert(8);
        two.insert(9);
        two.insert(1);
        two.print();

        Summer summer = new Summer(one, two);
        summer.sum().print();

    }
}

class Node
{
    private Integer value;
    private Node next;

    public Node(Integer value) {
        this.value = value;
        next = null;
    }

    public Node() {
        value = null;
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