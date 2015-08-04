replace surr

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] ar = {{1, 0, 1, 1, 1, 1},
                {1, 0, 1, 1, 0, 1},
                {1, 1, 1, 0, 0, 1},
                {0, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 0},
                {0, 0, 1, 0, 0, 0},
        };

        (new PathFinder()).find(ar);
        for (Integer r=0; r<ar.length; ++r)
        {
            for (Integer c=0; c<ar[0].length; ++c)
            {
                System.out.print(ar[r][c] + " ");
            }
            System.out.println();
        }
    }
}

class PathFinder
{
    int[][] known;
    final int GOOD=1, NK=0, BAD=-1;
    private Integer[][] ar;

    public void find(Integer[][] ar)
    {
        this.ar = ar;

        known = new int[ar.length][ar[0].length];
        for (Integer r=1; r<ar.length; ++r)
        {
            for (Integer c=1; c<ar[0].length; ++c)
            {
                if (ar[r][c] == 0)
                {
                    bfs(r,c);
                }
            }
        }
    }

    private void bfs(Integer startR, Integer startC)
    {
        Queue<Node> que = new ArrayDeque<>();
        que.add(new Node(startR,startC));
        boolean valid = false;
        Stack<Node> visited = new Stack<>();

        while (que.size() > 0)
        {
            Node node = que.remove();
            int r = node.r;
            int c = node.c;

            if (known[r][c]==GOOD || r==0 || c==0 || r==ar.length-1 || c==ar[0].length-1)
            {
                valid = true;
                break;
            }
            else
            {
                visited.push(node);
            }

            if (ar[r+1][c] == 0 && !visited.contains(new Node(r+1, c)))
            {
                que.add(new Node(r+1, c));
            }
            if (ar[r][c+1] == 0 && !visited.contains(new Node(r, c+1)))
            {
                que.add(new Node(r, c+1));
            }
            if (ar[r-1][c] == 0 && !visited.contains(new Node(r-1, c)))
            {
                que.add(new Node(r-1, c));
            }
            if (ar[r][c-1] == 0 && !visited.contains(new Node(r, c-1)))
            {
                que.add(new Node(r, c-1));
            }
        }

        if (!valid)
        {
            while (visited.size() > 0)
            {
                Node node = visited.pop();
                int r = node.r;
                int c = node.c;

                ar[r][c] = 1;
                known[r][c] = BAD;
            }
        }
        else
        {
            while (visited.size() > 0)
            {
                Node node = visited.pop();
                int r = node.r;
                int c = node.c;

                known[r][c] = GOOD;
            }
        }
    }
}

class Node
{
    Integer r, c;
    public Node(Integer r, Integer c)
    {
        this.c = c;
        this.r = r;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Node))
        {
            return false;
        }

        Node otherNode = (Node) o;
        return otherNode.r == r && otherNode.c == c;
    }

    @Override
    public int hashCode()
    {
        Integer prime = 17;
        Integer result = 13;
        result += r*prime;
        result += c*prime;

        return result;
    }
}