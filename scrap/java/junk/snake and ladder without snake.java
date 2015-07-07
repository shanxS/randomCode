// snake and ladder without snake

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main
{
    public static void main(String[] er)
    {
        int[] board = new int[30];
        board[10] = 25;
        board[2] = 21;
        board[4] = 7;

//        int[] board = new int[9];
//        board[1] = 7;
//        board[5] = 8;

        FindMinThrows fmt = new FindMinThrows();
        System.out.print(fmt.find(board));
        System.out.println("---------------");
        Integer node = board.length-1;
        while (node != 0)
        {
            System.out.print((node+1) + " ");
            node = fmt.minSteps[node];
        }
        System.out.print(node+1);
    }
}

class FindMinThrows
{
    int[] minSteps;

    public Integer find(int[] board)
    {
        Queue<Integer> que = new ArrayDeque<>();

        final Integer nodeCount = board.length;
        final Integer diceCount = 6;
        Integer minThrows = Integer.MAX_VALUE;

        que.add(0);
        int[] parent = new int[nodeCount];
        while (que.size() > 0)
        {
            Integer thisNode = que.remove();

            if (thisNode == (nodeCount - 1))
            {
                Integer thisMinThrow = getThrows(parent, nodeCount);
                if (minThrows > thisMinThrow )
                {
                    minSteps = Arrays.copyOf(parent, nodeCount);
                    minThrows = thisMinThrow;
                }
            }

            for (Integer i = 1; i <= diceCount; ++i)
            {
                Integer nextNode = thisNode + i;
                if (nextNode < nodeCount)
                {
                    if (!que.contains(nextNode) && board[nextNode] == 0)
                    {
                        que.add(nextNode);
                        parent[nextNode] = thisNode;
                    }
                    else if (!que.contains(board[nextNode]) && board[nextNode] != 0)
                    {
                        que.add(board[nextNode]);
                        parent[board[nextNode]] = thisNode;
                    }
                }
            }
        }

        return minThrows;
    }

    private int getThrows(int[] parent, Integer nodeCount)
    {
        Integer steps = 0;
        Integer node = nodeCount-1;
        while (node != 0)
        {
            node = parent[node];
            ++steps;
        }

        return steps;
    }
}