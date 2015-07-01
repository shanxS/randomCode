// stack with mid elemnt access using dll

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1, 2, 3, 4, 5, 6};
        ModifiedStack ms = new ModifiedStack();
        for (Integer v : array)
        {
            ms.push(v);
            System.out.println(ms.getMid() + " " + ms.peek());
        }

        System.out.println("---------------");
        while (ms.getSize() > 0)
        {
            System.out.println(ms.pop() + " " + ms.getMid());
        }
    }
}

class ModifiedStack
{
    private Node bottom, mid, top;
    private Integer size;
    private final Boolean INCREASING, DECREASING;

    public ModifiedStack()
    {
        bottom = null;
        mid = null;
        top = null;
        size = 0;
        INCREASING = false;
        DECREASING = true;
    }

    public void push(Integer value)
    {
        if (bottom == null)
        {
            bottom = new Node(value);
            top = bottom;
            ++size;
        }
        else
        {
            Node cursor = bottom;
            while (cursor.getRight() != null)
            {
                cursor = cursor.getRight();
            }

            Node node = new Node(value);
            node.setLeft(cursor);
            cursor.setRight(node);
            top = node;
            ++size;

            updateMid(INCREASING);
        }
    }

    public Integer getMid()
    {
        if (mid == null)
        {
            return null;
        }

        return mid.getValue();
    }

    public Integer removeMid()
    {
        if (mid == null)
        {
            return null;
        }

        Integer value = mid.getValue();

        if (size == 2)
        {
            mid = null;
            bottom.setRight(null);
            top.setLeft(null);
            bottom = top;
        }
        else
        {
            Node midRight = mid.getRight();
            Node midLeft = mid.getLeft();

            midLeft.setRight(midRight);
            midRight.setLeft(midLeft);

            if (size % 2 == 0)
            {
                mid = midRight;
            }
            else
            {
                mid = midLeft;
            }
        }

        --size;

        return value;
    }

    public Integer peek()
    {
        if (size == 0)
        {
            return null;
        }

        return top.getValue();
    }

    public Integer pop()
    {
        if (size == 0)
        {
            return null;
        }

        Integer value = top.getValue();

        if (size == 1)
        {
            top = null;
            bottom = null;
        }
        else
        {
            Node previousTop = top;
            top = top.getLeft();

            previousTop.setLeft(null);
            top.setRight(null);
        }

        --size;
        updateMid(DECREASING);

        return value;
    }

    private void updateMid(Boolean trend)
    {
        if (size < 2)
        {
            mid = null;
        }
        else if (size == 2)
        {
            mid = bottom;
        }
        else
        {
            if (trend == INCREASING && size%2 != 0)
            {
                mid = mid.getRight();
            }
            else if (trend == DECREASING && size%2 == 0)
            {
                mid = mid.getLeft();
            }
        }
    }

    public Integer getSize()
    {
        return size;
    }
}

class Node
{
    private Node right, left;
    private Integer value;

    public Node(Integer v)
    {
        this.value = v;
    }

    public Integer getValue()
    {
        return value;
    }

    public Node getRight()
    {
        return right;
    }

    public void setRight(Node right)
    {
        this.right = right;
    }

    public Node getLeft()
    {
        return left;
    }

    public void setLeft(Node left)
    {
        this.left = left;
    }
}