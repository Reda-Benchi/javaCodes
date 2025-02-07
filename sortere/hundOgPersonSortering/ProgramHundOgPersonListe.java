class ProgramHundOgPersonListe {
    public static void main (String [] args) {
        // Oppretter et objekt av klassen HundeOgPersonListe
        HundeOgPersonListe liste = new HundeOgPersonListe();
        
        // Leser inn filen ved å kalle LeseFil-metoden
        liste.LeseFil();
        
        // Skriver ut innholdet i listene ved å kalle SkrivUtLister-metoden
        liste.SkrivUtLister();
    }
}
