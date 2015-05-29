// print max freq in string
// set32, written, q1
// code question 7

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Main
{
    public static void main(String[] er)
    {
        String[] str = new String[] {"When riding your bicycle backwards down a one-way street, if the wheel falls of a canoe, how many ball bearings does it take to fill",
                "up a water buffalo?",
                "Hello Howard"};

        PrintMaxFrequency pmf = new PrintMaxFrequency();
        for (String s : str)
        {
            pmf.print(s);
        }
    }
}

class PrintMaxFrequency
{
    private Integer maxCount;
    private TreeSet<Character> caps, notCaps;

    public void print(String text)
    {
        maxCount = -1;
        caps = new TreeSet<>();
        notCaps = new TreeSet<>();

        analyse(text);

        while (caps.size() > 0)
        {
            Character cCaps = caps.pollFirst();
            System.out.print(cCaps);

            if (notCaps.first() != null
                    && Character.toLowerCase(cCaps) == notCaps.first())
            {
                Character nCaps = notCaps.pollFirst();
                System.out.print(nCaps);
            }
        }

        while (notCaps.size() > 0)
        {
            System.out.print(notCaps.pollFirst());
        }

        System.out.println(" " + maxCount);
    }

    private void analyse(String text)
    {
        HashMap<Character, Integer> charCount = new HashMap<>();

        for (Character c : text.toCharArray())
        {
            if (!Character.isLetter(c))
            {
                continue;
            }

            Integer previousCount = charCount.get(c);
            if (previousCount == null)
            {
                previousCount = 0;
                charCount.put(c, previousCount);
            }
            ++previousCount;
            if (previousCount > maxCount)
            {
                maxCount = previousCount;
            }
            charCount.put(c, previousCount);
        }

        for (Map.Entry<Character, Integer> entry : charCount.entrySet())
        {
            if (entry.getValue() == maxCount)
            {
                if (Character.isUpperCase(entry.getKey()))
                {
                    caps.add(entry.getKey());
                }
                else
                {
                    notCaps.add(entry.getKey());
                }
            }
        }
    }
}