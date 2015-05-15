// nth row of pascal triangle
// https://stackoverflow.com/questions/15580291/how-to-efficiently-calculate-a-row-in-pascals-triangle
// f2f 5, q1

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] wer)
    {
        Integer n = 3;
        List<Integer> list = new ArrayList<>();

        list.add(1);
        Integer k = 0;
        while(k<n)
        {
            Integer thisEntry = list.get(k) * (n-k) / (k+1);
            list.add(thisEntry);
            ++k;
        }

        list.stream().forEach(x -> System.out.print(x + " "));
    }
}