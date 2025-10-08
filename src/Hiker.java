public class Hiker {
    private int hikerNum;

    public Hiker(int hikerNum) {
        this.hikerNum = hikerNum;
    }

    @Override
    public String toString() {
        return "Hiker" + hikerNum;
    }
}
