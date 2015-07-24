import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main
{
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static Integer[][] A, B, SUM;
    private static Map<Integer, Map<Integer, Integer[][]>> AMap, BMap;

    private static Integer R, C;

    public static void main(String[] er)
    {
        try
        {
            Integer T = Integer.parseInt(bufferedReader.readLine());
            AMap = new HashMap<>();
            BMap = new HashMap<>();

            for (int i=0; i<T; ++i)
            {
                String[] in = bufferedReader.readLine().split(" ");
                R = Integer.parseInt(in[0]);
                C = Integer.parseInt(in[1]);

                computeMatries();
                sumMatrices();
                System.out.println(findTrace());
            }
        }
        catch (Exception e)
        {
            System.out.print("exception in buffered reader");
        }

    }

    private static Integer findTrace()
    {
        Integer value = 0;

        Integer lim = Math.min(R, C);
        for (Integer r=0; r<lim; ++r)
        {
            value += SUM[r][r];
        }

        return value;
    }

    private static void sumMatrices()
    {
        SUM = new Integer[R][C];
        for (Integer r=0; r<R; ++r)
        {
            for (Integer c=0; c<C; ++c)
            {
                SUM[r][c] = A[r][c] + B[r][c];
            }
        }
    }

    private static void computeMatries()
    {
        if (AMap.get(R) != null
                && AMap.get(R).get(C) != null)
        {
            A = AMap.get(R).get(C);
            B = BMap.get(R).get(C);

            return;
        }

        A = new Integer[R][C];
        B = new Integer[R][C];

        Integer value = 1;
        Integer bC = 0, bR = 0;
        for (Integer aR=0; aR < R; ++aR)
        {
            for (Integer aC = 0; aC<C; ++aC)
            {
                A[aR][aC] = value;

                if (bR >= R)
                {
                    ++bC;
                    bR = 0;
                }
                B[bR][bC] = value;
                ++bR;

                ++value;
            }
        }

        HashMap<Integer, Integer[][]> AHash = new HashMap<>();
        AHash.put(C, A);
        AMap.put(R, AHash);

        HashMap<Integer, Integer[][]> BHash = new HashMap<>();
        BHash.put(C, B);
        BMap.put(R, BHash);
    }
}