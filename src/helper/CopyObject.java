package helper;

import ui.BoardView;


import java.io.*; //too much files to include
/**
  * The class <code>CopyObject</code> can copy instance of object
  * @version 1.0
  * @author Lucas Augusto 
**/

public class CopyObject {

    private static final String STORAGE_FILE = "./rsc/files/storage.dat";

    public CopyObject() {

    }

    public static final BoardView getCopyOf(BoardView view) {

        ObjectOutputStream outfile;
        ObjectInputStream infile;

        //write the object
        try {
            outfile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(STORAGE_FILE))));

            //write the view
            try {
                outfile.writeObject(view);
            } catch(IOException writeException) {
                System.err.println(writeException.getMessage());
                System.err.println("Error when writing view");
                System.exit(1);
            }

            //close
            try {
                outfile.close();
            } catch(IOException closeException) {
                System.err.println("Error when closing file");
                System.exit(1);
            }
        } catch(FileNotFoundException fileNotFoundException) {
            System.err.println("Error when search file");
            System.exit(1);
        } catch(IOException ioExcpetion) {
            System.err.println("Error when creating output");
            System.exit(1);
        }

        BoardView newView = null;
        //read the view
        try {
            infile = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(STORAGE_FILE))));

            try {
                //read view
                Object o = infile.readObject();
                if(o instanceof BoardView) {
                }
            } catch(IOException readException) {
                System.err.println("Error during read file");
                System.exit(1);
            } catch(ClassNotFoundException classNotFoundException) {
                System.err.println("Class BoardView not found");
                System.exit(1);
            }
        } catch(IOException ioException) {
            System.err.println("Error when opening file");
            System.exit(1);
        }

        return newView;
    }
}