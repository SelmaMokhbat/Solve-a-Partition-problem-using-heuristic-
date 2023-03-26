import java.util.*;
import java.util.ArrayList;
import java.util.List;
public class AStar1 {
    // la classe représentant un état
    static class State {
        int sum1, sum2, index;
        ArrayList<Integer> S1, S2;

        State(int s1, int s2, int idx, ArrayList<Integer> set1, ArrayList<Integer> set2) {
            sum1 = s1;
            sum2 = s2;
            index = idx;
            S1 = set1;
            S2 = set2;
        }
    }

    static int heuristic(State s) {
        return Math.abs(s.sum1 - s.sum2);
    }

    static int cost(State s) {
        return s.S1.size() + s.S2.size(); // coût égal à la taille des deux ensembles
    }

    static void printSets(ArrayList<Integer> S1, ArrayList<Integer> S2) {
        System.out.print("S1: ");
        for (int i : S1) System.out.print(i + " ");
        System.out.print("S2: ");
        for (int i : S2) System.out.print(i + " ");
        System.out.println();
    }

    static void aStar(ArrayList<Integer> set, int n) {
        //file de priorite qui est définie par un comparator qui est lui meme un constructeur
        // priorite a celui qui a la valeur de f la plus basse
        PriorityQueue<State> file = new PriorityQueue<State>((a, b) -> (heuristic(a) + cost(a)) - (heuristic(b) + cost(b)));
        file.add(new State(0, 0, 0, new ArrayList<Integer>(), new ArrayList<Integer>()));

        while (!file.isEmpty()) {
            State curr = file.poll();

            if ((curr.index == n)&&(heuristic(curr)==0)) {
                System.out.println("Solution trouvée:");
                printSets(curr.S1, curr.S2);
                System.out.println("Heuristique de Algo A* \n  " + heuristic(curr)); //affiche l'heuristique
                System.out.println("Cout de Algo A* \n  " + cost(curr)); //affiche le cout
                System.out.println("Fonction de Algo A* \n F = " + (heuristic(curr) + cost(curr))); //affiche la fonction de l'algo
                return;
            }

            // ajouter l'élément courant à S1
            ArrayList<Integer> S1 = new ArrayList<Integer>(curr.S1);
            S1.add(set.get(curr.index));
            //on cree un nouvel objet et on recupere l'element de l'indice index
            State newState1 = new State(curr.sum1 + set.get(curr.index), curr.sum2, curr.index + 1, S1, curr.S2);
            file.add(newState1);

            // ajouter l'élément courant à S2
            ArrayList<Integer> S2 = new ArrayList<Integer>(curr.S2);
            S2.add(set.get(curr.index));
            State newState2 = new State(curr.sum1, curr.sum2 + set.get(curr.index), curr.index + 1, curr.S1, S2);
            file.add(newState2);
        }

        System.out.println("Aucune solution trouvée");
    }

    public static void main(String[] args) {
        
       
        ArrayList<Integer> set = new ArrayList<Integer>(Arrays.asList(7,1,10,5,2,6,5,2,3,5,1,1,4,-2,4,0));
        int n = set.size();
        aStar(set, n); 


        ArrayList<Integer> set1 = new ArrayList<Integer>(Arrays.asList(748,348,699,597,502,204));
        int n1=set1.size();
        aStar(set1, n1);

        ArrayList<Integer> set2 = new ArrayList<Integer>(Arrays.asList(1081,256,172,21,95,34,12,1,671,181));
        int n2=set2.size();
        aStar(set2, n2);
        
        
        
        //System.out.println(); 


        /*ArrayList<Integer> set = new ArrayList<Integer>(Arrays.asList(5,868,363,461,287,181,307,279,582,635,55,476,206,839,977,891,13,654,477,29,930,736,449,977,161,508,38,276,375,799,501,313,339,218,704,651,735,257,178,316,905,102,166,942,610,338,912,219,960,636,382,557,340,726,751,64,645,574,35,214,293,411,530,243,913,482,540,924,181,241 ));
        int n = set.size();
        long dur=0;
        for(int i=0;i<1000;i++){
            long debut = System.currentTimeMillis();
            aStar(set, n);
            long fin = System.currentTimeMillis();
             dur = dur + (fin - debut); // durée en millisecondes
        }
        dur=dur/1000;
        double duree = (double) dur / 1000; // durée en secondes
        System.out.println(n+" "+duree+ " \n");*/
        

        /*//genere tableau aleatoire

        for(int j=5;j<40;j++){
            // remplir le tableau avec des nombres aléatoires
            ArrayList<Integer> tableau = new ArrayList<Integer>(); // créer un nouvel ArrayList vide

            Random rand = new Random(); // créer une instance de la classe Random

            // remplir l'ArrayList avec des nombres aléatoires
            for (int i = 0; i <j; i++) {
                int nombreAleatoire = rand.nextInt(100); // générer un nombre aléatoire entre 0 et 99
                tableau.add(nombreAleatoire); // ajouter le nombre aléatoire à l'ArrayList
            }
            //resoudre le probleme
            int k=tableau.size();
            long debut = System.currentTimeMillis();
            for(int l=0;l<10000;l++){
                aStar(tableau,k);
            }
            long fin = System.currentTimeMillis();
            long dur = fin - debut; // durée en millisecondes
            double duree = (double) dur / 1000; // durée en secondes
            System.out.println(k+" "+duree+ " \n");
        }*/
    }
}
