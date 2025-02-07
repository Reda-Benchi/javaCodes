import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

class HundeOgPersonListe {
    
    // Lister for å lagre hunde- og personnavn
    ArrayList<String> hundeListe = new ArrayList<>();
    ArrayList<String> personListe = new ArrayList<>();

    // Metode for å lese inn filen
    public void LeseFil () {
        try {
            // Scanner for å ta inn filnavn fra brukeren
            Scanner tastatur = new Scanner(System.in);
            System.out.println("Skriv inn navn på dokumentet du skal lese: ");
            String tilFil = tastatur.nextLine();
            
            // Åpner filen som ble spesifisert
            Scanner lesFil = new Scanner(new File(tilFil));
            SorterFil(lesFil);  // Sender filen videre til SorterFil-metoden
        }
        catch (Exception e) {
            // Håndterer eventuelle feil ved fillesing
            System.out.println("Feilmelding med lesing av dokument");
            System.exit(1);
        }
    }

    // Metode for å sortere innholdet i filen
    public void SorterFil (Scanner lesFil) {
        String p = "P"; // Brukes for å sjekke om linjen starter med "P" (person) eller noe annet (hund)
        
        // Leser hver linje i filen
        while (lesFil.hasNextLine()) {
            String linje = lesFil.nextLine();
            if (linje.trim().split(" ").length > 0) {
                // Deler opp linjen i ord
                String[] setning = linje.trim().split(" ");
                if (setning[0].equals(p)) { // Hvis linjen starter med "P" legges den til i personListe
                    if (setning.length > 2) {
                        personListe.add(setning[1] + " " + setning[2]);
                    } else {
                        personListe.add(setning[1]);
                    }
                } else { // Hvis linjen starter med noe annet, legges den til i hundeListe
                    if (setning.length > 2) {
                        hundeListe.add(setning[1] + " " + setning[2]);
                    } else {
                        hundeListe.add(setning[1]);
                    }
                }
            }
        }
    }

    // Metode for å skrive ut de to listene
    public void SkrivUtLister () {
        System.out.println("Hundeliste: " + hundeListe);
        System.out.println("Personliste: " + personListe);
    }
}
