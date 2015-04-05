#include <iostream>
#include <string>
#include <cassert>

using namespace std;

class LengthCalculator
{
    public:
        LengthCalculator()
            : m_invalid(-1),
              m_start(m_invalid),
              m_end(m_invalid),
              m_length(m_invalid)
        {}
    
        string getPalindrome(string text)
        {
            m_text = text;
            calculate();
            return extractPlaindrome();
        }
    
    private:
        const int m_invalid;
        int m_start, m_end, m_length;
        string m_text;
        
        string extractPlaindrome()
        {
            assert(m_start != m_invalid);
            assert(m_length != m_invalid);
        
            return m_text.substr(m_start, m_length);
        }
        
        void calculate()
        {
            m_length = m_invalid;
            
            for (int i=1; i<m_text.length(); ++i)
            {
                // check even lenght
                int start = i-1;
                int end = i;
                while (start >=0 
                       && end < m_text.length()
                       && m_text.at(start) == m_text.at(end))
                {
                    int currentLength = end - start + 1;
                    if (currentLength > m_length)
                    {
                        m_start = start;
                        m_end = end;
                        m_length = currentLength;
                    }
                    
                    --start;
                    ++end;
                }
                
                // check odd length
                start = i-1;
                end = i + 1;
                while (start >=0 
                       && end < m_text.length()
                       && m_text.at(start) == m_text.at(end))
                {
                    int currentLength = end - start + 1;
                    if (currentLength > m_length)
                    {
                        m_start = start;
                        m_end = end;
                        m_length = currentLength;
                    }
                    
                    --start;
                    ++end;
                }
            }
        }
};

int main()
{
    LengthCalculator lc;
    cout << lc.getPalindrome("formaamfor");
}




















