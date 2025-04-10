import java.util.ArrayList;

class Flettetråd implements Runnable {
    Monitor monitor;

    public Flettetråd (Monitor monitor) {
        this.monitor = monitor;}
    // deklarer instansvariabler

    @Override 
    public void run () {
        while (monitor.predikertFletting != monitor.fletteTrådTeller) {
            ArrayList <Frekvenstabell> toerListe = new ArrayList <>();

            try {toerListe = monitor.taUtTo();}
            catch (InterruptedException e) 
            {System.out.println("Feilmelding: flettetråd klassen bruk av monitor.taUt()\n" + e.getMessage()); System.exit(1);}

            if (monitor.predikertFletting == monitor.fletteTrådTeller && toerListe == null) return;
            else { Frekvenstabell f1 = toerListe.remove(0); Frekvenstabell f2 =toerListe.remove(0);
                Frekvenstabell flettet = Frekvenstabell.flett(f1, f2);
                try{monitor.settInnFlettet(flettet);}
                catch (InterruptedException e) 
                {System.out.println("Feilmelding: flettetråd klasse bruk av settInnFlettet(flettet)\n" + e.getMessage()); System.exit(1);}}}
    }
}
    