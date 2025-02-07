class Rutenett {
	int antRader;
	int antKolonner;
	Celle [][] rutene;

	// deklarer dimensjon til instansvariablen rutenett  
	public Rutenett (int rader, int kolonner) {
		antRader = rader;
		antKolonner = kolonner;
		rutene = new Celle [rader][kolonner];
	}

	// oppretter insatans av klassen Celle
	// legger en instans i hver rute i det todimensjonale arrayet
	// gjør cellen levende når den legges inn
	public void lagCelle (int rad, int kolonne){
		Celle nyCeller = new Celle();
		if (Math.random() <= 0.3333) {
			nyCeller.settLevende();
			rutene[rad][kolonne] = nyCeller;
		}
		else{
			rutene[rad][kolonne] = nyCeller;
		}
	}

	// fyller hele rutenettet med celler ved bruk av metoden lagCelle
	public void fyllMedTilfeldigeCeller () {
		for (int rad = 0; rad < antRader; rad++){
			for (int kol = 0; kol < antKolonner; kol++){
				lagCelle(rad,kol);
			}
		}
	}

	// henter celle fra rutenettet
	public Celle hentCelle (int rad, int kol) {
		if (0 > rad || rad > antRader-1 || 0 > kol || kol > antKolonner-1) {
			return null;
		}
		return rutene[rad][kol];
	}

	// skriver ut hvert element i rutenettet til terminal
	public void tegnRutenett () {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	 	System.out.println("");
		System.out.println("");
		System.out.println("");	
		System.out.println("");
	 	System.out.println("");
		System.out.println("\n\n\n\n\n\n\n\n");

		for (int rad = 0; rad < antRader; rad++) {
			System.out.println(" -- ".repeat(antKolonner));
			for (int kol = 0; kol < antKolonner; kol++) {
				if (kol == antKolonner-1) {
				System.out.print("| "+rutene[rad][kol].hentStatusTegn()+" |");
				}
				else {
				System.out.print("| "+rutene[rad][kol].hentStatusTegn()+" ");
				}
			}
		System.out.println("");
		}
	}

	// Bestemmer hvilke celler som er døde og levende for hver generasjon
	// ved å gi naboer til cellen så avgjøres om cellen dør eller lever til neste gen.
	public void settNaboer (int rx, int kx) {

		if (hentCelle(rx-1,kx-1) != null){
			hentCelle(rx,kx).leggTilNabo(hentCelle(rx-1,kx-1)); 	// venstre over
		}
		if (hentCelle(rx-1,kx) != null){
			hentCelle(rx,kx).leggTilNabo(hentCelle(rx-1,kx));	// over
		}
		if (hentCelle(rx-1,kx+1) != null){   				// høyre over
			hentCelle(rx,kx).leggTilNabo(hentCelle(rx-1,kx+1));	
		}	
		if (hentCelle(rx,kx-1) != null){   				// venstre
			hentCelle(rx,kx).leggTilNabo(hentCelle(rx,kx-1));	
		}
		if (hentCelle(rx,kx+1) != null){   				// høyre
			hentCelle(rx,kx).leggTilNabo(hentCelle(rx,kx+1));	
		}		
		if (hentCelle(rx+1,kx-1) != null){   				// venstre under
			hentCelle(rx,kx).leggTilNabo(hentCelle(rx+1,kx-1));	
		}	
		if (hentCelle(rx+1,kx) != null){   				// under
			hentCelle(rx,kx).leggTilNabo(hentCelle(rx+1,kx));	
		}	
		if (hentCelle(rx+1,kx+1) != null){   				// under høyre
			hentCelle(rx,kx).leggTilNabo(hentCelle(rx+1,kx+1));	
		}
	}

	// kobler sammen alle cellene ved hjelp at metoden settNaboer
	public void kobleAlleCeller () {
		for (int rad = 0; rad < antRader; rad++) {
			for (int kol = 0; kol < antKolonner; kol++) {
				settNaboer(rad, kol);
			}
		}
	}

	// returnerer antall levende i rutenettet
	public int antallLevende () {
		int teller = 0;
		for (int rad = 0; rad < antRader; rad++) {
			for (int kol = 0; kol < antKolonner; kol++) {
				if (hentCelle(rad,kol).erLevende()) {
					teller += 1;
				}
			}
		}
		return teller;
	}
	// teller levende naboer til hver celle i rutenettet
	public void tellLevendeNaboerRutenett () {
		for (int rad = 0; rad < antRader; rad++) {
			for (int kol = 0; kol < antKolonner; kol++) {
				hentCelle(rad,kol).tellLevendeNaboer();
			}
		}
	}
	// oppdaterer hver celle i rutenettet, gir status død eller levende
	// basert på regler gitt i klassen Celle
	public void oppdaterRutenett () {
		for (int rad = 0; rad < antRader; rad++) {
			for (int kol = 0; kol < antKolonner; kol++) {
				hentCelle(rad,kol).oppdaterStatus();
			}
		}
	}

}
