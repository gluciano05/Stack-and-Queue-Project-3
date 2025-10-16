import javax.swing.*;
import java.util.Random;
import java.util.Collections;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Simulation {
    ArrayList<Queue<Stack<Hiker>>> entranceQueues;
    private static int hikerCount;
    private String listOfHikers;
    private int[] hikersPerEntrance;

    public Simulation() {
        entranceQueues = new ArrayList<Queue<Stack<Hiker>>>();
        hikersPerEntrance = new int[3];
        listOfHikers = "";
        hikerCount = 0;
    }
    //3 BUTTON METHODS:
    public void start() {
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            entranceQueues.add(new Queue<Stack<Hiker>>(10));
        }

        for (int i = 0; i < 9; i++) {
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

    public void openTrial() {
        StringBuilder result = new StringBuilder();
        ArrayList<Hiker> allHikers = new ArrayList<>();

        try (FileWriter writer = new FileWriter("hikersGoingUp.txt")) {
            for (int entranceNum = 0; entranceNum < 3; entranceNum++) {
                if (entranceNum >= entranceQueues.size()) continue;

                Queue<Stack<Hiker>> currentQueue = entranceQueues.get(entranceNum);
                int hikersFromEntrance = 0;

                ArrayList<Stack<Hiker>> partialStacks = new ArrayList<>();

                while (!currentQueue.isEmpty() && hikersFromEntrance < 20) {
                    Stack<Hiker> tempStack = currentQueue.dequeue();

                    if (tempStack != null) {
                        int stackSize = 0;
                        ArrayList<Hiker> stackHikers = new ArrayList<>();

                        while (!tempStack.isEmpty()) {
                            Hiker h = tempStack.pop();
                            stackHikers.add(h);
                            stackSize++;
                        }

                        if (stackSize < 10) {
                            Stack<Hiker> savedStack = new Stack<>();
                            for (int i = stackHikers.size() - 1; i >= 0; i--) {
                                savedStack.push(stackHikers.get(i));
                            }
                            partialStacks.add(savedStack);
                        } else {
                            for (Hiker hiker : stackHikers) {
                                if (hikersFromEntrance < 20) {
                                    allHikers.add(hiker);
                                    hikersFromEntrance++;
                                }
                            }
                        }
                    }
                }

                for (Stack<Hiker> partialStack : partialStacks) {
                    while (!partialStack.isEmpty() && hikersFromEntrance < 20) {
                        Hiker hiker = partialStack.pop();
                        allHikers.add(hiker);
                        hikersFromEntrance++;
                    }
                }

                hikersPerEntrance[entranceNum] = hikersFromEntrance;
            }

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

    public void closeTrial() {
        try (FileWriter writer = new FileWriter("hikersComingDown.txt")) {
            String[] hikers = listOfHikers.split("\n");
            ArrayList<String> hikerList = new ArrayList<>();

            for (String hiker : hikers) {
                if (!hiker.trim().isEmpty()) {
                    hikerList.add(hiker);
                }
            }

            Collections.reverse(hikerList);

            StringBuilder reversed = new StringBuilder();
            for (String hiker : hikerList) {
                reversed.append(hiker).append("\n");
                writer.write(hiker + "\n");
            }

            listOfHikers = reversed.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //UTILITY FUNCTIONS:
    public int getHikerCount() {
        return hikerCount;
    }

    public String getAllHikersRegistered() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hikerCount; i++) {
            stringBuilder.append("hiker").append(i).append("\n");
        }
        return stringBuilder.toString();
    }
    public String getHikersPerEntrance() {
        return  "Entrance 0: " + hikersPerEntrance[0] + " hikers\n" +
                "Entrance 1: " + hikersPerEntrance[1] + " hikers\n" +
                "Entrance 2: " + hikersPerEntrance[2] + " hikers";
    }
    public String getListOfHikers() {
        return listOfHikers;
    }
}
