// Boolean Parenthesization Problem

public class Main
{
    public static void main(String[] er)
    {
//        Character[] operand = {'T', 'F', 'T'};
//        Character[] operator  = {'^', '&'};
//        Character[] operand = {'T', 'F', 'F'};
//        Character[] operator  = {'^', '|'};
        Character[] operand = {'T', 'T', 'F', 'T'};
        Character[] operator  = {'|', '&', '^'};

        ExprEvaluator ee = new ExprEvaluator();
        System.out.print(ee.countTrue(operator, operand));
    }
}

class ExprEvaluator
{
    private Character[] operator, operand;
    final private Character AND = '&', OR = '|', XOR = '^', TRUE = 'T', FALSE = 'F';

    public Integer countTrue(Character[] operator, Character[] operand)
    {
        this.operand = operand;
        this.operator = operator;

        if (operand.length == 2)
        {
            return evaluate(0, 1) == TRUE ? 1 : 0;
        }
        else
        {
            return contemplate(0, operand.length - 1);
        }
    }

    private Integer contemplate(Integer start, Integer end)
    {
        if (end - start == 1)
        {
            if (evaluate(start, end) == TRUE)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }

        Integer exStartCount = contemplate(start + 1, end);
        Integer withStartCount = (evaluate(operand[start], operator[start], (exStartCount > 0 ? TRUE : FALSE)))
                == TRUE
                ? 1 : 0;
        exStartCount = Math.max(withStartCount, Math.max(withStartCount*exStartCount, exStartCount));



        Integer exEndCount = contemplate(start, end - 1);
        Integer withEndCount = (evaluate((exEndCount > 0 ? TRUE : FALSE), operator[end-1], operand[end]))
                == TRUE
                ? 1 : 0;
        exEndCount = Math.max(withEndCount, Math.max(withEndCount*exEndCount, exEndCount));


        return exEndCount + exStartCount;
    }

    private Character evaluate(Integer start, Integer end)
    {
        return evaluate(operand[start], operator[start], operand[end]);
    }

    private Character evaluate(Character operand1, Character operator, Character operand2)
    {
        boolean op1 = operand1 == TRUE;
        boolean op2 = operand2 == TRUE;

        switch (operator)
        {
            case '&':
                return (op1 && op2) ? TRUE : FALSE;
            case '|':
                return (op1 || op2) ? TRUE : FALSE;
            case '^':
                return (op1 ^ op2) ? TRUE : FALSE;
        }

        System.out.println("cant find operator");
        return ' ';
    }
}