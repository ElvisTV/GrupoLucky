package com.elvistezen.grupolucky.ui.home;

public class Frase {
    private int id;
    private String autor;
    private String frase;
    private String significado;

    // Constructor
    public Frase(int id, String autor, String frase, String significado) {
        this.id = id;
        this.autor = autor;
        this.frase = frase;
        this.significado = significado;
    }

    // Métodos getter
    public int getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }

    public String getFrase() {
        return frase;
    }

    public String getSignificado() {
        return significado;
    }

    // Métodos setter
    public void setId(int id) {
        this.id = id;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    // Método para mostrar información de la frase
    public void mostrarInfo() {
        System.out.println("ID: " + id);
        System.out.println("Autor: " + autor);
        System.out.println("Frase: " + frase);
        System.out.println("Significado: " + significado);
    }
}
