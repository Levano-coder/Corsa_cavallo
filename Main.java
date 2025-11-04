import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


public class Main {
    static String primo="";
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        String tmpS;
        int tmp;
        String path;
        System.out.println("Inserisci il percorso del file su cui tracrivere i risultati: ");
        path = input.nextLine();
        Path results = Path.of(path);
        try{
            BufferedWriter writer = Files.newBufferedWriter(results);
            System.out.println("works");


        ArrayList<Cavallo> listaCavallo = new ArrayList<>();
        for (int i = 1; i<=4; i++){
            System.out.println("Inserisci nome del cavallo " + i);
            tmpS = input.nextLine();
            System.out.println("Inserisci la lentezza del cavallo  " + i);
            tmp = input.nextInt();
            String v = input.nextLine();
            Cavallo c = new Cavallo(tmpS, tmp, writer);
            listaCavallo.add(c);
        }

        for (Cavallo c: listaCavallo) {
            c.start();
        }

        int rand = (int)(Math.random()* 5);
        Cavallo azzoppato = listaCavallo.get(rand);
        azzoppato.interrupt();
        System.out.println("Il cavallo" + azzoppato.getNome() + "è stato azzoppato");
        writer.write("Il cavallo" + azzoppato.getNome() + "è stato azzoppato");
        writer.newLine();
        writer.flush();

        for (Cavallo c: listaCavallo && !this.isInterrupted()){
            try{
                c.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Il primo cavallo è " + primo);

        writer.write("Il primo cavallo è " + primo);
        writer.newLine();
        writer.flush();
        }
    }
 

    public static String getPrimo() {
        return primo;
    }

    public static void setPrimo(String primo) {
        Main.primo = primo;
    }
}