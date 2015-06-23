// Find the two numbers with odd occurrences in an unsorted array
// code question: 114

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {4, 2, 4, 5, 2, 3, 3, 1};
        Integer xor2 = array[0];

        for (Integer i=1; i<array.length; ++i)
        {
            xor2 ^= array[i];
        }

        Integer bitSet = xor2 & ~(xor2-1);

        Integer x = 0, y=0;
        for (Integer i=0; i<array.length; ++i)
        {
            if ((bitSet & array[i]) != 0)
            {
                x ^= array[i];
            }
            else
            {
                y ^= array[i];
            }
        }

        System.out.print(x + " " + y);
    }
}