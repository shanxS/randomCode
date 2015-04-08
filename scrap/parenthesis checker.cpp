// parenthesis checker
// amazon set 2
// onlne test, ques 1
// http://www.geeksforgeeks.org/amazon-interview-set-2/

#include <iostream>

using namespace std;

void checkParenthesis(string text)
{
    int count = 0;
    
    int i=0;
    for (; i<text.size() && count>=0; ++i)
    {
        if (text.at(i) == '(')
        {
            ++count;
        }
        else if (text.at(i) == ')')
        {
            --count;
        }
    }
    
    if (i != text.size() || count != 0)
    {
        cout << "not equal";
    }
    else
    {
        cout << "equal";
    }
}

int main()
{
    string text = ")(";
    checkParenthesis(text);
}