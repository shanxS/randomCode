// count number of triangles

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {10, 21, 22, 100, 101, 200, 300};
        Integer count = 0;

        for (Integer i=0; i<ar.length-2; ++i)
        {
            for (Integer j=i+1; j<ar.length-1; ++j)
            {
                Integer k = j+1;

                while (k<ar.length
                        && ar[i] + ar[j] > ar[k])
                {
                    ++k;
                }

                count += k - j - 1;
            }
        }

        System.out.print(count);
    }
}