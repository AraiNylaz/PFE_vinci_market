package com.example.backend.Enums;

public enum Etat {

    Debut("En cours de validation"),
    Vente("En Vente"),
    Vendu("Vendu"),
    SUPPRIME("Supprime"),
    REFUSE("Refuse");

    private String name;

    Etat(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Etat getEtatFromString(String text){
        if(this.getName().equals(text))return this;

        return null;
    }
}
