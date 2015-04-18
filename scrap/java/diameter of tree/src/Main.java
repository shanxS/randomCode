public class Main {

    public static void main(String[] args)
    {
        BST bst = new BST();
        bst.insert(6);
        bst.insert(7);
        bst.insert(4);
        bst.insert(5);
        bst.insert(1);
        bst.insert(3);

        bst.print();

        TreeDiameter td = new TreeDiameter(bst);
        System.out.println("diameter " + td.getDiameter());
    }
}


class Node
{
    private Integer m_value;
    private Node m_right;
    private Node m_left;

    public Node(Integer value)
    {
        m_value  = value;
        m_right = null;
        m_left = null;
    }

    public Integer getValue() {
        return m_value;
    }

    public void setValue(Integer m_value) {
        this.m_value = m_value;
    }

    public Node getRight() {
        return m_right;
    }

    public void setRight(Node m_right) {
        this.m_right = m_right;
    }

    public Node getLeft() {
        return m_left;
    }

    public void setLeft(Node m_left) {
        this.m_left = m_left;
    }
}