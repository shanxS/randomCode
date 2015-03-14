#include<iostream>
#include<utility>
#include<string>
#include<map>
#include<vector>

using namespace std;

class TrieEntry
{
    public:
    TrieEntry()
    {
        thisEntry = '\0';
        ending = 0;
    }
    
    bool isParentEntry()
    {
        if (thisEntry == '\0') return true;
        else                   return false;
    }
   
    TrieEntry (char entry)
    {
        thisEntry = entry;
        ending = 0;
    }
    
    void addEntry (string entry)
    {
        if (entry.size() == 0)
        {
            ++ending;
            return;
        }
    
        pair<map<char, TrieEntry>::iterator, bool> insertResult = entryMap.insert(make_pair(entry.at(0), TrieEntry(entry.at(0))));
        
        map<char, TrieEntry>::iterator it = insertResult.first;
        (it->second).addEntry(entry.substr(1, entry.size() - 1));
    }
    
    void print()
    {
        print(0);
    }
    
    bool findExactEntry (string entry)
    {   
        if (entry.size() == 0)
        {
            return true;
        }
    
        map<char, TrieEntry>::iterator it = entryMap.find(entry.at(0));
        if (it == entryMap.end())
        {
            return false;
        }
        else
        {
            return (it->second).findExactEntry(entry.substr(1, entry.size() - 1));
        }
    }
    
    int countPrefixEntry (string entry)
    {
        if (entry.size() == 0)
        {
            return ending + calculatePostFixEnding();
        }
    
        map<char, TrieEntry>::iterator it = entryMap.find(entry.at(0));
        if (it == entryMap.end())
        {
            return 0;
        }
        else
        {
            return (it->second).countPrefixEntry(entry.substr(1, entry.size() - 1));
        }
    }
    
    vector<string> getPrefixEntries (string entry)
    {   
        vector<string> prefix; 
        
        if (entry.size() == 0)
        {
            return getChildEntries();
        }
        
        map<char, TrieEntry>::iterator it = entryMap.find(entry.at(0));
        if (it == entryMap.end())
        {
            return prefix;
        }
        else
        {
            string thisEntryString;
            thisEntryString.push_back(thisEntry);
        
            prefix = (it->second).getPrefixEntries(entry.substr(1, entry.size() - 1));
            for(int i=0; i<prefix.size(); ++i)
            {
                prefix[i] = thisEntryString + prefix[i];
            }
             
            return prefix;
        }
    }
    
    private:
    
    vector<string> getChildEntries()
    {
        vector<string> prefix; 
        string thisEntryString;
        thisEntryString.push_back(thisEntry);
    
        for (int i=0; i<ending; ++i)
        {
            prefix.push_back(thisEntryString);
        }
        
        vector<string> childEntries;
        map<char, TrieEntry>::iterator it = entryMap.begin();
        while (it != entryMap.end())
        {
            vector<string> thisChildEntry = (it->second).getChildEntries();
            childEntries.insert(childEntries.end(), thisChildEntry.begin(), thisChildEntry.end());
            
            it++;
        }
        
        for (int i=0; i<childEntries.size(); ++i)
        {
            childEntries[i] = thisEntryString + childEntries[i];
        }
        
        prefix.insert(prefix.end(), childEntries.begin(), childEntries.end());
        
        return prefix;
    }
    
    int calculatePostFixEnding()
    {
        int postFixEndingCount = 0;
    
        map<char, TrieEntry>::iterator it = entryMap.begin();
        while (it != entryMap.end())
        {   
            postFixEndingCount += (it->second).ending + (it->second).calculatePostFixEnding();
            it++;
        }
        
        return postFixEndingCount;
    }
    
    void print(int depth)
    {
        for (int i=0; i<depth; ++i) cout << " ";
        cout << thisEntry << " " << ending;
        
        cout << " - ";
        map<char, TrieEntry>::iterator it = entryMap.begin();
        map<char, TrieEntry>::iterator itEnd = entryMap.end();
        while(it != itEnd)
        {
            cout << it->first << " ";
            it++;
        }
        cout << endl;
        
        it = entryMap.begin();
        while(it != itEnd)
        {
            (it->second).print(depth + 1);
            it++;
        }
        
    }
    
    char thisEntry;
    map<char, TrieEntry> entryMap;
    int ending;
};

int main()
{
    TrieEntry trie;
    trie.addEntry("win");
    trie.addEntry("winner");
    trie.addEntry("this");
    trie.addEntry("train");
    trie.addEntry("is");
    trie.addEntry("isis");
    trie.addEntry("struggle");
    trie.addEntry("shashank");
    trie.addEntry("shahtanu");
    trie.addEntry("shanx");
    trie.addEntry("shanu");
    trie.addEntry("shanu");
    trie.addEntry("shanu");
    trie.addEntry("sumedha");
    
    trie.print();
    
    while(true)
    {
        string s;
        cin >> s;
        vector<string> str = trie.getPrefixEntries(s);
        for (int i=0; i<str.size(); ++i)
        {
            cout << str[i] << endl;
        }
    }
    
}
