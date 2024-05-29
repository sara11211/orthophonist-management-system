package com.models;

import java.io.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.HashSet;
public class Dossier implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String ID_FILE_PATH = "uniqueId.dat";
    private static final AtomicLong uniqueIdGenerator = new AtomicLong(loadUniqueId());
    private final long numDossier;

    private Patient patient;



    public Dossier(Patient patient) {
        this.patient = patient;
        this.numDossier = uniqueIdGenerator.getAndIncrement();
        saveUniqueId(uniqueIdGenerator.get());
    }


    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public long getNumDossier() {
        return numDossier;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeLong(uniqueIdGenerator.get());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        long id = in.readLong();
        uniqueIdGenerator.set(id);
    }

    private static long loadUniqueId() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ID_FILE_PATH))) {
            return ois.readLong();
        } catch (IOException e) {
            return 1;
        }
    }

    private static void saveUniqueId(long uniqueId) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ID_FILE_PATH))) {
            oos.writeLong(uniqueId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}