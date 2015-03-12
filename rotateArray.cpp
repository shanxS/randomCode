#include<iostream>

using namespace std;

int LEN = 7;
int A[] = {1,2,3,4,5,6,7};

void print()
{
    cout << endl;
    for (int i=0; i<LEN; ++i) cout << A[i] << " ";
}

void rotateRight(int n)
{
    for(int j=0; j<n; ++j)
    {
        int tmp = A[1];
        A[1] = A[0];
        for (int i=2; i<LEN; ++i)
        {
            int tmp2 = A[i];
            A[i] = tmp;
            tmp = tmp2;
        }
        A[0] = tmp;
    }
}

void rotateLeft(int n)
{
    for(int j=0; j<n; ++j)
    {
        int tmp = A[LEN-2];
        A[LEN-2] = A[LEN-1];
        for (int i=LEN-3; i>=0; --i)
        {
            int tmp2 = A[i];
            A[i] = tmp;
            tmp = tmp2;
        }
        A[LEN-1] = tmp;
    }
}

int main()
{
    print();
    
    rotateLeft(3);
    
    print();
}