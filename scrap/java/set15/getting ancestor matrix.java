// getting ancestor matrix
// r1, q2, set15

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{4, 2, 5, 1, 6, 3, 7};
        BST tree = new BST(array);
        BST.print(tree.getHead());

        GenerateAncestorMatrix gam = new GenerateAncestorMatrix(tree);
        gam.getAncestorMatrix();
    }
}

class GenerateAncestorMatrix
{
    private Integer[][] ancestorMatrix;
    private Map<Integer, Integer> valueIndex;
    private Integer lastUsedIndex;
    private Node head;

    public GenerateAncestorMatrix(BST tree)
    {
        this.ancestorMatrix = new Integer[tree.getElementCount()][tree.getElementCount()];
        for (Integer r=0; r<tree.getElementCount(); ++r)
        {
            for (Integer c=0; c<tree.getElementCount(); ++c)
            {
                ancestorMatrix[r][c] = 0;
            }
        }

        this.valueIndex = new HashMap<>();
        this.lastUsedIndex = -1;
        this.head = tree.getHead();
    }

    public Integer[][] getAncestorMatrix()
    {
        computeMatrix(head, null);

        for (Integer r=0; r<ancestorMatrix.length; ++r)
        {
            System.out.print(getValueFromIndex(r) + ": ");

            for(Integer c=0; c<ancestorMatrix.length; ++c)
            {
                if (ancestorMatrix[r][c] == 1)
                {
                    System.out.print(getValueFromIndex(c) + " ");
                }
            }

            System.out.println();
        }

        return ancestorMatrix;
    }

    private Integer getValueFromIndex(Integer index)
    {
        for (Integer key : valueIndex.keySet())
        {
            if (valueIndex.get(key) == index)
            {
                return key;
            }
        }

        return null;
    }

    private void computeMatrix(Node node, Node parent)
    {
        if (node == null)
        {
            return;
        }

        copyAndAddAncestor(node, parent);

        computeMatrix(node.getLeft(), node);
        computeMatrix(node.getRight(), node);
    }

    private void copyAndAddAncestor(Node child, Node parent)
    {
        ++lastUsedIndex;
        valueIndex.put(child.getValue(), lastUsedIndex);

        if (parent != null)
        {
            Integer parentIndex = valueIndex.get(parent.getValue());
            Integer childIndex =  valueIndex.get(child.getValue());

            for (Integer cursor=0; cursor < ancestorMatrix.length; ++cursor)
            {
                ancestorMatrix[childIndex][cursor] = ancestorMatrix[parentIndex][cursor];
            }

            ancestorMatrix[childIndex][parentIndex] = 1;
        }
    }
}

class BST
{
    private Node head;
    private Integer[] array;

    public BST(Integer[] array)
    {
        this.array = array;
        this.head = addRecursively(0, array.length);
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

    private Node addRecursively(Integer start, Integer length)
    {
        if (length - start < 1)
        {
            return null;
        }

        Integer midIndex = (start + length)/2;
        Node node = new Node(array[midIndex]);

        node.setLeft(addRecursively(start, midIndex));
        node.setRight(addRecursively(midIndex + 1, length));

        return node;
    }

    public Node getHead()
    {
        return head;
    }

    public int getElementCount()
    {
        return array.length;
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