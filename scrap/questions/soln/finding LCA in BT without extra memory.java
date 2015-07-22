// finding LCA in BT without extra memory
// code question: 134

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {32, 24, 41, 21, 28, 36, 14, 25, 31, 39};
        BST bst = new BST();
        for (Integer i : ar)
        {
            bst.insert(i);
        }
        bst.print(bst.getHead());
        LCA lca = new LCA();
//        System.out.print(lca.find(bst.getHead(), 28, 14));
//        System.out.print(lca.find(bst.getHead(), 28, 31));
//        System.out.print(lca.find(bst.getHead(), 28, 39));
        System.out.print(lca.find(bst.getHead(), 24, 39));


    }
}


class LCA
{
    private boolean target1Found;
    private Integer status;
    private final Integer SEARCHING = 0, FOUNDONE = 1, FOUNDBOTH = 2;
    Integer LCA, target1, target2;

    public Integer find(Node head, Integer target1, Integer target2)
    {
        target1Found = false;
        status = SEARCHING;
        LCA = -1;
        this.target1 = target1;
        this.target2 = target2;

        find(head);

        return LCA;
    }

    private void find(Node node)
    {
        if (node == null || status == FOUNDBOTH)
        {
            return;
        }

        if (node.getValue() == target2 || node.getValue() == target1)
        {
            if (status == SEARCHING)
            {
                status = FOUNDONE;

                if (node.getValue() == target1)
                {
                    LCA = node.getValue();
                    if (findIn(node, target2))
                    {
                        status = FOUNDBOTH;
                        return;
                    }

                    LCA = null;
                    return;
                }
                else if (node.getValue() == target2)
                {
                    LCA = node.getValue();
                    if (findIn(node, target1))
                    {
                        status = FOUNDBOTH;
                        return;
                    }

                    LCA = null;
                    return;
                }

                return;
            }
            else
            {
                status = FOUNDBOTH;
                return;
            }
        }

        find(node.getLeft());
        if (status == FOUNDONE && LCA == null)
        {
            LCA = node.getValue();
        }

        find(node.getRight());
        if (status != FOUNDBOTH && LCA != null)
        {
            LCA = null;
        }
    }

    private boolean findIn(Node node, Integer target)
    {
        if (node == null)
        {
            return false;
        }

        if (node.getValue() == target)
        {
            return true;
        }

        return findIn(node.getLeft(), target) || findIn(node.getRight(), target);
    }
}

class BST
{
    private Node head;

    public BST()
    {
        head = null;
    }

    public void insert(Integer v)
    {
        if (head == null)
        {
            head = new Node(v);
        } else
        {
            insert(head, v);
        }
    }

    public void print(Node node)
    {
        if (node == null)
        {
            return;
        }

        System.out.print(node.getValue() +  " - ");
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

    private void insert(Node node, Integer v)
    {
        if (node.getValue() > v)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), v);
            }
            else
            {
                node.setLeft(new Node(v));
            }
        }
        else if (node.getValue() < v)
        {
            if (node.getRight() != null)
            {
                insert(node.getRight(), v);
            }
            else
            {
                node.setRight(new Node(v));
            }
        }
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Node left, right;
    private Integer value;

    public Node(Integer v)
    {
        value = v;
    }

    public Integer getValue()
    {
        return value;
    }

    public Node getLeft()
    {
        return left;
    }

    public void setLeft(Node left)
    {
        this.left = left;
    }

    public Node getRight()
    {
        return right;
    }

    public void setRight(Node right)
    {
        this.right = right;
    }
}