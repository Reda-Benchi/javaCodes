import java.util.ArrayList;

// Abstract class representing a vehicle (Kjt)
abstract class Kjt {
    public String sjåfør; // The driver of the vehicle
    public int regnummer; // The registration number of the vehicle
    
    // Constructor to initialize the vehicle with a driver and registration number
    public Kjt (String sjåfør, int regnummer) {
        this.sjåfør = sjåfør;
        this.regnummer = regnummer;
    }

    // Override the equals method to compare two vehicles
    @Override
    public boolean equals (Object objekt) {
        return this instanceof Kjt && objekt instanceof Kjt;
    }

    // Override the toString method to provide a string representation of the vehicle
    @Override
    public String toString () {
        return "Kjt sjåfør heter " + sjåfør + " og regnummer er " + regnummer;
    }
}

// Class representing a motorcycle, which extends the Kjt class
class Motorsykkel extends Kjt {
    public Motorsykkel (String sjåfør, int regnummer) {
        super(sjåfør, regnummer);
    }
}

// Class representing a car, which extends the Kjt class
class Bil extends Kjt {
    ArrayList <String> personListe; // List of passengers in the car

    // Constructor for a car with 1 passengers
    public Bil (String sjåfør, int regnummer, String pers1) {
        super(sjåfør, regnummer);
        personListe = new ArrayList<>(); // Initialize the passenger list
        personListe.add(pers1); // Add the first passenger
    }

    // Constructor for a car with 2 passengers
    public Bil (String sjåfør, int regnummer, String pers1, String pers2) {
        super(sjåfør, regnummer);
        personListe = new ArrayList<>(); // Initialize the passenger list
        personListe.add(pers1); // Add the first passenger
        personListe.add(pers2); // Add the second passenger
    }

    // Constructor for a car with 3 passengers
    public Bil (String sjåfør, int regnummer, String pers1, String pers2, String pers3) {
        super(sjåfør, regnummer);
        personListe = new ArrayList<>(); // Initialize the passenger list
        personListe.add(pers1); // Add the first passenger
        personListe.add(pers2); // Add the second passenger
        personListe.add(pers3); // Add the third passenger
    }

    // Constructor for a car with 4 passengers
    public Bil (String sjåfør, int regnummer, String pers1, String pers2, String pers3, String pers4) {
        super(sjåfør, regnummer);
        personListe = new ArrayList<>(); // Initialize the passenger list
        personListe.add(pers1); // Add the first passenger
        personListe.add(pers2); // Add the second passenger
        personListe.add(pers3); // Add the third passenger
        personListe.add(pers4); // Add the fourth passenger
    }

    // Constructor for a car with 5 passengers
    public Bil (String sjåfør, int regnummer, String pers1, String pers2, String pers3, String pers4, String pers5) {
        super(sjåfør, regnummer);
        personListe = new ArrayList<>(); // Initialize the passenger list
        personListe.add(pers1); // Add the first passenger
        personListe.add(pers2); // Add the second passenger
        personListe.add(pers3); // Add the third passenger
        personListe.add(pers4); // Add the fourth passenger
        personListe.add(pers5); // Add the fifth passenger
    }

    // Override the toString method to provide a string representation of the car and its passengers
    @Override
    public String toString () {
	String p = "";

	for (String pers : personListe) {
		p += ", "+pers;
	}

        return "Kjt sjåfør heter " + sjåfør + " og regnummer er " + regnummer + " og alle personene i bilen " + p;
    }
}

