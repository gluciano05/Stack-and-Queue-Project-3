//coded by Gabriel Luciano
/** Represents a hiker with a number and creates a name using that number
  * (i.e. hiker 1)
  */
public class Hiker {
    private int hikerNum;

    /** Constructs a hiker and takes in the hikers number.
     * @param hikerNum the hikers number, used to identify the hiker
     */
    public Hiker(int hikerNum) {
        this.hikerNum = hikerNum;
    }

    /** prints the Hikers information
     * @return returns "Hiker" + the hikers number (i.e Hiker 2).
     */
    @Override
    public String toString() {
        return "hiker" + hikerNum;
    }
}
