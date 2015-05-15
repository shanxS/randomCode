// cost of a stock on each day is given in an array - find the max profit that you can make
// f2f 1, q2

public class Main
{
    public static void main(String[] wer)
    {
        Integer[] array = new Integer[]{50,70,45,75,10,45};

        Integer rightMax = array[array.length - 1];
        for (Integer i=array.length - 2; i>=0; --i)
        {
            Integer thisValue = array[i];
            array[i] = rightMax - array[i];

            if (thisValue > rightMax)
            {
                rightMax = thisValue;
            }
        }

        Integer max = Integer.MIN_VALUE;
        for (Integer i=0; i<array.length-1; ++i)
        {
            if (array[i] > max)
            {
                max = array[i];
            }
        }

        System.out.print(max);
    }
}