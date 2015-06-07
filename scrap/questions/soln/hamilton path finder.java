// hamilton path finder
// code question: 58

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] map = {
                {0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
        };

//        Integer[][] map = {
//                {0, 1, 0, 1, 0},
//                {1, 0, 1, 1, 1},
//                {0, 1, 0, 0, 1},
//                {1, 1, 0, 0, 0},
//                {0, 1, 1, 0, 0},
//        };

        HamiltonFinder hf = new HamiltonFinder();
        hf.findPath(map).stream().forEach(x -> System.out.print(x + " "));
    }
}

class HamiltonFinder
{
    private Integer[][] map;
    private Integer source;

    public List<Integer> findPath(Integer[][] amap)
    {
        this.map = amap;

        List<Integer> bag = new ArrayList<>();
        for (Integer i = 0; i < map.length; ++i)
        {
            bag.add(i);
        }

        for (Integer bagCursor=0; bagCursor<bag.size(); ++bagCursor)
        {
            Integer thisNode = bag.get(bagCursor);
            source = thisNode;
            List<Integer> newBag = new ArrayList<>(bag);
            newBag.remove(thisNode);
            for (Integer connectedNode : fetchConnectedNodes(thisNode))
            {
                List<Integer> forwardPath = find(connectedNode, newBag);
                if (forwardPath != null)
                {
                    forwardPath.add(thisNode);
                    return forwardPath;
                }
            }
        }

        return null;
    }

    private List<Integer> find(Integer thisNode, List<Integer> bag)
    {
        if (bag.size() == 1)
        {
            if (fetchConnectedNodes(thisNode).contains(source))
            {
                return bag;
            }
            else
            {
                return null;
            }
        }

        List<Integer> newBag = new ArrayList<>(bag);
        newBag.remove(thisNode);

        for (Integer connectedNode : fetchConnectedNodes(thisNode))
        {
            if (newBag.contains(connectedNode))
            {
                List<Integer> forwardPath = find(connectedNode, newBag);
                if (forwardPath != null)
                {
                    forwardPath.add(thisNode);
                    return forwardPath;
                }
            }
        }

        return null;
    }

    private List<Integer> fetchConnectedNodes(Integer startNode)
    {
        List<Integer> connectedNodes = new ArrayList<>();
        for (Integer c=0; c<map.length; ++c)
        {
            if (map[startNode][c] == 1)
            {
                connectedNodes.add(c);
            }
        }

        return connectedNodes;
    }
}