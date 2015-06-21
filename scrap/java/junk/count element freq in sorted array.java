// count element freq in sorted array

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1, 1, 2, 2, 2, 2, 3};
        ModifiedBS bs = new ModifiedBS(array);

        Integer cursor = 0;
        while (cursor < array.length)
        {
            Integer lastIndex = bs.findLastIndex(cursor, array[cursor]);
            System.out.println(array[cursor] + " :" + (lastIndex-cursor+1));

            cursor = lastIndex+1;
        }
    }
}

class ModifiedBS
{
    private Integer[] array;

    public ModifiedBS(Integer[] array)
    {
        this.array = array;
    }

    public Integer findLastIndex(Integer startIndex, Integer target)
    {
        Integer lastKnownIndex = startIndex;
        Integer endIndex = array.length-1;

        while (startIndex <= endIndex)
        {
            Integer mid = startIndex + (endIndex-startIndex)/2;

            if (array[mid] > target)
            {
                endIndex = mid-1;
            }
            else if (array[mid] == target)
            {
                lastKnownIndex = mid;
                startIndex = mid+1;
            }
        }

        return lastKnownIndex;
    }
}