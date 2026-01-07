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
        int[] penalitzacio = new int[nomsJugadors.length];
        int jugadorActual = 0;

        boolean finalJoc = false;

        while (!finalJoc) {

        finalJoc = tornJugador(jugadorActual, nomsJugadors, casella, penalitzacio);

        jugadorActual = (jugadorActual + 1) % nomsJugadors.length;
        System.out.println("**************************************************");
        
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

    public int tirarUnDau() {

    return (int)(Math.random() * 6) + 1;
    }

    public int[] tirarDausSegonsCasella(int posicio) {
    int dau1 = tirarUnDau();
    int dau2 = 0;

    if (posicio < 60) {
        dau2 = tirarUnDau();
    }

    return new int[]{dau1, dau2};
    }

    public boolean tornJugador(int jugadorActual, String[] nomsJugadors, int[] casella, int[] penalitzacio) {

    if (penalitzacio[jugadorActual] > 0) {
        penalitzacio[jugadorActual]--;
        System.out.println(nomsJugadors[jugadorActual] + " perd un torn.");
        return false;
    }

    System.out.println();
    System.out.println("És el torn del jugador " + (jugadorActual + 1) + ", " + nomsJugadors[jugadorActual]);
    System.out.print(">> tiro");
    e.nextLine();

    int[] daus = tirarDausSegonsCasella(casella[jugadorActual]);
    int dau1 = daus[0];
    int dau2 = daus[1];
    int suma = dau1 + dau2;

    if (dau2 > 0) {
        System.out.println("Has obtingut un " + dau1 + " i un " + dau2 + " = " + suma);
    } else {
        System.out.println("Has obtingut un " + dau1);
    }

    casella[jugadorActual] += suma;

    if (casella[jugadorActual] > 63) {
        casella[jugadorActual] = 63 - (casella[jugadorActual] - 63);
    }

    return casella[jugadorActual] == 63;
    }
}