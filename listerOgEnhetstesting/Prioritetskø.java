class Prioritetskø <E extends Comparable<E>> extends Lenkeliste<E> {
	@Override
	public void leggTil (E x) {
		if (siste == null) {
			Node ny = new Node (x, siste, null);
			foerste = ny;
			siste = ny;
			antallNoder++;
		}
		else {
			Node temp = foerste;
			while (temp != null) {
				if(temp.element.compareTo(x) > 0 && temp == foerste) {
					Node ny = new Node (x, temp.forrige, temp);
					temp.forrige = ny;
					foerste = ny;
					break;
				}
				// setter element x foran første, hvis x er mindre
				else if (temp.element.compareTo(x) > 0 && størrelse() > 1) {
					Node ny = new Node (x, temp.forrige, temp);
					temp.forrige.neste = ny;
					temp.forrige = ny;
					break;
				}
				// setter element x foran ett element i midten, hvis x er mindre 
				temp = temp.neste; 

				if (temp == null) {
					Node ny = new Node(x, siste, null);
					siste.neste = ny;
					siste = ny;
				}
				// setter element x som siste element, hvis x er større eller lik
			}
			antallNoder++;	
		}
	}
	// Element x er lagt inn i en sortert liste 
}
	
