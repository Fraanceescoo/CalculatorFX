package model;

/**
 * Definizione della classe Frazione
 * - il denominatore è sempre positivo
 */
public class Frazione {
    private long numeratore;
    private long denominatore;

    public Frazione(long numeratore, long denominatore) {
        if(denominatore == 0){
            throw new ArithmeticException("Non definita frazione con denominatore uguale a 0");
        }
        if(denominatore < 0){
            numeratore *= -1;
            denominatore *= -1;
        }
        if (numeratore == 0) {
            this.numeratore = 0;
            this.denominatore = 1;
        } else {
            long mcd = massimoComuneDivisore(numeratore, denominatore);
            this.numeratore = numeratore / mcd;
            this.denominatore = denominatore / mcd;
        }
    }

    @Override
    public String toString(){
        return "(" + numeratore + "/" + denominatore + ")";
    }

    private static long massimoComuneDivisore(long a, long b){
        long a1 = Math.abs(a);
        long b1 = Math.abs(b);
        while(a1 != b1){
            if(a1 > b1){
                a1 -= b1;
            }else{
                b1 -= a1;
            }
        }
        return a1;
    }

    private static long minimoComuneMultiplo(long a, long b) {
        return a * b / massimoComuneDivisore(a, b);
    }

    public Frazione mult(Frazione f){
        return new Frazione(this.numeratore * f.numeratore,
                this.denominatore*f.denominatore);
    }

    public Frazione div(Frazione f) {
        return this.mult(inverso(f));
    }

    public Frazione add(Frazione f) {
        long denominatore = minimoComuneMultiplo(this.denominatore, f.denominatore);
        long numeratore = denominatore / this.denominatore * this.numeratore +
                          denominatore / f.denominatore * f.numeratore;
        return new Frazione(numeratore, denominatore);
    }

    public Frazione sub(Frazione f) {
        return this.add(nega(f));
    }

    public Frazione pow(Frazione exponent) {
        if (this.numeratore == 0)
            if(exponent.numeratore == 0) throw new ArithmeticException("0^0 indefinito");
        else
            return(new Frazione(1, 1));
        if (exponent.denominatore != 1) throw new ArithmeticException("Potenza con esponente non intero non implementata");
        if(exponent.numeratore > 0)
            return new Frazione(
                    (long) Math.pow(this.numeratore, exponent.numeratore),
                    (long) Math.pow(this.denominatore, exponent.numeratore));
        else
            return new Frazione(
                    (long) Math.pow(this.denominatore, -exponent.numeratore),
                    (long) Math.pow(this.numeratore, -exponent.numeratore));
    }

    private static Frazione nega(Frazione f) {
        return new Frazione(-f.numeratore, f.denominatore);
    }

    private static Frazione inverso(Frazione f) {
        return new Frazione(f.denominatore, f.numeratore);
    }

}
