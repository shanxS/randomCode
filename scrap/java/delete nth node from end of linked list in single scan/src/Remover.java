// q1, round 2, set 3, amazon, geek for geeks
// http://www.geeksforgeeks.org/amazon-interview-set-3/

// Created by shanxS on 4/11/2015.

public class Remover
{
    Node m_head;

    public Remover (NodeList nodeList)
    {
        m_head = nodeList.getHead();
    }

    public NodeList remove(Integer n)
    {
        remove(m_head, n);

        return new NodeList(m_head);
    }

    private Integer remove(Node node, Integer n) {
        if (node.getNext() != null)
        {
            n = remove(node.getNext(), n);
        }
        else if (node.getNext() == null)
        {
            --n;
            return n;
        }

        if (n == -1)
        {
            node.setNext(node.getNext().getNext());
        }

        --n;
        return n;
    }
}

class NodeList
{
    private Node m_head;

    public NodeList(Node node)
    {
        m_head = node;
    }

    public NodeList()
    {
        m_head = null;
    }

    public Node getHead()
    {
        return m_head;
    }

    public void insert(Integer value)
    {
        if (m_head == null)
        {
            m_head = new Node(value);
        }
        else
        {
            Node cursor = m_head;
            while(cursor.getNext() != null)
            {
                cursor = cursor.getNext();
            }

            cursor.setNext(new Node(value));
        }
    }

    public void print()
    {
        Node cursor = m_head;
        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
    }
}