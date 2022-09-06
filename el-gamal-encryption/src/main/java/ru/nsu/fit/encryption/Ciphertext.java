package ru.nsu.fit.encryption;

public class Ciphertext {
    private int r;
    private int e;

    public Ciphertext(int r, int e) {
        this.r = r;
        this.e = e;
    }

    public Ciphertext(Ciphertext ciphertext) {
        this.e = ciphertext.getE();
        this.r = ciphertext.getR();
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return "(" + r + "," + e + ")";
    }
}
