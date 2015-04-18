// I have used BST to make a tree
// BUT DFS class does not care if we are looking at BST or just a BT
// aint that cool ?

// DFS without using extra memory
// where tree is a 2-ary tree


import java.util.ArrayList;
import java.util.List;

class DepthFirstSearch
{
    private BST m_bst;
    private boolean m_found;

    public DepthFirstSearch(BST bst)
    {
        m_bst = bst;
        m_found = false;
    }

    public List<Integer> getPath(Integer key)
    {
        List<Integer> path = new ArrayList<>();
        Node head = m_bst.getHead();

        getPath(head, key, path);

        return path;
    }

    private void getPath(Node node, Integer key, List<Integer> path)
    {
        if (node.getValue() == key)
        {
            path.add(node.getValue());
            m_found = true;
        }

        if (node.getRight() != null)
        {
            getPath(node.getRight(), key, path);

            if (m_found)
            {
                path.add(node.getValue());
                return;
            }
        }

        if (node.getLeft() != null)
        {
            getPath(node.getLeft(), key, path);

            if (m_found)
            {
                path.add(node.getValue());
                return;
            }
        }
    }
}

class BST
{
    private Node m_head;

    public BST()
    {
        m_head = null;
    }

    public void insert(Integer value)
    {
        if (m_head == null)
        {
            m_head = new Node(value);
        }
        else
        {
            insert (m_head, value);
        }
    }

    public Node getHead()
    {
        return m_head;
    }

    public void print()
    {
        print(m_head);
    }

    private void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " - ");
        if (node.getRight() != null)
        {
            System.out.print(node.getRight().getValue());
        }
        System.out.print(", ");
        if (node.getLeft() != null)
        {
            System.out.print(node.getLeft().getValue());
        }

        System.out.println();

        print(node.getRight());
        print(node.getLeft());
    }

    private void insert(Node node, Integer value)
    {
        if (node.getValue() > value)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), value);
            }
            else
            {
                node.setRight(new Node(value));
            }
        }
        else if (node.getValue() < value)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value);
            }
            else
            {
                node.setLeft(new Node(value));
            }
        }
    }

}