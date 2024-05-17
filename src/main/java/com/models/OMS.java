package com.models;

import com.HelloApplication;
import static com.HelloApplication.*;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

public class OMS implements Serializable {

    private HashMap<Orthophoniste, String> orthophonistes;

    // constructeur
    public OMS() {
        this.orthophonistes = new HashMap<Orthophoniste, String>();
        this.orthophonistes.put(new Orthophoniste("sarah","sarah"),"sarah");
        this.orthophonistes.put(new Orthophoniste("admin","admin"),"admin");
    }

    // getter
    public HashMap<Orthophoniste, String> getOrthophonistes() {
        return orthophonistes;
    }

    //vérifier si l'orthophoniste existe 
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

    // public boolean isExist(String email) {
    //     return orthophonistes.containsKey(new Orthophoniste(email)); // Use email for comparison
    // }

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

    public void sauvegarder() {
        if (utilisateurCourant != null) {
            orthophonistes.remove(utilisateurCourant);
            orthophonistes.put(utilisateurCourant, utilisateurCourant.getMotDePasse());
            System.out.println("utilisateurCourant est bien sauvegarder");
        }

        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(FILE_PATH))) {
            out.writeObject(oms);
            System.out.println("my orthophonist app est bien sauvegarder");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public List<Anamnese> getAnamnesesByUser(Orthophoniste user) {
        return user.getAnamneses();
    }

    public void addAnamneseToUser(Orthophoniste user, Anamnese anamnese) {
        user.addAnamnese(anamnese);
    }
}
