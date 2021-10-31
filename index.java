public class index {

    public static void main(String[] args) {

        Genetic.genes();
        Genetic.population(Genetic.genes);
        int i = 0;
        while (Genetic.max_generation > 0) {
            
            if (Genetic.best_pop(Genetic.genes)) {
                break;
            } else {
                System.out.println("\nThis is the " + i + " Generation");
                Genetic.sum(Genetic.genes);
                Genetic.crossover(Genetic.genes);
                Genetic.mutation(Genetic.genes);
                Genetic.max_generation--;
                i++;
            }
            
        }

    }
}
