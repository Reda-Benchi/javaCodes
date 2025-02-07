class Verden {
	Rutenett rutenett;
	int genNr;
	
	// skaper instansen verden med tilhørende rutenett som fylles opp med tilfeldige celler
	// og alle celler kobles sammen. Gennr starter på 0 og bygger seg opp for hver verden
	public Verden (int rader, int kolonner) {
		rutenett = new Rutenett (rader, kolonner);
		genNr = 0;
		rutenett.fyllMedTilfeldigeCeller();
		rutenett.kobleAlleCeller();
	}

	// tegner rutenettet og skriver ut genNr + antall levende celler
	public void tegn () {
		rutenett.tegnRutenett();
		System.out.println("Generasjonsnummer : " +genNr);
		System.out.println("Antall levende celler : " +rutenett.antallLevende());

	}
	
	public void nyGen () {
		genNr += 1;
	}

	// Oppdaterer hele systemet. Starter på verdensnivå, går ned til rutenett-nivå,
	// og deretter oppdateres hver enkelt celle på celle-nivå.
	// fornyer gen nivå også tegnes rutenettet
	public void oppdatering () {
		rutenett.tellLevendeNaboerRutenett();
		rutenett.oppdaterRutenett();
		nyGen();
		tegn();
	}
}
