public class Arbres {

    private int val;
    private Arbres filsGauche;
    private Arbres filsDroit;

    public Arbres(int val, Arbres filsDroit, Arbres filsGauche){
        this.val = val;
        this.filsDroit = filsDroit;
        this.filsGauche = filsGauche;
    }

    public Arbres(int val){
        this.val = val;
        this.filsGauche = null;
        this.filsDroit = null;
    }

    public static void main(String[] args) {
        Arbres a1 = new Arbres(30);
        Arbres a2 = new Arbres(22);
        Arbres a3 = new Arbres(25, a1, a2);

        Arbres a4 = new Arbres(13);
        Arbres a8 = new Arbres(7);
        Arbres a9 = new Arbres(10);
        Arbres a5 = new Arbres(9,a9, a8);
        Arbres a6 = new Arbres(11, a4, a5);

        Arbres a7 = new Arbres(19, a3, a6);

        /*System.out.println(rechercheMax(a7));
        System.out.println(a7.toString());
        System.out.println(suppr(a7, 25));*/

        System.out.println(toStringTrie(a7));
    }

    @Override
    public String toString() {
        return "Arbres{" +
                "val=" + val +
                ", filsGauche=" + filsGauche +
                ", filsDroit=" + filsDroit +
                '}';
    }

    public static boolean estVide(Arbres a){
        return a == null;
    }

    public static boolean recherche (Arbres a, int x) {
        if (estVide(a)) {
            return false;
        } else {
            if (x == a.val) {
                return true;
            } else {
                if (x < a.val) {
                    return recherche(a.filsGauche, x);
                } else {
                    return recherche(a.filsDroit, x);
                }
            }
        }
    }

    public static String toStringTrie(Arbres a){
        if(estVide(a)){
            return " ";
        }
        else{
            return toStringTrie(a.filsGauche) + a.val + toStringTrie(a.filsDroit);
        }
    }

    public static Arbres insert (Arbres a, int x) {
        if (estVide(a)) {
            return new Arbres(x);
        } else {
            if (x < a.val) {
                return new Arbres(a.val, a.filsDroit, insert(a.filsGauche, x));
            }
            if (x > a.val) {
                return new Arbres(a.val, insert(a.filsDroit, x), a.filsGauche);
            }
        }
        return a;
    }

    public static Arbres suppr (Arbres a, int x) {
        if (recherche(a, x)) {
            if (x < a.val) {
                return new Arbres(a.val, a.filsDroit, suppr(a.filsGauche, x));
            }
            if (x > a.val) {
                return new Arbres(a.val, suppr(a.filsDroit, x), a.filsGauche);
            }
            if (a.filsDroit == null & a.filsGauche == null) {
                return null;
            } else {
                if (a.filsGauche == null) {
                    return a.filsDroit;
                }
                if (a.filsDroit == null) {
                    return a.filsGauche;
                } else {
                    int max = rechercheMax(a.filsGauche);
                    return new Arbres(max, a.filsDroit, null);
                }
            }
        }
        return a;
    }

    public static int rechercheMax(Arbres a){
        int x=a.val;
        Arbres current = a;
        while(current.filsDroit!=null){
            current = current.filsDroit;
            x = current.val;
        }
        return x;
    }
}


