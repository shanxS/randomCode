#include<iostream>
#include<stdio.h>
#include<list>
#include<map>
#include<utility>

using namespace std;

class Cache
{
    public:
    Cache(int size)
    : m_keyAccessTime(),
      m_keyLocations(),
      m_cache(),
      m_cacheSize(size)
    {}
    
    void insert(string key, string value)
    {
        if (m_cache.size() >= m_cacheSize)
        {
            debug("enacting lru");
            enactLRU();
        }
        
        m_keyAccessTime.push_front(key);
        list<string>::iterator it = m_keyAccessTime.begin();
        m_keyLocations.insert(make_pair<string, list<string>::iterator>(key, it));
        
        m_cache.insert(make_pair<string, string>(key, value));
    }
    
    string getValue(string key)
    {
        map<string, string>::iterator it = m_cache.find(key);
        
        if (it != m_cache.end())
        {
            list<string>::iterator oldIt = m_keyLocations.at(key);
            m_keyAccessTime.erase(oldIt);
            m_keyAccessTime.push_front(key);
            m_keyLocations.at(key) = m_keyAccessTime.begin();
            
            debug("cache timings updated");
            return it->second;
        }
        else
        {
            return string();
        }
    }
    
    void printCacheEntries()
    {
        map<string, string>::iterator it = m_cache.begin();
        while(it != m_cache.end())
        {
            cout << it->first << " " << it->second << endl;
            it++;
        }
    }
    
    void enableDebug() {m_debug = true;}
    void disableDebug() {m_debug = false;}
    
    private:
    
    void debug(string log)
    {
        if (m_debug)
        {
            cerr << log << endl;
        }
    }
    
    void enactLRU()
    {
        list<string>::iterator keyIt = m_keyAccessTime.end();
        keyIt--;
        string key = *keyIt;
        
        m_keyAccessTime.erase(keyIt);
        m_keyLocations.erase(key);
        m_cache.erase(key);
        
        m_cacheSize--;
    }
    
    list<string> m_keyAccessTime;
    map<string, list<string>::iterator> m_keyLocations;
    map<string, string> m_cache;
    int m_cacheSize;
    bool m_debug;
};

int main()
{
    string k1 = "1"; string v1 = "a";
    string k2 = "2"; string v2 = "b";
    string k3 = "3"; string v3 = "c";
    string k4 = "4"; string v4 = "d";
    string k5 = "5"; string v5 = "e";

    Cache cache(4);
    cache.enableDebug();
    
    cache.insert(k1, v1);
    cache.insert(k2, v2);
    cache.insert(k3, v3);
    cache.insert(k4, v4);
    
    cache.printCacheEntries();

    cout << cache.getValue(k1) << endl;
    
    cache.insert(k5, v5);
    
    cache.printCacheEntries();
}
