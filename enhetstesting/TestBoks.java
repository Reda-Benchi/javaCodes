class TestBoks {
	private static void sjekk (String hva, Boolean test) {
		if (! test) {
		System.out.println("Sjekken av "+hva+" var feil");
		System.exit(1);
		}
	}
	// tester resultat hvis argumenter er 0
	private static void regnArealNull() {
		Boks amazon = new Boks (0, 0, 0);
		sjekk("Test 1, arealberegning ved 0 som arg", amazon.beregnAreal() == 0);
		System.out.println("Test 1, arealberegning ved 0 som arg var riktig");
	}
	// tester resultat ved random areal tall argumenter
	private static void regnArealRandom() {
		int inp = (int)Math.random()*3+1;
		int inp1 = (int)Math.random()*7+1;
		int inp2 = (int)Math.random()*5+1;
		Boks amazon = new Boks (inp, inp1, inp2);
		sjekk("Test 2, arealberegning ved 0 som arg", amazon.beregnAreal() == (inp *inp1 * 2) + (inp1 * inp2 * 2) + (inp2 * inp * 2));
		System.out.println("Test 2, arealberegning ved random som arg var riktig");
	}

		// tester resultat ved random areal tall argumenter
	private static void regnVolumRandom() {
		int inp = (int)Math.random()*3+1;
		int inp1 = (int)Math.random()*7+1;
		int inp2 = (int)Math.random()*5+1;
		Boks amazon = new Boks (inp, inp1, inp2);
		sjekk("Test 3, arealberegning ved 0 som arg", amazon.beregnVolum() == inp*inp1*inp2);
		System.out.println("Test 3, arealberegning ved random som arg var riktig");
	}
	
	public static void main (String [] args) {
		regnArealNull(); 	// tester om arealet er 0
		regnArealRandom();	// tester areal med random tall over 0
		regnVolumRandom();	// Tester volum med random tall over 0
	}
}
