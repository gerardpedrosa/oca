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
            boolean repetir = tornJugador(jugadorActual, nomsJugadors, casella, penalitzacio);

            if (casella[jugadorActual] == 63) {
                finalJoc = true;
            } else if (!repetir) {
                
                jugadorActual = (jugadorActual + 1) % nomsJugadors.length;
            }
        }
        System.out.println("**************************************************");

        int guanyador = -1;
        for (int i = 0; i < casella.length; i++) {
            if (casella[i] == 63) {
                guanyador = i;
                break;
            }
        }

        if (guanyador != -1) {
            System.out.println();
            System.out.println("El jugador " + nomsJugadors[guanyador] + " ha guanyat la partida!");
            System.out.println();
            System.out.println("**************************************************");
            System.out.println("*                 Fi del Joc!                    *");
            System.out.println("**************************************************");
        }
    }

    public String[] jugadors() {

        int numeroJugadors = llegirNumeroJugadors();

        String[] nomsJugadors = new String[numeroJugadors];

        for (int i = 0; i < numeroJugadors; i++) {
        nomsJugadors[i] = llegirNomJugadors(i + 1);
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
    System.out.print(">> tirar daus (Enter)");
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

    System.out.println("El jugador " + nomsJugadors[jugadorActual] + " està a la casella " + casella[jugadorActual]);

    boolean repetir = gestionarCasellaEspecial(jugadorActual, casella, penalitzacio, casella, true);

    return repetir || casella[jugadorActual] == 63; 
    }

    public boolean gestionarCasellaEspecial(int jugadorActual, int[] casella, int[] penalitzacio, int[] posicionsJugadors, boolean primeraTirada) {
    boolean repetir = false;
    
    if (gestioOca(jugadorActual, casella)) {
        repetir = true;
    }
    if (gestioPont(jugadorActual, casella)) {
        repetir = true;
    }
    if (casella[jugadorActual] == 19) {
        gestioFonda(jugadorActual, penalitzacio);
    }
    if (casella[jugadorActual] == 31) {
        gestioPou(jugadorActual, casella, penalitzacio);
    }
    if (casella[jugadorActual] == 52) {
        gestioPreso(jugadorActual, penalitzacio);
    }
    if (primeraTirada && casella[jugadorActual] == 26) {
        System.out.println("Daus 3-6: avança a la casella 26 i torna a tirar.");
        repetir = true;
    }
    if (primeraTirada && casella[jugadorActual] == 53) {
        System.out.println("Daus 4-5: avança a la casella 53 i torna a tirar.");
        repetir = true;
    }
    if (casella[jugadorActual] == 42) gestioLaberint(jugadorActual, casella);
    if (casella[jugadorActual] == 58) gestioMort(jugadorActual, casella);
    return repetir;
}

    public boolean gestioOca(int jugadorActual, int[] casella) {
    int[] oques = {5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59};
        for (int i = 0; i < oques.length; i++) {
            if (casella[jugadorActual] == oques[i]) {
                if (i + 1 < oques.length) {
                    casella[jugadorActual] = oques[i + 1];
                    System.out.println("De oca en oca i tiro perquè em toca.");
                    return true;
                } else {
                    System.out.println("Última oca, no es torna a tirar.");
                    return false;
                }
            }
        }
        return false;
    }


    public boolean gestioPont(int jugadorActual, int[] casella) {
        if (casella[jugadorActual] == 6) {
            casella[jugadorActual] = 12;
            System.out.println("De pont a pont i tiro perquè em porta la corrent.");
            return true; 
        } else if (casella[jugadorActual] == 12) {
            casella[jugadorActual] = 6;
            System.out.println("De pont a pont i tiro perquè em porta la corrent.");
            return true; 
        }
        return false;
    }   

    public void gestioFonda(int jugadorActual, int[] penalitzacio) {
    System.out.println("Fonda: perds un torn.");
    penalitzacio[jugadorActual] = 1;
    }

    public void gestioPou(int jugadorActual, int[] casella, int[] penalitzacio) {
    System.out.println("Pou: perds dos torns.");
    penalitzacio[jugadorActual] = 2;
        for (int i = 0; i < casella.length; i++) {
            if (i != jugadorActual && casella[i] == 31) {
                System.out.println("Un altre jugador estava al pou: surt immediatament!");
                penalitzacio[i] = 0;
            }
        }
    }  

    public void gestioPreso(int jugadorActual, int[] penalitzacio) {
    System.out.println("Presó: perds tres torns.");
    penalitzacio[jugadorActual] = 3;
    }

    public void gestioLaberint(int jugadorActual, int[] casella) {
        if (casella[jugadorActual] == 42) {
            System.out.println("Laberint: torna a la casella 39.");
            casella[jugadorActual] = 39;
        }
    }   

    public boolean gestioDaus45(int jugadorActual, int[] casella) {
        if (casella[jugadorActual] == 53) {
            System.out.println("Daus 4-5: avança a la casella 53 i torna a tirar.");
            return true;
        }
        return false;
    }


    public void gestioMort(int jugadorActual, int[] casella) {
        if (casella[jugadorActual] == 58) {
            System.out.println("La Mort: torna a la casella inicial.");
            casella[jugadorActual] = 0;
        }
    }

    public int llegirNumeroJugadors() {
    int numeroJugadors = 0;
    boolean valid = false;

    while (!valid) {
        System.out.print("Introdueix el nombre de jugadors (2 - 4): ");
        String entrada = e.nextLine();

        try {
            numeroJugadors = Integer.parseInt(entrada);

            if (numeroJugadors >= 2 && numeroJugadors <= 4) {
                valid = true;
            } else {
                System.out.println("El nombre de jugadors ha de ser entre 2 i 4.");
            }

        } catch (NumberFormatException ex) {
            System.out.println("Entrada no vàlida. Introdueix un número.");
        }
    }
    return numeroJugadors;

    }

    
    public String llegirNomJugadors(int numeroJugador) {
    String nom;
    boolean valid = false;

    while (!valid) {
        System.out.print("Introdueix el nom del jugador " + numeroJugador + ": ");
        nom = e.nextLine().trim();


        if (!nom.isEmpty() && !nom.matches(".*\\d.*")) {
            valid = true;
            return nom;
        } else {
            System.out.println("Nom invàlid. No pot estar buit ni contenir números.");
        }
    }
    return "";
    }
}