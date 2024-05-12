package com.models;

import com.HelloApplication;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;

import static com.HelloApplication.*;

public class OMS implements Serializable {

    private HashMap<Orthophoniste, String> orthophonistes;

    public OMS() {
        this.orthophonistes = new HashMap<Orthophoniste, String>();
        this.orthophonistes.put(new Orthophoniste("admin","admin"),"admin");
    }

    public HashMap<Orthophoniste, String> getOrthophonistes() {
        return orthophonistes;
    }

    public boolean isExist (String email) {
        Orthophoniste utilisateurRecherche = new Orthophoniste(email);
        if (orthophonistes.containsKey(utilisateurRecherche)) {
            System.out.println("isExist found user");
            return true;
        } else {
            System.out.println("isExist can't find the user!");
            return false;
        }
    }

    public Orthophoniste findUser(String email, String mdp) {
        Orthophoniste utilisateurRecherche = new Orthophoniste(email);
        if (orthophonistes.containsKey(utilisateurRecherche)) {
            for (Orthophoniste user : orthophonistes.keySet()) {
                if (user.getAdresseEmail().equals(email) && user.getMotDePasse().equals(mdp)) {
                    return user;
                }
            }
        } else {
            System.out.println("Function findUser can't find the user!");
        }
        return null;
    }

    // public void sauvegarder() {
    //     if (utilisateurCourant != null) {
    //         orthophonistes.remove(utilisateurCourant);
    //         orthophonistes.put(utilisateurCourant, utilisateurCourant.getMotDePasse());
    //         System.out.println("utilisateurCourant est bien sauvegarder");
    //     }
    
    //     try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(FILE_PATH))) {
    //         // Only write the Orthophoniste keys (not the String values)
    //         out.writeObject(orthophonistes.keySet());
    //         System.out.println("oms est bien sauvegarder");
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}
