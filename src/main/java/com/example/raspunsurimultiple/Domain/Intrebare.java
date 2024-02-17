package com.example.raspunsurimultiple.Domain;

public class Intrebare {
    int id;
    String text;
    String raspunsA;
    String raspunsB;
    String raspunsC;
    String raspunsCorect;
    String dificultate;

    public Intrebare(int id, String text, String raspunsA, String raspunsB, String raspunsC, String raspunsCorect, String dificultate) {
        this.id = id;
        this.text = text;
        this.raspunsA = raspunsA;
        this.raspunsB = raspunsB;
        this.raspunsC = raspunsC;
        this.raspunsCorect = raspunsCorect;
        this.dificultate = dificultate;
    }

    public int getID() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRaspunsA() {
        return raspunsA;
    }

    public void setRaspunsA(String raspunsA) {
        this.raspunsA = raspunsA;
    }

    public String getRaspunsB() {
        return raspunsB;
    }

    public void setRaspunsB(String raspunsB) {
        this.raspunsB = raspunsB;
    }

    public String getRaspunsC() {
        return raspunsC;
    }

    public void setRaspunsC(String raspunsC) {
        this.raspunsC = raspunsC;
    }

    public String getRaspunsCorect() {
        return raspunsCorect;
    }

    public void setRaspunsCorect(String raspunsCorect) {
        this.raspunsCorect = raspunsCorect;
    }

    public String getDificultate() {
        return dificultate;
    }

    public void setDificultate(String dificultate) {
        this.dificultate = dificultate;
    }

    @Override
    public String toString() {
        return "Intrebare{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", raspunsA='" + raspunsA + '\'' +
                ", raspunsB='" + raspunsB + '\'' +
                ", raspunsC='" + raspunsC + '\'' +
                ", raspunsCorect='" + raspunsCorect + '\'' +
                ", dificultate=" + dificultate +
                '}';
    }
}
