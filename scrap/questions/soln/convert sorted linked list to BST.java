// convert sorted linked list to BST
// code question: 73

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array = {1, 2, 3, 4, 5};
//        Integer[] array = {1, 2, 3, 4, 5, 6, 7};
        Integer[] array = {1, 2, 3, 4};
        LL ll = new LL();
        for (Integer value : array)
        {
            ll.insert(value);
        }

        LLToBST l2b = new LLToBST();
        BST.print(l2b.convert(ll.getHead()));
    }
}


class LLToBST
{
    public TNode convert(LLNode llNode)
    {
        Integer llLenght = computeLength(llNode);
        return makeTree(llNode, 0, llLenght-1);
    }

    private TNode makeTree(LLNode cursor, Integer start, Integer end)
    {
        if (end < start)
        {
            return null;
        }

        Integer mid = (start + end)/2;

        LLNode midLLNode = getLLNodeAt(cursor, mid-start);

        TNode thisHead = new TNode(midLLNode.getValue());
        TNode leftHead = makeTree(cursor, start, mid-1);
        TNode rightHead = makeTree(midLLNode.getNext(), mid+1, end);

        thisHead.setLeft(leftHead);
        thisHead.setRight(rightHead);

        return thisHead;
    }

    private LLNode getLLNodeAt(LLNode cursor, Integer count)
    {
        while(count > 0)
        {
            cursor = cursor.getNext();
            --count;
        }

        return cursor;
    }

    private Integer computeLength(LLNode llNode)
    {
        LLNode cursor = llNode;
        Integer count = 0;
        while(cursor != null)
        {
            ++count;
            cursor = cursor.getNext();
        }

        return count;
    }
}

class BST
{
    private TNode head;

    public BST()
    {
        head = null;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new TNode(value);
        }
        else
        {
            insert(head, value);
        }
    }

    private void insert(TNode node, Integer value)
    {
        if(node.getValue() > value)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value);
            }
            else
            {
                node.setLeft(new TNode(value));
            }
        }
        else if (node.getValue() < value)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), value);
            }
            else
            {
                node.setRight(new TNode(value));
            }
        }
    }

    public static void print(TNode node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() + " - ");
        if (node.getLeft() != null)
        {
            System.out.print(node.getLeft().getValue());
        }
        System.out.print(", ");
        if (node.getRight() != null)
        {
            System.out.print(node.getRight().getValue());
        }
        System.out.println();

        print(node.getLeft());
        print(node.getRight());
    }
}

class LL
{
    private LLNode head;

    public LL()
    {
        head = null;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new LLNode(value);
        }
        else
        {
            LLNode cursor = head;
            while(cursor.getNext() != null)
            {
                cursor = cursor.getNext();
            }

            cursor.setNext(new LLNode(value));
        }
    }

    public static void print(LLNode head)
    {
        LLNode cursor = head;

        while(cursor != null)
        {
            System.out.print(cursor.getValue() + " ");
            cursor = cursor.getNext();
        }
    }

    public LLNode getHead()
    {
        return head;
    }
}

class LLNode
{
    private LLNode next;
    private Integer value;

    public LLNode(Integer value)
    {
        this.value = value;
    }

    public LLNode getNext()
    {
        return next;
    }

    public void setNext(LLNode next)
    {
        this.next = next;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}

class TNode
{
    private TNode left, right;
    private Integer value;

    public TNode(Integer value)
    {
        this.value = value;
    }

    public TNode getLeft()
    {
        return left;
    }

    public void setLeft(TNode left)
    {
        this.left = left;
    }

    public TNode getRight()
    {
        return right;
    }

    public void setRight(TNode right)
    {
        this.right = right;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}