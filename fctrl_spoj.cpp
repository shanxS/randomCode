#include <bits/stdc++.h>
#include <iostream>
#include <math.h>
#include <vector>


#define P(x) printf("%d\n", x)
#define PS(x) printf("%s\n", x)
#define S(x) scanf("%d", &x)
#define SS(x) scanf("%s", &x)

int N;
int Z;
const int MUL = 5;


int main()
{
    int t;
    S(t);
    while(t-- > 0)
    {
        S(N);

        for (int i=1;; ++i)
        {
            int k = N/(pow(MUL, i));
            if (k==0) break;
            else Z+=k;
        }

        P(Z);
        Z = 0;
    }
    return 0;
}
