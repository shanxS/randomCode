// union find with Union By Rank and Path Compression

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] adjMatrix = {
                {0,1,1},
                {1,0,0},
                {1,0,0}
        };

//        Integer[][] adjMatrix = {
//                {0,1,1},
//                {1,0,1},
//                {1,1,0}
//        };

        UnionFind uf = new UnionFind();
        System.out.print(uf.hasLoop(adjMatrix));
    }
}

class UnionFind
{
    private Map<Integer, Node> nodeNameNodeMap;
    private List<Edge> edges;
    private Integer[][] adjMatrix;

    public boolean hasLoop(Integer[][] adjMatrix)
    {
        Integer nodeCount = adjMatrix.length;
        this.adjMatrix = adjMatrix;

        nodeNameNodeMap = new HashMap<>();
        for (Integer nodeName = 0; nodeName<nodeCount; ++nodeName)
        {
            nodeNameNodeMap.put(nodeName, new Node(nodeName));
        }

        edges = new ArrayList<>();
        for (Integer fromNodeName = 0; fromNodeName<nodeCount; ++fromNodeName)
        {
            for (Integer toNodeName = 0; toNodeName<nodeCount; ++toNodeName)
            {
                if (adjMatrix[fromNodeName][toNodeName] == 1 && (!edges.contains(new Edge(toNodeName, fromNodeName))))
                {
                    edges.add(new Edge(fromNodeName, toNodeName));
                }
            }
        }

        for (Edge edge : edges)
        {
            if (formLoop(edge.from, edge.to))
            {
                return true;
            }

            union(edge.from, edge.to);
        }

        return false;
    }

    private boolean formLoop(Integer fromNodeName, Integer toNodeName)
    {
        Node fromNodeGroupHead = findNodeGroupHead(fromNodeName);
        Node toNodeGroupHead = findNodeGroupHead(toNodeName);
        
        if (fromNodeGroupHead.getNodeName() == toNodeGroupHead.getNodeName())
        {
            return true;
        }
        
        return false;
    }

    private void union(Integer fromNodeName, Integer toNodeName)
    {
        Node fromNodeGroupHead = findNodeGroupHead(fromNodeName);
        Node toNodeGroupHead = findNodeGroupHead(toNodeName);

        Integer newSubNodeCout = fromNodeGroupHead.getSubNodeCount() + toNodeGroupHead.getSubNodeCount();
        if (fromNodeGroupHead.getSubNodeCount() > toNodeGroupHead.getSubNodeCount())
        {
            toNodeGroupHead.setParent(fromNodeGroupHead);
            toNodeGroupHead.setSubNodeCount(newSubNodeCout);
        }
        else
        {
            fromNodeGroupHead.setParent(toNodeGroupHead);
            fromNodeGroupHead.setSubNodeCount(newSubNodeCout);
        }
    }

    private Node findNodeGroupHead(Integer nodeName)
    {
        Node node = nodeNameNodeMap.get(nodeName);
        Node headNode = node;

        while (!headNode.isHead())
        {
            headNode = headNode.getParent();
        }

        if (!node.isHead() && node.getParent().getNodeName() != headNode.getNodeName())
        {
            node.setParent(headNode);
        }

        return headNode;
    }

    private class Edge
    {
        private Integer from, to;

        public Edge(Integer from, Integer to)
        {
            this.from = from;
            this.to = to;
        }

        public Integer getFrom()
        {
            return from;
        }

        public Integer getTo()
        {
            return to;
        }

        @Override
        public boolean equals(Object o)
        {
            Edge otherEdge = (Edge) o;
            if ((otherEdge.from == from && otherEdge.to == to)
                    || (otherEdge.from == to && otherEdge.to == from))
            {
                return true;
            }
            else
            {
                return false;
            }
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
        this.subNodeCount = 1;
    }

    public boolean isHead()
    {
        return parent == null;
    }

    public Integer getNodeName()
    {
        return nodeName;
    }

    public Node getParent()
    {
        return parent;
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
    }

    public Integer getSubNodeCount()
    {
        return subNodeCount;
    }

    public void setSubNodeCount(Integer subNodeCount)
    {
        this.subNodeCount = subNodeCount;
    }
}