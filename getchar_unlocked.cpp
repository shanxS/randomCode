// only int are read, -vs sign is ignored
// for background: https://stackoverflow.com/questions/19630669/reading-input-using-getchar-unlocked

#include<stdio.h>

void scanInt(int &x)
{
	register int c = getchar_unlocked();
	x = 0;

	for (; (c<48 || c>57); c=getchar_unlocked());
	for (; c>47 && c <58; c=getchar_unlocked())
	{
		x = (x<<1) + (x<<3) + c - 48;
	}
	
}

int main()
{
	int a;
	for (;;)
	{
		scanInt(a);
		printf("%d \n", a);
	}
}