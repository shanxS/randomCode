// permutation of character with repetion
// r3, q2, set18, partial

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        String string = "sum";
        PermutationGenerator permutationGenerator = new PermutationGenerator(string);
        System.out.print(permutationGenerator.generateWithReplication(2));
    }
}

class PermutationGenerator
{
    private List<Character> characters;

    public PermutationGenerator(String str)
    {
        this.characters = new ArrayList<>();
        for (Character c : str.toCharArray())
        {
            characters.add(c);
        }
    }

    public ArrayList<String> generateWithReplication(Integer size)
    {
        ArrayList<String> strings = new ArrayList<>();
        return generateWithReplication(strings, characters, size);
    }

    private ArrayList<String> generateWithReplication(ArrayList<String> previousStrings, List<Character> characters, Integer count)
    {

        ArrayList<String> theseStrings = new ArrayList<>();
        if (previousStrings.size() == 0)
        {
            for (Character c : characters)
            {
                theseStrings.add(c + "");
            }

            if (count-1 >= 1)
            {
                theseStrings = generateWithReplication(theseStrings, characters, count - 1);
            }
        }
        else
        {
            for (String s : previousStrings)
            {
                for (Character c : characters)
                {
                    theseStrings.add(s + c);
                }
            }
            if (count-1 >= 1)
            {
                theseStrings = generateWithReplication(theseStrings, characters, count - 1);
            }
        }

        return theseStrings;
    }
}