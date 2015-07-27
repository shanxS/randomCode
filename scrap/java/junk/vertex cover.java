// vertex cover

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main
{
    public static void main(String[] er)
    {
//        Integer[][] ar = {
//                {0,1,1,1},
//                {1,0,1,1},
//                {1,1,0,1},
//                {1,1,1,0},
//        };

//        Integer[][] ar = {
//                {0,1,0,0},
//                {1,0,1,0},
//                {0,1,0,0},
//                {0,0,0,0},
//        };

        Integer[][] ar = {
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},

                {1,1,1,0,1,1,1},

                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0}
        };

        VertexCover vc = new VertexCover();
        for(Integer i : vc.cover(ar))
        {
            System.out.print(i + " ");
        }
    }
}

class VertexCover
{
    private Integer[][] adjMatrix;
    private Set<Edge> edges;
    private List<Integer> vertices;
    public List<Integer> cover(Integer[][] ar)
    {
        adjMatrix = ar;
        edges = makeEdges();

        vertices = new ArrayList<>();
        for (Integer i=0; i<ar.length; ++i)
        {
            vertices.add(i);
        }

        List<Integer> thisCover = new ArrayList<>();
        while (edges.size() > 0)
        {
            thisCover.add(findNext());
        }

        return thisCover;
    }

    private Integer findNext()
    {
        Integer maxEdgeCount = 0;
        Integer thisParent = -1;

        for (Integer vertex : vertices)
        {
            Integer count = 0;
            for (Integer i=0; i<adjMatrix.length; ++i)
            {
                if (adjMatrix[vertex][i] == 1 && edges.contains(new Edge(vertex, i)))
                {
                    ++count;
                }
            }

            if (count > maxEdgeCount)
            {
                maxEdgeCount = count;
                thisParent = vertex;
            }
        }

        vertices.remove(thisParent);
        for (Integer i=0; i<adjMatrix.length; ++i)
        {
            if (adjMatrix[thisParent][i] == 1)
            {
                edges.remove(new Edge(thisParent, i));
            }
        }

        return thisParent;
    }

    private Set<Edge> makeEdges()
    {
        Set<Edge> edges = new HashSet<>();
        for (Integer parent = 0; parent < adjMatrix.length; ++parent)
        {
            for (Integer child = 0; child < adjMatrix.length; ++child)
            {
                if (adjMatrix[parent][child] == 1)
                {
                    edges.add(new Edge(parent, child));
                }
            }
        }

        return edges;
    }
}

class Edge
{
    final public int v, u;

    public Edge(Integer v, Integer u)
    {
        this.v = v;
        this.u = u;
    }

    @Override
    public boolean equals(Object o)
    {
        Edge otherEdge = (Edge) o;
        if ((otherEdge.v == v && otherEdge.u == u)
                || (otherEdge.v == u && otherEdge.u == v))
        {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Math.min(v,u);
        result = prime * result + Math.max(v,u);
        return result;
    }
}