public class Main
{
    static Integer[] ar1 = {2, 3, 7, 10, 12, 15, 30, 34}, ar2 = {1, 5, 7, 8, 10, 15, 16, 19};//ar1 = {10, 12}, ar2 = {5, 7, 9};//ar1 = {2, 3, 7, 10, 12}, ar2 = {1, 5, 7, 8};

    public static void main(String[] er)
    {
        Integer sum1 = 0;
        Integer sum2 = 0;
        Integer maxSum = 0;

        Integer cursor1 = 0, cursor2 = 0;

        while (cursor1 < ar1.length && cursor2 < ar2.length)
        {
            if (ar1[cursor1] < ar2[cursor2])
            {
                while (cursor1 < ar1.length && ar1[cursor1] < ar2[cursor2])
                {
                    sum1 += ar1[cursor1];
                    ++cursor1;
                }
            }
            else if (ar1[cursor1] > ar2[cursor2])
            {
                while (cursor2 < ar2.length && ar1[cursor1] > ar2[cursor2])
                {
                    sum2 += ar2[cursor2];
                    ++cursor2;
                }
            }
            else if (ar1[cursor1] == ar2[cursor2])
            {
                if (sum1 > sum2)
                {
                    maxSum += sum1;
                }
                else
                {
                    maxSum += sum2;
                }

                maxSum += ar1[cursor1];

                sum1 = 0;
                sum2 = 0;
                ++cursor1;
                ++cursor2;
            }
        }

        while (cursor1 < ar1.length)
        {
            sum1 += ar1[cursor1];
            ++cursor1;
        }

        while (cursor2 < ar2.length)
        {
            sum2 += ar2[cursor2];
            ++cursor2;
        }

        if (sum1 > sum2)
        {
            maxSum += sum1;
        }
        else
        {
            maxSum += sum2;
        }

        System.out.print(maxSum);
    }
}