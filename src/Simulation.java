import java.util.ArrayList;
import java.util.LinkedList;

public class Simulation {
    private static int hikerCount;

    /** Constructs a new Simulation object and sets the count of hikers to 0
     */
    public Simulation() {
        hikerCount = 0;
    }

    /**handles the bulk of the simulation, constructs all the stacks, queues,
     * and hikers necessary for the simulation. Also handles the timeline and
     * coordinating the events of simulation and UIHandler.
     */
    public static void startSimulation () {
        //populating the stacks and queues
        @SuppressWarnings("unchecked") //the yellow squiggle was annoying me
        ArrayList<Queue<Stack<Hiker>>> entranceQueues = new ArrayList<Queue<Stack<Hiker>>>();

        //makes the 3 queues for the 3 entrances, one for each.
        for (int i = 0; i < 3; i++) {
            Queue<Stack<Hiker>> queue = new Queue<Stack<Hiker>>(3);

            for  (int j = 0; j < 3; j++) {
                Stack<Hiker> stack = new Stack<>();

                for (int k = 0; k < 10; k++) {
                    Hiker hiker = new Hiker(hikerCount);
                    hikerCount++;
                }
                queue.enqueue(stack);
            }
            entranceQueues.add(queue);
        }
    }


}
