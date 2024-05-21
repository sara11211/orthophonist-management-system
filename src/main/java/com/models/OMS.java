package com.models;

import java.io.*;
import java.util.HashMap;

public class OMS implements Serializable {
    private static final long serialVersionUID = 1L;

    private HashMap<Orthophoniste, String> orthophonistes;

    // constructeur
    public OMS() {
        this.orthophonistes = new HashMap<Orthophoniste, String>();
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

    public void addOrthophoniste(Orthophoniste orthophoniste, String val) {
        this.orthophonistes.put(orthophoniste, val);
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


    // Save orthophonistes to file
    public void saveOrthophonistesToFile(String filename) throws IOException {
        File file = new File(filename);
        System.out.println("Saving to: " + file.getAbsolutePath());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        }
    }

    public static OMS loadOrthophonistesFromFile(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        System.out.println("Loading from: " + file.getAbsolutePath());
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (OMS) ois.readObject();
        }
    }




}
