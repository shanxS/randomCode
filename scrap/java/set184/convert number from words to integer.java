// convert number from words to integer
// r3, q1, set184
// code question: 47

import java.util.*;

public class Main
{
    public static void main(String[] er)
    {
        WordToNumber wtn = new WordToNumber();
        System.out.print(wtn.convert("one thousand three hundred and seventy two"));
//        System.out.print(wtn.convert("nineteen hundred and six"));
//        System.out.print(wtn.convert("one thousand one hundred and one"));
    }
}

class WordToNumber
{
    private Map<String, Integer> numberMap;
    private Map<String, Integer> multiplierMap;
    private final String NUMBER, MULTIPLIER;

    public WordToNumber()
    {
        numberMap = new HashMap<>();
        setNumberMap();

        multiplierMap = new HashMap<>();
        setMultiplierMap();

        NUMBER = "number";
        MULTIPLIER = "multiplier";
    }

    public Integer convert(String str)
    {
        String[] words = str.split(" ");
        List<ValueTypePair> valueStack = new ArrayList<>();
        Set<String> usedMultipliers = new HashSet<>();
        for (String word : words)
        {
            if (numberMap.keySet().contains(word))
            {
                valueStack.add(new ValueTypePair(numberMap.get(word), NUMBER));
            }
            else if (multiplierMap.keySet().contains(word))
            {
                if (!usedMultipliers.add(word))
                {
                    System.out.print("repeated multilplers");
                }
                else
                {
                    valueStack.add(new ValueTypePair(multiplierMap.get(word), MULTIPLIER));
                }
            }
            else if (!word.equals("and"))
            {
                System.out.println("not recognised - " + word);
            }
        }

        return convertToNumber(valueStack);
    }

    private Integer convertToNumber(List<ValueTypePair> valueStack)
    {
        Integer number = 0;


        Integer cursor = 0;
        while (cursor < valueStack.size() )
        {
            Integer value = valueStack.get(cursor).value;
            String type = valueStack.get(cursor).type;
            if (type.equals(NUMBER))
            {
                if (cursor + 1 < valueStack.size() && valueStack.get(cursor+1).type.equals(MULTIPLIER))
                {
                    value *= valueStack.get(cursor+1).value;
                    number += value;

                    cursor += 2;
                }
                else
                {
                    number += value;
                    ++cursor;
                }
            }
            else
            {
                number += value;
                ++cursor;
            }
        }

        return number;
    }

    private class ValueTypePair
    {
        Integer value;
        String type;

        public ValueTypePair(Integer valu, String type)
        {
            this.value = valu;
            this.type = type;
        }
    }

    private void setMultiplierMap()
    {
        multiplierMap.put("hundred", 100);
        multiplierMap.put("thousand", 1000);
        multiplierMap.put("lacs", 100000);
    }

    private void setNumberMap()
    {
        numberMap.put("one", 1);
        numberMap.put("two", 2);
        numberMap.put("three", 3);
        numberMap.put("four", 4);
        numberMap.put("five", 5);
        numberMap.put("six", 6);
        numberMap.put("seven", 7);
        numberMap.put("eight", 8);
        numberMap.put("nine", 9);

        numberMap.put("ten", 10);
        numberMap.put("eleven", 11);
        numberMap.put("twelve", 12);
        numberMap.put("thirteen", 13);
        numberMap.put("fourteen", 14);
        numberMap.put("fifteen", 15);
        numberMap.put("sixteen", 16);
        numberMap.put("seventeen", 17);
        numberMap.put("eighteen", 18);
        numberMap.put("nineteen", 19);

        numberMap.put("twenty", 20);
        numberMap.put("thirty", 30);
        numberMap.put("forty", 40);
        numberMap.put("fifty", 50);
        numberMap.put("sixty", 60);
        numberMap.put("seventy", 70);
        numberMap.put("eighty", 80);
        numberMap.put("ninty", 90);
    }
}