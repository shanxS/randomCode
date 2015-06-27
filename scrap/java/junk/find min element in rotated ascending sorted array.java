// find min element in rotated ascending sorted array

public class Main
{
    private static Integer[] array = {2,3,4,5,1};

    public static void main(String[] er)
    {
        System.out.print(array[search()]);
    }

    private static Integer search()
    {
        Integer startIndex = 0;
        Integer endIndex = array.length-1;

        while (startIndex < endIndex)
        {
            Integer midIndex = startIndex + (endIndex-startIndex)/2;

            if (midIndex < endIndex && array[midIndex] > array[midIndex+1])
            {
                return midIndex+1;
            }
            else if (midIndex > startIndex && array[midIndex] < array[midIndex-1])
            {
                return midIndex;
            }
            else
            {
                if (array[midIndex] < array[endIndex])
                {
                    endIndex = midIndex-1;
                }
                else
                {
                    startIndex = midIndex + 1;
                }
            }
        }

        // for startIndex == endIndex
        return startIndex;
    }
}