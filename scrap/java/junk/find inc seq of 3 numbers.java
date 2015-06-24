// find inc seq of 3 numbers

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1,2,3,4};//{12, 11, 10, 5, 6, 2, 30};
        Integer[] decFormStart = new Integer[array.length];
        Integer[] incFromEnd = new Integer[array.length];

        Integer smallest = array[0];
        Integer smallestIndex = 0;
        for (Integer i=1; i<array.length; ++i)
        {
            if (smallest < array[i])
            {
                decFormStart[i] = smallestIndex;
            }
            else if (smallest > array[i])
            {
                smallest = array[i];
                smallestIndex = i;
            }
        }

        Integer largest = array[array.length-1];
        Integer largestIndex = array.length-1;
        for (Integer i=largestIndex-1; i>=0; --i)
        {
            if (largest > array[i])
            {
                incFromEnd[i] = largestIndex;
            }
            else if (largest < array[i])
            {
                largest = array[i];
                largestIndex = i;
            }
        }

        for (Integer i=0; i<array.length-1; ++i)
        {
            if (decFormStart[i] != null && incFromEnd[array.length-i-1] != null)
            {
                System.out.println(array[decFormStart[i]] + " " + array[i] + " " + array[incFromEnd[array.length - i - 1]]);
            }
        }
    }
}