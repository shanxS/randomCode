// max product sum
// code question: 131

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {-1, -3, -10, 0, 2, 5, 6};
        Integer max = ar[0];
        Integer min = ar[0];
        Integer maxSoFar = ar[0];

        for (Integer i=1; i<ar.length; ++i)
        {
            Integer prevMax = max, prevMin = min;

            max = Math.max(ar[i], Math.max(ar[i] * prevMax, ar[i] * prevMin));
            min = Math.min(ar[i], Math.min(ar[i] * prevMax, ar[i] * prevMin));
            maxSoFar = Math.max(maxSoFar, max);
        }

        System.out.print(maxSoFar);
    }

}