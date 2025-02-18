class Hovedprogram {
    public static void main (String [] args) {
        // Create a new parking house with 4 rows and 4 columns
        Parkeringshus parkering = new Parkeringshus(4, 4);
        
        // Create a new car (ferrari) with driver name and passengers
        Bil ferrari = new Bil("Max", 459658, "Johnny", "Dave", "Mitch");
        
        // Create a new motorcycle (mot1) with driver name
        Motorsykkel mot1 = new Motorsykkel("Edvard", 10000);

        // Create another motorcycle (mot2) with driver name
        Motorsykkel mot2 = new Motorsykkel("Ben", 30000);
        
        // Park the car in position (2,2)
        parkering.settInn(2, 2, ferrari);
        
        // Park the first motorcycle in position (1,1)
        parkering.settInn(1, 1, mot1);

        // Remove the first motorcycle from the parking lot
        parkering.fjernKjt(mot1);
        
        // Park the second motorcycle in position (1,1)
        parkering.settInn(1, 1, mot2);

        int teller = 0;
        
        // Loop through the parking house to print all parked vehicles
        for (Kjt[] rad : parkering.parkeringshus) {
            for (Kjt kolonne : rad) {
                // Only count and print non-null parked vehicles
                if (!(kolonne == null)) {
                    teller++;
                    System.out.println("Vehicle no: " + teller + " " + kolonne);
                }
            }
        }

        // Simulate errors 
        // Create a new car (lada) with a driver and passengers
        Bil lada = new Bil("Jenny", 69696969, "Ingrid");
        
        // Try to park the car in position (1,1), where the spot is already taken (error message)
        parkering.settInn(1, 1, lada);
        
        // Try to park the car in an invalid position (-1, -1), which will trigger an index error exception
        parkering.settInn(-1, -1, lada);
    }
}

