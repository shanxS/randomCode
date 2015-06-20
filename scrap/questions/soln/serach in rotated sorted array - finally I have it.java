// serach in rotated sorted array - finally I have it
// code question: 107

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {5,4,3,2,1};
        PivotedSortedSearcher pss = new PivotedSortedSearcher();
        System.out.print(pss.find(array, 2));
    }
}

class PivotedSortedSearcher
{
    public Boolean find (Integer[] array, Integer target)
    {
        if (array.length < 2)
        {
            return (array[0] == target);
        }

        Integer infexionIndex = fetchInflexionIndex(array);

        if (!binsrySearch(array, 0, infexionIndex, target))
        {
            return binsrySearch(array, infexionIndex, array.length-1, target);
        }

        return true;
    }

    private boolean binsrySearch(Integer[] array, Integer start, Integer end, Integer target)
    {
        if (end - start == 0)
        {
            return false;
        }
        else  if (end - start == 1)
        {
            return (array[end] == target) || (array[start] == target);
        }

        Boolean isIncreasingTrend = array[start] < array[start+1];

        while (start <= end)
        {
            Integer mid = (start+end)/2;

            if (array[mid] == target)
            {
                return true;
            }
            else if (array[mid] < target)
            {
                if (isIncreasingTrend)
                {
                    start = mid + 1;
                }
                else
                {
                    end = mid-1;
                }
            }
            else if (array[mid] > target)
            {
                if (isIncreasingTrend)
                {
                    end = mid - 1;
                }
                else
                {
                    start = mid+1;
                }
            }
        }

        return false;
    }

    private Integer fetchInflexionIndex(Integer[] array)
    {
        Integer cursor  = 1;
        Boolean trend = array[cursor-1] < array[cursor];

        while (cursor < array.length)
        {
            if (array[cursor-1] < array[cursor] != trend)
            {
                break;
            }
            ++cursor;
        }

        return cursor-1;
    }

}