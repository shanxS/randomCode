import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        final int invalidNumber = -1;
        int[] numbers = {2,5,3,5,5,3,5,3,1,2,5,1,1,1};//{1,8,1,2,3,9,7,6,5,3,2,3,1};

        Arrays.sort(numbers);

        for (int i=0; i<numbers.length-1; ++i)
        {
            if (numbers[i] == numbers[i+1])
            {
                numbers[i] = invalidNumber;
            }
        }

        TreeMap<Integer, Integer> occurance = new TreeMap<>();

        int count = 0;
        for(int i : numbers)
        {
            if (i == invalidNumber)
            {
                ++count;
            }
            else
            {
                ++count;
                occurance.put(count, i);
                count = 0;
            }

            if (occurance.size() > 2)
            {
                occurance.pollFirstEntry();
            }
        }

        Map.Entry<Integer, Integer> entry = occurance.pollFirstEntry();
        System.out.println(entry.getValue());
    }
}
