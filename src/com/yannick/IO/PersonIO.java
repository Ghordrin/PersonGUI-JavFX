package com.yannick.IO;

import com.yannick.Person.Person;

import java.io.*;


public class PersonIO {
    private static File data = new File("person.txt");

    /**
     * Adds a {@link Person} object to a file named "person.txt"
     *
     * @param person is a {@link Person} object
     * @throws IOException
     */
    public static void addPersonToFile(Person person) throws IOException {
        try {
            FileOutputStream fo = new FileOutputStream(data);
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(person);
            System.out.println(person.getName() + " succesfully written to file");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets the last saved person from the file.
     *
     * @return a {@link Person} object
     */
    public static Person retrieveLastPersonFromFile() {
        try {
            FileInputStream fi = new FileInputStream(data);
            ObjectInputStream oi = new ObjectInputStream(fi);
            return (Person) oi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
