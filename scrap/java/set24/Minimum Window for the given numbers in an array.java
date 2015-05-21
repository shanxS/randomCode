// Minimum Window for the given numbers in an array
// quesiotn better explained here https://stackoverflow.com/questions/3382757/minimum-window-for-the-given-numbers-in-an-array
// my answer is better
// r2, q3, set24

import java.util.*;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1,3,5,0,2,3,1};
        Integer[] pattern = new Integer[]{1,3,2};
        RangeFinder rf = new RangeFinder();
        System.out.print(rf.findRange(array, pattern));
    }
}

class RangeFinder
{
    private Integer[] array;
    private List<Integer> bag;
    private Deque<Integer> indices;
    private Deque<Integer> numbers;
    private Integer indexRange;

    public Integer findRange(Integer[] array, Integer[] pattern)
    {
        this.array = array;
        this.bag = new ArrayList<Integer>(Arrays.asList(pattern));
        this.indices = new ArrayDeque<>();
        this.numbers = new ArrayDeque<>();
        this.indexRange = Integer.MAX_VALUE;

        for (Integer i=0; i<array.length; ++i)
        {
            if (bag.contains(array[i]))
            {
                indices.add(i);
                numbers.add(array[i]);
                bag.remove(array[i]);
            }

            if (bag.size() == 0)
            {
                Integer tempRange = indices.peekLast() - indices.peekFirst();
                if (tempRange < indexRange)
                {
                    indexRange = tempRange;
                }

                bag.addAll(numbers);
                indices.clear();
                numbers.clear();

                // to reconsider this poisiton
                --i;
            }

        }

        return indexRange;
    }

}
