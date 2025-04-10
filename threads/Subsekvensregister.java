import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

class Subsekvensregister {
    private ArrayList <Frekvenstabell> register;
    private final static int  SUBSEKVENSLENGDE = 3;

    public Subsekvensregister () {
        register = new ArrayList <> ();
    }
    // laget en dynamisk liste med statisk subsekvenslengde for alle instanser

    public void settInn (Frekvenstabell f) {register.add(f);}
    // satt inn en frekvenstabell i register

    public Frekvenstabell taUt () {
        return register.remove(0);}
        /*Random random = new Random ();
        if (register.size() == 1)return register.remove(0);
        else {  
            int randomTall = random.nextInt(register.size());
            return register.remove(randomTall);}}*/
    // hentet ut en vilkårlig frekvenstabell fra registeret 

    public int antall () {return register.size();}
    // henter ut antall frekvenstabeller i register

    public static Frekvenstabell les (String filnavn) {
        Frekvenstabell f = new Frekvenstabell ();
        Scanner fil = null;
        try{fil = new Scanner ( new File (filnavn));}
        catch (Exception e) {
            System.out.println ("Feil ved åpning av filen med navn: \n" + filnavn + "\n" + e.getMessage());}
        while (fil.hasNextLine()){
            String linje = fil.nextLine().trim();
            char [] bokstaver = linje.toCharArray();
            for (int i = 0; i <= (bokstaver.length - SUBSEKVENSLENGDE); i++) {
                String key = "" + bokstaver[i] + bokstaver[i+1] + bokstaver [i+2]; 
                Integer verdi = 1;
                if (!(f.containsKey(key))) f.put(key, verdi);
            }  
        }
        fil.close();
        return f;
    }
    // Vi har nå lest filnavnet git ved argument, gått igjennom filen og lagt hver unike SUBSEKVENSLENGDE inn som nøkkel med verdi 1
}