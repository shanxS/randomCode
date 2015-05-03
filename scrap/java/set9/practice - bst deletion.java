// practice - bst deletion

public class Main
{
    public static void main(String[] args)
    {
        BST bst = new BST();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);
        bst.insert(12);
        bst.insert(17);
        bst.insert(11);
        bst.insert(13);
        bst.insert(16);
        bst.insert(20);

        BST.print(bst.getHead());
        System.out.println("---------------");

        bst.delete(10);
        BST.print(bst.getHead());
        System.out.println("---------------");

        bst.delete(7);
        BST.print(bst.getHead());
        System.out.println("---------------");

        bst.delete(5);
        BST.print(bst.getHead());
        System.out.println("---------------");

    }
}

class BST
{
    private Node head;
    private Node parentNode, targetNode;

    public BST()
    {
        head = null;
        parentNode = null;
        targetNode = null;
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

    public void delete(Integer value)
    {
        parentNode = null;
        targetNode = null;

        if (head.getValue() == value)
        {
            parentNode = null;
            targetNode = head;
        }
        else
        {
            setParentAndTargetNodes(head, value);
        }

        if (targetNode == null)
        {
            System.out.println("cannot find target " + value);
            return;
        }

        if (targetNode.getLeft() == null && targetNode.getRight() == null)
        {
            System.out.println("deleint for NO child ");
            deleteNodeWithNoChild(parentNode, targetNode);
        }
        else if ((targetNode.getLeft() != null && targetNode.getRight() == null)
                || (targetNode.getLeft() == null && targetNode.getRight() != null))
        {
            System.out.println("deleint for ONE child ");
            deleteNodeWithOneChild(parentNode, targetNode);
        }
        else
        {
            System.out.println("deleint for TWO child ");
            deleteNodeWithTwoChildren(parentNode, targetNode);
        }

    }

    private void deleteNodeWithTwoChildren(Node parentNode, Node targetNode)
    {
        Node targetRightSmallestChild = getSmallestChild(targetNode.getRight());
        delete(targetRightSmallestChild.getValue());
        targetRightSmallestChild.setRight(targetNode.getRight());
        targetRightSmallestChild.setLeft(targetNode.getLeft());

        if (parentNode == null)
        {
            head = targetRightSmallestChild;
        }
        else
        {
            if (parentNode.getValue() > targetNode.getValue())
            {
                parentNode.setLeft(targetRightSmallestChild);
            }
            else
            {
                parentNode.setRight(targetRightSmallestChild);
            }
        }
    }

    private Node getSmallestChild(Node node)
    {
        if (node.getLeft() != null)
        {
            return getSmallestChild(node.getLeft());
        }
        else
        {
            return node;
        }
    }

    private void deleteNodeWithOneChild(Node parentNode, Node targetNode)
    {
        Node targetChildNode = targetNode.getLeft() == null ? targetNode.getRight() : targetNode.getLeft();

        if (parentNode == null)
        {
            head = targetChildNode;
        }
        else
        {
            if (parentNode.getValue() > targetNode.getValue())
            {
                parentNode.setLeft(targetChildNode);
            }
            else
            {
                parentNode.setRight(targetChildNode);
            }
        }
    }

    private void deleteNodeWithNoChild(Node parentNode, Node targetNode)
    {
        if (parentNode == null)
        {
            head = null;
        }
        else
        {
            if (parentNode.getValue() > targetNode.getValue())
            {
                parentNode.setLeft(null);
            }
            else
            {
                parentNode.setRight(null);
            }
        }
    }

    private void setParentAndTargetNodes(Node node, Integer value)
    {
        if (node.getValue() > value && node.getLeft() != null)
        {
            Node leftChild = node.getLeft();

            if (leftChild.getValue() == value)
            {
                parentNode = node;
                targetNode = leftChild;
            }
            else
            {
                setParentAndTargetNodes(leftChild, value);
            }
        }
        else if (node.getValue() < value && node.getRight() != null)
        {
            Node rightChild = node.getRight();
            if (rightChild.getValue() == value)
            {
                parentNode = node;
                targetNode = rightChild;
            }
            else
            {
                setParentAndTargetNodes(rightChild, value);
            }
        }
    }

    public Node getHead()
    {
        return head;
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
}

class Node
{
    private Integer value;
    private Node left, right;

    public Node(Integer value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
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
}