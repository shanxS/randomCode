// round 1, question 2, amazon set 3, geek for geeks
// http://www.geeksforgeeks.org/amazon-interview-set-3/

// Created by shanxS on 4/11/2015.

public  class Summer {

    INormalNumber m_one;
    INormalNumber m_two;
    IReverseNumber m_result;

    public Summer (INormalNumber one, INormalNumber two)
    {
        m_one = one;
        m_two = two;
        m_result = new Number();
    }

    public Number sum ()
    {
        Integer carry = sum(m_one.getNode(), m_one.getLength()-1, m_two.getNode(), m_two.getLength()-1);
        if (carry >= 10)
        {
            m_result.insertTail(carry/10);
            m_result.insertTail(carry%10);
        }
        else
        {
            m_result.insertTail(carry);
        }

        return (Number)m_result;
    }

   private Integer sum (Node one, Integer oneLength, Node two, Integer twoLength)
    {
        Integer carry = 0;
        if (oneLength > twoLength)
        {
            carry = sum(one.getNext(), oneLength-1, two, twoLength);
        }
        else if (oneLength < twoLength)
        {
            carry = sum(one, oneLength, two.getNext(), twoLength-1);
        }
        else if (oneLength == twoLength && oneLength != 0)
        {
            carry = sum(one.getNext(), oneLength-1, two.getNext(), twoLength-1);
        }

        Integer value = 0;
        if (twoLength == oneLength) {
            value = one.getValue() + two.getValue() + carry;
        }
        else if (twoLength > oneLength)
        {
            value = two.getValue() + carry;
        }
        else
        {
            value = one.getValue() + carry;
        }

        if (value >= 10)
        {
            carry = value/10;
            value %= 10;
        }

        m_result.insertTail(value);
        return carry;
    }

}

interface INormalNumber extends INumberCommon
{
    public void insert(Integer value);
}

interface INumberCommon
{
    public void print();
    public Node getNode();
    public Integer getLength();
}

interface IReverseNumber extends INumberCommon
{
    public void insertTail(Integer value);
}

class Number implements INormalNumber, IReverseNumber
{
    private Node m_head;

    public Number()
    {
        m_head = null;
    }

    @Override
    public void insertTail(Integer value)
    {
        if (m_head == null)
        {
            m_head = new Node(value);
        }
        else
        {
            Node node = new Node(value);
            node.setNext(m_head);
            m_head = node;
        }
    }

    @Override
    public void insert(Integer value)
    {
        if (m_head == null)
        {
            m_head = new Node(value);
        }
        else
        {
            Node cursor = m_head;
            while (cursor.getNext() != null)
            {
                cursor = cursor.getNext();
            }

            cursor.setNext(new Node(value));
        }
    }

    @Override
    public void print()
    {
        Node cursor = m_head;
        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }

        System.out.println();
    }

    @Override
    public Node getNode() {
        return m_head;
    }

    @Override
    public Integer getLength() {
        Integer length = 0;

        Node cursor = m_head;
        while(cursor != null)
        {
            ++length;
            cursor = cursor.getNext();
        }

        return length;
    }
}