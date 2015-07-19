import java.util.ArrayList;
import java.util.List;

public class Main
{
    private static boolean[] T;

    public static void main(String[] er)
    {
//        Integer[] ar = {2,8,6};
        Integer[] ar = {3,2,1,1,1,2};
        List<Integer> list = new ArrayList<>();
        for (Integer i : ar)
        {
            list.add(i);
        }



        System.out.print(partition(list));
    }

    private static boolean partition(List<Integer> C)
    {
        int n = C.size();
        int N = 0;
        for (int i=0; i<n; ++i) N += C.get(i);

        T = new boolean[N+1];
        T[0] = true;

        for (int i=0; i<n; ++i)
        {
            for (int j=N-C.get(i); j>=0; --j)
            {
                if (T[j])
                {
                    T[j + C.get(i)] = true;
                }
            }
        }

        return T[N/2];
    }
}