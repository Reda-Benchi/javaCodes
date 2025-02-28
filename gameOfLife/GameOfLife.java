class GameOfLife {
	public static void main (String [] args) {
		Verden earth = new Verden(8,12);
		
		earth.tegn();
		earth.oppdatering();
		earth.oppdatering();
		earth.oppdatering();	
	}
}
