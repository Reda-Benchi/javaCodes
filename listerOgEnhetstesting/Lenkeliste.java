import java.util.Iterator;

abstract class Lenkeliste <E> implements Liste<E> {
	protected Node foerste;
	protected Node siste;
	protected int antallNoder = 0;

	protected class Node {
		protected E element;
		protected Node forrige;
		protected Node neste;


		public Node (E element, Node forrige, Node neste) {
			this.element = element;
			this.forrige = forrige;
			this.neste = neste;
		}
	}
		// Konstruktør for Node objekter
	
	@Override
	public Iterator <E> iterator () {
		return new ListeIterator ();
	}
	
	public class ListeIterator implements Iterator <E> {
		Node temp = foerste;
		int teller = 0;
	
		@Override
		public boolean hasNext() {
			return temp != null;
		}

		@Override
		public E next () {
			E mid = temp.element;
			temp = temp.neste;
			teller ++;
			return mid;
		}
	}
	// iterer gjennom Noder og oppdaterer teller;

    	@Override
   	 public String toString () {
		Node temp = foerste;
		String svar = "";
	
		if (temp != null) {
		svar = svar + temp.element;
		}
	
		while (temp.neste != null) {
			svar = svar + ", " + temp.neste.element;
			temp = temp.neste;
		}
		return svar;  
		// Samler alle elmentene til en liste og returnerer dem som en string
    	}

	@Override
	public int størrelse () {
		return antallNoder;  
		// returnerer antall noder som er lenket
	}

	@Override
	public void leggTil (E x) {
		Node nyNode = new Node (x, siste, null);
		if (siste == null) {
			foerste = nyNode;
		}
		// Hvis listen er tom, blir første lik den nye noden		

		else {
			siste.neste = nyNode;		
		}
		//Hvis ikke, sett den nåværende siste sin neste til ny node
	
		siste = nyNode;
		antallNoder++;
		// Oppdaterer siste til den nye noden og øker telleren med en	
	}
	    
	@Override
	public E hent () {
		return foerste.element;  
		// returnerer forste Noden 
	 }

	 @Override
	public E fjern () {
		Node temp = foerste;
		if (foerste == null) {
			throw new UgyldigListeindeks (-1);
			// hvis man prøver å fjerne fra en tom listen kastes exception 
			// for å unngå nullpointexception
		}

		if (foerste != null) {
			foerste = foerste.neste;
		}
		// Hvis første ikke er lik null, fjerner vi første Node 

		if (foerste == null) {
			siste = null;
		
		}
		// Hvis første nå er lik null så er listen tom

		else {
			foerste.forrige = null;
		}
		antallNoder --;
		return temp.element;  
		// Fjerner første Node fra listen og returnerer Nodens element
	 }
}

