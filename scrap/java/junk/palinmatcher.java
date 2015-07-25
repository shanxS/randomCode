import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main
{
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<String, Integer> map;

    public static void main(String[] er)
    {
        try
        {
            Integer T = Integer.parseInt(bufferedReader.readLine());
            map = new HashMap<>();

            for (int i=0; i<T; ++i)
            {
                String s = bufferedReader.readLine();
                Integer sCount = map.get(s);
                if (sCount == null)
                {
                    sCount = 0;
                }
                map.put(s, sCount+1);
            }

            Integer pairCount = 0;
            String[] keys = new String[map.size()];
            Set<String> keySet = map.keySet();
            keySet.toArray(keys);
            for (String key : keys)
            {
                Integer keyCount = map.remove(key);
                String revKey = new StringBuffer(key).reverse().toString();

                if (keySet.contains(revKey))
                {
                    Integer revKeyCount = map.remove(revKey);
                    pairCount += keyCount*revKeyCount;
                }
            }

            System.out.println(pairCount);
        }
        catch (Exception e)
        {
            System.out.print("exception in buffered reader " + e.getMessage());
        }

    }
}