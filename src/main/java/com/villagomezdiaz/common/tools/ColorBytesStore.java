package com.villagomezdiaz.common.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class ColorBytesStore implements Serializable {
    HashMap<String, double[][]> store;

    public ColorBytesStore() {
        store = new HashMap<String, double[][]>();
    }

    public void addToStore(String key, double[][] value) {
        store.put(key, value);
    }

    public void saveToFile(String filename) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(store);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public static HashMap<String, double[][]> getFromFile(String filename) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(filename);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        HashMap<String, double[][]> store = (HashMap<String, double[][]>) objectInputStream.readObject();
        objectInputStream.close();
        return store;
    }
}
