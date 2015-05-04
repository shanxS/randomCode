// finding all triplets in array - works with duplicate as well
// set10, r2, q3

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{1,1,2,3,4,4,8};

        for (Integer i=array.length-1; i>=0; --i)
        {
            Integer sum = array[i];
            Integer startIndex = 0;
            Integer endIndex = i-1;

            while(startIndex < endIndex)
            {
                Integer thisSum = array[startIndex] + array[endIndex];
                if (thisSum == sum)
                {
                    System.out.println("pair " + array[startIndex] + " + " + array[endIndex] + " = " + sum);
                    ++startIndex;
                    --endIndex;
                }
                else if (thisSum < sum)
                {
                    ++startIndex;
                }
                else
                {
                    --endIndex;
                }
            }
        }
    }
}
