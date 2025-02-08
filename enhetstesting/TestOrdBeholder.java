class TestOrdBeholder {
	public static void main (String [] args) {
		Ordbeholder beholder = new Ordbeholder();

		//Sjekk at pop på en tom liste returnerer korrekt.
		if (beholder.pop() == null) {
			
			System.out.println("Test pop er riktig");
		}
		else {
			System.out.println("Test pop er feil");

		}

		//Sjekk at settInn returnerer riktig med et nytt ord.
		if (beholder.settInn("Hei")) {
		
			System.out.println("Test settInn er riktig");
		}
		else {
			System.out.println("Test SettIn er feil");

		}

		//Sjekk at settInn returnerer riktig når man setter inn et ord som allerede er
		//i beholderen.
 		if (! beholder.settInn("Hei")) {
		
			System.out.println("Test settInn med samme ord er riktig");
		}
		else {
			System.out.println("Test settInn med samme ord er feil");

		}

		//Sett inn noen flere ord, og sjekk at antallOrd returnerer korrekt.
		for (int i = 2; i<5; i++) {
	 	 // tester 3 forskjellige ord
			if (beholder.settInn("hei"+i)) {
				System.out.println("Test settInn ord nr: " +i+ " er riktig");
			}
			else {
				System.out.println("Test settInn ord nr: " +i+ " er feil");

			}
		}
		//Sjekk at pop returnerer det siste elementet som ble lagt inn i listen..
		String ord = beholder.hentOrdbeholder().get(beholder.antallOrd()-1);
		if (beholder.pop().equals(ord)) {
			System.out.println("Test pop gir ut siste ord er riktig");
		}
		else {
			System.out.println("Test gir ut siste ord feil");

		}
	
	}
}
