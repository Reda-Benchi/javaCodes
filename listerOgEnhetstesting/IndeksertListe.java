class IndeksertListe <E> extends Lenkeliste<E> {

	public void leggTil (int pos, E x) {
		if (pos < 0 || pos > størrelse () ) {
			throw new UgyldigListeindeks(pos);
		}

		else if (siste == null) {
			Node ny = new Node (x, siste, null);
			foerste = ny;
			siste = ny;	
		}
		else if (pos == størrelse()) {
			Node ny = new Node (x,siste,null);
			siste.neste = ny;
			siste = ny;
		}
		else if (pos == 0) {
			Node ny = new Node (x, foerste.forrige, foerste);
			foerste.forrige = ny;
			foerste = ny;
		}
		else {
			Node temp = hentNode(pos);
			Node ny = new Node (x,temp.forrige,temp);
			temp.forrige.neste = ny;
		}
		antallNoder++;

	// Legger element i valgt pos, flytter resterende elmenter mot høyre og oppdaterer antall
	}
	public void sett (int pos, E x) {
		if (pos < 0 || pos >= størrelse()) {
			throw new UgyldigListeindeks(pos);
		}

		else if (siste == null) {
			Node ny = new Node (x, siste, null);
			foerste = ny;
			siste = ny;
			antallNoder++;
		}

		else if (pos == 0) {
			Node ny = new Node (x, foerste.forrige, foerste.neste);
			foerste.neste.forrige = ny;
			foerste = ny;
		}
		else if (pos == størrelse()-1) {
			Node ny = new Node (x,siste.forrige,siste.neste);
			siste.forrige.neste = ny;
			siste = ny;
		}
		else {
			Node temp = (hentNode(pos));
			Node ny = new Node (x, temp.forrige, temp.neste);
			temp.forrige.neste = ny;
			temp.neste.forrige = ny;
		}	

	// Erstater nåværende element med det nye elementet 
	}

	public Node hentNode (int pos) {
		if (pos < 0 || pos >= størrelse() || siste == null) {
			throw new UgyldigListeindeks(pos);
		}
		int teller = 0;
		Node temp = foerste; 
		while (temp.neste != null && teller != pos) {
			temp = temp.neste;
			teller ++;
		}
		return temp;

	// Iterer gjennom listen. Når teller er lik pos: returner tilhørende Node
	}

	public E hent (int pos) {
		if (pos < 0 || pos >= størrelse() || siste == null) {
			throw new UgyldigListeindeks(pos);
		}
		int teller = 0;
		Node temp = foerste; 
		while (temp.neste != null && teller != pos) {
			temp = temp.neste;
			teller ++;
		}
		return temp.element;

	// Iterer gjennom listen. Når teller er lik pos: returner tilhørende Element
	}
	
	public E fjern (int pos) {
		Node fjernes; 

		if (pos < 0 || pos >= størrelse()) {
			throw new UgyldigListeindeks(pos);
		}	

		else if (størrelse() == 1) {
			fjernes = foerste;
			foerste = null;
			siste = null;
		}

		else if (pos == 0) {
			fjernes = foerste;
			foerste.neste = foerste.forrige;
			foerste = foerste.neste;
		
		}
		else if (pos == størrelse()-1) {
			fjernes = siste;
			siste.forrige = siste.neste;
			siste = siste.forrige;
		}
		else {
			fjernes = hentNode(pos);
			fjernes.forrige.neste = fjernes.neste;
			fjernes.neste.forrige = fjernes.forrige;
		}
		antallNoder--;
		return fjernes.element;


	// Fjerner elementet fra pos i listen
	}
}
