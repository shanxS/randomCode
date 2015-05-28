// run length encoding of string
// written, q2, set31

public class Main
{
    public static void main(String[] er)
    {
        String s = "aabbbbcccd";
        ENDE ende = new ENDE();
        String encode = ende.encode(s);
        System.out.println(encode);
        System.out.println(ende.decode(encode));
    }
}

class ENDE
{
    public String encode(String s)
    {
        String encoded = "";
        Character cache = s.charAt(0);
        Integer count = 1;

        for (Integer i=1; i<s.length(); ++i)
        {
            if (s.charAt(i) == cache)
            {
                ++count;
            }
            else
            {
                encoded += cache;
                encoded += count;

                cache = s.charAt(i);
                count =1;
            }
        }
        encoded += cache;
        encoded += count;

        return encoded;
    }

    public String decode(String s)
    {
        String decode = "";

        Integer cursor = 0;
        while(cursor < s.length())
        {
            Character cache = s.charAt(cursor);
            Integer count = Integer.parseInt(s.charAt(cursor+1) + "");

            while(count > 0)
            {
                decode += cache;
                --count;
            }

            cursor += 2;
        }

        return decode;
    }
}