// idea comes from https://people.csail.mit.edu/thies/6.046-web/recitation9.txt

public class Main
{
    public static void main(String[] er)
    {

//        from: http://courses.csail.mit.edu/6.006/fall11/rec/rec14.pdf
        Integer[][] adjMat = new Integer[][] {
                {0,1,1,0,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
                {1,0,0,0,1,0,1},
                {0,0,0,0,0,1,1},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
        };

//        from: http://cs.stackexchange.com/a/11125
//        Integer[][] adjMat = new Integer[][] {
//                {0,1,0,0,1,0,0,0},
//                {0,0,1,0,0,0,0,0},
//                {0,0,0,1,0,0,0,0},
//                {0,1,0,0,0,0,0,0},
//                {0,0,0,0,0,1,0,0},
//                {0,0,1,0,0,0,1,1},
//                {0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0}
//        };

        DFSTimer dt = new DFSTimer();
        dt.time(adjMat );
    }
}

class DFSTimer
{
    private Integer[] startTime, endTime;
    private Integer[][] adjMatrix;
    private Integer[] parent;
    private Integer time;
    private boolean[] visited;

    public void time(Integer[][] adjMatrix)
    {
        this.adjMatrix = adjMatrix;
        startTime = new Integer[adjMatrix.length];
        parent = new Integer[adjMatrix.length];
        endTime = new Integer[adjMatrix.length];
        time = 0;
        visited = new boolean[adjMatrix.length];

        for (Integer node=0; node<visited.length; ++node)
        {
            if (!visited[node])
            {
                contempate(node);
            }
        }
    }

    private void contempate(Integer thisNode)
    {
        visited[thisNode] = true;
        ++time;
        startTime[thisNode] = time;

        Integer[] children = adjMatrix[thisNode];
        for (Integer nextNode=0; nextNode<children.length; ++nextNode)
        {
            if (children[nextNode] != 1)
            {
                continue;
            }

            parent[nextNode] = thisNode;

            if (!visited[nextNode])
            {
                contempate(nextNode);
            }
            else
            {
                if (endTime[nextNode] != null)
                {
                    if(startTime[nextNode] < startTime[thisNode])
                    {
                        System.out.println("cross " + thisNode + " " + nextNode);
                    }
                    else
                    {
                        System.out.println("forward " + thisNode + " " + nextNode);
                    }
                }
                else
                {
                    System.out.println("back " + thisNode + " " + nextNode);
                }
            }
        }

        ++time;
        endTime[thisNode] = time;
    }
}













