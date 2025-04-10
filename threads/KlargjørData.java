
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

class KlargjørData {

static final int ANTALL_TRÅDER = 8;

public static void main (String [] args){
    /*
    System.out.println("--------      testFrekvenstabell      ---------- ");
    testFrekvenstabell ();
    System.out.println("\n\n--------      testSubsekvensregister      ---------- ");
    testSubsekvensregister ();
    System.out.println("\n\n--------      testMonitor      ---------- ");
    testMonitor ();
    System.out.println("\n\n--------      testBeggeTråder      ---------- ");
    testBeggeTråder(); */
    // Tester alle static testet som er knyttet til hver klasse og dens metode
    
    if (args.length != 1) {
			System.out.println("\nSkriv filnavnet som argument når du åpner KlargjoerData filen.\nEks:java KlargjørData filnavn.csv");
			System.exit(1);}
	// programmet går videre hvis og bare hvis 1 argument er gitt når filen åpnes

    Scanner fil = null;
    File f = null;
    String mappe = "";
    try {
        f = new File(args[0]);
        mappe = f.getParent() + "/";
        fil = new Scanner(f);
    }
    catch (Exception e) {
        System.out.println("Feil ved innlesing av metaData fil: " + e.getMessage());
        System.exit(1);}

    ArrayList <String> smitteListeFilnavn = new ArrayList <>();
    ArrayList <String> friskListeFilnavn = new ArrayList <>();

    while (fil.hasNextLine()) {
        String linje = fil.nextLine().trim();
        String [] deler = linje.split(",");
        if (deler[1].toUpperCase().equals("TRUE")) smitteListeFilnavn.add(mappe + deler[0]);
        else {friskListeFilnavn.add(mappe + deler[0]);}}
    System.out.println("\nVi har nå lest gjennom metadata filen og sortert ¨filnavnene¨ fra metadata filen inn til smitteListeFilnavn og friskListeFilnavn");
    // Lest gjennom metadata filen og sortert "filnavnene" fra metadata filen inn til smitteListeFilnavn og friskListeFilnavn
    // Vi har nå to fulstendige lister med navn på alle filnavnene som skal brukes

    Subsekvensregister smitteRegister = new Subsekvensregister ();
    int smitteAntall = smitteListeFilnavn.size() -1;
    Monitor smitteMonitor = new Monitor (smitteRegister); smitteMonitor.predikertFletting(smitteAntall); 
    
    Subsekvensregister friskRegister = new Subsekvensregister ();
    int friskAntall = friskListeFilnavn.size() -1;
    Monitor friskMonitor = new Monitor (friskRegister); friskMonitor.predikertFletting(friskAntall);
    
    System.out.println("\nStørrelse på smitteliste: " + smitteAntall + "\nStørrelse på friskliste: " + friskAntall);
    for (String e : smitteListeFilnavn) System.out.println("\nSmittelisteFilnavn: " + e);
    for (String e : friskListeFilnavn) System.out.println("\nFriskListeFilnavn: " + e);
    // test av riktig innhold i listene
    
     Thread [] smitteLeseTråd = new Thread [ANTALL_TRÅDER];
    while ( ! (smitteListeFilnavn.isEmpty())){
        for (int i = 0; i < smitteLeseTråd.length; i++)
            if (smitteListeFilnavn.isEmpty())  smitteLeseTråd [i]= null;
            else {smitteLeseTråd [i] = new Thread (new Lesetråd (smitteListeFilnavn.remove(0), smitteMonitor));}
        for (Thread traad : smitteLeseTråd) 
            if (traad != null) traad.start();
        for (Thread traad : smitteLeseTråd) {
        try {if (traad != null) traad.join();}
        catch (InterruptedException e) {System.out.println("SmitteLeseTråd er avbrutt ved join(): " + e.getMessage());}}}

    Thread [] friskLeseTråd = new Thread [ANTALL_TRÅDER];
    while ( ! (friskListeFilnavn.isEmpty())){
        for (int j = 0; j < friskLeseTråd.length; j++)
            if (friskListeFilnavn.isEmpty()) friskLeseTråd[j] = null;
            else {friskLeseTråd [j]= new Thread (new Lesetråd (friskListeFilnavn.remove(0), friskMonitor));}
        for (Thread traad : friskLeseTråd) 
            if (traad != null) traad.start();
        for (Thread traad : friskLeseTråd) {
        try {if (traad != null) traad.join();}
        catch (InterruptedException e) {System.out.println("FriskLeseTråd er avbrutt ved join(): " + e.getMessage());}}}
     System.out.println("\nAlle lesetråder er ferdig");
     System.out.println("\nfriskAntall: "+ friskAntall);
     System.out.println("\nsmitteAntall: "+ smitteAntall);
    // startet alle lesetråder og programmet går videre når alle dataene er lest inn, først da starter fletting

    Thread [] smitteFletteTråd = new Thread [ANTALL_TRÅDER];
    for (int i = 0; i < smitteFletteTråd.length; i++) smitteFletteTråd [i] = new Thread (new Flettetråd (smitteMonitor));
    for (Thread tråd : smitteFletteTråd) if (tråd != null) tråd.start();
  
    Thread [] friskFletteTråd = new Thread [ANTALL_TRÅDER];
    for (int j = 0; j < friskFletteTråd.length; j++) friskFletteTråd[j] = new Thread (new Flettetråd (friskMonitor));
    for (Thread tråd : friskFletteTråd) if (tråd != null) tråd.start();
    // alle flette trådene er startet 
    
    for (Thread traad : smitteFletteTråd) {
        try {if (traad != null) traad.join();}
        catch(InterruptedException e) {System.out.println("SmitteFletteTråd er avbrutt ved join(): " + e.getMessage());}}
    for (Thread traad : friskFletteTråd){
        try {if (traad != null) traad.join();}
        catch(InterruptedException e) {System.out.println("FriskFletteTråd er avbrutt ved join(): " + e.getMessage());}}
    System.out.println("\nAlle FletteTråder er ferdig");
    System.out.println("SmitteMonitor antall: " + smitteMonitor.antall());
    System.out.println("FriskMonitor antall: " + friskMonitor.antall());
    if (smitteMonitor.antall() != 1 || friskMonitor.antall() != 1) {
    System.out.println("Feil: Fletting resulterte ikke i én tabell per monitor!");
    System.out.println("SmittetabellAntall: " + smitteMonitor.antall() + "\nFrisktabellAntall: " + friskMonitor.antall() );}
    // programmet går videre når alle fletting i hvert register er flettet til en frekvenstabell 

    Frekvenstabell friskFrekvenstabell = null;
    try {friskFrekvenstabell = friskMonitor.taUt();}
    catch (InterruptedException e) {System.out.println("FriskFrekvenstabell, siste versjon ble avbrutt: " + e.getMessage());}
    friskFrekvenstabell.skrivTilFil(mappe + "ikke_smittet");
    // skrevet over frekvenstabell til ikke_smittet filen

    Frekvenstabell smittetFrekvenstabell = null;
    try {smittetFrekvenstabell = smitteMonitor.taUt();}
    catch (InterruptedException e) {System.out.println("SmittetFrekvenstabell, siste versjon ble avbrutt: " + e.getMessage());}
    smittetFrekvenstabell.skrivTilFil(mappe + "smittet");

    System.out.println("\n¨smittet¨ og ¨ikke_smittet¨ filene ble velykket etablert");
    System.out.println("Informasjonen fra " + f.getName() + " er nå sortert i smittet.ny dokument og ikke_smittet.ny dokument\n");
    // skrevet over frekvenstabell til ikke_smittet filen*/

}

public static void sjekk (String hva, boolean test) {
    if (! test) {
        System.out.println("Sjekken '" + hva + "' feilet!");
	    System.exit(1);
    }
    else {System.out.println("Sjekken '" + hva + "' var velykket!");}}
    // hjelpemetode som brukes av de andre testmetodene 

public static void testFrekvenstabell () {
    Frekvenstabell f1 = new Frekvenstabell ();
    Frekvenstabell f2 = new Frekvenstabell ();
    Frekvenstabell flettet = new Frekvenstabell ();
    f1.put("ABC", 1); f1.put("BCD", 1); f1.put("CDE", 1);
    f2.put("ABC", 2); f2.put("BCD", 1);
    flettet = flettet.flett(f1, f2);
    for (String key : flettet.keySet())
        System.out.println(key + ": " + flettet.get(key));
    sjekk("Fletting av to f1 og f2", flettet.get("ABC").equals(3)&& flettet.get("BCD").equals(2) && flettet.get("CDE").equals(1));
    // testet fletting av to Frekvenstabeller

    flettet.skrivTilFil("TestAvFlettet.txt");
    // testet utskrift av toString() - verifiser fil i mappen og dens innhold
}

public static void testSubsekvensregister () {
    Subsekvensregister r1 = new Subsekvensregister ();
    Frekvenstabell f2 = Subsekvensregister.les("/Users/reda/uio/v25/in1010/innlevering5-Oblig2/TestData/fil2.csv");
                                // argument stringen byttes ut med egen filbane
    String f1Svar = "CAS, ASS, SSP, PTP, PTG, SSQ, SQS, QSS, SPL, PLH, LHF, HFY, FYH, YHG, HGY, GYT, TFC, CAT, ATS, TSR, SST, STY, TYG, YTF, FYH, TYH, totalt 26 ord";
    f2.skrivTilFil("TestAvSubsekvens.txt");
                                // verifiser svarene "f1svar" med filen "TestAvSubsekvens.txt" som produseres 
    // gjennomfør lesing fra fil og funnet alle unike SUBSEKVENSER og deretter lagret dem i et frekvenstabell objekt i registeret 
    
    sjekk("tom register gir null", r1.taUt() == null);
    // metoden taUt() når register er tom gir null

    r1.settInn(f2);
    sjekk("sett inn elementene i ny register inneholder et element", r1.antall() == 1);
    // sett inn elementene i ny register inneholder riktig størrelse 

    Frekvenstabell mid = r1.taUt();
    sjekk("metode gir riktig størrelse og fjerner element fra register", r1.antall() == 0 );
    // taUt() element gir riktig størrelse og fjerner element når det kun er 1 element igjen 
}

public static void testMonitor () {
    Subsekvensregister register = new Subsekvensregister ();
    Monitor monitor = new Monitor (register);
    Frekvenstabell f2 = monitor.les("/Users/reda/uio/v25/in1010/innlevering5-Oblig2/TestData/fil2.csv");
    sjekk("monitor objekt har et register som inneholder ingen frekvenstabell", monitor.antall() == 0);
    try {monitor.settInn(f2);}catch (InterruptedException e) 
    {System.out.println("Feilmelding: monitor.settInn()\n" + e.getMessage()); System.exit(1);}
    sjekk("monitor objekt har et register som inneholder en frekvenstabell", monitor.antall() == 1);
    Frekvenstabell mid = null;
    try {mid = monitor.taUt();}catch (InterruptedException e) 
    {System.out.println("Feilmelding: monitor.taUt()\n" + e.getMessage()); System.exit(1);}
    sjekk("monitor objekt har et register som inneholder ingen frekvenstabell", monitor.antall() == 0);
    try {monitor.settInn(f2);}catch (InterruptedException e) 
    {System.out.println("Feilmelding: monitor.settInn()\n" + e.getMessage()); System.exit(1);}
    try {monitor.settInn(mid);}catch (InterruptedException e) 
    {System.out.println("Feilmelding: monitor.settInn()\n" + e.getMessage()); System.exit(1);}
    sjekk("monitor objekt har et register som inneholder to frekvenstabell", monitor.antall() == 2);
    ArrayList <Frekvenstabell> frek = null;
    try {frek = monitor.taUtTo();}catch (InterruptedException e) 
    {System.out.println("Feilmelding: monitor.taUt()\n" + e.getMessage()); System.exit(1);}
    sjekk("monitor objekt har et register som inneholder ingen frekvenstabell", monitor.antall() == 0);
    sjekk("arraylisten fra taUtTo() inneholder 2 elementer", frek.size() == 2);
    // tester alle metodene som kaller på Subsekvensregister-metoder
}
public static void testBeggeTråder () {
    Subsekvensregister register = new Subsekvensregister ();
    Monitor monitor = new Monitor (register);
    Lesetråd l1 = new Lesetråd ("/Users/reda/uio/v25/in1010/innlevering5-Oblig2/TestData/fil1.csv", monitor);
    Lesetråd l2 = new Lesetråd ("/Users/reda/uio/v25/in1010/innlevering5-Oblig2/TestData/fil2.csv", monitor);
    Lesetråd l3 = new Lesetråd ("/Users/reda/uio/v25/in1010/innlevering5-Oblig2/TestData/fil3.csv", monitor);
    Thread t1 = new Thread (l1);
    Thread t2 = new Thread (l2);
    Thread t3 = new Thread (l3);
    sjekk("register er tom ", monitor.antall() == 0);
    // verifiser at registeret er tomt 

    t1.start(); t2.start(); t3.start();
    try {t1.join();} catch (InterruptedException e)
    {System.out.println("Feilmelding: lesetråd i main ble avbrutt\n" + e.getMessage());}
    try {t2.join();} catch (InterruptedException e)
    {System.out.println("Feilmelding: lesetråd i main ble avbrutt\n" + e.getMessage());}
    try {t3.join();} catch (InterruptedException e)
    {System.out.println("Feilmelding: lesetråd i main ble avbrutt\n" + e.getMessage());}
    sjekk("lesetråder er ferdig, vi går videre til fletting ", monitor.antall() == 3);
    // lesetråder er vellykket og vi har 3 frekventabeller i registeret 

    int predikasjonFletteAntall = monitor.antall() -1;
    monitor.predikertFletting(predikasjonFletteAntall);
    Flettetråd f1 = new  Flettetråd (monitor);
    Flettetråd f2 = new  Flettetråd (monitor);
    Flettetråd f3 = new  Flettetråd (monitor);
    Flettetråd f4 = new  Flettetråd (monitor);
    Thread t4 = new Thread (f1);
    Thread t5 = new Thread (f2);
    Thread t6 = new Thread (f3);
    Thread t7 = new Thread (f4);
    t4.start(); t5.start(); t6.start(); t7.start();
    try {t4.join();} catch (InterruptedException e)
    {System.out.println("Feilmelding: flettetråd i main ble avbrutt\n" + e.getMessage());}
    try {t5.join();} catch (InterruptedException e)
    {System.out.println("Feilmelding: flettetråd i main ble avbrutt\n" + e.getMessage());}
    try {t6.join();} catch (InterruptedException e)
    {System.out.println("Feilmelding: flettetråd i main ble avbrutt\n" + e.getMessage());}
    try {t7.join();} catch (InterruptedException e)
    {System.out.println("Feilmelding: flettetråd i main ble avbrutt\n" + e.getMessage());}
    sjekk("FletteTråd er ferdig, SJEKKEN FOR ALLE KLASSENE ", monitor.antall() == 1);
    // FletteTråd er vellykket og vi har 1 frekvenstabell i registeret 
}
}
