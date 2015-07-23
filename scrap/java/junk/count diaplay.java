import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main
{
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] er)
    {
        try
        {
            String inputString = bufferedReader.readLine();
            Integer[] map = {6,2,5,5,4,5,6,4,7,6};

            int count = 0;
            for (int i=0; i<inputString.length(); ++i)
            {
                count += map[Integer.parseInt(inputString.charAt(i) + "")];
            }

            System.out.print(count);
        }
        catch (Exception e)
        {
            System.out.print("exception in buffered reader");
        }

    }
}