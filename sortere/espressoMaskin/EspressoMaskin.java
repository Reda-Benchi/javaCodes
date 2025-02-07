import java.util.Scanner;

class EspressoMaskin {
    private int vanntank; // Instansvariabel for å lagre vannmengden i vanntanken

    // Metode for å lage espresso dersom det er nok vann
    public void lagEspresso() {
        if (vanntank >= 40) { // Sjekker om det er nok vann til å lage espresso
            vanntank -= 40; // Reduserer vannmengden med 40 ml
            System.out.println("......Lager Espresso......");
            System.out.println("******Espresson din er klar******");
        } else {
            // Hvis det ikke er nok vann, skrives en feilmelding
            System.out.println("Du må fylle på vann, vanntanken din har " + vanntank + " ml");
        }
    }

    // Metode for å lage lungo dersom det er nok vann
    public void lagLungo() { 
        if (vanntank >= 110) { // Sjekker om det er nok vann til å lage lungo
            vanntank -= 110; // Reduserer vannmengden med 110 ml
            System.out.println("......Lager Lungo......");
            System.out.println("******Lungoen din er klar******");
        } else {
            // Hvis det ikke er nok vann, skrives en feilmelding
            System.out.println("Du må fylle på vann, vanntanken din har " + vanntank + " ml");
        }
    }

    // Metode for å fylle på vann i vanntanken, med en grense på 1000 ml
    public void fyllVann(int ml) {
        vanntank += ml; // Legger til den angitte mengden vann
        if (vanntank > 1000) { // Hvis vannmengden overstiger 1000 ml, settes den til 1000 ml
            vanntank = 1000;
        }
        System.out.println("Du har fylt på " + vanntank + " ml vann i vanntanken din");
    }

    // Metode for å hente den nåværende vannmengden i vanntanken
    public int hentVannmengde() {
        return vanntank; // Returnerer vannmengden i vanntanken
    }
}

class TestEspressoMaskin {
    public static void main(String[] args) {
        Scanner tastatur = new Scanner(System.in);
        
        // Spør brukeren om hvilken type kaffe de ønsker
        System.out.println("Kaffe du kan velge imellom Lungo eller Espresso ");
        System.out.println("Skriv inn kaffen du ønsker: ");
        String kaffe = tastatur.nextLine();

        // Oppretter en ny EspressoMaskin-objekt
        EspressoMaskin maskin = new EspressoMaskin();
        maskin.hentVannmengde(); // Henter vannmengde (men gjør ingenting med det her)
        
        // Prøver å fylle på 2000 ml vann, men begrenses til 1000 ml
        maskin.fyllVann(2000);
        
        // Basert på brukerens valg, lager enten Espresso eller Lungo
        if (kaffe.equals("Lungo")) {
            maskin.lagLungo(); // Lager Lungo hvis brukerens valg er Lungo
            System.out.println(maskin.hentVannmengde()); // Skriver ut gjenværende vannmengde
        } else {
            maskin.lagEspresso(); // Lager Espresso hvis brukerens valg er Espresso
            System.out.println(maskin.hentVannmengde()); // Skriver ut gjenværende vannmengde
        }
    }
}
