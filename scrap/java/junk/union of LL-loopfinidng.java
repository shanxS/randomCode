import java.util.HashMap;
import java.util.Map;

public class Main
{
    private static Integer[][] adjMatrix = {
            {0,1,1},
            {1,0,0},
            {1,0,0}
    };
    private static final Integer nodeCount = adjMatrix.length;
    private static Map<Integer, Node> nodeNameNodeMap;

    public static void main(String[] er)
    {
        nodeNameNodeMap = new HashMap<>();
        for (Integer node = 0; node < nodeCount; ++node)
        {
            nodeNameNodeMap.put(node, new Node(node));
        }

        boolean noLoopFound = true;
        for (Integer parentNodeName = 0; parentNodeName<nodeCount && noLoopFound; ++parentNodeName)
        {
            for (Integer childNodeName = 0; childNodeName<nodeCount && noLoopFound; ++childNodeName)
            {
                if (adjMatrix[parentNodeName][childNodeName] == 1)
                {
                    adjMatrix[childNodeName][parentNodeName] = 0;
                    if(isLoop(parentNodeName, childNodeName))
                    {
                        noLoopFound = false;
                        break;
                    }

                    union(parentNodeName, childNodeName);
                }
            }
        }

        if (noLoopFound)
        {
            System.out.println("no loop found");
        }
        else
        {
            System.out.println("loop found");
        }
    }

    private static void union(Integer parentNodeName, Integer childNodeName)
    {
        Node parentNodeHead = findParent(parentNodeName);
        Node childNodeHead = findParent(childNodeName);

        if (parentNodeHead.getSubNodeCount() > childNodeHead.getSubNodeCount())
        {
            childNodeHead.setParent(parentNodeHead);
        }
        else
        {
            parentNodeHead.setParent(childNodeHead);
        }
    }

    private static Node findParent(Integer nodeName)
    {
        Node node = nodeNameNodeMap.get(nodeName);

        Node parentNode = node;
        while (!parentNode.isHead())
        {
            parentNode = parentNode.getParent();
        }

        if (!node.isHead() && node.getParent() != parentNode)
        {
            node.setParent(parentNode);
        }

        return parentNode;
    }

    private static boolean isLoop(Integer parentNodeName, Integer childNodeName)
    {
        Integer parentNodeGroupName = findParent(parentNodeName).getName();
        Integer childNodeGroupName = findParent(childNodeName).getName();

        return (parentNodeGroupName == childNodeGroupName);
    }
}

class Node
{
    private Integer name, subNodeCount;
    private Node parent;

    public Node(Integer name)
    {
        this.name = name;
        subNodeCount = 1;
    }

    public Integer getSubNodeCount()
    {
        return subNodeCount;
    }

    public void setSubNodeCount(Integer subNodeCount)
    {
        this.subNodeCount = subNodeCount;
    }

    public boolean isHead()
    {
        return parent == null;
    }

    public Integer getName()
    {
        return name;
    }

    public Node getParent()
    {
        return parent;
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
    }
}