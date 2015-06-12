// delete node form BST
// code question: 84

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {30, 10, 40, 5, 20, 35, 50, 15, 45};
        BST bst = new BST();
        for (Integer value : array)
        {
            bst.insert(value);
        }

        BST.print(bst.getHead());
        System.out.println("-------------------------------");

        NodeDeleter nd = new NodeDeleter();
//        BST.print(nd.delete(bst.getHead(), 20));
//        BST.print(nd.delete(bst.getHead(), 30));
        BST.print(nd.delete(bst.getHead(), 40));
    }
}

class NodeDeleter
{
    private Node parent, target, head;


    public Node delete(Node head, Integer value)
    {
        this.head = head;
        setTarget(head, value, null);
        if (target == null)
        {
            System.out.print("cant find target");
            return head;
        }

        return deleteTarget();
    }

    private void setTarget(Node node, Integer value, Node parent)
    {
        if (node == null)
        {
            return;
        }

        if (node.getValue() == value)
        {
            this.parent = parent;
            this.target = node;
        }
        else if (node.getValue() > value)
        {
            setTarget(node.getLeft(), value, node);
        }
        else
        {
            setTarget(node.getRight(), value, node);
        }
    }

    private Node deleteTarget()
    {
        Node nextBiggestNode = getNextBiggest(target);
        resetConnections(parent, target, nextBiggestNode);

        if (parent == null)
        {
            return nextBiggestNode;
        }
        else
        {
            return head;
        }
    }

    private Node getNextBiggest(Node node)
    {
        if (node.getRight() == null)
        {
            if (node.getLeft() != null)
            {
                Node leftNode = node.getLeft();
                node.setLeft(null);

                return leftNode;
            }
            else
            {
                return null;
            }
        }
        else
        {
            if (node.getRight().getLeft() != null)
            {
                return getMin(node.getRight().getLeft(), node.getRight());
            }
            else
            {
                Node rightNode = node.getRight();
                node.setRight(null);
                return rightNode;
            }
        }

    }

    private Node getMin(Node node, Node parent)
    {
        if (node.getLeft() == null)
        {
            parent.setLeft(null);
            return node;
        }
        else
        {
            return getMin(node.getLeft(), node);
        }
    }

    private void resetConnections(Node parent, Node target, Node nextBiggestNode)
    {
        if (parent != null)
        {
            if (parent.getValue() > target.getValue())
            {
                parent.setLeft(nextBiggestNode);
            }
            else
            {
                parent.setRight(nextBiggestNode);
            }
        }

        if (nextBiggestNode != null)
        {
            nextBiggestNode.setLeft(target.getLeft());
            nextBiggestNode.setRight(target.getRight());
        }
    }
}

class BST
{
    private Node head;

    public BST()
    {
        head = null;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
        }
        else
        {
            insert(head, value);
        }
    }

    private void insert(Node node, Integer value)
    {
        if (node.getValue() > value)
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
        else if (node.getValue() < value)
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
    }

    public static void print(Node node)
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

    public Node getHead()
    {
        return head;
    }

    public void setHead(Node head)
    {
        this.head = head;
    }
}

class Node
{
    private Node left, right;
    private Integer value;

    public Node(Integer value)
    {
        this.value = value;
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

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}