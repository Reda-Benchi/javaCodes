class Hund {
    private String navn;  // instansvariabel for å lagre hundens navn
    
    // Konstruktør som tar et navn som parameter og initialiserer instansvariabelen
    public Hund(String navn) {
        this.navn = navn;  // Setter instansvariabelen 'navn' lik parameteren 'navn'
    }

    // Metode som returnerer hundens navn
    public String HentNavn() {
        return navn;  // Returnerer verdien som er lagret i instansvariabelen 'navn'
    }
}
