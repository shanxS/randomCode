// Cryptarithmetic puzzle - given 
//  SEND
//+ MORE
//--------
// MONEY
//--------
//
//assign numeric values to characters
// code question: 61

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        String s1 = "send";
        String s2 = "more";
        String result = "money";

        Cryptarithmetic cpa = new Cryptarithmetic();
        for (Map.Entry<Character, Integer> entry : cpa.solve(s1, s2, result).entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}

class Cryptarithmetic
{
    private Map<Character, Integer> charValueMap;
    private List<Map.Entry<Character, Integer>> mapEntries;
    private String s1, s2, result;

    public Map<Character, Integer> solve(String s1, String s2, String result)
    {
        this.s1 = s1;
        this.s2 = s2;
        this.result = result;

        setup(s1, s2, result);

        List<Integer> bag = new ArrayList<>();
        for (Integer i=0; i<10; ++i)
        {
            bag.add(i);
        }


        Integer thisIndex = 0;
        for (Integer bagCursor=0; bagCursor<bag.size(); ++bagCursor)
        {
            charValueMap.put(mapEntries.get(thisIndex).getKey(), bag.get(bagCursor));
            List<Integer> newBag = new ArrayList<>(bag);
            newBag.remove((int) bagCursor);
            
            if (contemplate(thisIndex+1, newBag))
            {
                return charValueMap;
            }
        }

        return null;
    }

    private boolean contemplate(Integer thisIndex, List<Integer> bag)
    {
        if (thisIndex == mapEntries.size())
        {
            return test();
        }
        
        for (Integer bagCursor = 0; bagCursor<bag.size(); ++bagCursor)
        {
            charValueMap.put(mapEntries.get(thisIndex).getKey(), bag.get(bagCursor));
            List<Integer> newBag = new ArrayList<>(bag);
            newBag.remove((int) bagCursor);
            
            if (contemplate(thisIndex+1, newBag))
            {
                return true;
            }
        }
        
        return false;
    }

    private boolean test()
    {
        Integer carry = 0;
        Integer cursor1 = s1.length() - 1;
        Integer cursor2 = s2.length() - 1;
        Integer resultCursor = result.length() - 1;
        while (cursor1 >= 0 && cursor2 >= 0)
        {
            Integer thisValue = charValueMap.get(s1.charAt(cursor1))
                            + charValueMap.get(s2.charAt(cursor2))
                            + carry;

            if (charValueMap.get(result.charAt(resultCursor)) != thisValue%10)
            {
                return false;
            }

            carry = thisValue/10;

            --cursor1;
            --cursor2;
            --resultCursor;
        }

        while (cursor1 >=0 )
        {
            Integer thisValue = charValueMap.get(s1.charAt(cursor1)) + carry;

            if (charValueMap.get(result.charAt(resultCursor)) != thisValue%10)
            {
                return false;
            }

            carry = thisValue/10;

            --cursor1;
            --resultCursor;
        }

        while (cursor2 >= 0)
        {
            Integer thisValue = charValueMap.get(s2.charAt(cursor2)) + carry;

            if (charValueMap.get(result.charAt(resultCursor)) != thisValue%10)
            {
                return false;
            }

            carry = thisValue/10;

            --cursor2;
            --resultCursor;
        }

        while (resultCursor >= 0)
        {
            if (charValueMap.get(result.charAt(resultCursor)) != carry%10)
            {
                return false;
            }

            carry /= 10;

            --resultCursor;
        }

        return true;
    }

    private void setup(String s1, String s2, String result)
    {

        charValueMap = new HashMap<>();
        for (Character c : s1.toCharArray())
        {
            charValueMap.put(c, 0);
        }
        for (Character c : s2.toCharArray())
        {
            charValueMap.put(c, 0);
        }
        for (Character c : result.toCharArray())
        {
            charValueMap.put(c, 0);
        }

        mapEntries = new ArrayList<>(charValueMap.entrySet());
    }


}