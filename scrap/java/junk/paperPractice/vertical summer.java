public class Main
{
    public static void main(String[] er)
    {
        TreeMaker tm = new TreeMaker();
        TNode tHead = tm.make(new Integer[]{10,5,20,4,6,11,21});


        tm.print(tHead);
        System.out.println("--------------");

        VerticalSummer vm = new VerticalSummer();
        LNode lHead = vm.sum(tHead);


        LNode cursor = lHead;
        while (cursor.bwd != null)
        {
            cursor = cursor.bwd;
        }

        while(cursor != null)
        {
            System.out.print(cursor.v + " ");
            cursor = cursor.fwd;
        }
    }
}

class VerticalSummer
{
    public LNode sum(TNode tHead)
    {
        LNode lHead = new LNode (tHead.v);
        sumFor(lHead, tHead);
        return lHead;
    }

    private void sumFor(LNode lNode, TNode tNode)
    {
        if (tNode.left != null)
        {
            if (lNode.bwd != null)
            {
                lNode.bwd.v += tNode.left.v;
                sumFor(lNode.bwd, tNode.left);
            }
            else
            {
                lNode.bwd = new LNode(tNode.left.v);
                lNode.bwd.fwd = lNode;
                sumFor(lNode.bwd, tNode.left);
            }
        }

        if (tNode.right != null)
        {
            if (lNode.fwd != null)
            {
                lNode.fwd.v += tNode.right.v;
            }
            else
            {
                lNode.fwd = new LNode(tNode.right.v);
                lNode.fwd.bwd = lNode;
            }

            sumFor (lNode.fwd, tNode.right);
        }
    }
}

class TreeMaker
{
    public void print(TNode node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.v + " - ");
        if (node.left != null)
        {
            System.out.print(node.left.v);
        }
        System.out.print(", ");
        if (node.right != null)
        {
            System.out.print(node.right.v);
        }
        System.out.println();

        print(node.left);
        print(node.right);
    }


    public TNode make (Integer[] ar)
    {
        TNode head = null;

        for (Integer i : ar)
        {
            if (head == null)
            {
                head = new TNode(i);
            }
            else
            {
                insert(head, i);
            }
        }

        return head;
    }

    private void insert(TNode node, Integer v)
    {
        if (node.v > v)
        {
            if (node.left != null)
            {
                insert(node.left, v);
            }
            else
            {
                node.left = new TNode(v);
            }
        }
        else if(node.v < v)
        {
            if (node.right != null)
            {
                insert(node.right, v);
            }
            else
            {
                node.right = new TNode(v);
            }
        }
    }

}

class LNode
{
    public Integer v;
    public LNode fwd, bwd;

    public LNode(Integer v)
    {
        this.v = v;
    }
}

class TNode
{
    public final Integer v;
    public TNode left, right;

    public TNode(Integer v)
    {
        this.v = v;
    }
}