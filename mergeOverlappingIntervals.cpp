#include<iostream>
#include<utility>
#include<list>
#include<algorithm>

using namespace std;

list< pair<int, int> > intervals;

bool comparator(pair<int, int> a, pair<int, int> b) 
{
    if(a.first > b.first) return false;
    else return true; 
}

void init()
{
    intervals.push_back(pair<int, int>(1,3));
    intervals.push_back(pair<int, int>(8,10));
    intervals.push_back(pair<int, int>(2,6));
    intervals.push_back(pair<int, int>(15,18));
}

void print()
{
    list< pair<int, int> >::iterator it = intervals.begin();
    list< pair<int, int> >::iterator itEnd = intervals.end();
    for (; it != itEnd; it++)
    {
        pair<int, int> val = *it;
        cout << endl << val.first << " " << val.second;
    }
}

void merge()
{
    list< pair<int, int> >::iterator it = intervals.begin();
    list< pair<int, int> >::iterator itOneLessThanEnd = intervals.end();
    --itOneLessThanEnd;
    
    for (; it != itOneLessThanEnd; ++it)
    {
        list< pair<int, int> >::iterator nextIt = it;
        ++nextIt;
        
        if (it->second >= nextIt->first)
        {
            it->second = nextIt->second;
            intervals.erase(nextIt);
        }
    }
}

int main()
{
    init();
    
    print();
    
    intervals.sort (comparator);
    merge();
    
    print();
}
