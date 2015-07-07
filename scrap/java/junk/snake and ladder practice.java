// snake and ladder practice

import java.util.ArrayDeque;
import java.util.Queue;

public class Main
{
    public static void main(String[] er)
    {
        int[] board = new int[30];
        board[10] = 25;
        board[2] = 21;
        board[4] = 7;

        board[18] = 6;
        board[16] = 3;
        board[20] = 8;
        board[26] = 0;

        SnakeAndLadder sal = new SnakeAndLadder();
        int[] path = sal.getMinThrows(board);
        Integer node = board.length-1;
        Integer count = 0;
        while (node != 0)
        {
            System.out.print((node+1) + " ");
            node = path[node];
            ++count;
        }
        System.out.println(node+1);
        System.out.print(count);

    }
}

class SnakeAndLadder
{
    public int[] getMinThrows(int[] board)
    {
        final Integer nodeCount = board.length;
        final Integer diceCount = 6;

        Queue<Integer> que = new ArrayDeque<>();
        int[] parent = new int[nodeCount];

        que.add(0);
        while (que.size() > 0)
        {
            Integer thisNode = que.remove();

            if (thisNode == nodeCount-1)
            {
                break;
            }

            for (Integer i=1; i<diceCount; ++i)
            {
                Integer nextNode = thisNode + i;
                if (nextNode < nodeCount)
                {
                    if (!que.contains(nextNode) && board[nextNode] == 0)
                    {
                        parent[nextNode] = thisNode;
                        que.add(nextNode);
                    }
                    else if ((!que.contains(board[nextNode]) && board[nextNode] > thisNode))
                    {
                        parent[board[nextNode]] = thisNode;
                        que.add(board[nextNode]);
                    }
                }
            }
        }

        return parent;
    }
}