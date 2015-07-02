// topo sort

import java.util.HashSet;
import java.util.Set;

public class Main
{
    final private static Integer VALID = 1;
    private static Set<Integer> printedNodes = new HashSet<>();
    private static Integer[][] adjacencyList = {
            {0,1,0,0,0},
            {0,0,1,0,0},
            {0,0,0,0,0},
            {0,1,0,0,0},
            {0,0,0,1,0}
    };

    public static void main(String[] er)
    {
        for (Integer node=0; node<adjacencyList.length; ++node)
        {
            if (!printedNodes.contains(node))
            {
                topoPrint(node);
            }
        }
    }

    private static void topoPrint(Integer node)
    {
        for (Integer i=0; i<adjacencyList.length; ++i)
        {
            if (adjacencyList[node][i] == VALID && !printedNodes.contains(i))
            {
                topoPrint(i);
            }
        }

        System.out.print(node + " ");
        printedNodes.add(node);
    }
}