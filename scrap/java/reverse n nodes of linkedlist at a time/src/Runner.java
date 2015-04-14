// You are given a linked list and an integer k. Reverse every consecutive k nodes of the given linked list.
// q3, round 3, set 2 amazon, geek for geeks

// Created by shanxS on 4/11/2015.
public class Runner {

    Node m_head;

    public Runner()
    {
        m_head = null;
    }

    public void insertValue(Integer value)
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

    public void run()
    {
        m_head = reverse(m_head);
    }

    private Node reverse(Node head) {

        Integer count = 4;

        Node cursor = head;
        if (cursor.getNext() == null)
        {
            return cursor;
        }

        Node cursorNext = cursor.getNext();
        if (cursorNext.getNext() == null)
        {
            cursorNext.setNext(cursor);
            cursor.setNext(null);
            return cursorNext;
        }

        Node cursorNextNext = null;

        while (count > 1 && cursorNext != null)
        {
            cursorNextNext = cursorNext.getNext();

            cursorNext.setNext(cursor);

            cursor = cursorNext;
            cursorNext = cursorNextNext;

            --count;
        }


        if (cursorNext != null)
        {
            Node temp = reverse(cursorNext);
            head.setNext(temp);
        }
        else
        {
            head.setNext(null);
        }

        return cursor;

    }

    public void print()
    {
        int count = 20;
        Node cursor = m_head;
        while(cursor != null && count > 0)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();

            --count;
        }

        System.out.println();
    }

}

