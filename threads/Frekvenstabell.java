import java.io.PrintWriter;
import java.util.TreeMap;

class Frekvenstabell extends TreeMap<String, Integer> {

@Override
public String toString() {
    String linje = "";
    for (String key : this.keySet()){
        if (linje.isEmpty()) linje += key + " " + this.get(key);
        else {linje += "\n" + key + " " + this.get(key);}
    }
    return linje;
}
// returnerer alle en string med egen linje for hver nøkkel og verdi 

public static Frekvenstabell flett(Frekvenstabell f1,Frekvenstabell f2) {
    Frekvenstabell flettet = new Frekvenstabell();
    Integer nyVerdi;

    for (String key : f1.keySet())
        {flettet.put(key, f1.get(key));}

    for (String key : f2.keySet()){
        if (flettet.containsKey(key)){
            nyVerdi = flettet.get(key) + f2.get(key);
            flettet.put(key, nyVerdi);}
        else {flettet.put(key, f2.get(key));}}
    return flettet;
}
/*flettet sammen to frekvenstabeller, altså slått sammen verdier der nøklene er like
eller legger til ny nøkkel med verdi hvis nøkkel ikke finnes */

public void skrivTilFil(String filnavn) {
    PrintWriter fil = null;

    try {fil = new PrintWriter (filnavn);}
    catch (Exception e) {
        System.out.println("Feil ved skriving av fil for \n" + filnavn + "\n" + e.getMessage());
        System.exit(1);}
    
    fil.print (this);
    fil.close();
}
// Frekvenstabellen er skrevet over i et dokument med argumentet som filnavn 
}