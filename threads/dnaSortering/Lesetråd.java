class Lesetråd implements Runnable {
    String filnavn;
    Monitor monitor;

    public Lesetråd (String filnavn, Monitor monitor) {
        this.filnavn = filnavn;
        this.monitor = monitor;}
    // deklarer instansvariabler

    @Override 
    public void run () {
        Frekvenstabell f = monitor.les(filnavn);
        try {monitor.settInn(f);}catch (InterruptedException e) 
        {System.out.println("Feilmelding: lesetråd klasse bruk av monitor.settInn()\n" + e.getMessage()); System.exit(1);}}
    // lest filnavn og plassert resulatet (frekvenstabellen) inn i register

}