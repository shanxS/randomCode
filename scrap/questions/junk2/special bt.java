// special bt

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] values = {10, 30, 20, 5, 15};
//        Character[] type = {'N', 'N', 'L', 'L', 'L'};
        Integer[] values = {10, 15, 30,20, 5};
        Character[] type = {'N', 'L', 'N', 'L', 'L'};

        BTUtil btUtil = new BTUtil();
        btUtil.print(btUtil.make(values, type));
    }
}

class BTUtil
{
    private final Character LEAF='L', NODE='N';

    public void print(Node node)
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

    private Node[] nodes;
    Character[] type;
    public Node make(Integer[] values, Character[] type)
    {
        this.type = type;
        nodes = new Node[values.length];
        for (Integer i=0; i<values.length; ++i)
        {
            nodes[i] = new Node(values[i]);
        }

        connect(0);

        return nodes[0];
    }

    private Integer connect(Integer index)
    {
        if (index > nodes.length)
        {
            return -1;
        }

        if (type[index] == LEAF)
        {
            return index+1;
        }
        else
        {
            Node thisNode = nodes[index];
            thisNode.setLeft(nodes[index + 1]);

            Integer rightNodeIndex = connect(index+1);
            thisNode.setRight(nodes[rightNodeIndex]);

            connect(rightNodeIndex);

            return rightNodeIndex+1;
        }
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