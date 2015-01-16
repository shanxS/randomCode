// 1st problem (the coins problem)
// in https://www.topcoder.com/tc?d1=tutorials&d2=dynProg&module=Static
/*

Given a list of N coins, their values (V1, V2, ... , VN), and the total sum S. Find the minimum number of coins the sum of which is S (we can use as many coins of one type as we want), or report that it's not possible to select coins in such a way that they sum up to S. 

V = {1,3,5}
S = 11

*/

#include <iostream>
#include <string>
#include <cstring>
#include <limits>

using namespace std;

#define S 11
#define CCOUNT 3
#define NF -1
#define INTMAX 1000

int C[CCOUNT+1];
int M[S+1];

void printC()
{
    for (int i=0; i<=CCOUNT; ++i)
        cout << C[i] << " ";

    cout << endl;
}

void printM()
{
    for (int i=0; i<=S; ++i)
        cout << M[i] << " ";

    cout << endl;
}

int getM(int val)
{
    if (val < 0 || val > S)
    {
        return INTMAX;
    }
    else
    {
        return M[val];
    }
}

int evaluate()
{
    M[0] = 0;

    // get all possibel values
    // use minimum
    for (int i=1; i<=S; ++i)
    {
        int minVal = INTMAX;
        for (int j=1; j<=CCOUNT; ++j)
        {
            int val = getM(i - C[j]) + 1;
            if (val < minVal)
            {
                minVal = val;
            }
        }

        M[i] = minVal;
    }

    return M[S];
}

int main()
{
    C[0] = 0;
    C[1] = 1;
    C[2] = 3;
    C[3] = 5;

    memset(M, INTMAX, sizeof(M));


    cout << "value " << evaluate() << endl;

    printM();

    string s;
    cin >> s;
}
