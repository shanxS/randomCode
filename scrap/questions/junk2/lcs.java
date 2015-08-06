// lcs

public class Main
{
    public static void main(String[] er)
    {
        String s1 = "AGGTAB";//"ABCDGH";
        String s2 = "GXTXAYB";//"AEDFHR";

        System.out.print((new LCS()).find(s1.toCharArray(), s2.toCharArray()));
    }
}

class LCS
{
    private char[] c1, c2;
    public Integer find(char[] c1, char[] c2)
    {
        this.c1 = c1;
        this.c2 = c2;

        Integer i1 = 0, i2 = 0;
        Integer thisMax;
        if (c1[i1] == c2[i2])
        {
            thisMax = 1 + contemplate(i1 + 1, i2 + 1);
        }
        else
        {
            thisMax = Math.max(contemplate(i1, i2 + 1),
                    contemplate(i1 + 1, i2));
        }

        return thisMax;
    }

    private Integer contemplate(int i1, int i2)
    {
        if (i1 == c1.length || i2 == c2.length)
        {
            return 0;
        }

        Integer thisMax;
        if (c1[i1] == c2[i2])
        {
            thisMax = 1 + contemplate(i1 + 1, i2 + 1);
        }
        else
        {
            thisMax = Math.max(contemplate(i1, i2 + 1),
                    contemplate(i1 + 1, i2));
        }

        return thisMax;
    }
}