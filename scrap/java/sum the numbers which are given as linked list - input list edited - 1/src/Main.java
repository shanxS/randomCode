public class Main {

    public static void main(String[] args)
    {
        Number num1 = new Number();
        num1.insert(9);
        num1.insert(9);
        num1.insert(3);
        num1.insert(4);
        num1.insert(5);
        num1.print();


        Number num2 = new Number();
        num2.insert(8);
        num2.insert(9);
        num2.insert(1);
        num2.print();

        Summer summer = new Summer(num1, num2);
        summer.sum().print();
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

    public Node(Integer value, Node next) {
        this.value = value;
        this.next = next;
    }

    public Node(Integer value) {
        this.value = value;
        next = null;
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