import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;



public class Main {

    public static void main(String [] argz) {
        if(argz.length != 1) {
            System.err.println("Argument error");
            System.exit(1);
        }
        int mode = -1;
        if(argz[0].equals("-e")) {
            mode = 1;
        } else if(argz[0].equals("-a")) {
            mode=2;
        } else if(argz[0].equals("-u")) {
            mode=3;
        } else if(argz[0].equals("-v")) {
            mode=4;
        } else if(argz[0].equals("-f")) {
            mode=5;
        } else {
            System.err.println("Argument error");
            System.exit(1);
        }

        Flexer scanner = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader("dfa"));
            scanner = new Flexer(br);
            scanner.yylex();
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //TODO: Done parsing
        AFD dfa = new AFD();
        dfa.buildAFD(scanner.states, scanner.transitions, scanner.sigma,
                scanner.firstState, scanner.lastStates);

        switch(mode) {
            case 1:
                //TODO: Has e
                dfa.hasE();
                break;
            case 2:
                //TODO: Accessible states
                dfa.printAccessible();
                break;
            case 3:
                //TODO: Useful states
                dfa.printUseful();
                break;
            case 4:
                //TODO: Empty language
                dfa.isEmpty();
                break;
            case 5:
                //TODO: Finite language
                dfa.isFinite();
                break;
            default:
                System.err.println("Argument error");
                System.exit(1);
        }

    }
}
