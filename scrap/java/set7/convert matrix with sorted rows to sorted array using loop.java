// convert matrix with sorted rows to sorted array using loop
// r2, q2, set7 amazon

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Integer[][] array = new Integer[][]{
                {1,3,5,7},
                {11,13,15,17},
                {2,4,6,8}
        };

        Integer rowCount = array.length;
        Integer columnCount = array[0].length;
        List<Integer> list = new ArrayList<>();
        List<Integer> columnCounters = new ArrayList<>();
        for (int i=0; i<rowCount; ++i)
        {
            columnCounters.add(0);
        }

        Boolean stopProcessing = false;
        while (!stopProcessing)
        {
            TreeMap<Integer, Integer> valueColumnIndexMap = new TreeMap<>();
            for(Integer r=0; r<rowCount; ++r)
            {
                if (columnCounters.get(r) < columnCount) {
                    valueColumnIndexMap.put(array[r][columnCounters.get(r)], r);
                }
            }

            Map.Entry<Integer, Integer> entry = valueColumnIndexMap.firstEntry();
            if (entry != null) {
                list.add(entry.getKey());
                columnCounters.set(entry.getValue(), columnCounters.get(entry.getValue()) + 1);
            }
            else
            {
                stopProcessing = true;
            }
        }

        for (Integer i : list)
        {
            System.out.print(i + " ");
        }
    }
}