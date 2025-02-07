/*Denne klassen beskriver en celle under simulering. 
Cellen får en variabel som enten levende eller død */

class Celle {
	Boolean levende; 		// instansvariabel for status på celle 
	Celle [] naboer;		// array for naboer til cellen
	int antNaboer;
	int antLevendeNaboer;

	public Celle () { 		// deklarasjon av instansvariabler
		levende = false; 	
		naboer = new Celle [8];
		antNaboer = 0;
		antLevendeNaboer = 0;
	}
	
	public void settDoed () {		// instansvariabel levende deklareres til false
		levende = false;		// altså (død)
	}

	public void settLevende () {		// instansvariavel levende deklareres til true 
		levende = true;			// altså (levende)
	}

	public boolean erLevende () {		// returnerer status på cellen i boolean verdi 
		return levende;			// altså (true/false)
	}
	
	public char hentStatusTegn () {		// returnerr status på cellen i form av
		char statusLevende = 'O';	// ett tegn (O = levende, .=doed)
		char statusDoed = '.';
		if (levende == true ) {
			return statusLevende;
		}
		else {
			return statusDoed;
		}
	}

	public void leggTilNabo (Celle nabo) {	// legger til naboceller i arrayet naboer
		if (antNaboer < naboer.length) {
			naboer [antNaboer] = nabo;
			antNaboer ++ ;
		}
	}

	public void tellLevendeNaboer () {	// teller antall levende naboer og oppdater
		antLevendeNaboer = 0;		// instansvariablet antLevendeNaboer
		for (int i= 0; i<antNaboer; i++) {
			if (naboer[i].erLevende()) {
				antLevendeNaboer += 1;
			}
		}
	}

	public void oppdaterStatus () {
		tellLevendeNaboer();		// nullstiller og teller naboceller

						// naboceller antall er lik 2 eller 3 lar
						// cellen fortsette å leve
		if (antLevendeNaboer== 2 || antLevendeNaboer== 3) {
			levende = levende;
		}
		if (antLevendeNaboer > 3) {	// flere enn 3 naboer dreper cellen
			settDoed();
		}
		if (antLevendeNaboer == 3) {	// reproduksjon av cellen hvis
			settLevende(); 		// antall naboer er 3
		}
		else {				// cellen dør hvis ingen av kravene over 
			settDoed();		// oppfylles
		}
	}
}
