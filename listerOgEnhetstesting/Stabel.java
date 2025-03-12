class Stabel <E> extends Lenkeliste<E> {
	@Override
	public void leggTil (E x) {
		Node nyNode = new Node (x, null, foerste);
		if (foerste == null) {
			siste = nyNode;
		}
		// Hvis listen er tom, blir siste lik den nye noden		

		else {
			foerste.forrige = nyNode;		
		}
		// Hvis ikke, settes den nåværende siste sin neste til ny node
	
		foerste = nyNode;
		antallNoder++;
		// Oppdaterer siste til den nye noden og øker telleren med en	
	}
}
