#include <iostream>
#include <limits>
#include <cstring>

using namespace std;

#define K 3
#define N 4
#define NF -1

int M[N+1][K+1];
int S[N+1];
int I[K];

void printM()
{
    for (int i=0; i<=N; ++i)
    {
        for (int j=0; j<=K; ++j)
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
    
    if (M[n][k] != NF)
    {
        return M[n][k];
    }
    
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
    S[1] = 1000;
    S[2] = 250;
    S[3] = 250;
    S[4] = 1000;

    memset(M, -1, sizeof(M)); 
    
    cout << "cost " << calculateCost(N, K) << endl;

    printI();
    printM();
    
    string s;
    cin >> s;
    
    return 0;
}
