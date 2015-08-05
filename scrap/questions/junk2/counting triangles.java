// counting triangles

public class Main
{
    private static Integer[] ar = {10, 21, 22, 100, 101, 200, 300};
    public static void main(String[] er)
    {
        Integer count = 0;

        for (int i=0; i<ar.length-2; ++i)
        {
            for (int j=i+1; j<ar.length-1; ++j)
            {
                int sum = ar[i] + ar[j];
                int k = j+1;
                if (k < ar.length && ar[k] > sum)
                {
                    continue;
                }

                while (k < ar.length && ar[k] < sum )
                {
                    ++k;
                }

                count += k - j - 1;
            }
        }

        System.out.print(count);
    }
}