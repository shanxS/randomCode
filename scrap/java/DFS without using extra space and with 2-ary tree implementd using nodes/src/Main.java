import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        BST bst = new BST();
        bst.insert(6);
        bst.insert(4);
        bst.insert(7);
        bst.insert(1);
        bst.insert(5);
        bst.insert(3);

        bst.print();

        DepthFirstSearch dfs = new DepthFirstSearch(bst);
        List<Integer> path = dfs.getPath(10);
        if (path.size() == 0)
        {
            System.out.println("not found");
        }
        else
        {
            System.out.println("path ");
            for (Integer value : path) {
                System.out.print(value + " ");
            }
            System.out.println();
        }

    }
}

class Node
{
    private Integer m_value;
    private Node m_left, m_right;

    public Node(Integer value)
    {
        m_value = value;
        m_left = null;
        m_right = null;
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
