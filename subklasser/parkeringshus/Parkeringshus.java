class Parkeringshus {
    // 2D array representing the parking spaces in the parking house
    Kjt [][] parkeringshus;

    // Constructor initializing the parking house with given rows (x) and columns (y)
    public Parkeringshus (int x, int y) {
        parkeringshus = new Kjt [x][y];
    }

    // Method to park a vehicle at the specified coordinates (x, y)
    public void settInn (int x, int y, Kjt kjøretøy) {
        try {
            // Check if the given coordinates are out of bounds
            if (x < 0 || x >= parkeringshus[x].length || y < 0 || y >= parkeringshus.length) {
                throw new ArrayIndexOutOfBoundsException ("Plassen finnes ikke");
            }
            // Check if the parking space is already occupied
            if (parkeringshus[x][y] != null) {
                System.out.println("Det står en bil parkert der");
            } else {
                // Park the vehicle in the specified space
                parkeringshus[x][y] = kjøretøy;
                System.out.println("Bilen ble parkert i rad: " + x + " kolonne: " + y);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // Handle invalid parking space coordinates
            System.out.println("Feil verdi for plarkeringsplass, parkering finnes ikke");
            System.exit(2);
        } catch (Exception e) {
            // Catch any other unexpected errors
            System.out.println("Uventet feil");
            System.exit(2);
        }
    }
    
    // Method to remove a vehicle from the parking house
    public Kjt fjernKjt(Kjt kjøretøy){
        // Iterate over the entire parking house
        for (int i = 0; i < parkeringshus.length; i++){
            for(int j = 0; j< parkeringshus[i].length; j++) {
                // Check if the vehicle is found in the parking space
                if(parkeringshus[i][j] != null && parkeringshus[i][j].equals(kjøretøy)) {
                    // Remove the vehicle from the parking space
                    Kjt fjernetkjt = parkeringshus[i][j];
		    parkeringshus[i][j] = null;
                    return fjernetkjt;
                }
            }
        }
        // Return null if the vehicle is not found
        return null;
    }
}

