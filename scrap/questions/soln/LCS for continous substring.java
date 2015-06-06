// LCS for continous substring
// code question 52

public class Main
{
    public static void main(String[] er)
    {
        String s1 = "shasume";
        String s2 = "sumesha";

        LCS lcs = new LCS();
        System.out.print(lcs.find(s1,s2));
    }
}

class LCS
{
    private Character[] str1, str2;
    private Integer largestLCS;

    public Integer find(String text1, String text2)
    {
        str1 = new Character[text1.length()];
        for (Integer i=0; i<text1.length(); ++i)
        {
            str1[i] = text1.charAt(i);
        }

        str2 = new Character[text2.length()];
        for (Integer i=0; i<text2.length(); ++i)
        {
            str2[i] = text2.charAt(i);
        }

        largestLCS = Integer.MIN_VALUE;

        findLCS(str1.length-1, str2.length-1);

        return largestLCS;
    }

    private void  findLCS(Integer end1, Integer end2)
    {
        if (end1 < 0 || end2 < 0)
        {
            return;
        }

        final Integer cachedEnd1 = end1;
        final Integer cachedEnd2 = end2;
        while(end1 >= 0 && end2 >= 0 && str1[end1] == str2[end2])
        {
            --end1;
            --end2;
        }

        if (cachedEnd1 - end1 > largestLCS)
        {
            largestLCS = cachedEnd1 - end1;
        }

        findLCS(cachedEnd1-1, cachedEnd2);
        findLCS(cachedEnd1, cachedEnd2-1);
    }
}