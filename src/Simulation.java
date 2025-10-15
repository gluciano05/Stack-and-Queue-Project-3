import java.util.ArrayList;
import java.util.LinkedList;

public class Simulation {
    private static int hikerCount;
    private String listOfHikers;
    ArrayList<Queue<Stack<Hiker>>> entranceQueues;

    /** Constructs a new Simulation object and sets the count of hikers to 0
     */
    public Simulation() {
        entranceQueues = new ArrayList<Queue<Stack<Hiker>>>();
        listOfHikers = "";
        hikerCount = 0;
    }

    /**handles the bulk of the simulation, constructs all the stacks, queues,
     * and hikers necessary for the simulation. Also handles the timeline and
     * coordinating the events of simulation and UIHandler.
     */
    public void populateQueues () {
        //makes the 3 queues for the 3 entrances, one for each.
        for (int i = 0; i < 3; i++) {
            Queue<Stack<Hiker>> queue = new Queue<Stack<Hiker>>(3);

            for  (int j = 0; j < 3; j++) {
                Stack<Hiker> stack = new Stack<Hiker>();

                for (int k = 0; k < 10; k++) {
                    Hiker hiker = new Hiker(hikerCount);
                    stack.addLast(hiker);
                    hikerCount++;
                }
                queue.enqueue(stack);
            }
            entranceQueues.add(queue);
        }
    }

    public void openTrial () {
        Stack<Hiker> tempStack = new Stack<Hiker>();
        Queue<Stack<Hiker>> tempQueue = new Queue<Stack<Hiker>>(3);

        while (!entranceQueues.isEmpty()) {
            while (!entranceQueues.getFirst().isEmpty()) {
                tempStack = entranceQueues.getFirst().dequeue();

                /* poll's everything off the stack, calls toString
                 * concatenates the toString to the listOfHikers variable
                 */
                while(!tempStack.isEmpty()) {
                    listOfHikers += tempStack.pollLast().toString();
                }
            }
            //removes queue from ArrayList once empty
            entranceQueues.removeFirst();
        }
    }
}
