package model;

public enum Parentesi {
    PARENTESI_CHIUSA(')'), PARENTESI_APERTA('(');
    private char simbolo;

    Parentesi(char simbolo) {
        this.simbolo = simbolo;
    }

    @Override
    public String toString() {
        return Character.toString(simbolo);
    }
}
