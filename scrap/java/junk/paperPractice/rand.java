public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {1,2};
        Node head = null, cursor = null;
        for(Integer i : ar)
        {
            if (head == null)
            {
                head = new Node(i);
                cursor = head;
            }
            else
            {
                cursor.next = new Node(i);
                cursor = cursor.next;
            }
        }

        cursor = head;
        while (cursor != null)
        {
            System.out.print(cursor.v + " ");
            cursor = cursor.next;
        }

        LLReverser rev = new LLReverser();
        head = rev.reverse(head);

        cursor = head;
        System.out.println("-------");
        while (cursor != null)
        {
            System.out.print(cursor.v + " ");
            cursor = cursor.next;
        }

    }
}

class LLReverser
{
    public Node reverse(Node head)
    {
        if (head == null || head.next == null)
        {
            return head;
        }

        Node prev = null;
        Node cur = head;
        Node next = cur.next;

        while (cur != null)
        {
            cur.next = prev;
            prev = cur;
            cur = next;

            if (next != null)
            {
                next = next.next;
            }
        }

        return prev;
    }
}

class Node
{
    final Integer v;
    Node next;

    public Node (Integer v)
    {
        this.v = v;
    }
}
