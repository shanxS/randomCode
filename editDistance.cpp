#include <iostream>
#include <string>
#include <stdio.h>
#include <string.h>

using namespace std;

#define MATCH 0 /* enumerated type symbol for match */
#define INSERT 1 /* enumerated type symbol for insert */
#define DELETE 2

int lena = 14;
char a[] = {' ', 't', 'h', 'o', 'u', '-', 's', 'h', 'a', 'l', 't', '-', 'n', 'o', 't', '\0'};
//char a[] = {' ', 'z', 't', 'r', '\0'};
//char a[] = {' ', 'z', 't', 'r', '\0'};

int lenb = 14;
char b[] = {' ', 'y', 'o', 'u', '-', 's', 'h', 'o', 'u', 'l', 'd', '-', 'n', 'o', 't', '\0'};
//char b[] = {' ', 't', 'r', '\0'};
//char b[] = {' ', 't', 'r', 'y', '\0'};

#define maxlena 15

struct cell
{
    int cost; /* cost of reaching this cell */
    int parent; /* parent cell */
};

cell m[maxlena][maxlena];

void printm()
{
    cout << endl;

    for (int i=0; i<maxlena; i++) {
        for (int j=0; j<maxlena; j++) {
            cout << m[i][j].cost /*<< "," << m[i][j].parent*/ << " ";
        }
        cout << endl;
    }
}

void insert_out(char *t, int j)
{
    cout << "I";
}

void delete_out(char *s, int i)
{
    cout << "D";
}

void match_out(char *s, char *t, int i, int j)
{
    if (s[i]==t[j]) 
        cout << "M";
    else 
        cout << "S";
}

void row_init(int i)
{
    m[0][i].cost = i;
    if (i>0)
        m[0][i].parent = INSERT;
    else
        m[0][i].parent = -1;
}

void column_init(int i)
{
    m[i][0].cost = i;
    if (i>0){
        m[i][0].parent = DELETE;
    }
    else
        m[i][0].parent = -1;
}

int match(char c, char d)
{
  if (c == d) return(0);
  else return(1);
}

int indel(char c)
{
  return(1);
}

void goal_cell(char *s, char *t, int *i, int *j)
{
    *i = strlen(s) - 1;
    *j = strlen(t) - 1;
}

int string_compare(char *s, char *t)
{
    int i,j,k; /* counters */
    int opt[3]; /* cost of the three options */

    for (i=0; i<maxlena; i++) {
        row_init(i);
        column_init(i);
    }

    cout << "after init";
    printm();

    for (i=1; i<strlen(s); i++) {
        for (j=1; j<strlen(t); j++) {

            opt[MATCH] = m[i-1][j-1].cost + match(s[i],t[j]);
            opt[INSERT] = m[i][j-1].cost + indel(t[j]);
            opt[DELETE] = m[i-1][j].cost + indel(s[i]);

            m[i][j].cost = opt[MATCH];
            m[i][j].parent = MATCH;

            for (k=INSERT; k<=DELETE; k++)
                if (opt[k] < m[i][j].cost) {
                    m[i][j].cost = opt[k];
                    m[i][j].parent = k;
                }
        }
    }

    goal_cell(s,t,&i,&j);

    return( m[i][j].cost );
}

void reconstruct_path(char *s, char *t, int i, int j)
{
    if (m[i][j].parent == -1) 
        return;
    
    if (m[i][j].parent == MATCH) 
    {
        reconstruct_path(s,t,i-1,j-1);
        match_out(s, t, i, j);
        return;
    }
    if (m[i][j].parent == INSERT) 
    {
        reconstruct_path(s,t,i,j-1);
        insert_out(t,j);
        return;
    }
    if (m[i][j].parent == DELETE) 
    {
        reconstruct_path(s,t,i-1,j);
        delete_out(s,i);
        return;
    }
}

int main()
{
    int s1;
    cout << endl << "ans " << string_compare(a, b);
    reconstruct_path(a, b, lena, lenb);
    cin >> s1;
}
