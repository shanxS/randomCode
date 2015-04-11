// http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
// longest not incresing subseq and print the sub seq

#include <iostream>
#include <utility>
#include <map>
#include <vector>
#include <limits>

using namespace std;

vector<int> A = /*{ 10, 22, 9, 33, 21, 50, 41, 60 };*/{ 10, 22, 9, 33, 21, 22, 23, 50, 41, 60 };
vector<int> DP;
const int INVALID = 0;
map<int, vector<int>> MAP;

void updateList(int lastIndex, int thisIndex)
{
    auto it = MAP.find(lastIndex);
    if (it == MAP.end())
    {
        vector<int> v = {A.at(thisIndex)};
        MAP.insert(make_pair(thisIndex, v));
    }
    else
    {
        auto v = it->second;
        v.push_back(A.at(thisIndex));
        MAP.insert(make_pair(thisIndex, v));
    }
}

void find(int n)
{
    if (DP.at(n) != INVALID)
    {
        return;
    }

    if (n == 0)
    {
        DP.at(n) = 1;
        updateList (n, n);
        return;
    }
    
    int lastIndex = n;
    for (int i=0; i<n; ++i)
    {
        if (DP.at(i) == INVALID) find(i);
        
        int prevMaxLen = DP.at(i);
        if (A.at(i) < A.at(n))
        {
            if (DP.at(n) < prevMaxLen + 1)
            {
                DP.at(n) = prevMaxLen + 1;
                lastIndex = i;
            }
        }
    }
    
    updateList (lastIndex, n);
}

void initialiseDP()
{
    for (int i=0; i<A.size(); ++i)
    {
        DP.push_back(INVALID);
    }
}

int main()
{   
    initialiseDP();
    find (A.size() - 1);
    
    int max = 0;
    int lastIndex = 0;
    for (int i=0; i<DP.size(); ++i)
    {
        if (DP.at(i) > max)
        {
            max = DP.at(i);
            lastIndex = i;
        }
    }
    cout << "lenght " << max << endl;
    
    auto it = MAP.find(lastIndex);
    for (const auto &value : it->second)
    {
        cout << value << " ";
    }
    
    
}
