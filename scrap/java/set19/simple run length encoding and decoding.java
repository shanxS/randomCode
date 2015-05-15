// simple run length encoding and decoding
// written q3

import java.util.LinkedHashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        String str = "AAACCCBBD";
        Encoder encoder = new Encoder(str);
        String encoded = encoder.getEncodedString();
        System.out.println(encoded);
        System.out.println(encoder.decode(encoded));

    }
}

class Encoder
{
    private String string;
    private Map<Character, Integer> charCount;

    public Encoder(String str)
    {
        this.string = str;
        this.charCount = new LinkedHashMap<>();
    }

    public String getEncodedString()
    {
        for (Character c : string.toCharArray())
        {
            Integer count = charCount.get(c);
            if (count == null)
            {
                count = 0;
            }
            charCount.put(c, count + 1);
        }

        String encodedString = "";
        for (Map.Entry<Character, Integer> entry : charCount.entrySet())
        {
            encodedString += entry.getKey();
            encodedString += entry.getValue();
        }

        return encodedString;
    }

    public String decode(String encoded)
    {
        String decoded = "";
        Character character = null;
        for (Integer cursor = 0; cursor < encoded.length(); ++cursor)
        {
            if (cursor%2 == 0)
            {
                character = encoded.charAt(cursor);
            }
            else
            {
                Integer count = Character.getNumericValue(encoded.charAt(cursor));
                for(Integer i=0; i<count; ++i)
                {
                    decoded += character;
                }

            }
        }

        return decoded;
    }
}
