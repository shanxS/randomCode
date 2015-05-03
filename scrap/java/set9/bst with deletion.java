// bst with deletion

public class Main
{
    public static void main(String[] args)
    {
        BinarySearchTree bst = new BinarySearchTree();
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


        BinarySearchTree.print(bst.getHead());
        System.out.println("--------------------");

        bst.delete(3);
        BinarySearchTree.print(bst.getHead());
        bst.delete(5);
        BinarySearchTree.print(bst.getHead());
    }
}

class BinarySearchTree
{
    private Node head;
    Node parentNode, targetNode;

    public BinarySearchTree()
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

    public void delete(Integer value)
    {
        parentNode = null;
        targetNode = null;

        if (head.getValue() == value)
        {
            targetNode = head;
        }
        else
        {
            findParentAndTarget(head, value);
        }

        if (parentNode == null && targetNode == null)
        {
            System.out.println("cant find value " + value);
        }
        else
        {
            if (targetNode.getRight() == null && targetNode.getLeft() == null)
            {
                System.out.println("removing node with NO child ");
                removeNodeWithNoChild(parentNode, targetNode);
            }
            else if ((targetNode.getRight() != null && targetNode.getLeft() == null)
                || (targetNode.getRight() == null && targetNode.getLeft() != null))
            {
                System.out.println("removing node with ONE child ");
                removeNodeWithOneChild(parentNode, targetNode);
            }
            else
            {
                System.out.println("removing node with TWO child ");
                removeNodeWithTwoChildren(parentNode, targetNode);
            }
        }
    }

    private void removeNodeWithTwoChildren(Node parentNode, Node targetNode)
    {
        Node smallestNode = getSmallestNode(targetNode.getRight());
        delete(smallestNode.getValue());
        smallestNode.setLeft(targetNode.getLeft());
        smallestNode.setRight(targetNode.getRight());

        if (parentNode == null)
        {
            head = smallestNode;
        }
        else
        {
            if (parentNode.getValue() > targetNode.getValue())
            {
                parentNode.setLeft(smallestNode);
            }
            else
            {
                parentNode.setRight(smallestNode);
            }
        }
    }

    private Node getSmallestNode(Node node)
    {

        if (node.getLeft() != null)
        {
            return getSmallestNode(node.getLeft());
        }
        else
        {
            return node;
        }
    }

    private void removeNodeWithOneChild(Node parentNode, Node targetNode)
    {
        if (parentNode == null)
        {
            head = targetNode;
        }
        else
        {
            Node targetChildNode = targetNode.getLeft() == null ? targetNode.getRight() : targetNode.getLeft();

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

    private void removeNodeWithNoChild(Node parentNode, Node targetNode)
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

    private void findParentAndTarget(Node node, Integer value)
    {
        if (node.getValue() > value && node.getLeft() != null)
        {
            Node leftChild = node.getLeft();
            if (leftChild.getValue() == value)
            {
                parentNode = node;
                targetNode = leftChild;
                return;
            }
            else
            {
                findParentAndTarget(leftChild, value);
                return;
            }
        }
        else if (node.getValue() < value && node.getRight() != null)
        {
            Node rightChild = node.getRight();
            if (rightChild.getValue() == value)
            {
                parentNode = node;
                targetNode = rightChild;
                return;
            }
            else
            {
                findParentAndTarget(rightChild, value);
                return;
            }
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

        print(node.getRight());
        print(node.getLeft());
    }

    public Node getHead()
    {
        return head;
    }
}

class Node
{
    private Integer value;
    private Node left, right;

    public Node(Integer value)
    {
        this.value = value;
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