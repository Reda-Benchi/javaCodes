public class Boks {

    private int lengde;
    private int bredde;
    private int hoyde;

    public Boks(int lengde, int bredde, int hoyde) {
        this.lengde = lengde;
        this.bredde = bredde;
        this.hoyde = hoyde;
    }

    public int beregnAreal() {
        return (lengde * bredde * 2) + (bredde * hoyde * 2) + (hoyde * lengde * 2);
    }

    public int beregnVolum() {
        return lengde * bredde * hoyde;
    }
}
