// set 4, q1, amazon , geek for geeks
// Given a 2D array containing only 0/1’s and each row is in sorted order. Find the row which contains maximum number of 1s.
// I was asked to code. Algo which I told was I will search position of first 1 in 1st row using binary search. And mark it. Now note that position check in 2nd row. If there is 1 for that position already found in 1st row, then binary search from 0 to that position else move to row number 3. Similarly continue further.
// http://www.geeksforgeeks.org/amazon-interview-set-4-2/

public class Main {

    public static void main(String[] args)
    {
        Integer[][] ones = new Integer[][]{
                {0,0,1,1,1},
                {0,1,1,1,1},
                {1,1,1,1,1},
                {0,0,0,1,1}};

        Integer prevMid = null;
        Integer resultRow = null;
        for (int i=0; i<ones.length; ++i)
        {
            Integer cahchePrevMid = BinarySearch.findFirst(ones[i], 1, prevMid);
            if ((cahchePrevMid != null && prevMid != null && cahchePrevMid < prevMid) || (resultRow == null && prevMid != null))
            {
                resultRow = i;
            }

            prevMid = cahchePrevMid;
        }

        System.out.print("index " + resultRow);
    }
}

class BinarySearch
{
    public static <E extends Comparable<E>> Integer findFirst(E[] array, E key, Integer lastFound)
    {
        Integer firstFoundIndex = null;

        Integer cache = search(array, key, lastFound);
        while (cache != null)
        {
            firstFoundIndex = cache;
            cache = search(array, key, firstFoundIndex);
        }

        return firstFoundIndex;
    }

    private static <E extends Comparable<E>> Integer search(E[] array, E key, Integer prevMid)
    {
        Integer index = null;
        Integer start = 0;

        Integer end = prevMid;
        if (end == null) {
            end = array.length - 1;
        }

        Integer mid = (start + end) / 2;

        while(start < end)
        {
            if (array[mid].compareTo(key) == 0)
            {
                index = mid;
                break;
            }
            else if (array[mid].compareTo(key) < 0)
            {
                start = mid + 1;
                mid = (start + end)/2;
            }
            else if (array[mid].compareTo(key) > 0)
            {
                end  = mid - 1;
                mid = (start + end)/2;
            }
        }

        return index;
    }
}