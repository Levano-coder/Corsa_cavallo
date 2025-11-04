import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Cavallo extends Thread {
    private String nome;
    private int lentezza;
     private BufferedWriter writer;

    public Cavallo(String nome, int lentezza, BufferedWriter writer) {
        super();
        this.nome = nome;
        this.lentezza = lentezza;
        this.writer = writer;
    }

    public int getLentezza() {
        return this.lentezza;
    }

    public String getNome() {
        return this.nome;
    }


    public void setLentezza(int lentezza) {
        this.lentezza = lentezza;
    }

    public void run() {
        System.out.println("Cavallo " + this.nome + " comincia il suo galoppo.");
        writer.write("Cavallo " + this.nome + " comincia il suo galoppo.");
        writer.newLine();
        writer.flush();

        for(int i = 1; i <= 10; ++i) {
            if(this.isInterrupted()){
                System.out.println(this.nome + "è stato azzoppato");
                writer.write(this.nome + "è stato azzoppato");
                writer.newLine();
                writer.flush();
                return;
            }
            try {
                sleep(this.lentezza);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(this.nome + " cavalca - passo " + i);
            writer.write(this.nome + " cavalca - passo " + i);
            writer.newLine();
            writer.flush();
        }
        if(Main.getPrimo().equals("")){
            Main.setPrimo(this.nome);
        }

    }
}