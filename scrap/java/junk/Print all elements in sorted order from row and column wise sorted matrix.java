// Print all elements in sorted order from row and column wise sorted matrix

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    private static final Integer[][] ar = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {27, 29, 37, 48},
            {32, 33, 39, 50}
    };
    private static final Integer R = ar.length;
    private static final Integer C = ar[0].length;
    private static Integer[] res = new Integer[R*C];
    private static Integer resCursor = 0;
    private static int[] cursors = new int[R];

    public static void main(String[] er)
    {
        MinHeap minHeap = new MinHeap();
        for (Integer i=0; i<cursors.length; ++i)
        {
            minHeap.insert(new Node(ar[i][0], i));
        }

        while (resCursor < res.length)
        {
            Node minNode = minHeap.removeMin();
            res[resCursor] = minNode.getValue();

            Integer index = minNode.getIndex();
            if (cursors[index]+1 < C)
            {
                ++cursors[index];
                minHeap.insert(new Node(ar[index][cursors[index]], index));
            }

            ++resCursor;
        }

        for (Integer i=0; i<resCursor; ++i)
        {
            System.out.print(res[i] + " ");
        }
    }
}

class MinHeap
{
    private List<Node> list;

    public MinHeap()
    {
        list = new ArrayList<>();
    }

    public Integer size()
    {
        return list.size();
    }

    public Node getMin()
    {
        if (list.size() > 0)
        {
            return list.get(0);
        }

        return null;
    }

    public void insert(Node value)
    {
        list.add(value);
        if (list.size() > 1)
        {
            bubbleUp();
        }
    }

    private void bubbleUp()
    {
        Integer childIndex = list.size()-1;
        Integer parentIndex = getParentIndex(childIndex);

        while (isVlaidIndex(parentIndex) && list.get(childIndex).compareTo(list.get(parentIndex)) == 1)
        {
            Collections.swap(list, childIndex, parentIndex);
            childIndex = parentIndex;
            parentIndex = getParentIndex(childIndex);
        }
    }

    private boolean isVlaidIndex(Integer index)
    {
        return (index>=0 && index<list.size());
    }

    private Integer getParentIndex(Integer index)
    {
        return (index-1)/2;
    }

    public Node removeMin()
    {
        if (list.size() == 0)
        {
            return null;
        }

        Node preMin = getMin();

        Collections.swap(list, 0, list.size()-1);
        list.remove((int) (list.size() - 1));

        bubbleDown();

        return preMin;
    }

    private void bubbleDown()
    {
        Integer parentIndex = 0;
        Integer leftChildIndex = getLeftChildIndex(parentIndex);
        Integer rightChildIndex = getRightChildIndex(parentIndex);

        while (isVlaidIndex(leftChildIndex) || isVlaidIndex(rightChildIndex))
        {
            if (isVlaidIndex(leftChildIndex) && isVlaidIndex(rightChildIndex))
            {
                if (list.get(leftChildIndex).compareTo(list.get(rightChildIndex)) == 1
                        && list.get(leftChildIndex).compareTo(list.get(parentIndex)) == 1)
                {
                    Collections.swap(list, leftChildIndex, parentIndex);
                }
                else if (list.get(rightChildIndex).compareTo(list.get(leftChildIndex)) == 1
                        && list.get(rightChildIndex).compareTo(list.get(parentIndex)) == 1)
                {
                    Collections.swap(list, rightChildIndex, parentIndex);
                }
                else
                {
                    break;
                }
            }
            else if (isVlaidIndex(leftChildIndex)
                    && list.get(leftChildIndex).compareTo(list.get(parentIndex)) == 1)
            {
                Collections.swap(list, leftChildIndex, parentIndex);
            }
            else if (isVlaidIndex(rightChildIndex)
                    && list.get(rightChildIndex).compareTo(list.get(parentIndex)) == 1)
            {
                Collections.swap(list, rightChildIndex, parentIndex);
            }
            else
            {
                break;
            }

            leftChildIndex = getLeftChildIndex(parentIndex);
            rightChildIndex = getRightChildIndex(parentIndex);
        }
    }

    private Integer getRightChildIndex(Integer index)
    {
        return getLeftChildIndex(index) + 1;
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (index*2)+1;
    }
}

class Node implements Comparable
{
    private Integer value, index;
    public Node(Integer v, Integer i)
    {
        value = v;
        index = i;
    }

    public Integer getValue()
    {
        return value;
    }

    public Integer getIndex()
    {
        return index;
    }

    @Override
    public int compareTo(Object o)
    {
        Node otherNode = (Node) o;
        if (otherNode.getValue() < getValue())
        {
            return -1;
        }
        else if (otherNode.getValue() > getValue())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}