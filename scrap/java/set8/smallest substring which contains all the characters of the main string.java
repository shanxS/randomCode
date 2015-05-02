// smallest substring which contains all the characters of the main string
// r4, q2, set 8, amazon

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        Finder finder = new Finder("hsaudmedha");
        finder.find();
    }
}

class Finder
{
    private String sourceText;
    private Map<Character, Integer> sourceHistogram;
    private String targetText;
    private Map<Character, Integer> targetHistogram;
    private Integer startIndex;
    private Integer minLength;

    public Finder(String text)
    {
        this.sourceText = text;
        this.sourceHistogram = new HashMap<>();
        for (Character c : sourceText.toCharArray())
        {
            addToHistogram(c, sourceHistogram);
        }

        this.targetText = "";
        this.targetHistogram = new HashMap<>();
        for (Character c : sourceHistogram.keySet())
        {
            targetText += c;
            addToHistogram(c, targetHistogram);
        }
        sourceHistogram.clear();

        startIndex = null;
        minLength = null;
    }

    private void addToHistogram(Character c, Map<Character, Integer> histogram)
    {
        Integer previousCount = histogram.get(c);
        if (previousCount == null)
        {
            previousCount = 0;
        }
        histogram.put(c, previousCount);
    }

    public void find()
    {
        Integer sourceCounter = findFirstEndIndexCandidate();
        if (!isSubHistogram(targetHistogram, sourceHistogram))
        {
            System.out.print("cannot find");
            return;
        }

        print();

        ++sourceCounter;
        for (; sourceCounter < sourceText.length(); ++sourceCounter)
        {
            Character c = sourceText.charAt(sourceCounter);
            addToHistogram(c, sourceHistogram);

            if (c == sourceText.charAt(startIndex))
            {
                Integer tempStartIndex = startIndex + 1;
                Map<Character, Integer> tempSourceHistorgram = new HashMap<>(sourceHistogram);
                removeFromHistogram(sourceText.charAt(tempStartIndex - 1), tempSourceHistorgram);

                while(isSubHistogram(targetHistogram, tempSourceHistorgram))
                {
                    commitGlobals(tempStartIndex, sourceCounter, tempSourceHistorgram);

                    ++tempStartIndex;
                    removeFromHistogram(sourceText.charAt(tempStartIndex - 1), tempSourceHistorgram);
                }
            }
        }

        print();
    }

    private void print()
    {
        for (Integer i=startIndex; i<startIndex + minLength; ++i)
        {
            System.out.print(sourceText.charAt(i));
        }
        System.out.println();
        System.out.println("--------------------");
    }

    private void removeFromHistogram(char key, Map<Character, Integer> historgram)
    {
        Integer value = historgram.get(key);
        if (value != null)
        {
            if (value == 1)
            {
                historgram.remove(key);
            }
            else
            {
                historgram.put(key, value-1);
            }
        }
    }

    private Integer findFirstEndIndexCandidate()
    {
        Integer sourceCounter = 0;

        for (; sourceCounter < sourceText.length() && !isSubHistogram(targetHistogram, sourceHistogram); ++sourceCounter)
        {
            Character c = sourceText.charAt(sourceCounter);
            addToHistogram(c, sourceHistogram);

            if (targetHistogram.containsKey(c) && startIndex == null)
            {
                startIndex = sourceCounter;
            }

        }
        commitGlobals(startIndex, sourceCounter-1, sourceHistogram);

        return sourceCounter-1;
    }

    private void commitGlobals(Integer tempStartIndex, Integer sourceCounter, Map<Character, Integer> tempSourceHistogram)
    {
        startIndex = tempStartIndex;
        minLength = sourceCounter - startIndex + 1;
        sourceHistogram = new HashMap<>(tempSourceHistogram);
    }

    private boolean isSubHistogram(Map<Character, Integer> subHistogram, Map<Character, Integer> superHistogram)
    {
        for (Map.Entry<Character, Integer> entry : subHistogram.entrySet())
        {
            Integer superCount = superHistogram.get(entry.getKey());
            if (superCount == null || superCount < entry.getValue())
            {
                return false;
            }
        }

        return true;
    }
}