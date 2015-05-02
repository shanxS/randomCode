// find sub string of a string which contains all letters of another string

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        Finder finder = new Finder("this is a test string", "tist");
        finder.find();
    }
}

class Finder
{
    private String sourceText;
    private String targetText;
    private Map<Character, Integer> sourceHistogram;
    private Map<Character, Integer> targetHistogram;
    private Integer startIndex;
    private Integer minLength;

    public Finder(String sourceText, String targetText)
    {
        this.sourceText = sourceText;
        this.targetText = targetText;

        sourceHistogram = new HashMap<>();
        targetHistogram = new HashMap<>();

        startIndex = null;
        minLength = null;
    }

    public void find()
    {
        for (Character c : targetText.toCharArray())
        {
            Integer previousCount = targetHistogram.get(c);
            if (previousCount == null)
            {
                previousCount = 0;
            }
            targetHistogram.put(c, previousCount+1);
        }

        Integer sourceCounter = getFirstCandidateEndIndex();
        if (!histogramSubSet(targetHistogram, sourceHistogram))
        {
            System.out.println("cannot find");
            return;
        }

        minLength = sourceCounter - startIndex + 1;
        System.out.println("first occourance ");
        for (Integer i=startIndex; i<startIndex+minLength; ++i)
        {
            System.out.print(sourceText.charAt(i));
        }
        System.out.println("----------------");
        if (sourceCounter == sourceText.length())
        {
            return;
        }

        ++sourceCounter;
        while(sourceCounter < sourceText.length())
        {
            Character c = sourceText.charAt(sourceCounter);
            Integer previousCount = sourceHistogram.get(c);
            if (previousCount == null)
            {
                previousCount = 0;
                sourceHistogram.put(c, previousCount);
            }
            sourceHistogram.put(c, previousCount+1);

            if (sourceText.charAt(startIndex) == sourceText.charAt(sourceCounter))
            {
                Map<Character, Integer> tempSourceHistogram = new HashMap<>(sourceHistogram);
                Integer tempStartIndex = startIndex+1;
                removeCharacter(sourceText.charAt(tempStartIndex-1), tempSourceHistogram);

                while (histogramSubSet(targetHistogram, tempSourceHistogram) && tempStartIndex < sourceCounter)
                {
                    startIndex = tempStartIndex;
                    minLength = sourceCounter - startIndex + 1;
                    sourceHistogram = new HashMap<>(tempSourceHistogram);

                    ++tempStartIndex;
                    removeCharacter(sourceText.charAt(tempStartIndex-1), tempSourceHistogram);
                }
            }

            ++sourceCounter;
        }

        System.out.println("final");
        for (Integer i=startIndex; i<startIndex+minLength; ++i)
        {
            System.out.print(sourceText.charAt(i));
        }
        System.out.println("----------------");
    }

    private void removeCharacter(Character c, Map<Character, Integer> histogram)
    {
        Integer previousCount = histogram.get(c);
        if (previousCount != null)
        {
            if (previousCount == 0)
            {
                histogram.remove(c);
            }
            else
            {
                histogram.put(c, previousCount-1);
            }
        }
    }

    private Integer getFirstCandidateEndIndex()
    {
        Integer sourceCounter = 0;
        while(!histogramSubSet(targetHistogram, sourceHistogram) && sourceCounter < sourceText.length())
        {
            Character c = sourceText.charAt(sourceCounter);

            if (targetHistogram.containsKey(c))
            {
                if (startIndex == null)
                {
                    startIndex = sourceCounter;
                }

                Integer previousCountInSource = sourceHistogram.get(c);
                if (previousCountInSource == null)
                {
                    previousCountInSource = 0;
                }
                sourceHistogram.put(c, previousCountInSource + 1);
            }

            ++sourceCounter;
        }

        return sourceCounter-1;
    }

    private boolean histogramSubSet(Map<Character, Integer> subHistogram, Map<Character, Integer> containingHistogram)
    {
        for (Map.Entry<Character, Integer> entry : subHistogram.entrySet())
        {
            Integer containerValue = containingHistogram.get(entry.getKey());
            if (containerValue == null || containerValue < entry.getValue())
            {
                return false;
            }
        }

        return true;
    }
}