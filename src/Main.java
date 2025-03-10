
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


// C:\Users\goran\Documents\Java-Algebra\original.pdf
// String s = "C:\\Users\\goran\\Documents\\Java-Algebra"
public class Main {
    public static void main(String[] args) throws IOException {
        byte[] podaci = new byte[0];
        FileInputStream fis = null;
        FileOutputStream fos = null;
        String outputString;
        String putanja;
        boolean exit = false;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("Unesite putanju do originalne datoteke:");
            putanja = s.nextLine().replace("\\", "\\\\");

            try {
                fis = new FileInputStream(putanja);
                podaci = new byte[(int) fis.getChannel().size()];
                fis.read(podaci);
                exit = true;
                fis.close();

            } catch (FileNotFoundException e) {
                System.out.println("Datoteka nije pronađena");
            }


        } while (!exit);
        exit = false;
        do {
            System.out.println("Unesite naziv kopiranje datoteke:");
             outputString = s.nextLine().trim();
            outputString = outputString.replace("\\", "\\\\");
            exit = true;
            try {
                fos = new FileOutputStream(outputString);
                fos.write(podaci);
                System.out.println("Datoteka uspješno kopirana.");
                fos.close();
            } catch (Exception e) {
                e.getMessage();

            }
        } while (!exit);
        System.out.println("Želite li isbrisati kopiju datoteke (D/N)?");
        String temp = s.nextLine().trim().toLowerCase();
        switch (temp){
            case "d"->{
                try {
                    Files.deleteIfExists(Path.of(outputString));
                    System.out.println("Datoteka "+outputString+" izbrisana");
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }

            }
            case "n" -> System.out.println("Datoteka sačuvana");
        }


    }
}