// Stable Marriage Problem
// code question: 122

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main
{
    private static List<Integer>[] femalesPref;
    private static Stack<Integer>[] malesPref;
    private static Integer[] matchedMales, matchedFemales;
    private static Integer nextUnpairedman;

    public static void main(String[] er)
    {
        malesPref = populateMalePref();
        femalesPref = populateFemalePref();

        matchedMales = new Integer[malesPref.length];
        matchedFemales = new Integer[femalesPref.length];

        match();

        System.out.println("male - female");
        for(Integer i=0; i<matchedMales.length; ++i)
        {
            System.out.println(i + " - " + matchedMales[i]);
        }
    }

    private static void match()
    {
        Integer unpairedManCursor = getNextUnpairedMan();
        while (unpairedManCursor != null)
        {
            while (matchedMales[unpairedManCursor] == null && malesPref[unpairedManCursor].size() > 0)
            {
                Integer matchedFemale = malesPref[unpairedManCursor].pop();
                if (matchedFemales[matchedFemale] == null)
                {
                    matchedFemales[matchedFemale] = unpairedManCursor;
                    matchedMales[unpairedManCursor] = matchedFemale;
                }
                else
                {
                    if (!isEnoughSatisfiedFemale(matchedFemale, unpairedManCursor, matchedFemales[matchedFemale]))
                    {
                        Integer previouslyMatchedMale = matchedFemales[matchedFemale];
                        matchedMales[previouslyMatchedMale] = null;

                        matchedFemales[matchedFemale] = unpairedManCursor;
                        matchedMales[unpairedManCursor] = matchedFemale;
                    }
                }
            }

            unpairedManCursor = getNextUnpairedMan();
        }
    }

    private static boolean isEnoughSatisfiedFemale(Integer femaleCursor, Integer candidateMaleCursor, Integer currentMaleCursor)
    {
        List<Integer> pref = femalesPref[femaleCursor];
        return pref.indexOf(candidateMaleCursor) > pref.indexOf(currentMaleCursor);
    }

    private static List<Integer>[] populateFemalePref()
    {
        List<Integer>[] femalesPref = new ArrayList[4];

        femalesPref[0] = new ArrayList<>();
        femalesPref[0].add(0);
        femalesPref[0].add(1);
        femalesPref[0].add(2);
        femalesPref[0].add(3);

        femalesPref[1] = new ArrayList<>();
        femalesPref[1].add(3);
        femalesPref[1].add(2);
        femalesPref[1].add(1);
        femalesPref[1].add(0);

        femalesPref[2] = new ArrayList<>();
        femalesPref[2].add(0);
        femalesPref[2].add(1);
        femalesPref[2].add(2);
        femalesPref[2].add(3);

        femalesPref[3] = new ArrayList<>();
        femalesPref[3].add(2);
        femalesPref[3].add(3);
        femalesPref[3].add(0);
        femalesPref[3].add(1);

        return femalesPref;
    }

    private static Stack<Integer>[] populateMalePref()
    {
        Stack<Integer>[] malesPref = new Stack[4];

        malesPref[0] = new Stack<>();
        malesPref[0].push(3);
        malesPref[0].push(2);
        malesPref[0].push(1);
        malesPref[0].push(0);

        malesPref[1] = new Stack<>();
        malesPref[1].push(3);
        malesPref[1].push(2);
        malesPref[1].push(0);
        malesPref[1].push(1);

        malesPref[2] = new Stack<>();
        malesPref[2].push(1);
        malesPref[2].push(2);
        malesPref[2].push(3);
        malesPref[2].push(0);

        malesPref[3] = new Stack<>();
        malesPref[3].push(1);
        malesPref[3].push(0);
        malesPref[3].push(2);
        malesPref[3].push(3);

        return malesPref;

    }

    public static Integer getNextUnpairedMan()
    {
        Integer cursor = 0;
        while (cursor < matchedMales.length && matchedMales[cursor] != null)
        {
            ++cursor;
        }

        return (cursor == matchedMales.length) ? null : cursor;
    }
}