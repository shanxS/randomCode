// word wrap problem

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        String s = "aaa bb cc ddddd";
        (new WordWrapper()).find(s, 6);
    }
}

class WordWrapper
{
    private List<Integer> weights;
    private Integer L;
    public void find(String s, int L)
    {
        preprocess(s);
        this.L = L;

        Integer weight = processFor(0);

        System.out.print(weight);
    }

    private Integer processFor(int start)
    {
        if (start == weights.size())
        {
            return 0;
        }

        Integer thisWeight = 0;
        Integer index = start;
        Integer minPenalty = L;
        while (L - thisWeight >= 0 && index < weights.size())
        {
            thisWeight += weights.get(index);
            if (L-thisWeight < 0)
            {
                break;
            }

            Integer thisPenalty = (L - thisWeight) + processFor(index+1);
            minPenalty = Math.min(thisPenalty, minPenalty);

            thisWeight += 1;
            ++index;
        }

        return minPenalty;
    }

    private void preprocess(String s)
    {
        weights = new ArrayList<>();

        String[] strs = s.split(" ");
        for (String str : strs)
        {
            weights.add(str.length());
        }
    }
}