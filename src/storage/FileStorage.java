package storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class FileStorage implements Serializable{
    public Object readObject(String fileName) {
		Object o = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
		    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		    o = objectInputStream.readObject();
		    objectInputStream.close(); 
		    fileInputStream.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		return o;
	}

	public boolean writeObject(Object o, String fileName) {
		boolean success = true;
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(o);
			out.close();
			fileOut.close();
			System.out.println("The Object  was succesfully written to a file");
		} catch (IOException i) {
			i.printStackTrace();
			success = false;
			System.out.println("failure");
		}
		return success;
	}
}
