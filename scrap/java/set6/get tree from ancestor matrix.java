// get tree from ancestor array
// 
// my def. for ancestor array is that root has no ancestors and so on...
// http://www.ritambhara.in/build-binary-tree-from-ancestor-matrics/

import java.util.*;

public class Main {

    private static Integer[] nodeParentArray;
    private static Map<Integer, Integer> nodeLevelMap;
    private final static Integer TOP = -1;

    public static void main(String[] args)
    {
        Integer[][] ancestorMatrix = new Integer[][] {
                {0,0,0, 1,1,1, 0,0},
                {0,0,0, 1,1, 0, 1, 0},
                {0,0,0, 1, 0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0, 1, 0,0,0,0},
                {0,0,0, 1,1, 0,0,0},
                {0,0,0, 1,1, 0,0,0},
                {0,0, 1,1, 0,0,0,0},
        };

        Map<Integer, Integer> rowNameMap = new HashMap<>();
        rowNameMap.put(0,1);
        rowNameMap.put(1, 2);
        rowNameMap.put(2, 3);
        rowNameMap.put(3, 6);
        rowNameMap.put(4, 8);
        rowNameMap.put(5, 9);
        rowNameMap.put(6, 10);
        rowNameMap.put(7, 11);

        nodeLevelMap = new HashMap<>();
        for (Integer r=0; r<ancestorMatrix.length; ++r)
        {
            Integer level = 0;
            for (Integer value : ancestorMatrix[r])
            {
               if (value == 1)
               {
                   ++level;
               }
            }

            nodeLevelMap.put(r, level);
        }

        nodeParentArray = new Integer[ancestorMatrix.length];
        for (Map.Entry<Integer, Integer> entry : nodeLevelMap.entrySet())
        {
            Integer node = entry.getKey();
            Integer level = entry.getValue();
            Integer parent = getParent(ancestorMatrix[node], level);
            nodeParentArray[node] = parent;
        }

        Map<Integer, List<Integer>> parentChildMap = new HashMap<>();
        for (Integer node=0; node<nodeParentArray.length; ++node)
        {
            List<Integer> childLIst = parentChildMap.get(nodeParentArray[node]);
            if (childLIst == null)
            {
                childLIst = new ArrayList<>();
                parentChildMap.put(nodeParentArray[node], childLIst);
            }
            childLIst.add(node);
        }

        for (Map.Entry<Integer, List<Integer>> entry : parentChildMap.entrySet())
        {
            System.out.print(rowNameMap.get(entry.getKey()) + " - ");
            for (Integer child : entry.getValue())
            {
                System.out.print(rowNameMap.get(child) + ", ");
            }

            System.out.println();
        }
    }

    private static Integer getParent(Integer[] ancestors, Integer level) {
        for(Integer ancestor=0; ancestor<ancestors.length; ++ancestor)
        {
            if (ancestors[ancestor] == 1 && nodeLevelMap.get(ancestor) == level-1)
            {
                return ancestor;
            }
        }

        return TOP;
    }
}
