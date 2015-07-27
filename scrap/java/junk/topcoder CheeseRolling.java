// topcoder CheeseRolling
// https://community.topcoder.com/stat?c=problem_statement&pm=13919

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Tournament tournament =  new Tournament();
        String[] strings =
                {"NYNYNYNY",
                        "NNYNYNYY",
                        "YNNNNNNN",
                        "NYYNNYNY",
                        "YNYYNYYY",
                        "NYYNNNNN",
                        "YNYYNYNN",
                        "NNYNNYYN"};

        for (Integer i : tournament.winners(strings))
        {
            System.out.print(i + " ");
        }
    }
}

class Tournament
{
    private int[] results;
    private char[][] wins;
    private List<Integer> thisArrangement;

    public int[] winners(String[] stringWins)
    {
        this.wins = makeWins(stringWins);
        results = new int[wins.length];

        List<Integer> candidates = makeCandidates(wins.length);
        List<List<Integer>> arrangements = makeArrangements(candidates);
        for (List<Integer> arrangement : arrangements)
        {
            thisArrangement = arrangement;

//            System.out.println("Arrangement: ");
//            thisArrangement.stream().forEach(x -> System.out.print(x + " "));

            Integer winner = findWinner(0, thisArrangement.size()-1);
//            System.out.print(" ---- " + winner);
            results[winner] += 1;

            System.out.println();
        }
        
        return results;
    }

    private int findWinner(Integer start, Integer end)
    {
        if (start+1 == end)
        {
            Integer startMan = thisArrangement.get(start);
            Integer endMan = thisArrangement.get(end);
            return (wins[startMan][endMan] == 'Y') ? startMan : endMan;
        }

        Integer midIndex = start + ((end-start)/2);
        Integer leftWinner = findWinner(start, midIndex);
        Integer rightWinner = findWinner(midIndex + 1, end);

        return (wins[leftWinner][rightWinner] == 'Y') ? leftWinner : rightWinner;
    }

    private char[][] makeWins(String[] stringWins)
    {
        char[][] wins = new char[stringWins.length][stringWins.length];

        for (Integer i=0; i<stringWins.length; ++i)
        {
            String str = stringWins[i];
            for (Integer j=0; j<str.length(); ++j)
            {
                wins[i][j] = str.charAt(j);
            }
        }

        return wins;
    }

    private List<List<Integer>> makeArrangements(List<Integer> candidates)
    {
        Permute permute = new Permute();
        return permute.permuteFor(candidates);
    }

    private List<Integer> makeCandidates(int length)
    {
        List<Integer> candidates = new ArrayList<>();
        for (Integer i=0; i<length; ++i)
        {
            candidates.add(i);
        }
        
        return candidates;
    }
}

class Permute
{
    private List<List<Integer>> permutations;
    private List<Integer> singlePermutation;

    public List<List<Integer>> permuteFor(List<Integer> input)
    {
        permutations = new ArrayList<>();
        singlePermutation = new ArrayList<>();
        for (Integer i=0; i<input.size(); ++i)
        {
            singlePermutation.add(i);
        }

        Integer thisIndex = 0;
        for (Integer i=0; i<input.size(); ++i)
        {
            singlePermutation.set(thisIndex, input.get(i));
            List<Integer> bag = new ArrayList<>(input);
            bag.remove((int) i);

            permuteFor(thisIndex+1, bag);
        }

        return permutations;
    }

    private void permuteFor(Integer thisIndex, List<Integer> bag)
    {
        if (bag.size() == 0)
        {
            permutations.add(new ArrayList<Integer>(singlePermutation));
            return;
        }

        for (Integer i=0; i<bag.size(); ++i)
        {
            singlePermutation.set(thisIndex, bag.get(i));
            List<Integer> newBag = new ArrayList<>(bag);
            newBag.remove((int) i);

            permuteFor(thisIndex + 1, newBag);
        }
    }
}
