package Genetic_Algorithm;
import java.util.Scanner;

public class Genetic {

    static int[][] genes;
    final static double probability_rate = 0.07;
    public static int max_generation = 200;
    static int[] best_chromo = new int[2];

    public Genetic() {

    }

    // genes selections
    public static void genes() {

        Scanner user = new Scanner(System.in);
        System.out.print("How many genes would you like to try : ");
        int number_genes = user.nextInt();

        if (number_genes <= 2) {
            genes();
        } else {
            genes = new int[number_genes][number_genes];
        }
        user.close();
    }

    // population filler
    public static int[][] population(int[][] genes) {

        System.out.println("\nStarting Genes:\n ");

        for (int i = 0; i < genes.length; i++) {

            for (int j = 0; j < genes[i].length; j++) {

                int number = (int) (Math.random() * 10);
                genes[i][j] = number;
            }

        }
        population_printer(genes);
        return genes;
    }

    // printer of the array in matrix
    public static void population_printer(int[][] population) {

        for (int row = 0; row < population.length; row++) {

            for (int column = 0; column < population[row].length; column++) {

                System.out.print(population[row][column] + " ");
            }

            System.out.println();
        }
    }

    // returns the sum of the chromosome and the best gene out of all
    public static int[] sum(int[][] genes_array) {
        int[] sum_array = new int[genes[0].length];
        // makes the sum and stores them in the array of the sum
        for (int row = 0; row < genes_array.length; row++) {
            int sum = 0;
            for (int column = 0; column < genes_array[row].length; column++) {
                sum += genes_array[row][column];
            }

            sum_array[row] = sum;
        }
        // compares all the sum and takes the best
        int compare = 0;
        int index = 0;
        for (int i = 0; i < sum_array.length; i++) {

            if (compare < sum_array[i]) {
                compare = sum_array[i];
                index = i;
            }
        }

        System.out.println("\nThe best gene is at Row: " + index);

        System.out.print("{");
        // prints out the row
        for (int i = 0; i < genes_array[index].length; i++) {

            System.out.print(genes[index][i] + " ");
        }
        System.out.print("}");

        return best_chromo;
    }

    // this will be crossover function
    public static int[][] crossover(int[][] new_genes) {

        int min = (int) (Math.random() * new_genes.length - 1);
        int max = (int) (Math.random() * new_genes[0].length - 1);

        if (min > max) {

            int tmp = min;
            min = max;
            max = tmp;

        }

        System.out.println("\nCrossover points: " + min + ", " + max + "\n");

        for (int row = 0; row < new_genes.length; row++) {

            for (int column = 0; column < new_genes[row].length; column++) {

                if (row == (new_genes.length - 1)) {
                    break;
                } else {

                    for (int i = min; i <= max; i++) {

                        int holder = new_genes[row][i];
                        new_genes[row][i] = new_genes[row + 1][i];
                        new_genes[row + 1][i] = holder;
                    }
                }

            }
        }
        System.out.println("\nCrossover: ");
        population_printer(new_genes);
        return new_genes;
    }

    // mutation function
    public static int[][] mutation(int[][] genes) {
        // takes the row and column
        int index_row = 0;
        int index_column = 0;

        for (int row = 0; row < genes.length; row++) {
            double tot_prob = 10.0; // sum of the highest one

            for (int column = 0; column < genes[row].length; column++) {

                // multiplying the probability rate by the gene
                double tmp_prob = genes[row][column] * probability_rate;
                // taking the one which comes out with low value as it is the weak one
                if (tmp_prob < tot_prob) {

                    tot_prob = tmp_prob;
                    index_row = row;
                    index_column = column;

                }

            }

            System.out.print("[" + index_row + ", " + index_column + "]");
            int holder = genes[index_row][index_column];
            do {
            genes[index_row][index_column] = (int) (Math.random() * 10);
            } while (!(genes[index_row][index_column] >= holder));

        }
        System.out.println("\nMutation: \n");
        population_printer(genes);
        return genes;
    }

    public static boolean best_pop(int[][] genes){

        for(int i = 0; i < genes.length; i++ ){

            for(int j = 0; j < genes[i].length; j++){

                if(genes[i][j] != 9){return false;}
            }
        }

        return true;
    }

}
