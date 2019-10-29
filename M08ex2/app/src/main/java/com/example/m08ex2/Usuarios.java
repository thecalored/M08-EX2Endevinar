package com.example.m08ex2;

public class Usuarios implements Comparable<Usuarios>{
    public String nombreUser;
    public int numFallos;

    public Usuarios(String nombreUser, int numFallos) {
        super();
        this.nombreUser = nombreUser;
        this.numFallos = numFallos;

    }
    public Usuarios() {


    }

    public String getNombreUser() {
        return nombreUser;
    }
    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }
    public int getNumFallos() {
        return numFallos;
    }
    public void setNumFallos(int numFallos) {
        this.numFallos = numFallos;
    }
    public int compareTo(Usuarios usuarios){return this.numFallos-usuarios.numFallos;}

    public String toString() {
        return "Usuarios [nombreUser=" + nombreUser + ", numFallos=" + numFallos + "]";
    }


}
