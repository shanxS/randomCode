// geek for geeks amazon 71
// round 3 ques 3
// http://www.geeksforgeeks.org/amazon-interview-set-71-sde-2/
// --------------------
// soln also prints the indices of elementa

#include <iostream>
#include <vector>
#include <cassert>
#include <map>
#include <utility>

using namespace std;

/*int WT[] = {1, 3, 4};
int V[] = {10, 20, 30};
int W = 5;*/

int WT[] = {1, 1, 1};
int V[] = {10, 20, 30};
int W = 2;

map<pair<int, int>, pair<vector<int>, int>> MAP;

pair<vector<int>, int> knapSack(int w, int n)
{
    auto key = make_pair(w, n);

    if (MAP.find(key) != MAP.end())
    {
        return MAP.at(key);
    }

    if (w == 0 || n < 0)
    {
        vector<int> history;
        auto value = make_pair(history, 0);
        
        MAP.insert(make_pair(key, value));
        
        return MAP.at(key);
    }
    
    if (WT[n] > w)
    {
        return knapSack(w, n-1);
    }
    
    auto retValIncludingCurrent = knapSack(w - WT[n], n-1);
    int valueIncludingCurrent = V[n] + retValIncludingCurrent.second;
    
    auto retValExcludingCurrent = knapSack(w, n-1);
    int valueExcludingCurrent = retValExcludingCurrent.second;
    
    if (valueIncludingCurrent < valueExcludingCurrent)
    {
        auto value = make_pair(retValExcludingCurrent.first, valueExcludingCurrent);

        MAP.insert(make_pair(key, value));
    }
    else
    {
        retValIncludingCurrent.first.push_back(n);
        auto value = make_pair(retValIncludingCurrent.first, valueIncludingCurrent);

        MAP.insert(make_pair(key, value));
    }
    
    return MAP.at(key);
}

int main()
{
    assert(sizeof(WT) == sizeof(V));
    
    auto result = knapSack(W, (sizeof(WT)/sizeof(WT[0])) - 1);
    cout << "cost " << result.second << endl;
    
    for (auto val : result.first)
    {
        cout << val << " ";
    }
}
