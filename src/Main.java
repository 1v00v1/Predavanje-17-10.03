
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


// C:\Users\goran\Documents\Java-Algebra\original.pdf

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner s = new Scanner(System.in);
        byte[] podaci = new byte[0];
        String outputString;
        String putanja;
        String lokacija;
        boolean exit = false;

        do {
            System.out.println("Unesite putanju do originalne datoteke:");

            putanja = s.nextLine();
            lokacija = putanja.substring(0, putanja.lastIndexOf("\\") + 1);

            try (FileInputStream fis = new FileInputStream(putanja)) {

                podaci = new byte[(int) fis.getChannel().size()];
                fis.read(podaci);
                exit = true;

            } catch (FileNotFoundException e) {
                System.out.println("Datoteka nije pronađena");
            }


        } while (!exit);
        exit = false;
        do {
            System.out.println("Unesite naziv kopirane datoteke:");
            outputString = s.nextLine().trim();

            outputString = lokacija + outputString;
            if (outputString.endsWith(".pdf")) {
                exit = true;
            } else {
                outputString += ".pdf";
                exit = true;
            }

            try (FileOutputStream fos = new FileOutputStream(outputString)) {
                fos.write(podaci);
                System.out.println("Datoteka uspješno kopirana.");

            } catch (FileNotFoundException e) {
                e.getMessage();
                exit = false;

            }
        } while (!exit);
        System.out.println("Želite li isbrisati kopiju datoteke (D/N)?");
        String temp = s.nextLine().trim().toLowerCase();
        s.close();
        switch (temp) {
            case "d" -> {
                try {
                    Files.deleteIfExists(Path.of(outputString));
                    System.out.println("Datoteka "
                            + outputString.substring(outputString.lastIndexOf("\\") + 1)
                            + " izbrisana");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            default -> System.out.println("Datoteka sačuvana");
        }
    }
}