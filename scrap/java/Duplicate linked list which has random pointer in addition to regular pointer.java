// Duplicate linked list which has random pointer in addition to regular pointer
// rond 2, q2, amazon set 3, http://www.geeksforgeeks.org/amazon-interview-set-3/

import java.util.HashMap;
import java.util.Map;

class DuplicatorClass
{
    private Map<Integer, Node> m_valueNode;
    private Node m_head;
    private Node m_dup;

    public DuplicatorClass(Node head)
    {
        m_valueNode = new HashMap<>();
        m_head = head;
        m_dup = null;
    }


    private void duplicator(Node orig, Node dup)
    {
        if (orig.getNext() != null)
        {
            dup.setNext(findNextNode(orig.getNext()));
            duplicator(orig.getNext(), dup.getNext());
        }

        if (orig.getRandNext() != null)
        {
            dup.setRandNext(findNextNode(orig.getRandNext()));
            duplicator(orig.getRandNext(), dup.getRandNext());
        }
    }

    private Node findNextNode(Node orig)
    {
        Node nextNode = m_valueNode.get(orig.getValue());
        if (nextNode == null)
        {
            nextNode = new Node(orig.getNext());
            m_valueNode.put(orig.getValue(), nextNode);
        }

        return nextNode;
    }

    public void duplicator()
    {
        m_dup = new Node(m_head);

        if (m_head.getNext() != null)
        {
            m_dup.setNext(findNextNode(m_head.getNext()));
            duplicator(m_head.getNext(), m_dup.getNext());
        }

        if (m_head.getRandNext() != null)
        {
            m_dup.setRandNext(findNextNode(m_head.getRandNext()));
            duplicator(m_head.getRandNext(), m_dup.getRandNext());
        }
    }
}

class Node
{
    private Node m_next;
    private Node m_randNext;
    private Integer m_value;

    public Node (Node node)
    {
        m_next = null;
        m_randNext = null;
        m_value = node.getValue();
    }

    public Node(Integer m_value) {
        this.m_value = m_value;
        m_next = null;
        m_randNext = null;
    }

    public void setRandNext(Node node)
    {
        m_randNext = node;
    }

    public Node getRandNext()
    {
        return m_randNext;
    }

    public Node getNext() {
        return m_next;
    }

    public void setNext(Node m_next) {
        this.m_next = m_next;
    }

    public Integer getValue() {
        return m_value;
    }

    public void setValue(Integer m_value) {
        this.m_value = m_value;
    }
}