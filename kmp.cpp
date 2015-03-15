#include <iostream>
#include <vector>
#include <memory>

using namespace std;

class SearchStringKMP
{
    public:
    SearchStringKMP() 
    : m_text(),
      m_debug(false)
    {}
    
    SearchStringKMP(string text)
    : m_text(text),
      m_debug(false)
    {}
    
    vector<int> findPositions(string subString)
    {
        vector<int> positions;
        if (subString.size() > m_text.size())
        {
            return positions;
        }
        
        createPartialMatchTable(subString);
        
        int position = 0;
        while(position<(m_text.size() - subString.size()))
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
                    
                    cout << "\n breaking at " << position << " " << evaluationPosition;
                    
                    break;
                }
                
                ++partialMatchLength;
            }
            
            if (evaluationPosition == subString.size())
            {
                positions.push_back(position);
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
            cout << "for i " << i << " " << " sslen " << subSubStringSize << endl;
            
            int maxPartialLen = 0;
            for (int j=0; j<subSubStringSize-1; ++j)
            {
                string prefix = subSubString.substr(0, j+1);
                string suffix = subSubString.substr(subSubStringSize-j-1, j+1);
                
                cout << " for j " << j << " "
                << prefix << " " << suffix << endl;
                
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

};

int main()
{
    SearchStringKMP ss("ABC ABCDAB ABCDABCDABDE");
    auto positions = ss.findPositions("ABCDABD");
    ss.printPartialMatchTable();
    
    cout << "\n positon ";
    for (auto position : positions)
    {
        cout << position << " ";
    }
}
