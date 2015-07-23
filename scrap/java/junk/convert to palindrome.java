// convert to palindrome

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main
{
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] er)
    {
        try
        {
            Integer T = Integer.parseInt(bufferedReader.readLine());

            for (int i=0; i<T; ++i)
            {
                String str = bufferedReader.readLine();
                Integer aCost = Integer.parseInt(bufferedReader.readLine());;
                Integer bCost = Integer.parseInt(bufferedReader.readLine());;

                Integer cost = computeCost(str, aCost, bCost);
                System.out.println(cost);
            }
        }
        catch (Exception e)
        {
            System.out.print("exception in buffered reader");
        }

    }

    private static Integer computeCost(String str, Integer aCost, Integer bCost)
    {
        Integer cost = 0;

        Integer revCursor = (str.length()/2)-1;
        Integer fwdCursor = (str.length()/2);

        while (revCursor >= 0 && fwdCursor < str.length())
        {
            if (str.charAt(revCursor) == '/' || str.charAt(fwdCursor) == '/')
            {
                if (str.charAt(revCursor) == '/' && str.charAt(fwdCursor) == '/')
                {
                    cost += (Math.min(aCost, bCost) * 2);
                }
                else if (str.charAt(revCursor) == '/')
                {
                    cost += str.charAt(fwdCursor) == 'a' ? aCost : bCost;
                }
                else if (str.charAt(fwdCursor) == '/')
                {
                    cost += str.charAt(revCursor) == 'a' ? aCost : bCost;
                }
            }
            else if (str.charAt(revCursor) != str.charAt(fwdCursor))
            {
                cost = -1;
                break;
            }

            --revCursor;
            ++fwdCursor;
        }

        return cost;
    }
}