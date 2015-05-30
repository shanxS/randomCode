// number of coins needed for change
// top coder intro problem

public class Main
{
    public static void main(String[] er)
    {
        final Integer NA = Integer.MAX_VALUE;
        Integer[] bag = new Integer[] {1,3,5};
        Integer target = 11;
        Integer[] array = new Integer[target + 1];
        for (Integer i=0; i<array.length; ++i)
        {
            array[i] = NA;
        }
        array[0] = 0;

        for (Integer thisTarget=1; thisTarget<array.length; ++thisTarget)
        {
            for (Integer coin : bag)
            {
                if (thisTarget - coin >= 0
                        && array[thisTarget - coin] + 1 < array[thisTarget])
                {
                    array[thisTarget] = array[thisTarget - coin] + 1;
                }
            }
        }

        System.out.print(array[target]);
    }
}