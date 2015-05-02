// improved - find sub string of a string which contains all letters of another string

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        //Finder finder = new Finder("this is a test string", "tist");
        Finder finder = new Finder("abbccddebfg", "cbd");
        finder.find();
    }
}

class Finder
{
    private String sourceText;
    private String targetText;
    private Map<Character, Integer> sourceHistogram;
    private Map<Character, Integer> targetHistogram;
    private Integer startIndex, minLength;

    public Finder(String source, String target)
    {
        this.sourceText = source;
        this.targetText = target;
        this.sourceHistogram = new HashMap<>();
        this.targetHistogram = new HashMap<>();
        this.startIndex = null;
        this.minLength = null;
    }

    private void addToHistogram(Character c, Map<Character, Integer> histogram)
    {
        Integer previousCount = histogram.get(c);
        if (previousCount == null)
        {
            previousCount = 0;
        }
        histogram.put(c, previousCount+1);
    }

    public void find()
    {
        // set target histogram
        for (Character c : targetText.toCharArray())
        {
            addToHistogram(c, targetHistogram);
        }


        // find first substring
        Integer sourceCounter = getFirstEndIndexCandidate();
        if (!isSubHistogram(targetHistogram, sourceHistogram))
        {
            System.out.print("cannot find");
            return;
        }
        printSubString();

        // if valid, start iterating
        ++sourceCounter;
        for (;sourceCounter < sourceText.length(); ++sourceCounter)
        {
            Character c = sourceText.charAt(sourceCounter);
            addToHistogram(c, sourceHistogram);

            if (c == sourceText.charAt(startIndex))
            {
                Integer tempStartIndex = startIndex+1;
                Map<Character, Integer> tempSourceHistogram = new HashMap<>(sourceHistogram);
                removeFromHistogram(c, tempSourceHistogram);

                while(isSubHistogram(targetHistogram, tempSourceHistogram))
                {
                    updateGlobals(tempStartIndex, sourceCounter, tempSourceHistogram);

                    ++tempStartIndex;
                    removeFromHistogram(sourceText.charAt(tempStartIndex-1), tempSourceHistogram);
                }
            }
        }

        printSubString();
    }

    private void removeFromHistogram(char c, Map<Character, Integer> histogram)
    {
        Integer previousCount = histogram.get(c);
        if (previousCount != null)
        {
            if (previousCount == 1)
            {
                histogram.remove(c);
            }
            else
            {
                histogram.put(c, previousCount-1);
            }
        }
    }

    private void printSubString()
    {
        for (Integer i=startIndex; i<startIndex+minLength; ++i)
        {
            System.out.print(sourceText.charAt(i));
        }
        System.out.println();
        System.out.println("-------------------");
    }

    private void updateGlobals(Integer tempStartIndex, Integer sourceCounter, Map<Character, Integer> tempSourceHistogram)
    {
        startIndex = tempStartIndex;
        minLength = sourceCounter - startIndex + 1;
        sourceHistogram = new HashMap<>(tempSourceHistogram);
    }

    private Integer getFirstEndIndexCandidate()
    {
        Integer sourceCounter = 0;
        for (; sourceCounter<sourceText.length() && !isSubHistogram(targetHistogram, sourceHistogram); ++sourceCounter)
        {
            addToHistogram(sourceText.charAt(sourceCounter), sourceHistogram);
            if (targetHistogram.containsKey(sourceText.charAt(sourceCounter)) && startIndex == null)
            {
                startIndex = sourceCounter;
            }
        }
        updateGlobals(startIndex, sourceCounter-1, sourceHistogram);

        return sourceCounter-1;
    }

    private boolean isSubHistogram(Map<Character, Integer> subHistogram, Map<Character, Integer> superHistogram)
    {
        for (Map.Entry<Character, Integer> entry : subHistogram.entrySet())
        {
            Integer previousCount = superHistogram.get(entry.getKey());
            if (previousCount == null || previousCount < entry.getValue())
            {
                return false;
            }
        }

        return true;
    }
}