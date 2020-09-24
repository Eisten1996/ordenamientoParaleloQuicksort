package pe.edu.unmsm.sistemas;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Eisten Flores
 * @project ordenamiento
 */
public class Main {
    String vec[];

    public static void main(String[] args) throws IOException {
        Main main1 = new Main();
        main1.inicializar();

        ActorSystem system = ActorSystem.create("ordenamiento-vector");
        ActorRef ordemaniento = system.actorOf(Props.create(OrdenamientoJefe.class));
        ordemaniento.tell(main1.vec, ActorRef.noSender());
        system.terminate();
    }

    private void inicializar() throws IOException {

        InputStream is = getClass().getClassLoader().getResourceAsStream("verbos-desorden.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        ArrayList<String> lista = new ArrayList<String>();
        String linea = br.readLine();
        while (linea != null) {
            lista.add(linea);
            linea = br.readLine();
        }
        vec = new String[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            vec[i] = lista.get(i);
        }
    }

 /*   public static void main(String args[]) {
        int arr[] = {10, 7, 8, 9, 1, 5};
        int n = arr.length;

        QuickSort ob = new QuickSort();
        ob.sort(arr, 0, n - 1);

        System.out.println("sorted array");
        printArray(arr);
    }

    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }*/
}
