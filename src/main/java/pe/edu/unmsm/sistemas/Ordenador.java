package pe.edu.unmsm.sistemas;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;

/**
 * @author Eisten Flores
 * @project ordenamiento
 */
public class Ordenador extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) {
        if (message instanceof Ordenar) {
            Ordenar ordena = (Ordenar) message;
            Ordenar ordenando = ordenamiento(ordena.vec, ordena.ini, ordena.fin);
            ActorRef jefe = this.getSender();
            jefe.tell(ordenando, this.getSelf());
        }
    }

    public Ordenar ordenamiento(String A[], int ini, int fin) {

        String o[] = new String[fin - ini];

        int cont = 0;
        for (int i = ini; i < fin; i++) {
            o[cont] = A[i];
            cont++;
        }
        int n = o.length;

        QuickSort ob = new QuickSort();
        ob.sort(o);


        Ordenar ordenado = new Ordenar();
        ordenado.ini = ini;
        ordenado.fin = fin;
        ordenado.vec = o;
        return ordenado;

    }


}
