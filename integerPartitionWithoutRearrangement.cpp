#include <iostream>
#include <limits>

using namespace std;

#define K 2
#define N 4

int M[K+1][N+1];
int S[N+1];
int I[K];

void printM()
{
    for (int i=1; i<=K+1; ++i)
    {
        for (int j=1; j<N+1; ++j)
        {
            cout << M[i][j] << " ";
        }
        cout << endl;
    }
        
}

void printI()
{
    for(int i=1; i<=K; ++i)
        cout << I[i] << " ";
        
    cout << endl;
}


int sum(int i, int j)
{
    int val = 0;
    for (int k=i; k<=j; ++k)
        val += S[k];
        
    return val;

}

int calculateCost(int n, int k)
{
    cout << n << " " << k << endl;

    if (k == 1)
    {
        M[n][k] = sum(1, n);
        return M[n][k];
    }

    if (n == 1)
    {
        M[n][k] = S[n];
        return M[n][k];
    }

    int minVal = numeric_limits<int>::max();
    int minI = -1;
    for (int i=1; i<=n; ++i)
    {
        int prevVal = calculateCost(i, k-1);
        int thisVal = sum(i+1, n);

        int maxVal = max(prevVal, thisVal);

        if (minVal > maxVal)
        {
            minVal = maxVal;
            minI = i;
        }
    }
    
    M[n][k] = minVal;
    I[k] = minI;

    return minVal;
}

int main()
{
    S[1] = 500;
    S[2] = 200;
    S[3] = 100;
    S[4] = 1000;

    cout << "cost " << calculateCost(N, K) << endl;

    printI();
    printM();

    string s;
    cin >> s;

    return 0;
}
