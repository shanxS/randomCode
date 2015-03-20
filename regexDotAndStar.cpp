// the idea is to search for all . meta char
// then search the results for * meta char
// and so on
// the problem is that the merging of values of 2 searches
// this is not perfect code - but gets pretty close

#include <iostream>
#include <algorithm>
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
      m_debug(false)
    {}
    
    SearchStringKMP(string text)
    : m_text(text),
      m_debug(false)
    {}
    
    map<int, int> findRegexDotPositions(string subString)
    {
        map<int, int> positions;
    
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
            
            position += (skipDistance > evaluationPosition) ? skipDistance : evaluationPosition;
        }
        
        return positions;
    }
    
    map<int, int> findRegexStarPositions(string subString)
    {
        map<int, int> positions;
        
        if (subString.size() > m_text.size())
        {
            return positions;
        }
        
        createPartialMatchTable(subString);
        
        int position = 0;
        while(position <= (m_text.size() - subString.size()) )
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
            
            if (evaluationPosition == subString.size() - 1)
            {
                positions.insert(make_pair(position, evaluationPosition));
                cout << "here " << endl;
            }
            else if (evaluationPosition == subString.size())
            {
                while ((evaluationPosition + position) < m_text.size()
                       && subString.back() == m_text.at(evaluationPosition + position))
                {
                    ++evaluationPosition;
                }
                
                positions.insert(make_pair(position, evaluationPosition));
            }
            
            position += (skipDistance > evaluationPosition) ? skipDistance : evaluationPosition;
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
    
    map<string, vector<pair<int, int>>> processAllForDot(vector<string> strings)
    {
        map<int, int> positions;
        vector<string> results;
        for (auto searchString : strings)
        {
            if (searchString.find('.') != string::npos)
            {
                auto partialPositions = findRegexDotPositions(searchString);
                positions.insert(partialPositions.begin(), partialPositions.end());
            }
        }
        
        return convertPositionsToPositionalStrings(positions);
    }

    map<string, vector<pair<int, int>>> processAllForStar(vector<string> strings)
    {
        map<int, int> positions;
        for (auto searchString : strings)
        {
            auto partialPositions = findRegexStarPositions(searchString);
            positions.insert(partialPositions.begin(), partialPositions.end());
        }
        
        return convertPositionsToPositionalStrings(positions);
    }

    vector<string> getStringFromPositionalStrings(map<string, vector<pair<int, int>>> positionalStrings)
    {
        vector<string> strings;
        for (auto positionalString : positionalStrings)
        {
            strings.push_back(positionalString.first);
        }
        
        return strings;
    }

    void printFinalResults (map<string, vector<pair<int, int>>> finalResults)
    {
        cout << "\n printing results " << endl;

        for (auto result : finalResults)
        {
            cout << result.first << " ";
            auto positions = result.second;
            for_each(positions.begin(), positions.end(), [] (pair<int, int> value) {
                                                            cout << value.first << "," << value.second << " | ";
                                                            });
            cout << endl;
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
    
    map<string, vector<pair<int, int>>> convertPositionsToPositionalStrings(map<int, int> positions)
    { 
        map<string, vector<pair<int, int>>> positionalString;
        
        for (auto position : positions)
        {
            string value;
            for (int i=position.first; i<position.first + position.second; ++i)
            {
                value.push_back(m_text.at(i));
            }
            vector<pair<int, int>> positionVector;
            positionVector.push_back(position);
            auto insertStatus = positionalString.insert(pair<string, vector<pair<int, int>>>(value, positionVector));
            if (!(insertStatus.second))
            {
                ((insertStatus.first)->second).push_back(position);
            }
            
        }
        
        return positionalString;
    }

    
    string m_text;
    bool m_debug;
    vector<int> m_partialMatchTable;

};



int main()
{
    SearchStringKMP ss("is is istis is i is iss ist istiss");
    string subString = "is.is*";
    
    map<string, vector<pair<int, int>>> finalResults;
    vector<string> searchStrings;
    searchStrings.push_back("");
    bool needToProcessForDot = false;
    for (int i=0; i<subString.size(); ++i)
    {
        if (subString.at(i) == '*')
        {
            if (needToProcessForDot)
            {
                finalResults = ss.processAllForDot(searchStrings);
                searchStrings = ss.getStringFromPositionalStrings(finalResults);
                needToProcessForDot = false;
            }
            finalResults = ss.processAllForStar(searchStrings);
            searchStrings = ss.getStringFromPositionalStrings(finalResults);
        }
        else
        {
            for_each(searchStrings.begin(), searchStrings.end(), [&] (string &searchString) {
                                                     searchString.push_back(subString.at(i));
                                                     });
            
            if(!needToProcessForDot)
            {
                needToProcessForDot = (subString.at(i) == '.') ? true : false;
            }
        }
    }
    if (needToProcessForDot)
    {
        finalResults = ss.processAllForDot(searchStrings);
    }
    
    ss.printFinalResults(finalResults);    
}
