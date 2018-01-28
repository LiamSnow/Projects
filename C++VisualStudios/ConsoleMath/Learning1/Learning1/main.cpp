#include "main.h"
#include <iostream>
#include <string>
using namespace std;

string Caps(string In) {
	for (int i = 0; i < In.length(); i++) {
		string a = "";
		a += (toupper(In.at(i)));
		In.erase(i, 1);
		In.insert(i, a);
	}
	return In;
}

bool ContainsWord(string Look, string Word) {
	Word = Caps(Word);
	Look = Caps(Look);
	bool hasSeen = false;
	if (Look.length() >= Word.length()) {
	for (int i = 0; i < (Look.length() - Word.length()+1); i++) {
		if (Word._Equal(Look.substr(i, Word.length()))) {
			hasSeen = true;
		}
	}
	}
	return hasSeen;
}

bool FWI(string Look, string Word) {
	Word = Caps(Word);
	Look = Caps(Look);
	bool res = false;
	if (Look.length() >= Word.length()) {
		if (Word._Equal(Look.substr(0, Word.length()))) {
			res = true;
		}
	}
	return res;
}

int main() {
	bool Running = true;
	while (Running) {
		cout << " :-: " << endl;
		string In;
		getline(cin, In);

		//Remove Begging Spaces
		bool hasSeen = false;
		for (int i = 0; i < In.length(); i++) {
			string cC = In.substr(i, 1);
			if (cC == " " && !hasSeen) {
				In.erase(i, 1);  i--;
			}
			else { hasSeen = true; }
		}


		//Classifier:
			//Is Command
			if (In.substr(0, 1) == "/") {
				In.erase(0, 1);
				if (FWI(Caps(In), "RUN")) {
					In.erase(0, 3);
					//Unspacer
					for (int i = 0; i < In.length(); i++) {
						string cC = In.substr(i, 1);
						if (cC == " ") {
							In.erase(i, 1);  i--;
						}
					}
					if (FWI(Caps(In), "GAME")) {
						In.erase(0, 4);
						cout << "..." << endl;
							
					}
				}
			}
			string Ncha[12] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-" };
			string Echa[6] = { "+", "-", "/", "*", "^", "%" };
			//Is Math
			bool IsMath = false;
				for (int i = 0; i < 12; i++) {
					if (FWI(In, Ncha[i])) {
						IsMath = true;
					}
				}
			if (IsMath) {
				//Unspacer
				for (int i = 0; i < In.length(); i++) {
					string cC = In.substr(i, 1);
					if (cC == " ") {
						In.erase(i, 1);  i--;
					}
				}
				//Character Remover
				for (int i = 0; i < In.length(); i++) {
					string cC = In.substr(i, 1);
					bool IsChar = false;
					for (int i = 0; i < 12; i++) {
						if (cC == Ncha[i]) {
							IsChar = true;
						}
					}
					for (int i = 0; i < 6; i++) {
						if (cC == Echa[i]) {
							IsChar = true;
						}
					}
					if (!IsChar) {
						In.erase(i, 1);  i--;
					}
				}
				//Extra Symbol Remover
				int SymCount = 0;
				for (int i = 0; i < In.length(); i++) {
					string cC = In.substr(i, 1);
					bool isEq = false;
					for (int i = 0; i < 6; i++) {
						if (cC == Echa[i]) {
							SymCount++;
							isEq = true;
						}
					}
					if (SymCount > 1 && !(cC == "-" && SymCount == 2) && isEq) {
						In.erase(i, 1);  i--;
					}
					else if (!isEq) { SymCount = 0; }
				}
				//Parser
				double Numbers[1000]; string NumStr;
				string Eqations[1000];
				int ArrPos = 0;
				int Mark = 0;
				for (int i = 0; i < In.length(); i++) {
					string cC = In.substr(i, 1);
					for (int ia = 0; ia < 6; ia++) {
						if (cC == Echa[ia]) {
							NumStr = In.substr(Mark, i - Mark);
							Numbers[ArrPos] = atof(NumStr.c_str());
							Eqations[ArrPos] = In.substr(i, 1);
							Mark = i + 1;
							ArrPos++;
						}
					}
				}
				NumStr = In.substr(Mark, In.length() - Mark);
				Numbers[ArrPos] = atof(NumStr.c_str());

				//Summer
				double Sum = Numbers[0];
				for (int i = 0; i < ArrPos; i++) {
					string CE = Eqations[i];
					if (CE == "+") { Sum = Sum + Numbers[i + 1]; }
					if (CE == "-") { Sum = Sum - Numbers[i + 1]; }
					if (CE == "*") { Sum = Sum * Numbers[i + 1]; }
					if (CE == "/") { Sum = Sum / Numbers[i + 1]; }
					if (CE == "^") { Sum = pow(Sum, Numbers[i + 1]); }
					if (CE == "%") { Sum = fmod(Sum, Numbers[i + 1]); }
				}

				//Print Out Final Answer
				cout << " = ";
				cout << Sum << endl;
			}
	}
	return 0;
}
