// Check for Identical BSTs without building the trees
// code question: 79

import java.util.*;

public class Main
{
    public static void main(String[] er)
    {
//        Integer[] array1 = {2, 4, 1, 3};
//        Integer[] array2 = {2, 4, 3, 1};

        Integer[] array1 = {8, 3, 6, 1, 4, 7, 10, 14, 13};
        Integer[] array2 = {8, 10, 14, 3, 6, 4, 1, 7, 13};

        BSTEqualTester bet = new BSTEqualTester();
        System.out.print(bet.test(array1, array2));

    }
}

class BSTEqualTester
{
    private List<Integer> sortedNumbers;
    private Map<Integer, Integer> valueIndex1, valueIndex2;
    private Integer[] array1, array2;

    public boolean test(Integer[] array1, Integer[] array2)
    {
        this.array1 = array1;
        this.array2 = array2;

        populateSortedNumbers();
        populateValueIndices();

        return test();
    }

    private boolean test()
    {
        boolean status = true;

        for (Integer cursor = 0; cursor<sortedNumbers.size(); ++cursor)
        {
            Integer number = sortedNumbers.get(cursor);

            Integer nextBig = (cursor < sortedNumbers.size()-1) ? sortedNumbers.get(cursor + 1) : null;
            if (nextBig != null
                    && ((valueIndex1.get(number) > valueIndex1.get(nextBig)) != (valueIndex2.get(number) > valueIndex2.get(nextBig))))
            {
                status = false;
                break;
            }

            Integer previousSmall = (cursor > 0) ? sortedNumbers.get(cursor-1) : null;
            if (previousSmall != null
                    && ((valueIndex1.get(number) > valueIndex1.get(previousSmall)) != (valueIndex2.get(number) > valueIndex2.get(previousSmall))))
            {
                status = false;
                break;
            }
        }

        return status;
    }

    private void populateValueIndices()
    {
        valueIndex1 = new HashMap<>();
        for (Integer index = 0; index<array1.length; ++index)
        {
            valueIndex1.put(array1[index], index);
        }

        valueIndex2 = new HashMap<>();
        for (Integer index = 0; index<array2.length; ++index)
        {
            valueIndex2.put(array2[index], index);
        }
    }

    private void populateSortedNumbers()
    {
        sortedNumbers = new ArrayList<>();

        for (Integer value : array2)
        {
            sortedNumbers.add(value);
        }

        Collections.sort(sortedNumbers);
    }
}