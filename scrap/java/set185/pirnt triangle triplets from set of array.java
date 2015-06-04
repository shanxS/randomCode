// pirnt triangle triplets from set of array
// r2, q1, set185

import java.util.Arrays;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{10, 21, 22, 100, 101, 200, 300};
        Arrays.sort(array);

        Integer count = 0;
        for (Integer i=0; i<array.length-2; ++i)
        {
            Integer k = i+2;
            for (Integer j=i+1; j<array.length-1; ++j)
            {
                while(k<array.length && array[i] + array[j] > array[k])
                {
                    ++k;
                }

                count += k-j-1;
            }
        }

        System.out.print(count);
    }
}