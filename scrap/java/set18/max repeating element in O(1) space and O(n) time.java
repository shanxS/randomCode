// max repeating element in O(1) space and O(n) time
// done in lieu of r3, q3. set18 because the question makes no sense

class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[] {2, 3, 3, 5, 3, 4, 1, 7};
        Integer range = 7;

        for (Integer i=0; i<array.length; ++i)
        {
            array[array[i] % range] += range;
        }

        Integer max = Integer.MIN_VALUE;
        Integer maxIndex = -1;
        for (Integer i=0; i<array.length; ++i)
        {
            if (array[i] > max)
            {
                max = array[i];
                maxIndex = i;
            }
        }

        System.out.print("max: " + maxIndex);
    }
}