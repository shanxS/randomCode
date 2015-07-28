// kth min in sorted matrix

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    private static Integer[][] ar = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {25, 29, 37, 48},
            {32, 33, 39, 50},
    };

    public static void main(String[] er)
    {
        List<Integer> cursors = new ArrayList<>();
        MinHeap mh = new MinHeap();
        for (Integer i=0; i<ar.length; ++i)
        {
            cursors.add(0);
            mh.insert(new Node(ar[i][0], i));
        }

        Integer k=2;
        Node lastMin = null;
        while (k>0)
        {
            lastMin = mh.removeMin();

            cursors.set(lastMin.row, cursors.get(lastMin.row) + 1);
            if (cursors.get(lastMin.row) < ar.length)
            {
                mh.insert(new Node(ar[lastMin.row][cursors.get(lastMin.row)], lastMin.row));
            }

            --k;
        }

        System.out.println(lastMin.value);
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

    public void insert(Node n)
    {
        list.add(n);
        bubbleUp();
    }

    private void bubbleUp()
    {
        if (list.size() == 1)
        {
            return;
        }

        Integer childIndex = list.size()-1;
        Integer parentIndex = getParentIndex(childIndex);

        while (isValidIndex(parentIndex) && list.get(parentIndex).value > list.get(childIndex).value)
        {
            Collections.swap(list, parentIndex, childIndex);
            childIndex = parentIndex;
            parentIndex = getParentIndex(childIndex);
        }
    }

    private boolean isValidIndex(Integer index)
    {
        return index>=0 && index<list.size();
    }

    private Integer getParentIndex(Integer childIndex)
    {
        return (childIndex-1)/2;
    }

    public Node getMin()
    {
        if (list.size() == 0)
        {
            return null;
        }

        return list.get(0);
    }

    public Node removeMin()
    {
        if (list.size() ==  0)
        {
            return null;
        }

        Node returnValue = list.get(0);
        Collections.swap(list, 0, list.size() - 1);
        list.remove((int)(list.size()-1));
        bubbleDown();

        return returnValue;
    }

    private void bubbleDown()
    {
        Integer parentIndex = 0;
        Integer leftChildIndex = getLeftChildIndex(parentIndex);
        Integer rightChildIndex = getRightChildIndex(parentIndex);

        while (isValidIndex(leftChildIndex) || isValidIndex(rightChildIndex))
        {
            if (isValidIndex(leftChildIndex) && isValidIndex(rightChildIndex))
            {
                if (list.get(leftChildIndex).value < list.get(rightChildIndex).value
                        && list.get(leftChildIndex).value < list.get(parentIndex).value)
                {
                    Collections.swap(list, leftChildIndex, parentIndex);
                    parentIndex = leftChildIndex;
                }
                else if (list.get(leftChildIndex).value > list.get(rightChildIndex).value
                    && list.get(rightChildIndex).value < list.get(parentIndex).value)
                {
                    Collections.swap(list, rightChildIndex, parentIndex);
                    parentIndex = rightChildIndex;
                }
                else
                {
                    break;
                }
            }
            else if (isValidIndex(leftChildIndex)
                    && list.get(leftChildIndex).value < list.get(parentIndex).value)
            {
                Collections.swap(list, leftChildIndex, parentIndex);
                parentIndex = leftChildIndex;
            }
            else if (isValidIndex(rightChildIndex)
                    && list.get(rightChildIndex).value < list.get(parentIndex).value)
            {
                Collections.swap(list, rightChildIndex, parentIndex);
                parentIndex = rightChildIndex;
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
        return (index*2) + 1;
    }
}

class Node implements Comparable
{
    final Integer value, row;
    public Node(Integer v, Integer r)
    {
        value = v;
        row = r;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null
                || (o != null && !(o instanceof Node)))
        {
            return false;
        }

        if (o == this)
        {
            return true;
        }

        Node otherNode = (Node) o;
        return (value == otherNode.value && row == otherNode.row);
    }

    @Override
    public int hashCode()
    {
        Integer prime = 21;
        Integer result = 17;
        result += prime*value;
        result += prime*row;

        return result;
    }

    @Override
    public int compareTo(Object o)
    {
        Node otherNode = (Node)o;

        if (otherNode.value > value)
        {
            return -1;
        }
        else if (otherNode.value < value)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}