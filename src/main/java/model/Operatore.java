package model;

public enum Operatore {
    ADD('+', 0), SUB('-', 1), MULT('*', 2), DIV('/', 3), POW('^', 4);
    private char simbolo;
    private int valore;

    Operatore(char simbolo, int valore) {
        this.simbolo = simbolo;
        this.valore = valore;
    }
    @Override
    public String toString(){
        return Character.toString(simbolo);
    }

    public int getValore() {
        return valore;
    }

    boolean compare(Operatore op){
        return valore > op.valore;
    }
}
