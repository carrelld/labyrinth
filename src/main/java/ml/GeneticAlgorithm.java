package ml;
import java.util.Random;

/**
 * A very simple implementation of a genetic algorithm. Implements tournament selection, mutation, crossover.
 * A better implementation would have the ability to choose the program's implementations and parameters, 
 * allele data type (complex custom objects should work), gene-level units of collections of alleles for 
 * better cross-over.
 * 
 * @author carredx
 *
 */

public class GeneticAlgorithm {
    
    // Settings for how the genetic algorithm should be run
    private static final int GENERATIONS = 100;
    private static final int NUMBER_OF_GENES = 100;
    private static final int POPULATION_SIZE = 100;
    private static final double CROSSOVER_PROBABILITY = 0.50;
    private static final double MUTATION_PROBABILITY = 0.01;
    
    public static void main(String[] args) {
        // Establish the population
        Population pop = new Population(POPULATION_SIZE, NUMBER_OF_GENES, CROSSOVER_PROBABILITY, MUTATION_PROBABILITY);
        
        // Print the initial statistics
        System.out.println(pop.toString());
        
        // LabyrinthDesignUtils subsequent populations and print their statistics after each iteration
        for (int i = 0; i < GENERATIONS; i++) {
            pop.nextGeneration();
            System.out.println(pop.toString());
        }
    }
}

/**
 * A group of individuals all interacting as part of the same evolutionary unit.
 * @author carredx
 *
 */
class Population {
    
    // single object for the entire class to generate stochasticity
    private static final Random r =  new Random();
    // size of the subset when performing selection
    private static final int SELECTION_GROUP_SIZE = 2;
    
    // count to keep track of the generation
    private int generation = 0;
    // arrays to hold the individuals of this generation
    private Individual[] currentGen;
    private double probCrossover;
    private double probMutation;


    /**
     * Constructor for initializing a starting population and the population's genetic properties
     * @param size Number of individuals in the population
     * @param numberOfGenes Number of genes per individual
     * @param probCrossover
     * @param probMutation
     */
    public Population(int size, int numberOfGenes, double probCrossover, double probMutation) {
        this.probCrossover = probCrossover;
        this.probMutation = probMutation;
        this.currentGen = new Individual[size];
        // initialize the population with individuals with random genes
        for (int i = 0; i < currentGen.length; i++) {
            currentGen[i] = new Individual(numberOfGenes);
            currentGen[i].populateGenes();
        }
    }
    
    /**
     * Replace the current generation with new individuals produced from crossover and mutation and increment generation field
     * @param probCrossover Requires value between 0 and 1 indicating the probability that crossover takes place during mating
     * @param probMutation Requires value between 0 and 1 indicating the probability that mutation occurs at a particular gene
     */
    public void nextGeneration() {
        // selection from current generation into temporary parent array
        Individual[] parents = new Individual[currentGen.length];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = this.tournamentSelection(SELECTION_GROUP_SIZE);
        }
        // make new individuals from 2 random parents and replace current generation
        for (int i = 0; i < currentGen.length; i++) {
            Individual parent1 = parents[r.nextInt(parents.length)];
            Individual parent2 = parents[r.nextInt(parents.length)];
            currentGen[i] = parent1.makeOffspring(parent2, this.probCrossover, this.probMutation);
        }
        // increment the generation counter
        this.generation++;
    }
    
    /**
     * Implement tournament selection. Return the highest fitness individual from a randomly selected subset of the current generation
     * @param TournamentSize size of the randomly selected subset
     * @return Individual with the highest fitness from within the subset
     */
    private Individual tournamentSelection(int tournamentSize) {
        // tournament selection
        Individual winner = currentGen[r.nextInt(currentGen.length)];
        for (int i = 1; i < tournamentSize; i++) {
            Individual challenger = currentGen[r.nextInt(currentGen.length)];
            if (challenger.getFitness() > winner.getFitness()) {
                winner = challenger;
            }
        }
        return winner;
    }
    
    /**
     * @return Highest fitness among the individuals in the current generation
     */
    public int highestFitness() {
        int max = currentGen[0].getFitness();
        for (int i = 1; i < currentGen.length; i++) {
            if (currentGen[i].getFitness() > max) {
                max = currentGen[i].getFitness();
            }
        }
        return max;
    }
    
    /**
     * @return Average fitness of the individuals in the current generation
     */
    public double averageFitness() {
        int sum = 0;
        for (int i = 0; i < currentGen.length; i++) {
            sum += currentGen[i].getFitness();
        }
        return (double)sum / currentGen.length; 
    }
    
    /**
     * @return The current generation iteration
     */
    public int getGeneration() {
        return generation;
    }
    
    /**
     * Print summary statistics of the current state of the population
     */
    @Override
    public String toString() {
        return "Generation: " + this.getGeneration() + "\tHighest Fitness: " + this.highestFitness() + "\tAverage Fitness: " + this.averageFitness();
    }
}

/**
 * An individual unit of a genetic algorithm is a collection of binary (1 or 0) genes. This class includes methods mainly for different ways to generate new individuals.
 * @author carredx
 *
 */
class Individual {
    
    private static final Random r = new Random();
    
    private int[] geneArr;
    private int fitness;
    
    /**
     * Mating method that generates a new individual by simulating crossover between two Individuals, then susequent mutation
     * @param otherParent Parent that "this" will mate with
     * @param probCrossover Requires value between 0 and 1 indicating the probability that crossover takes place during mating
     * @param probMutation Requires value between 0 and 1 indicating the probability that mutation occurs at a particular gene
     * @return
     */
    public Individual makeOffspring(Individual otherParent, double probCrossover, double probMutation) {
        // Establish the new offspring object
        Individual offspring = new Individual(this.geneArr.length);
        
        // Check if Crossover occurs and where it should start
        boolean crossoverOccurs = r.nextBoolean();
        int crossoverStartIndex = r.nextInt(offspring.geneArr.length);
                
        // Perform the gene assignment for the offspring
        // start copying from this parent and switch when and if crossover takes place
        Individual parentToCopy = this;
        
        for (int i = 0; i < offspring.geneArr.length; i++) {
            
            // change to copying from otherParent when the startindex is reached, but only if crossoverOccurs
            if (crossoverOccurs && crossoverStartIndex == i) {
                parentToCopy = otherParent;
            }
            
            // copy gene at index i to offspring
            offspring.geneArr[i] = parentToCopy.geneArr[i];
            
            // attempt to mutate gene at index i
            boolean mutationOccurs = r.nextDouble() <= probMutation; // See if mutation occurs
            if (mutationOccurs) {
                // flip from 0 to 1 or from 1 to 0 depending on current value using XOR operator
                offspring.geneArr[i] ^= 1;                
            }
        }
        
        // update fitness score and return
        offspring.calculateFitness();
        return offspring;
    }
    
    /**
     * Make an individual with a specified gene capacity
     * @param numberOfGenes Number of genes that the individual will have
     */
    public Individual(int numberOfGenes) {
        this.geneArr = new int[numberOfGenes];
    }

    /**
     * Randomly assign gene values of 0 or 1 to every gene in the Individual's gene array
     */
    protected void populateGenes() {
        // LabyrinthDesignUtils random number
        Random random = new Random();
        for (int i = 0; i < geneArr.length; i++) {
            geneArr[i] = random.nextInt(2);
        }
        // update the fitness for the new set of genes
        this.calculateFitness();
    }

    /**
     * Calculate and cache the fitness score
     */
    public void calculateFitness() {
        // Using "counting ones" algorithm for fitness
        int sum = 0;
        for (int i = 0; i < geneArr.length; i++) {
            sum += geneArr[i];
        }
        this.fitness = sum;
    }
    
    /**
     * Read-only access to the Individual's fitness score
     * @return
     */
    public int getFitness() {
        return fitness;
    }

}
