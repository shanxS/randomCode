#include <iostream>
#include <string>

using namespace std;

#define MATCH 0 /* enumerated type symbol for match */
#define INSERT 1 /* enumerated type symbol for insert */
#define DELETE 2

int match(char c, char d)
{
  if (c == d) return(0);
  else return(1);
}

int indel(char c)
{
  return(1);
}

int string_compare(char *s, char *t, int i, int j, string recur)
{
    int k; /* counter */
    int lowest_cost; /* lowest cost */
    int opt[3]; /* cost of the three options */

    cout << recur << " " << s[i] << " " << t[j] << "\n";

    if (i == 0) return(j * indel(' '));
    if (j == 0) return(i * indel(' '));

    opt[MATCH] = string_compare(s,t,i-1,j-1, "MATCH") + match(s[i],t[j]);
    opt[INSERT] = string_compare(s,t,i,j-1, "INSERT") + indel(t[j]);
    opt[DELETE] = string_compare(s,t,i-1,j, "DELETE") + indel(s[i]);

    cout << endl
         << i << " " << j << " "
         << s[i] << " " << t[j] << " "
         << opt[MATCH] << " " << opt[INSERT] << " " << opt[DELETE] << " "
         << recur
         << endl;

    lowest_cost = opt[MATCH];
    for (k=INSERT; k<=DELETE; k++)
        if (opt[k] < lowest_cost) lowest_cost = opt[k];

    return( lowest_cost );
}


int main()
{
    char str1[12] = "Hello";
    char str2[12] = "ello";
    std::string s1, s2;

    int lena = 3;
    char a[] = {'a', 'z', 'i', 'e'};

    int lenb = 2;
    char b[] = {'b', 't', 'r'};
    cout << endl << "ans " << string_compare(a, b, lena, lenb, "ORIG");
    cin >> s1;
}
