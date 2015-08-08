sum of common elements in arrays

public class Main
{
    public static void main(String[] er)
    {
        int[] ar1 = {2, 3, 7, 10, 12}, ar2 = {1, 5, 7, 8};
        (new CommonSummer()).sum(ar1, ar2);
    }
}

class CommonSummer
{
    public void sum(int[] ar1, int[] ar2)
    {
        int c1 = 0, c2 = 0;

        int sum = 0;
        while (c1 < ar1.length && c2 < ar2.length)
        {
            if (ar1[c1] == ar2[c2])
            {
                sum += ar1[c1];
                ++c1;
                ++c2;
            }
            else if (ar1[c1] < ar2[c2])
            {
                while (c1 < ar1.length && (ar1[c1] < ar2[c2]))
                {
                    ++c1;
                }
            }
            else
            {
                while (c2 < ar2.length && (ar2[c2] < ar1[c1]))
                {
                    ++c2;
                }
            }
        }

        System.out.print(sum);
    }
}