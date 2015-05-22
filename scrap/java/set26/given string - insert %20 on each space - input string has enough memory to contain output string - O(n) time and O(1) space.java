// given string - insert %20 on each space - input string has enough memory to contain output string - O(n) time and O(1) space
// r2, q1, set26

public class Main
{
    public static void main(String[] er)
    {
        Character[] data = new Character[]{' ', 'a', 'b', ' ', 'c', ' ', 'd', ' ', ' ', ' ', ' ', ' '};
        Integer lastCharIndex = 7;

        for (Integer reader=lastCharIndex, writer=data.length-1; reader>=0; --reader, --writer)
        {
            if (data[reader] == ' ')
            {
                data[writer] = 'A';
                --writer;
            }
            data[writer] = data[reader];
        }

        for (Character c : data)
        {
            System.out.print(c);
        }
    }
}