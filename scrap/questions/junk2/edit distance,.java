// edit distance

public class Main
{
    public static void main(String[] er)
    {
        System.out.print((new EditDistance()).find("SUNDAY", "SATURDAY"));
    }
}

class EditDistance
{
    private char[] c1, c2;
    public Integer find(String s1, String s2)
    {
        c1 = s1.toCharArray();
        c2 = s2.toCharArray();

        return contemplate(0, 0);
    }

    private Integer contemplate(int in1, int in2)
    {
        if (in1 == c1.length)
        {
            return c2.length - in2;
        }
        else if (in2 == c2.length)
        {
            return c1.length - in1;
        }

        if (c1[in1] == c2[in2])
        {
            return contemplate(in1+1, in2+1);
        }
        else
        {
            return 1 + Math.min(contemplate(in1+1, in2),
                    Math.min(contemplate(in1, in2+1), contemplate(in1+1, in2+1)));
        }
    }
}