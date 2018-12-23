package storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileStorage {
    public Object readObject(String fileName) {
		Object o = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
		    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		    o = objectInputStream.readObject();
		    objectInputStream.close(); 
		} catch (IOException i) {
//			i.printStackTrace();
		} catch (ClassNotFoundException c) {
//			c.printStackTrace();
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
		} catch (IOException i) {
			i.printStackTrace();
			success = false;
		}
		return success;
	}
}
