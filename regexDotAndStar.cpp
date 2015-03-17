#include <iostream>
#include <utility>
#include <map>
#include <vector>
#include <memory>

using namespace std;

class SearchStringKMP
{
    public:
    SearchStringKMP() 
    : m_text(),
      m_debug(false),
      positions()
    {}
    
    SearchStringKMP(string text)
    : m_text(text),
      m_debug(false),
      positions()
    {}
    
    map<int, int> findRegexDotPositions(string subString)
    {
        if (subString.size() > m_text.size())
        {
            return positions;
        }
        
        createPartialMatchTable(subString);
        
        int position = 0;
        while(position <= (m_text.size() - subString.size()))
        {
            int skipDistance = 1;
            int partialMatchLength = 0;
            
            int evaluationPosition = 0;
            for (; evaluationPosition < subString.size(); ++evaluationPosition)
            {
                if (subString.at(evaluationPosition) != '.'
                    && subString.at(evaluationPosition) != m_text.at(position + evaluationPosition))
                {
                    if (partialMatchLength > 0
                        && m_partialMatchTable.at(partialMatchLength-1) > 0)
                    {
                        skipDistance = partialMatchLength - m_partialMatchTable.at(partialMatchLength-1);
                    }
                    
                    break;
                }
                
                ++partialMatchLength;
            }
            
            if (evaluationPosition == subString.size())
            {
                positions.insert(make_pair(position, evaluationPosition));
            }
            
            position += skipDistance;
        }
        
        return positions;
    }
    
    map<int, int> findRegexStarPositions(string subString)
    {
        
        if (subString.size() > m_text.size())
        {
            return positions;
        }
        
        createPartialMatchTable(subString);
        
        int position = 0;
        while(position <= (m_text.size() - subString.size()))
        {
            int skipDistance = 1;
            int partialMatchLength = 0;
            
            int evaluationPosition = 0;
            for (; evaluationPosition < subString.size(); ++evaluationPosition)
            {
                if (subString.at(evaluationPosition) != m_text.at(position + evaluationPosition))
                {
                    if (partialMatchLength > 0
                        && m_partialMatchTable.at(partialMatchLength-1) > 0)
                    {
                        skipDistance = partialMatchLength - m_partialMatchTable.at(partialMatchLength-1);
                    }
                    
                    break;
                }
                
                ++partialMatchLength;
            }
            
            // for repetition
            if (evaluationPosition == subString.size())
            {
                while ((evaluationPosition + position) < m_text.size()
                       && subString.back() == m_text.at(evaluationPosition + position))
                {
                    ++evaluationPosition;
                }
            }
            
            if (evaluationPosition >= subString.size()-1)
            {
                positions.insert(make_pair(position, evaluationPosition));
            }
            
            position += skipDistance;
        }
        
        return positions;
    }
    
    
    void enbleDebug() {m_debug = true;}
    void disbleDebug() {m_debug = true;}
    
    void printPartialMatchTable()
    {
        for (auto size : m_partialMatchTable)
        {
            cout << size << " ";
        }
    }
    
    private:
    
    void createPartialMatchTable(string subString)
    {
        int subStringSize = subString.size();
        
        if (m_partialMatchTable.capacity() < subStringSize)
        {
            m_partialMatchTable.resize(subStringSize);
        }
        
        for(int i=0; i<subStringSize; ++i)
        {
            string subSubString = subString.substr(0, i+1);
            
            int subSubStringSize = subSubString.size();
            
            int maxPartialLen = 0;
            for (int j=0; j<subSubStringSize-1; ++j)
            {
                string prefix = subSubString.substr(0, j+1);
                string suffix = subSubString.substr(subSubStringSize-j-1, j+1);
                
                if (prefix == suffix)
                {
                    maxPartialLen = j+1;
                }
            }
            m_partialMatchTable.at(i) = maxPartialLen;
        }
    }
    
    void debug(string log)
    {
        if (m_debug)
        {
            cerr << log << endl;
        }
    }
    
    string m_text;
    bool m_debug;
    vector<int> m_partialMatchTable;
    map<int,int> positions;

};

int main()
{
    SearchStringKMP ss("qm qml qqmllllqmqmll");
    ss.printPartialMatchTable();
    auto positions = ss.findRegexStarPositions("qml");
    
    cout << "\n positon ";
    for (auto position : positions)
    {
        cout << position.first << " " << position.second << endl;
    }
}
