package pe.edu.unmsm.sistemas;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

/**
 * @author Eisten Flores
 * @project ordenamiento
 */
public class OrdenamientoJefe extends UntypedAbstractActor {

    private int vec1[];

    private int vectorParcial1[];
    private int vectorParcial2[];
    private int vectorParcial3[];
    private int vectorParcial4[];

    private int vectorTotal1[];
    private int vectorTotal2[];
    private Ordenar vec2[];
    private int seg = 0;

    @Override
    public void onReceive(Object message) throws Throwable {
        vec2 = new Ordenar[4];


        if (message instanceof Ordenar) {
            vec2[seg] = (Ordenar) message;
            if (seg == 0) {
                vectorParcial1 = new int[vec2[seg].vec.length];
                int cont = 0;
                for (int n : vec2[seg].vec) {
                    vectorParcial1[cont] = n;
                    cont++;
                }
            } else if (seg == 1) {
                vectorParcial2 = new int[vec2[seg].vec.length];
                int cont = 0;
                for (int n : vec2[seg].vec) {
                    vectorParcial2[cont] = n;
                    cont++;
                }
            } else if (seg == 2) {
                vectorParcial3 = new int[vec2[seg].vec.length];
                int cont = 0;
                for (int n : vec2[seg].vec) {
                    vectorParcial3[cont] = n;
                    cont++;
                }
            } else if (seg == 3) {
                vectorParcial4 = new int[vec2[seg].vec.length];
                int cont = 0;
                for (int n : vec2[seg].vec) {
                    vectorParcial4[cont] = n;
                    cont++;
                }
            }

            seg++;
            if (seg == 4) {
                vectorTotal1 = ordenandoPartes(vectorParcial1, vectorParcial2);
                vectorTotal2 = ordenandoPartes(vectorParcial3, vectorParcial4);
                for (int n : ordenandoPartes(vectorTotal1, vectorTotal2)) System.out.printf("%d - ", n);

                this.getContext().stop(getSelf());
            }
        } else if (message instanceof int[]) {
            seg = 0;
            vec1 = (int[]) message;
            Ordenar ordenar[] = new Ordenar[4];
            ActorRef ordenador[] = new ActorRef[4];

            for (int i = 0; i < 4; i++) {

                ordenar[i] = new Ordenar();
                ordenar[i].vec = vec1;
                ordenar[i].ini = (int) (i / 4.0 * ordenar[i].vec.length);
                ordenar[i].fin = (int) ((i + 1) / 4.0 * ordenar[i].vec.length);

                ordenador[i] = this.getContext().actorOf(Props.create(Ordenador.class), "Ordenador" + i);
                ordenador[i].tell(ordenar[i], this.getSelf());
            }
        }
    }

    public int[] ordenandoPartes(int[] vector1, int[] vector2) {
        int resultado[] = new int[(vector1.length + vector2.length)];
        System.arraycopy(vector1, 0, resultado, 0, vector1.length);
        System.arraycopy(vector2, 0, resultado, vector1.length, vector2.length);
        QuickSort ob = new QuickSort();
        ob.sort(resultado, 0, resultado.length - 1);
        return resultado;
    }


}
