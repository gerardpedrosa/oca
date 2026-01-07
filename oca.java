import java.util.Scanner;

public class oca {

    Scanner e = new Scanner(System.in);

    public static void main(String[] args) {
        oca principal = new oca();
        principal.iniciarJoc();
    }

    public void iniciarJoc() {
        
        System.out.println("**************************************************");
        System.out.println("*            Benvingut/da al Joc de l'Oca!       *");
        System.out.println("**************************************************");
        System.out.println();

        String[] nomsJugadors = jugadors();
        
        ordre(nomsJugadors);

        int [] casella = new int [nomsJugadors.length];

        
        boolean finalJoc = false;
        if (casella[0] == 63 || casella[1] == 63 || casella[2] == 63 || casella[3] == 63) {
            
            finalJoc = true;
        }
        

        while (!finalJoc) {

            int [] torn = new int [nomsJugadors.length];

            
        }

        System.out.println();
        System.out.println("El jugador " + nomsJugadors[0] + " ha guanyat la partida!");
        System.out.println();
        System.out.println("**************************************************");
        System.out.println("*                 Fi del Joc!                    *");
        System.out.println("**************************************************");
        
    }

    public String[] jugadors() {

        int numeroJugadors;

        do {
            System.out.print("Introdueix el nombre de jugadors (2 - 4): ");
            numeroJugadors = e.nextInt();
            e.nextLine();

            if (numeroJugadors < 2 || numeroJugadors > 4) {
                System.out.println("El nombre de jugadors ha de ser entre 2 i 4.");
            }

        } while (numeroJugadors < 2 || numeroJugadors > 4);

        String[] nomsJugadors = new String[numeroJugadors];

        for (int i = 0; i < numeroJugadors; i++) {
            System.out.print("Introdueix el nom del jugador " + (i + 1) + ": ");
            nomsJugadors[i] = e.nextLine();
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println("Els jugadors registrats són:");
        
        for (int i = 0; i < nomsJugadors.length; i++) {
        System.out.println("Jugador " + (i + 1) + ": " + nomsJugadors[i]);
        }

        return nomsJugadors;
    }

    public void ordre(String[] nomsJugadors) {

        System.out.println();
        System.out.println("**************************************************");
        System.out.println("L'ordre que s'ha definit aleatòriament és :");

        for (int i = 0; i < nomsJugadors.length; i++) {
            int aleatori = (int)(Math.random() * nomsJugadors.length);

            String jugador = nomsJugadors[i];
            nomsJugadors[i] = nomsJugadors[aleatori];
            nomsJugadors[aleatori] = jugador;
        }
 
        for (int i = 0; i < nomsJugadors.length; i++) {
            System.out.println((i + 1) + ". " + nomsJugadors[i]);
        }
    }

    public int tirarDau() {

        int tirada1 = (int)(Math.random() * 6) + 1;

        int tirada2 = (int)(Math.random() * 6) + 1;

        System.out.println("Has tret un " + tirada1 + " i un " + tirada2 + ".");
        
        return tirada1 + tirada2;
    }

    public void primerTorn() {


        
    }
}