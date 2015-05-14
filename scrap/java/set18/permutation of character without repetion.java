// permutation of character without repetion
// r3, q2, set18, partial

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        String s = "sume";
        PermutationGenerator permutationGenerator = new PermutationGenerator(s);
        System.out.print(permutationGenerator.generate(3));
    }
}

class PermutationGenerator
{
    private List<Character> characters;

    public PermutationGenerator(String str)
    {
        this.characters = new ArrayList<>();
        for(Character c : str.toCharArray())
        {
            characters.add(c);
        }
    }

    public List<String> generate(Integer size)
    {
        return generate(characters, new ArrayList<String>(), size);
    }

    private List<String> generate(List<Character> bag, ArrayList<String> previousStrings, Integer size)
    {
        ArrayList<String> theseStrings = new ArrayList<>();
        if (previousStrings.size() == 0)
        {
            for (Integer i=0; i<bag.size(); ++i)
            {
                List<Character> newBag = new ArrayList<>(bag);
                ArrayList<String> newStrings = new ArrayList<>();
                newStrings.add("" + newBag.get(i));
                newBag.remove(newBag.get(i));

                if (size > 1)
                {
                    theseStrings.addAll(generate(newBag, newStrings, size - 1));
                }
                else
                {
                    theseStrings.addAll(newStrings);
                }

            }
        }
        else
        {
            for (String s : previousStrings)
            {
                for (Integer i = 0; i < bag.size(); ++i)
                {
                    List<Character> newBag = new ArrayList<>(bag);
                    ArrayList<String> newStrings = new ArrayList<>();
                    newStrings.add(s + newBag.get(i));
                    newBag.remove(newBag.get(i));

                    if (size > 1)
                    {
                        theseStrings.addAll(generate(newBag, newStrings, size - 1));
                    }
                    else
                    {
                        theseStrings.addAll(newStrings);
                    }

                }
            }
        }

        return theseStrings;
    }
}