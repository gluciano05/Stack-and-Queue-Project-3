public class Hiker {
    private int hikerNum;
    private String name;

    /** Constructs a hiker and takes in the hikers number.
     * @param hikerNum the hikers number, used to identify the hiker
     */
    public Hiker(int hikerNum) {
        this.hikerNum = hikerNum;
    }

    /** Sets the hiker name.
     * @param str the given string to be assigned to the hiker
     */
    public void setHikerName (String str) {
        this.name = str;
    }

    /** Gets the hikers name and returns it.
     * @return returns the Hikers name as a String
     */
    public String getHikerName () {
        return this.name;
    }

    /** prints the Hikers information
     * @return returns "Hiker" + the hikers number (i.e Hiker 2).
     */
    @Override
    public String toString() {
        return "Hiker" + hikerNum + "\n";
    }
}
