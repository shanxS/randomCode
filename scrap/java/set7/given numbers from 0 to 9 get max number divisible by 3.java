// r1, q1, set7, amazon
// given numbers from 0 to 9 get max number divisible by 3

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Integer[] numbers = new Integer[] {7, 7, 6, 0, 1};
        //Integer[] numbers = new Integer[] {7, 7, 6};
        //Integer[] numbers = new Integer[] {7, 7, 7, 6};;
        Integer[] numbers = new Integer[] {7, 7, 7, 6, 8};
        //Integer[] numbers = new Integer[] {7, 7, 7, 6, 5};
        MaxGenerator mg = new MaxGenerator(Arrays.asList(numbers));
        mg.printRemainderMap();

        for (Integer i : mg.generate())
        {
            System.out.print(i);
        }
    }
}

class MaxGenerator
{
    private List<Integer> numbers;
    Map<Integer, List<Integer>> remainderMap;

    public MaxGenerator(List<Integer> numbers)
    {
        this.numbers = numbers;
        this.remainderMap = new HashMap<>();
        remainderMap.put(0, new ArrayList<>());
        remainderMap.put(1, new ArrayList<>());
        remainderMap.put(2, new ArrayList<>());

        computeRemainderMap();
    }

    public List<Integer> generate()
    {
        List<Integer> auxilaryList = new ArrayList<>();

        Integer sumOfNunbers = 0;
        for (Integer number : numbers)
        {
            sumOfNunbers += number;
        }

        if (sumOfNunbers%3 == 0)
        {
            auxilaryList.addAll(remainderMap.get(0));
            auxilaryList.addAll(remainderMap.get(1));
            auxilaryList.addAll(remainderMap.get(2));
        }
        else if (sumOfNunbers%3 == 2)
        {
            if (remainderMap.get(2).size() >= 0)
            {
                Collections.sort(remainderMap.get(2));
                remainderMap.get(2).remove(0);
            }
            else if (remainderMap.get(1).size() >= 1)
            {
                Collections.sort(remainderMap.get(1));
                remainderMap.get(1).remove(0);
                remainderMap.get(1).remove(0);
            }
            else
            {
                return new ArrayList<>();
            }

            auxilaryList.addAll(remainderMap.get(0));
            auxilaryList.addAll(remainderMap.get(1));
            auxilaryList.addAll(remainderMap.get(2));
        }
        else if (sumOfNunbers%3 == 1)
        {
            if (remainderMap.get(1).size() >= 0)
            {
                Collections.sort(remainderMap.get(1));
                remainderMap.get(1).remove(0);
            }

            auxilaryList.addAll(remainderMap.get(0));
            auxilaryList.addAll(remainderMap.get(1));
            auxilaryList.addAll(remainderMap.get(2));
        }

        Collections.sort(auxilaryList);
        Collections.reverse(auxilaryList);

        return auxilaryList;
    }

    public void printRemainderMap()
    {
        for (Map.Entry<Integer, List<Integer>> entry : remainderMap.entrySet())
        {
            System.out.print(entry.getKey() + " - ");
            for (Integer numbeer : entry.getValue())
            {
                System.out.print(" " + numbeer);
            }
            System.out.println();
        }
    }

    private void computeRemainderMap()
    {
        for(Integer number : numbers)
        {
            Integer remainder = number%3;
            remainderMap.get(remainder).add(number);
        }
    }
}