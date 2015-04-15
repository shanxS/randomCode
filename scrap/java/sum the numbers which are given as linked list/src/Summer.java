// q1, round1, set 3, amazon, geek for geeks
// http://www.geeksforgeeks.org/amazon-interview-set-3/

// Created by shanxS on 4/11/2015.

public  class Summer {
    private Number m_one;
    private Number m_two;

    public Summer (Number one, Number two)
    {
        m_one = one;
        m_two = two;
    }

    public Number sum()
    {
        m_one.reverse();
        m_two.reverse();

        Node one = m_one.getHead();
        Node two = m_two.getHead();

        Number result = new Number();
        Integer carry = 0;
        while(one != null || two != null)
        {
            Integer v1 = (one != null) ? one.getValue() : 0;
            Integer v2 = (two != null) ? two.getValue() : 0;

            Integer v = v1 + v2 + carry;
            if (v >= 10)
            {
                carry = v/10;
                v %= 10;
            }

            result.insert(v);

            if (one != null)
            {
                one = one.getNext();
            }

            if (two != null)
            {
                two = two.getNext();
            }
        }

        if (carry > 0) {

            if (carry/10 > 0)
            {
                result.insert(carry%10);
                result.insert(carry/10);
            }
            else
            {
                result.insert(carry);
            }
        }

        result.reverse();
        return result;


    }
}

class Number {

    private Node m_head;

    public Number()
    {
        m_head = null;
    }

    public Node getHead()
    {
        return m_head;
    }

    public void reverse()
    {
        if (m_head == null)
        {
            return;
        }

        Node cursor = m_head;
        if (cursor.getNext() == null)
        {
            m_head =  cursor;
        }

        Node cursorNext = cursor.getNext();
        if (cursorNext.getNext() == null)
        {
            cursor.setNext(null);
            cursorNext.setNext(cursor);
            cursor = cursorNext;
            m_head = cursor;
        }

        Node cursorNextNext = cursorNext.getNext();
        cursor.setNext(null);
        cursorNext.setNext(cursor);


        while (cursorNextNext != null)
        {
            cursor = cursorNext;
            cursorNext = cursorNextNext;
            cursorNextNext = cursorNextNext.getNext();
            cursorNext.setNext(cursor);
        }

        m_head = cursorNext;
    }

    public void insert(Integer v)
    {
        if (m_head == null)
        {
            m_head = new Node(v);
        }
        else
        {
            Node cursor = m_head;

            while (cursor.getNext() != null)
            {
                cursor = cursor.getNext();
            }

            cursor.setNext(new Node(v));
        }
    }

    void print()
    {
        Node cursor = m_head;

        while (cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }

        System.out.println();
    }


}

