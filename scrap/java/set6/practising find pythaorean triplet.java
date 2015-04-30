// practising find pythaorean triplet
// telephonic2, q2, set8

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);

        List<Integer> squarees = new ArrayList<>();
        numbers.stream().forEach(x -> squarees.add((int)Math.pow(x, 2)));
        Collections.sort(numbers);
        squarees.stream().forEach(x -> System.out.print(x + " "));
        System.out.println();

        findTriplets(squarees);
    }

    private static void findTriplets(List<Integer> numbers) {
        Integer targetIndex = numbers.size() - 1;

        for (Integer index = numbers.size()-1; index>=0; --index)
        {
            Integer startIndex = 0;
            Integer endIndex = index - 1;

            while(startIndex < endIndex)
            {
                Integer sum = numbers.get(startIndex) + numbers.get(endIndex);
                if (sum == numbers.get(index))
                {
                    System.out.println("triplets are " + numbers.get(startIndex) + " " + numbers.get(endIndex) + " " + numbers.get(index));
                    ++startIndex;
                    --endIndex;
                }
                else if (sum > numbers.get(index))
                {
                    --endIndex;
                }
                else
                {
                    ++startIndex;
                }

            }
        }
    }
}