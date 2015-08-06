// rolling carp hash

public class Main
{
    public static void main(String[] er)
    {
        String s = "aabaacaabaa";
        PalindromTester pt = new PalindromTester();
        for (Character c : s.toCharArray())
        {
            System.out.println(pt.update(c));

        }
    }
}

class PalindromTester
{
    private Integer hashFirst, hashSecond;
    private String first, second, mid;
    private final Integer prime;

    public PalindromTester()
    {
        first = "";
        second = "";
        mid = "";
        hashFirst = 0;
        hashSecond = 0;
        prime = 1;
    }

    public boolean update(Character c)
    {
        if (first.length() == 0)
        {
            first += c;
            hashFirst = hashAddStart(c, hashFirst, first.length());

            return true;
        }
        else if (first.length() == 1 && second.length() == 0)
        {
            second += c;
            hashSecond = hashAddEnd(hashSecond, c);
        }
        else if (first.length() == second.length() && mid.length() == 0)
        {
            mid = "" + second.charAt(0);

            hashSecond = hashRemoveStart(mid.charAt(0), hashSecond, second.length());
            second = second.substring(1, second.length());

            second += c;
            hashSecond = hashAddEnd(hashSecond, c);
        }
        else if (first.length() == second.length() && mid.length() == 1)
        {
            first = mid + first;
            hashFirst = hashAddStart(mid.charAt(0), hashFirst, first.length());

            second += c;
            hashSecond = hashAddEnd(hashSecond, c);

            mid = "";
        }

        System.out.println(first + " "  + mid + " " + second + " " + hashFirst + " " + hashSecond);

        return hashFirst.intValue() == hashSecond.intValue();
    }

    private Integer hashRemoveStart(Character c, Integer oldHash, int length)
    {
        return oldHash - ((int)Math.pow(prime, length-1)*c);
    }


    private Integer hashAddEnd(Integer oldHash, Character c)
    {
        return (prime*oldHash) + c;
    }

    private Integer hashAddStart(Character c, Integer oldHash, int length)
    {
        return ((int)Math.pow(prime, length-1)*c) + oldHash;
    }
}