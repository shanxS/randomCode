// Maximum Length Chain of Pairs
// code question: 117

import java.util.Arrays;

public class Main
{
    private static Node[] array;

    public static void main(String[] er)
    {
        array = makeNodes();
        Arrays.sort(array);

        System.out.print(LIS(0));
    }

    private static Integer LIS(Integer startIndex)
    {
        if (startIndex >= array.length)
        {
            return 0;
        }

        Integer maxSoFar = 1;

        for (Integer i=startIndex+1; i<array.length; ++i)
        {
            if (array[startIndex].getSecond() < array[i].getFirst())
            {
                Integer thisMax = 1 + LIS(i);
                if (maxSoFar < thisMax)
                {
                    maxSoFar = thisMax;
                }
            }
        }

        return maxSoFar;
    }

    private static Node[] makeNodes()
    {
        return new Node[]{ new Node(5,24), new Node(15,250),
                new Node(50, 60), new Node(27, 40)};
    }
}

class Node implements Comparable
{
    private Integer first, second;
    private Boolean compareBySecond;
    public Node (Integer f, Integer s)
    {
        this.first = f;
        this.second = s;
        this.compareBySecond = false;
    }

    public void setCompareBySecond()
    {
        compareBySecond = true;
    }

    public void setCompareByFirst()
    {
        compareBySecond = false;
    }

    public Integer getFirst()
    {
        return first;
    }

    public void setFirst(Integer first)
    {
        this.first = first;
    }

    public Integer getSecond()
    {
        return second;
    }

    public void setSecond(Integer second)
    {
        this.second = second;
    }

    @Override
    public int compareTo(Object o)
    {
        Node otherNode = (Node) o;

        if (compareBySecond)
        {
            if (getSecond() < otherNode.getSecond())
            {
                return -1;
            }
            else if (getSecond() > otherNode.getSecond())
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        else
        {
            if (getFirst() < otherNode.getFirst())
            {
                return -1;
            }
            else if (getFirst() > otherNode.getFirst())
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }
}