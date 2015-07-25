// max coin value

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main
{
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static private Integer  N, M, A, B, C;
    public static void main(String[] er)
    {
        try
        {
            Integer T = Integer.parseInt(bufferedReader.readLine());

            for (int i=0; i<T; ++i)
            {
                String[] input = bufferedReader.readLine().split(" ");
                N = Integer.parseInt(input[0]);
                M = Integer.parseInt(input[1]);
                A = Integer.parseInt(input[2]);
                B = Integer.parseInt(input[3]);
                C = Integer.parseInt(input[4]);

                Integer cost = 0;
                if (C > A && C > B)
                {
                    if (N < M)
                    {
                        cost += 2 * N * C;
                        cost += (M-N) * B;
                    }
                    else
                    {
                        cost += 2 * M * C;
                        cost += (N-M) * A;
                    }
                }
                else
                {
                    cost += (A*N) + (B*M);
                }

                System.out.println(cost);
            }
        }
        catch (Exception e)
        {
            System.out.print("exception in buffered reader " + e.getMessage());
        }

    }
}