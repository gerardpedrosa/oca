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
}