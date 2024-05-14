package com.models;

import java.io.*;
import java.util.HashMap;

public class OMS implements Serializable {

    private HashMap<Orthophoniste, String> orthophonistes;

    // constructeur
    public OMS() {
        this.orthophonistes = new HashMap<Orthophoniste, String>();
        this.orthophonistes.put(new Orthophoniste("sarah","sarah"),"sarah");
    }

    // getter
    public HashMap<Orthophoniste, String> getOrthophonistes() {
        return orthophonistes;
    }

    // vérifier si l'orthophoniste existe 
    public boolean isExist(String email, String password) {
        Orthophoniste utilisateurRecherche = new Orthophoniste(email, password);
        if (orthophonistes.containsKey(utilisateurRecherche)) {
            System.out.println("isExist found user");
            return true;
        } else {
            System.out.println("isExist can't find the user!");
            return false;
        }
    }

    // vérifier si les informations de l'orthophoniste sont correctes
    public Orthophoniste findUser(String email, String password) {
        for (Orthophoniste user : orthophonistes.keySet()) {
            if (user.getAdresseEmail().equals(email) && orthophonistes.get(user).equals(password)) {
                return user;
            }
        }
        System.out.println("Function findUser can't find the user!");
        return null;
    }

}
