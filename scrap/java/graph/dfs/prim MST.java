// prim MST

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] adjMatrix = {
                {0,4,0,0,0,0,0,8,0},
                {4,0,8,0,0,0,0,11,0},
                {0,8,0,7,0,4,0,0,2},
                {0,0,7,0,9,14,0,0,0},
                {0,0,0,9,0,10,0,0,0},
                {0,0,4,14,10,0,2,0,0},
                {0,0,0,0,0,2,0,1,6},
                {8,11,0,0,0,0,1,0,7},
                {0,0,2,0,0,0,6,7,0}
        };

//        Integer[][] adjMatrix = {
//                {0,5,1,3},
//                {5,0,0,4},
//                {1,0,0,2},
//                {3,4,2,0}
//        };

        PrimMST pmst = new PrimMST();
        int[] path = pmst.findMST(adjMatrix);

        System.out.println("path is \n start at 0");
        for (Integer i=1; i<path.length; ++i)
        {
            System.out.println("from " + path[i] + " to " + i);
        }
    }
}

class PrimMST
{
    private int[] parent, nodeBag;
    private boolean[] usedNode;

    public int[] findMST(Integer[][] adjMatrix)
    {
        final Integer nodeCount = adjMatrix.length;

        usedNode = new boolean[nodeCount];

        nodeBag = new int[nodeCount];
        for (Integer i=0; i<nodeCount; ++i)
        {
            nodeBag[i] = Integer.MAX_VALUE;
        }
        nodeBag[0] = 0;

        parent = new int[nodeCount];
        parent[0] = -1;

        for(Integer count=0; count<nodeCount-1; ++count)
        {
            Integer parentName = findMin();

            usedNode[parentName] = true;

            for (Integer childName=0; childName<nodeCount; ++childName)
            {
                if (adjMatrix[parentName][childName] > 0 && usedNode[childName] == false && nodeBag[childName] > adjMatrix[parentName][childName])
                {
                    parent[childName] = parentName;
                    nodeBag[childName] = adjMatrix[parentName][childName];
                }
            }
        }

        return parent;
    }

    private Integer findMin()
    {
        Integer minIndex = null;
        Integer minValue = Integer.MAX_VALUE;

        for (Integer i=0; i<nodeBag.length; ++i)
        {
            if (!usedNode[i] && minValue > nodeBag[i])
            {
                minValue = nodeBag[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
}