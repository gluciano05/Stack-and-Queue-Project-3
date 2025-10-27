import javax.swing.*;
import java.util.Random;
import java.util.Collections;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/** handles hiking simulation + entrance logic*/
public class Simulation {
    ArrayList<Queue<Stack<Hiker>>> entranceQueues;
    private static int hikerCount;
    private ArrayList<String> entranceBreakdown;
    private String listOfHikers;
    private int[] hikersPerEntrance;

    /** Constructs a simulation and initializes the necessary data structures. */
    public Simulation() {
        entranceQueues = new ArrayList<Queue<Stack<Hiker>>>();
        hikersPerEntrance = new int[3];
        entranceBreakdown = new ArrayList<String>();
        listOfHikers = "";
        hikerCount = 0;
    }

    /** Creates and organizes hikers into appropriate data structures for the simulation.
     * This function does the following:
     * Creates the 3 queues for the entrances (one queue for each entrance),
     * Creates 10 stacks (max capacity is 10), 9 full stacks and 1 partial stack
     * Hikers get assigned a sequenced number
     * Stacks are randomly assigned to one of 3 entrances
     */
    public void start() {
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            entranceQueues.add(new Queue<Stack<Hiker>>(10));
        }

        for (int i = 0; i < 10; i++) {
            Stack<Hiker> stack = new Stack<Hiker>();
            int hikersInStack;

            if (i < 9) {
                hikersInStack = 10;
            }
            else {
                hikersInStack = 8;
            }
            for (int k = 0; k < hikersInStack; k++) {
                Hiker hiker = new Hiker(hikerCount);
                stack.push(hiker);
                hikerCount++;
            }

            int randomEntrance = random.nextInt(3);
            entranceQueues.get(randomEntrance).enqueue(stack);
        }
    }

    /** Opens the trial and processes hikers from each entrance.
     * This function does the following:
     * Processes each entrance in order,
     * Dequeues stacks and handles full stacks first then partial stacks,
     * Limits each entrance to 20 hikers,
     * Writes hiker information to hikersGoingUp.txt
     */
    public void openTrial() {
        StringBuilder result = new StringBuilder();
        ArrayList<Hiker> allHikers = new ArrayList<>();

        try (FileWriter writer = new FileWriter("hikersGoingUp.txt")) {
            //makes sure each entrance is in order, like 0, 1, 2
            for (int entranceNum = 0; entranceNum < 3; entranceNum++) {
                if (entranceNum >= entranceQueues.size()) continue;

                Queue<Stack<Hiker>> currentQueue = entranceQueues.get(entranceNum);
                int hikersFromEntrance = 0;
                ArrayList<Integer> stackSizes = new ArrayList<Integer>();
                ArrayList<Stack<Hiker>> partialStacks = new ArrayList<>();

                //dequeue's stacks so they can be used, no more than 20 at a time
                while (!currentQueue.isEmpty()) {
                    Stack<Hiker> tempStack = currentQueue.dequeue();

                    if (tempStack != null) {
                        int stackSize = 0;
                        ArrayList<Hiker> stackHikers = new ArrayList<>();

                        //pops hikers from stack to set size.
                        while (!tempStack.isEmpty()) {
                            Hiker h = tempStack.pop();
                            stackHikers.add(h);
                            stackSize++;
                        }

                        //Separates partial stacks (less than 10 hikers) to deal with later
                        if (stackSize < 10) {
                            Stack<Hiker> savedStack = new Stack<>();
                            for (int i = stackHikers.size() - 1; i >= 0; i--) {
                                savedStack.push(stackHikers.get(i));
                            }
                            partialStacks.add(savedStack);
                        } else {
                            //handles full stacks first
                            stackSizes.add(stackHikers.size());
                            for (Hiker hiker : stackHikers) {
                                if (hikersFromEntrance < 20) {
                                    allHikers.add(hiker);
                                    hikersFromEntrance++;
                                }
                            }
                        }
                    }
                }

                //handles partial stacks after full stacks have been addressed
                for (Stack<Hiker> partialStack : partialStacks) {
                    int partialSize = 0;
                    while (!partialStack.isEmpty() && hikersFromEntrance < 20) {
                        Hiker hiker = partialStack.pop();
                        allHikers.add(hiker);
                        hikersFromEntrance++;
                        partialSize++;
                    }
                    if (partialSize > 0) {
                        stackSizes.add(partialSize);
                    }
                }
                //tracks total hikers from this entrance
                hikersPerEntrance[entranceNum] = hikersFromEntrance;

                if (entranceNum == 0) {
                    entranceBreakdown.add("Entrance queues before departure: ");
                }

                StringBuilder breakdown = new StringBuilder("Entrance " +  entranceNum + ": ");
                for (int i = 0; i < stackSizes.size(); i++) {
                    breakdown.append(stackSizes.get(i));
                    if (i < stackSizes.size() - 1) {
                        breakdown.append(", ");
                    }
                }
                entranceBreakdown.add(breakdown.toString());
            }

            //write hikers to file
            for (Hiker hiker : allHikers) {
                String hikerInfo = hiker.toString() + "\n";
                result.append(hikerInfo);
                writer.write(hikerInfo);
            }

            listOfHikers = result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Closes the trial and processes hikers returning in reverse order.
     * This function does the following:
     * Reverses the order of hikers from the going up list,
     * Writes the reversed order to hikersComingDown.txt
     */
    public void closeTrial() {
        try (FileWriter writer = new FileWriter("hikersComingDown.txt")) {
            //splits list of hikers into individual entries
            String[] hikers = listOfHikers.split("\n");
            ArrayList<String> hikerList = new ArrayList<>();

            for (String hiker : hikers) {
                if (!hiker.trim().isEmpty()) {
                    hikerList.add(hiker);
                }
            }

            //reverses order of hikers, instructions called for different return order.
            Collections.reverse(hikerList);

            //writes the reverse order to a file.
            StringBuilder reversed = new StringBuilder();
            for (String hiker : hikerList) {
                reversed.append(hiker).append("\n");
                writer.write(hiker + "\n");
            }

            //updates list to show the return order
            listOfHikers = reversed.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //UTILITY FUNCTIONS:
    /** Returns the total number of hikers registered.
     * @return the total hiker count
     */
    public int getHikerCount() {
        return hikerCount;
    }

    /** Returns a list of all registered hikers.
     * @return a string containing all hikers in the format "hikerX"
     */
    public String getAllHikersRegistered() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hikerCount; i++) {
            stringBuilder.append("hiker").append(i).append("\n");
        }
        return stringBuilder.toString();
    }

    /** Returns the number of hikers that went through each entrance.
     * @return a string showing hikers per entrance
     */
    public String getHikersPerEntrance() {
        return  "Entrance 0: " + hikersPerEntrance[0] + " hikers\n" +
                "Entrance 1: " + hikersPerEntrance[1] + " hikers\n" +
                "Entrance 2: " + hikersPerEntrance[2] + " hikers";
    }

    /** Returns the breakdown of stack sizes for each entrance.
     * @return a string containing the entrance breakdown information
     */
    public String getEntranceBreakdown() {
        StringBuilder result = new StringBuilder();
        for (String line : entranceBreakdown) {
            result.append(line).append("\n");
        }
        return result.toString();
    }

    /** Returns the current list of hikers.
     * @return a string containing the list of hikers
     */
    public String getListOfHikers() {
        return listOfHikers;
    }
}