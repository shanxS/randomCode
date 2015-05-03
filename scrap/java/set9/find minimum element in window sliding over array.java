// find minimum element in window sliding over array
// r2, q1, set9, amazon
// output is with some commentary :)

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{10,1,9,2,8,3,7};
        MinFinder minf = new MinFinder(array, 3, 1);
        minf.find();
    }
}

class MinFinder
{
    private Integer[] array;
    private Integer W, S;
    private BST bst;

    public MinFinder(Integer[] array, Integer w, Integer s)
    {
        this.array = array;
        W = w;
        S = s;
        bst = new BST();
    }

    public void find()
    {
        Integer counter = 0;
        Integer previousCounter = counter;
        while (counter+W <= array.length)
        {
            System.out.print("removed ");
            for (Integer k=previousCounter; k<counter; ++k)
            {
                System.out.print(" " + array[k]);
                bst.delete(array[k]);
            }
            System.out.println();

            System.out.print("added ");
            if (previousCounter+W == counter+W)
            {
                for (Integer j=counter; j < counter+W; ++j)
                {
                    System.out.print(array[j] + " ");
                    bst.insert(array[j]);
                }
            }
            else
            {
                for (Integer l = previousCounter + W; l < counter + W; ++l)
                {
                    System.out.print(" " + array[l]);
                    bst.insert(array[l]);
                }
            }
            System.out.println();

            for (Integer j=counter; j < counter+W; ++j)
            {
                System.out.print(array[j] + " ");
            }
            System.out.println();

            previousCounter = counter;
            counter += S;

            System.out.print("MINIMUM ");
            bst.printMin();
        }
    }
}

class BST
{
    private Node head;
    private Node parentNode, targetNode;
    private Node minNode;

    public BST()
    {
        head = null;
        parentNode = null;
        targetNode = null;
        minNode = null;
    }

    public void insert(Integer value)
    {
        if (head == null)
        {
            head = new Node(value);
            minNode = head;
        }
        else
        {
            insert(head, value);
        }
    }

    public void printMin()
    {
        System.out.println("min " + minNode.getValue());
    }

    public void delete (Integer value)
    {
        parentNode = null;
        targetNode = null;

        if (head == null)
        {
            System.out.println("no tree cannot delete");
            return;
        }

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
            System.out.println("cannot find target");
            return;
        }

        if (targetNode.getLeft() == null && targetNode.getRight() == null)
        {
            System.out.println("deleting for NO child");
            deleteNodeWithNoChild(parentNode, targetNode);
        }
        else if ((targetNode.getLeft() != null && targetNode.getRight() == null)
         || (targetNode.getLeft() == null && targetNode.getRight() != null))
        {
            System.out.println("deleting for ONE child");
            deleteNodeWithOneChild(parentNode, targetNode);
        }
        else
        {
            System.out.println("deleting for TWO child");
            deleteNodeWithTwoChild(parentNode, targetNode);
        }

        if (minNode.getValue() == targetNode.getValue())
        {
            if (parentNode == null)
            {
                minNode = getSmallestNode(head);
            }
            else
            {
                minNode = parentNode;
            }
        }
    }

    private void deleteNodeWithTwoChild(Node parentNode, Node targetNode)
    {
        Node targetRightSmallest = getSmallestNode(targetNode.getRight());
        delete(targetRightSmallest.getValue());
        targetRightSmallest.setLeft(targetNode.getLeft());
        targetRightSmallest.setRight(targetNode.getRight());

        if (parentNode == null)
        {
            head = targetRightSmallest;
        }
        else
        {
            if (parentNode.getValue() > targetNode.getValue())
            {
                parentNode.setLeft(targetRightSmallest);
            }
            else
            {
                parentNode.setRight(targetRightSmallest);
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

    private void deleteNodeWithOneChild(Node parentNode, Node targetNode)
    {
        Node targetChild = targetNode.getLeft() == null ? targetNode.getRight() : targetNode.getLeft();

        if (parentNode == null)
        {
            head = targetChild;
        }
        else
        {
            if (parentNode.getValue() > targetNode.getValue())
            {
                parentNode.setLeft(targetChild);
            }
            else
            {
                parentNode.setRight(targetChild);
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
        if(node.getValue() > value && node.getLeft() != null)
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

    private void insert(Node node, Integer value)
    {
        if (node.getValue() > value)
        {
            if (node.getLeft() != null)
            {
                insert(node.getLeft(), value);
            } else
            {
                node.setLeft(new Node(value));
                if (minNode.getValue() == node.getValue())
                {
                    minNode = node.getLeft();
                }
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

    public Node getHead()
    {
        return head;
    }

    public static void print(Node node)
    {
        if(node == null)
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