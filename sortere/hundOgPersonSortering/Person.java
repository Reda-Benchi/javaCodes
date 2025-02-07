class Person {
    private String navn;  // instansvariabel for å lagre personens navn
    
    // Konstruktør som tar et navn som parameter og initialiserer instansvariabelen
    public Person(String navn) {
        this.navn = navn;  // Setter instansvariabelen 'navn' lik parameteren 'navn'
    }

    // Metode som returnerer personens navn
    public String HentNavn() {
        return navn;  // Returnerer verdien som er lagret i instansvariabelen 'navn'
    }
}
