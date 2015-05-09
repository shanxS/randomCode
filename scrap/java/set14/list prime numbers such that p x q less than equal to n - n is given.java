// list prime numbers such that p x q less than equal to n - n is given

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer n = 20;
        Seive s = new Seive(n/2);
        s.getSeive().stream().forEach(x -> System.out.print(x + " "));
        Permutation permutation = new Permutation(s.getSeive());
        permutation.getPermutations().stream().forEach(x -> System.out.println(x.get(0) + " " + x.get(1)));
    }
}

class Permutation
{
    List<Integer> numbers;
    List<List<Integer>> permutations;

    public Permutation(List<Integer> numbers)
    {
        this.numbers = numbers;
        this.permutations = new ArrayList<>();
    }

    public List<List<Integer>> getPermutations()
    {
        for (Integer i=0; i<numbers.size()-1; ++i)
        {
            for (Integer j=i+1; j<numbers.size(); ++j)
            {
                List<Integer> thisPermutation = new ArrayList<>();

                thisPermutation.add(numbers.get(i));
                thisPermutation.add(numbers.get(j));

                permutations.add(thisPermutation);
            }
        }

        return permutations;
    }

}

class Seive
{
    private Integer[] seive;
    private final Integer limit;

    public Seive(Integer limit)
    {
        this.limit = limit;
        this.seive = new Integer[limit];
        for (Integer i=0; i<limit; ++i)
        {
            seive[i] = 0;
        }
    }

    public List<Integer> getSeive()
    {
        for (Integer i = 2; i<=limit/2; ++i)
        {
            for (Integer k=2; k*i < limit; ++k)
            {
                seive[k*i] = 1;
            }
        }

        List<Integer> list = new ArrayList<>();
        for(Integer i=2; i<limit; ++i)
        {
            if (seive[i] == 0)
            {
                list.add(i);
            }
        }

        return list;
    }
}