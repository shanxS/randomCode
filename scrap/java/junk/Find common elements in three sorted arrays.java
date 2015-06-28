// Find common elements in three sorted arrays

public class Main
{
    private static Integer[] a0 = {1, 5, 5};
    private static Integer[] a1 = {3, 4, 5, 5, 10};
    private static Integer[] a2 = {5, 5, 10, 20};

//    private static Integer[] a0 = {1, 5, 10, 20, 40, 80};
//    private static Integer[] a1 = {6, 7, 20, 80, 100};
//    private static Integer[] a2 = {3, 4, 15, 20, 30, 70, 80, 120};

    public static void main(String[] er)
    {
        Integer c0 = 0, c1 = 0, c2 = 0;
        while (c0 < a0.length && c1 < a1.length && c2 < a2.length)
        {
            if (a0[c0] == a1[c1] && a1[c1] == a2[c2])
            {
                System.out.print(a2[c2] + " ");
                ++c0;

                if (!((c0 < a0.length && c1 < a1.length && c2 < a2.length)))
                {
                    break;
                }
            }

            Integer max = Math.max(a0[c0], Math.max(a1[c1], a2[c2]));

            while (c0 < a0.length && a0[c0] < max)
            {
                ++c0;
            }

            while (c1 < a1.length && a1[c1] < max)
            {
                ++c1;
            }

            while (c2 < a2.length && a2[c2] < max)
            {
                ++c2;
            }


        }
    }
}