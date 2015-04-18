// I have used BST to make a tree
// BUT BFS class does not care if we are looking at BST or just a BT
// aint that cool ?

// BFS without using extra memory
// where tree is a 2-ary tree
// very high time complexity

import java.util.ArrayList;
import java.util.List;

class BreadthFirstSearch
{
    private BST m_bst;
    private boolean m_isFound;
    private Node m_head;
    private boolean m_allRightFlagged, m_allLeftFlagged;

    public BreadthFirstSearch(BST bst)
    {
        m_bst = bst;
        m_isFound = false;
        m_head = bst.getHead();
        m_allRightFlagged = false;
        m_allLeftFlagged = false;
    }

    public List<Integer> getPath(Integer key)
    {
        List<Integer> path = new ArrayList<>();
        if (m_head.getValue() == key)
        {
            path.add(m_head.getValue());
            return path;
        }

        getPath(m_head, key, path);
        if (path.size() > 0)
        {
            path.add(m_head.getValue());
            return path;
        }
        else
        {
            return null;
        }
    }

    private void getPath(Node head, Integer key, List<Integer> path)
    {
        // evaluate for right node
        if (head.getRight() != null)
        {
            Node right = head.getRight();
            if (right.isFlag())
            {
                m_allRightFlagged = true;

                getPath(right, key, path);
                if (m_isFound)
                {
                    path.add(right.getValue());
                    return;
                }

            }
            else
            {
                m_allRightFlagged = false;

                if (right.getValue() == key)
                {
                    path.add(right.getValue());
                    m_isFound = true;
                    return;
                }
                else
                {
                    right.setFlag(true);
                }
            }
        }

        // evaluate for left node
        if (head.getLeft() != null)
        {
            Node left = head.getLeft();
            if (left.isFlag())
            {
                m_allLeftFlagged = true;

                getPath(left, key, path);
                if (m_isFound)
                {
                    path.add(left.getValue());
                    return;
                }
            }
            else
            {
                m_allLeftFlagged = false;

                if (left.getValue() == key)
                {
                    m_isFound = true;
                    path.add(left.getValue());
                    return;
                }
                else
                {
                    left.setFlag(true);
                }

            }
        }

        if (m_allRightFlagged && m_allLeftFlagged)
        {
            return;
        }

        if (!m_isFound && head.getValue() == m_head.getValue())
        {
            getPath(head, key, path);
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