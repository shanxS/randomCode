import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        BST bst = new BST();
        bst.insert(6);
        bst.insert(4);
        bst.insert(7);
        bst.insert(1);
        bst.insert(10);
        bst.insert(5);
        bst.insert(3);

        BreadthFirstSearch bfs = new BreadthFirstSearch(bst);
        List<Integer> path = bfs.getPath(3);
        if (path == null)
        {
            System.out.println("value not found");
        }
        else
        {
            System.out.println("path from value to root");
            for (Integer value : path)
            {
                System.out.print(value + " ");
            }
            System.out.println();
        }

        bst.print();

    }
}

class Node
{
    private Integer m_value;
    private Node m_left, m_right;
    boolean m_flag;

    public Node(Integer value)
    {
        m_value = value;
        m_left = null;
        m_right = null;
        m_flag = false;
    }

    public boolean isFlag() {
        return m_flag;
    }

    public void setFlag(boolean m_flag) {
        this.m_flag = m_flag;
    }

    public Integer getValue() {
        return m_value;
    }

    public void setValue(Integer m_value) {
        this.m_value = m_value;
    }

    public Node getLeft() {
        return m_left;
    }

    public void setLeft(Node m_left) {
        this.m_left = m_left;
    }

    public Node getRight() {
        return m_right;
    }

    public void setRight(Node m_right) {
        this.m_right = m_right;
    }
}
