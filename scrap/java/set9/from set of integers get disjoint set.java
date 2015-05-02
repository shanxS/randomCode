// from set of integers get disjoint set
// r1, q2, set9, amazon

import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Integer[][] array = new Integer[][]{
                {1,2,3,4,5,6},
                {2,5,10},
                {10,3,6},
                {3,4,8,11}
        };

        Isolater isolater = new Isolater(array);
        isolater.isolate().stream().forEach(x -> System.out.print(x));
    }
}

class Isolater
{
    Map<Integer, List<Integer>> valueArrayMap;
    Map<Integer, Integer> candidateRowsCollision;
    Integer[][] table;

    public Isolater(Integer[][] array)
    {
        this.valueArrayMap = new HashMap<>();
        this.candidateRowsCollision = new HashMap<>();
        this.table = new Integer[array.length][array.length];
        for (Integer r=0; r<array.length; ++r)
        {
            Arrays.fill(table[r], 0);

            candidateRowsCollision.put(r, 0);

            for (Integer c=0; c<array[r].length; ++c)
            {
                List<Integer> list = valueArrayMap.get(array[r][c]);
                if (list == null)
                {
                    list = new ArrayList<>();
                    valueArrayMap.put(array[r][c], list);
                }
                list.add(r);
            }
        }

        fillTable();
    }

    public Set<Integer> isolate()
    {
        getCollision();

        while(haveCollision())
        {
            resolveCollision();
            getCollision();
        }

        return candidateRowsCollision.keySet();
    }

    private void resolveCollision()
    {
        Integer maxIndex = -1;
        Integer maxValue = -1;

        for (Map.Entry<Integer, Integer> entry : candidateRowsCollision.entrySet())
        {
            if (entry.getValue() > maxValue)
            {
                maxValue = entry.getValue();
                maxIndex = entry.getKey();
            }
        }

        if (maxIndex >= 0)
        {
            candidateRowsCollision.remove(maxIndex);
            updateTable(maxIndex);
        }
    }

    private void updateTable(Integer c)
    {
        for(Integer r=0; r<table.length; ++r)
        {
            table[r][c] = 0;
        }
    }

    private boolean haveCollision()
    {
        for (Map.Entry<Integer, Integer> entry : candidateRowsCollision.entrySet())
        {
            if (entry.getValue() != 0)
            {
                return true;
            }
        }

        return false;
    }

    private void getCollision()
    {
        for(Map.Entry<Integer, Integer> entry : candidateRowsCollision.entrySet())
        {
            Integer index = entry.getKey();
            Integer sum = 0;
            for(Integer i=0; i<table[index].length; ++i)
            {
                sum += table[index][i];
            }
            candidateRowsCollision.put(index, sum);
        }
    }

    private void fillTable()
    {
        for (Map.Entry<Integer, List<Integer>> entry : valueArrayMap.entrySet())
        {
            if (entry.getValue().size() > 0)
            {
                populateForCollision(entry.getValue());
            }
        }
    }

    private void populateForCollision(List<Integer> list)
    {
        for (Integer i=0; i<list.size()-1; ++i)
        {
            for (Integer j=i+1; j<list.size(); ++j)
            {
                ++table[list.get(i)][list.get(j)];
                ++table[list.get(j)][list.get(i)];
            }
        }
    }
}