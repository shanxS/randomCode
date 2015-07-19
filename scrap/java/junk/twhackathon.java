import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main
{
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] er)
    {
        try
        {
            String s = bufferedReader.readLine();
            Integer[] num = new Integer[s.length()];
            Integer lastCount = 0;

            for (int i=s.length()-1; i>=0; --i)
            {
                switch (s.charAt(i))
                {
                    case '2':
                    case '4':
                    case '6':
                    case '8':
                        ++lastCount;
                }

                num[i] = lastCount;
            }

            for (Integer i : num)
            {
                System.out.print(i + " ");
            }
        }
        catch (Exception e)
        {
            System.out.print("exception in buffered reader");
        }

    }
}