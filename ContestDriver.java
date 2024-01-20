import java.util.LinkedList;  
import java.util.Random;

public class ContestDriver {

    public static void main(String[] args) {
        int numberOfContestants = 43; 
        // Array 
        int[] roundCounts = new int[]{10, 100, 1000, 10000};
        long[] times = new long[roundCounts.length]; 

        for (int i = 0; i < roundCounts.length; i++) {
            times[i] = runSimulation(numberOfContestants, roundCounts[i]);
        }

        System.out.println("\nSimulation Times:");
        for (int i = 0; i < roundCounts.length; i++) {
            System.out.println("Rounds: " + roundCounts[i] + ", Time: " + times[i] + " ms");
        }
    }

    private static long runSimulation(int numberOfContestants, int numberOfRounds) {
        System.out.println("\nStarting Simulation with " + numberOfContestants + " contestants and " + numberOfRounds + " rounds");

        LinkedList<Contestant> contestants = new LinkedList<>(); 

        for (int i = 0; i < numberOfContestants; i++) {
            Contestant contestant = new Contestant("Contestant " + i, 20 + i);
            contestants.add(contestant);
        }

        Random random = new Random();
        long startTime = System.currentTimeMillis();

        // Run multiple rounds
        for (int round = 0; round < numberOfRounds; round++) {
            for (int i = 0; i < contestants.size(); i++) {
                boolean answerCorrect = random.nextBoolean();
                Contestant contestant = contestants.remove(i);

                if (answerCorrect) {
                    contestants.add(0, contestant);
                } else {
                    contestants.add(contestant);
                }
            }

            for (int i = 0; i < contestants.size(); i++) {
                Contestant contestant = contestants.get(i);
                contestant.setCurrentPosition(i);
            }
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Simulation for " + numberOfRounds + " rounds completed in " + duration + " ms");
        
        return duration; // Return time taken 
    }
}
