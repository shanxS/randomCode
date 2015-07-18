// Mobile Numeric Keypad Problem

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] ar = {
                {1,0,0,0,0,0,0,0,1,0},
                {0,1,1,0,1,0,0,0,0,0},
                {0,1,1,1,0,1,0,0,0,0},
                {0,0,1,1,0,0,1,0,0,0},
                {0,1,0,0,1,1,0,1,0,0},
                {0,0,1,0,1,1,1,0,1,0},
                {0,0,0,1,0,1,1,0,0,1},
                {0,0,0,0,1,0,0,1,1,0},
                {1,0,0,0,0,1,0,1,1,1},
                {0,0,0,0,0,0,1,0,1,1}
        };

//        for (Integer r=0; r<ar.length; ++r)
//        {
//            System.out.print(r + " - ");
//            for (Integer c=0; c<ar.length; ++c)
//            {
//                if (ar[r][c] == 1)
//                {
//                    System.out.print(", " + c);
//                }
//            }
//            System.out.println();
//        }

        NumberCounter nc = new NumberCounter();
        System.out.println(nc.count(ar, 5));
    }
}

class NumberCounter
{
    private Integer[][] ar;

    public Integer count(Integer[][] ar, Integer n)
    {
        this.ar = ar;

        Integer counter = 0, prevCounter = 0;
        for (Integer key=0; key<ar.length; ++key)
        {
            counter += countFor(key, n);
//            System.out.print("for " + key + " count : " + (counter - prevCounter));
//            prevCounter = counter;
//            System.out.println();
        }

        return counter;
    }

    private Integer countFor(Integer key, Integer n)
    {
//        System.out.print(key);
        if (n==1)
        {
//            System.out.println();
            return 1;
        }

        Integer thisCount = 0;
        for (Integer i=0; i<ar.length; ++i)
        {
            if (ar[key][i] == 1)
            {
                thisCount += countFor(i, n-1);
            }
        }

        return thisCount;
    }
}