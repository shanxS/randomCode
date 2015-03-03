#include <cstdio>
#include <cstring>
#include <iostream>

using namespace std;

int counter = 1;

int main()
{
    int R = 3;
    int C = 3;
    int mat[3][3] = {{1,2,3}, {4,5,6}, {7,8,9}};

    int i,j,k,l;
    i=0;    // row inc
    j=C-1;  // col red
    k=R-1;  // row red
    l=0;    // col inc

    int counter;

    while(i<=j)
    {
        cout << "\n";
        for (counter=i; counter<=j; ++counter) cout << mat[i][counter];
        i++;

        cout << "\n";
        for (counter=i; counter<=k; ++counter) cout << mat[counter][j];
        j--;

        cout << "\n";
        for (counter=j; counter>=l; --counter) cout << mat[k][counter] << "(" << k << "," << counter << ")";
        k--;

        cout << "\n";
        for (counter=k; counter<=i; ++counter) cout << mat[counter][l];
        l++;
    }


    return 0;
}

