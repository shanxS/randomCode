// Maximum sum such that no two elements are adjacent
// code question: 108

public class Main
{
    private static Integer[][] preResults;
    final static private Integer incl = 0, excl = 1;

    public static void main(String[] er)
    {
//        Integer[] array = {3, 2, 5, 10, 7};
        Integer[] array = {3,50,5,10,100};
        preResults = new Integer[2][2];

        preResults[incl][0] = array[0];
        preResults[excl][0] = 0;

        preResults[incl][1] = array[1];
        preResults[excl][1] = Math.max(preResults[incl][0], preResults[incl][1]);

        for (Integer i=2; i<array.length; ++i)
        {
            Integer thisIncl = Math.max(preResults[incl][0], preResults[excl][0]) + array[i];
            Integer thisExcl = Math.max(preResults[incl][1], preResults[excl][1]);

            preResults[incl][0] = preResults[incl][1];
            preResults[excl][0] = preResults[excl][1];

            preResults[incl][1] = thisIncl;
            preResults[excl][1] = thisExcl;
        }

        System.out.println(Math.max(preResults[incl][1], preResults[excl][1]));
    }
}