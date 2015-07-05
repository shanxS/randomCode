// kruskal MST

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] adjMatrix = {
                {0,2,3,6},
                {2,0,4,0},
                {3,4,0,5},
                {6,0,5,0}
        };

//        Integer[][] adjMatrix = {
//                {0,1,3},
//                {1,0,2},
//                {3,2,0}
//        };

        KruskalMinimumSpanningTree mst = new KruskalMinimumSpanningTree();
        Integer[][] mt = mst.findMinimumSpanningTree(adjMatrix);
        for(Integer r=0; r<mt.length; ++r)
        {
            for (Integer c=0; c<mt.length; ++c)
            {
                System.out.print(mt[r][c] + " ");
            }
            System.out.println();
        }
    }
}

class KruskalMinimumSpanningTree
{
    private Set<Edge> edges;
    private Map<Integer, Node> nodeNameNodeMap;

    public Integer[][] findMinimumSpanningTree(Integer[][] adjMatrix)
    {
        final Integer nodeCount = adjMatrix.length;

        nodeNameNodeMap = new HashMap<>();
        for(Integer nodeName=0; nodeName<nodeCount; ++nodeName)
        {
            nodeNameNodeMap.put(nodeName, new Node(nodeName));
        }

        edges = new TreeSet<>();
        for (Integer fromName = 0; fromName < nodeCount; ++fromName)
        {
            for (Integer toName = 0; toName < nodeCount; ++toName)
            {
                if (adjMatrix[fromName][toName] > 0)
                {
                    edges.add(new Edge(adjMatrix[fromName][toName], fromName, toName));
                }
            }
        }

        Set<Edge> minSpanningEdges = new TreeSet<>();
        for (Edge edge : edges)
        {
            if (minSpanningEdges.size() == nodeCount-1)
            {
                break;
            }

            if (!hasLoop(edge.getFrom(), edge.getTo()))
            {
                minSpanningEdges.add(edge);
                union(edge.getFrom(), edge.getTo());
            }
        }

        return getMinimumSpannigTree(minSpanningEdges);
    }

    private Integer[][] getMinimumSpannigTree(Set<Edge> edges)
    {
        final Integer nodeCount = edges.size() + 1;
        Integer[][] adjMatrix = new Integer[nodeCount][nodeCount];

        for (Edge edge : edges)
        {
            adjMatrix[edge.getFrom()][edge.getTo()] = 1;
        }

        for (Integer r=0; r<nodeCount; ++r)
        {
            for(Integer c=0; c<nodeCount; ++c)
            {
                if (adjMatrix[r][c] == null)
                {
                    adjMatrix[r][c] = 0;
                }
            }
        }

        return adjMatrix;
    }

    private void union(Integer fromName, Integer toName)
    {
        Node fromGroupHead = getGroupHead(fromName);
        Node toGroupHead = getGroupHead(toName);

        Integer newSubNodeCount = fromGroupHead.getSubNodeCount() + toGroupHead.getSubNodeCount();
        if (fromGroupHead.getSubNodeCount() > toGroupHead.getSubNodeCount())
        {
            toGroupHead.setParent(fromGroupHead);
            toGroupHead.setSubNodeCount(newSubNodeCount);
        }
        else
        {
            fromGroupHead.setParent(toGroupHead);
            fromGroupHead.setSubNodeCount(newSubNodeCount);
        }

    }

    private boolean hasLoop(Integer fromName, Integer toName)
    {
        Node fromGroupHead = getGroupHead(fromName);
        Node toGroupHead = getGroupHead(toName);

        if (fromGroupHead.getNodeName() == toGroupHead.getNodeName())
        {
            return true;
        }

        return false;
    }

    private Node getGroupHead(Integer nodeName)
    {
        Node node = nodeNameNodeMap.get(nodeName);
        Node parentNode = node;

        while (!parentNode.isHead())
        {
            parentNode = parentNode.getParent();
        }

        if (!node.isHead() && node.getParent().getNodeName() != parentNode.getNodeName())
        {
            node.setParent(parentNode);
        }

        return parentNode;
    }

    private class Edge implements Comparable
    {
        private Integer weight, from, to;

        public Edge(Integer weight, Integer from, Integer to)
        {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o)
        {
            if (o instanceof  Edge)
            {
                Edge otherEdge = (Edge) o;
                if ((otherEdge.getFrom() == getFrom() && otherEdge.getTo() == getTo())
                        || (otherEdge.getFrom() == getTo() && otherEdge.getTo() == getFrom()))
                {
                    return true;
                }
            }

            return false;
        }

        @Override
        public int compareTo(Object o)
        {
            Edge otherEdge = (Edge) o;
            if (otherEdge.getWeight() == getWeight())
            {
                return 0;
            }
            else if (otherEdge.getWeight() < getWeight())
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }

        public Integer getWeight()
        {
            return weight;
        }

        public Integer getFrom()
        {
            return from;
        }

        public Integer getTo()
        {
            return to;
        }
    }
}

class Node
{
    private Integer nodeName, subNodeCount;
    private Node parent;


    public Node(Integer nodeName)
    {
        this.nodeName = nodeName;
        subNodeCount = 1;
    }

    public boolean isHead()
    {
        return parent == null;
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
    }

    public void setSubNodeCount(Integer subNodeCount)
    {
        this.subNodeCount = subNodeCount;
    }

    public Integer getNodeName()
    {
        return nodeName;
    }

    public Integer getSubNodeCount()
    {
        return subNodeCount;
    }

    public Node getParent()
    {
        return parent;
    }
}