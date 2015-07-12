public class Main
{
    public static void main(String[] er)
    {
        Character[] s1 = {'A','B','C','D',};
        Character[] s2 = {'A','E','D','C',};

        LCS lcs = new LCS();
        System.out.print(lcs.find(s1, s2));
    }
}

class LCS
{
    private Character[] s1, s2;
    public Integer find(Character[] s1, Character[] s2)
    {
        this.s1 = s1;
        this.s2 = s2;

        return contemplate(0,0);
    }

    private Integer contemplate(int index1, int index2)
    {
        if (index1 == s1.length || index2 == s2.length)
        {
            return 0;
        }
        else if (s1[index1] == s2[index2])
        {
            return 1 + contemplate(index1+1, index2+1);
        }
        else
        {
            return Math.max(contemplate(index1+1, index2),
                    contemplate(index1, index2+1));
        }
    }
}