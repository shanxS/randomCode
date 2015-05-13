// infix to postfix
// r2, q1, set18, partial

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        String in = "3+4*2/(1-5)";//^2^3";
        InfixToPostfix itp = new InfixToPostfix(in);
        itp.getPost();
    }
}

class InfixToPostfix
{
    private List<Character> infix;
    private List<Character> postfix;
    private List<Character> symbols;
    private List<Character> precedenceList;

    public InfixToPostfix(String infixStr)
    {
        this.infix = new ArrayList<>();
        for(Character c : infixStr.toCharArray())
        {
            infix.add(c);
        }

        this.postfix = new ArrayList<>();
        this.symbols = new ArrayList<>();
        this.precedenceList = new ArrayList<>();
        precedenceList.add('-');
        precedenceList.add('+');
        precedenceList.add('*');
        precedenceList.add('/');
        precedenceList.add('^');
    }

    public List<Character> getPost()
    {
        for (Character c : infix)
        {
            if (Character.isDigit(c))
            {
                postfix.add(c);
            }
            else
            {
                if (c == '(')
                {
                    symbols.add(c);
                }
                else if (c == ')')
                {
                    while(symbols.get(symbols.size()-1) != '(')
                    {
                        postfix.add(symbols.get(symbols.size()-1));
                        symbols.remove(symbols.size()-1);
                    }
                    symbols.remove(symbols.size()-1);
                }
                else
                {
                    if (symbols.size() > 0 && !lowPrecedence(c, symbols.get(symbols.size()-1)))
                    {
                        while(symbols.size() > 0 && !lowPrecedence(c, symbols.get(symbols.size()-1)))
                        {
                            postfix.add(symbols.get(symbols.size()-1));
                            symbols.remove(symbols.size()-1);
                        }
                    }
                    symbols.add(c);
                }
            }
        }

        for (Integer cursor = symbols.size()-1; cursor >= 0; --cursor)
        {
            postfix.add(symbols.get(cursor));
        }

        return postfix;
    }

    private boolean lowPrecedence(Character newCharacter, Character oldOharacter)
    {
        if ((newCharacter == '/' && oldOharacter == '*')
            || (oldOharacter == '/' && newCharacter == '*'))
        {
            return false;
        }

        Integer newIndex = precedenceList.indexOf(newCharacter);
        Integer oldIndex = precedenceList.indexOf(oldOharacter);

        if (newIndex < oldIndex)
        {
            return false;
        }

        // for higher or equal precedence
        return true;
    }
}