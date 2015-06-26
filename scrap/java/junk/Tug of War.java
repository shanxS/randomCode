// Tug of War

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{
    private static Integer[] array = {3, 4, 5, -3, 100, 1, 89, 54, 23, 20};
    private static Integer[] result = new Integer[array.length];
    private static Integer minDiff = Integer.MAX_VALUE, leftSum, rightSum;
    private static Integer[] globalResults = null;

    public static void main(String[] er)
    {

        List<Integer> bag = new ArrayList<>();
        for (Integer v : array)
        {
            bag.add(v);
        }

        populateOrTest(bag, 0);

        System.out.print(minDiff + " " + leftSum + " " + rightSum);
    }

    private static void populateOrTest(List<Integer> bag, Integer index)
    {
        if (index == array.length)
        {
            test();
        }

        for (Integer i=0; i<bag.size(); ++i)
        {
            result[index] = bag.get(i);

            List<Integer> newBag = new ArrayList<>(bag);
            newBag.remove((int) i);
            populateOrTest(newBag, index+1);
        }
    }

    private static void test()
    {
        Integer fwdCursor = 0;
        Integer fwdSum = 0;
        Integer revCursor = result.length-1;
        Integer revSum = 0;

        while (fwdCursor < revCursor)
        {
            fwdSum += result[fwdCursor];
            ++fwdCursor;

            revSum += result[revCursor];
            --revCursor;
        }

        if (Math.abs(revSum - fwdSum) < minDiff)
        {
            minDiff = Math.abs(revSum - fwdSum);
            leftSum = fwdSum;
            rightSum = revSum;
            globalResults = Arrays.copyOf(result, result.length);
        }
    }
}