import java.util.ArrayList;
import java.util.concurrent.locks.*;

class Monitor {
    Subsekvensregister register;
    Lock lås;
    Condition færreEnnToTråder;
    Condition tomtForTråder;
    volatile int fletteTrådTeller = 0;
    int predikertFletting;

    public Monitor (Subsekvensregister register){
            this.register = register;
            lås = new ReentrantLock ();
            færreEnnToTråder = lås.newCondition();
            tomtForTråder = lås.newCondition();}
    // etablert instansvariabler

    public void predikertFletting (int predikertFletting) {
        this.predikertFletting = predikertFletting;}
    // etablert antall som skal flettes

    public void settInn (Frekvenstabell f) throws InterruptedException {
        lås.lock();
        try {register.settInn(f);
            tomtForTråder.signalAll();}
        finally{lås.unlock();}}
    // satt inn en frekvenstabell i register

    public void settInnFlettet(Frekvenstabell flettet) throws InterruptedException {
        lås.lock();
        try {fletteTrådTeller ++;
            settInn(flettet);
            færreEnnToTråder.signalAll();}
        finally{lås.unlock();}}
    // satt inn en frekvenstabell i register

    public Frekvenstabell taUt () throws InterruptedException {
        lås.lock();
        try {
            while (antall() == 0) 
                tomtForTråder.await();
            return register.taUt();}
        finally{lås.unlock();}}
    // hentet ut en vilkårlig frekvenstabell fra registeret

    public int antall () {return register.antall();}
    // henter ut antall frekvenstabeller i register

    public Frekvenstabell les (String filnavn) {return register.les(filnavn);}
    // Vi har nå lest filnavnet git ved argument, gått igjennom filen og lagt hver unike SUBSEKVENSLENGDE inn som nøkkel med verdi 1

    public ArrayList <Frekvenstabell> taUtTo () throws InterruptedException {
        lås.lock();
        ArrayList <Frekvenstabell> toerListe = new ArrayList<>();
        Frekvenstabell f1; Frekvenstabell f2;
        try {
            if (predikertFletting == fletteTrådTeller && antall() == 1) 
                return null;

            while(antall() < 2){
                if (predikertFletting == fletteTrådTeller && antall() == 1) 
                    return null;
                færreEnnToTråder.await();}

            f1 = taUt(); f2 = taUt();
            toerListe.add(f1); toerListe.add(f2);
            return toerListe;
        }
        finally{lås.unlock();}}
    // hentet ut en ArrayList med to vilkårlige frekvenstabeller fra registeret
}