import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Main
{
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] er)
    {
        try
        {
            Integer M = Integer.parseInt(bufferedReader.readLine());
            String[] marutQualities = bufferedReader.readLine().split(" ");
            Map neededQualitues = new HashMap<>();
            for (String s : marutQualities)
            {
                neededQualitues.put(Integer.parseInt(s), 1);
            }

            Integer N = Integer.parseInt(bufferedReader.readLine());
            Integer considerableGirlCount = 0;
            while (N > 0)
            {
                String[] girlQualities = bufferedReader.readLine().split(" ");
                Integer foundCount = 0;
                if (girlQualities.length >= M)
                {
                    for (String s : girlQualities)
                    {
                        if (neededQualitues.keySet().contains(Integer.parseInt(s)))
                        {
                            ++foundCount;
                        }
                    }
                }

                if (foundCount == neededQualitues.size())
                {
                    ++considerableGirlCount;
                }

                --N;
            }

            System.out.println(considerableGirlCount);
        }
        catch (Exception e)
        {
            System.out.print("exception in buffered reader");
        }

    }
}