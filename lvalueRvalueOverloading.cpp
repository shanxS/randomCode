#include <iostream>
#include <ostream>
#include <string>

using namespace std;

void meow(string& s) {
    cout << "meow(string&): " << s << endl;
}

void meow(const string& s) {
    cout << "meow(const string&): " << s << endl;
}

void meow(string&& s) {
    cout << "meow(string&&): " << s << endl;
}

void meow(const string&& s) {
    cout << "meow(const string&&): " << s << endl;
}

string strange() {
    return "modifialbe_ravalue";
}

const string charm() {
    return "cosnt_rvalue";
}

int main() {

    string up("modifiable_lvalue");
    const string down("const_lvalue");

    meow(up);
    meow(down);
    meow(strange());
    meow(charm());
}