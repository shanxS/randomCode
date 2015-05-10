// first subarray which has a zero sum in an array - generalized
// r3, q3, set15

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{4,2,-3,1,4,2,2,5};
        SumFinder sumFinder = new SumFinder(array);
        //sumFinder.find(0);
        sumFinder.find(7);
    }
}

class SumFinder
{
    private Integer[] array;
    private Integer[] sumArray;
    private Integer validIndex;

    public SumFinder(Integer[] array)
    {
        this.array = array;
        this.sumArray = new Integer[array.length];
        for (Integer i=0; i<sumArray.length; ++i)
        {
            sumArray[i] = 0;
        }
        this.validIndex = 0;
    }

    public void find(Integer target)
    {
        sumAll();
        while(validIndex < sumArray.length)
        {
            testAndPrint(target);
            dropFromLeft();
        }
    }

    private void dropFromLeft()
    {
        Integer value = array[validIndex];
        for (Integer i=validIndex+1; i<sumArray.length; ++i)
        {
            sumArray[i] -= value;
        }

        ++validIndex;
    }

    private void testAndPrint(Integer target)
    {
        Integer cursor = validIndex;
        Integer previousStart = cursor;
        while( cursor < sumArray.length)
        {
            if (sumArray[cursor] == target)
            {
                for (Integer i=previousStart; i<=cursor; ++i)
                {
                    System.out.print(array[i] + " ");
                }

                System.out.println();
                previousStart = cursor+1;
            }

            ++cursor;
        }
    }

    private void sumAll()
    {
        sumArray[0] = array[0];

        for (Integer i=1; i<array.length; ++i)
        {
            sumArray[i] = sumArray[i-1] + array[i];
        }
    }
}
