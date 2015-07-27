// topcoder YetAnotherCardGame
// https://community.topcoder.com/stat?c=problem_statement&pm=13912

import java.util.Set;
import java.util.TreeSet;

public class Main
{
    private static Set<Integer> pCards, sCards;
    public static void main(String[] ar)
    {
        Integer[] p ={1, 7, 1, 5, 7};
        pCards = new TreeSet<>();
        for (Integer i : p)
        {
            pCards.add(i);
        }

        Integer[] s = {1, 4, 6, 7, 3};
        sCards = new TreeSet<>();
        for (Integer i : s)
        {
            sCards.add(i);
        }

        Integer sFirstPile = findMax(new TreeSet<>(sCards), new TreeSet<>(pCards));
        Integer pFirstPile = findMax(new TreeSet<>(pCards), new TreeSet<>(sCards));

        System.out.print((sFirstPile > pFirstPile ? sFirstPile : pFirstPile));

    }

    private static Integer findMax(TreeSet<Integer> first, TreeSet<Integer> second)
    {
        boolean chance = true;
        Integer size = 0, lastTop = 0;

        while ((chance && first.size() > 0)
                || (!chance && second.size() > 0))
        {
            if (chance)
            {
                while (first.size() > 0 && lastTop >= first.first())
                {
                    first.pollFirst();
                }

                if (first.size() > 0)
                {
                    Integer value = first.pollFirst();
                    ++size;
                    lastTop = value;

                    chance = !chance;
                }
            }
            else
            {
                while (second.size() > 0 && lastTop >= second.first())
                {
                    second.pollFirst();
                }

                if (second.size() > 0)
                {
                    Integer value = second.pollFirst();
                    ++size;
                    lastTop = value;

                    chance = !chance;
                }
            }
        }

        return size;
    }
}