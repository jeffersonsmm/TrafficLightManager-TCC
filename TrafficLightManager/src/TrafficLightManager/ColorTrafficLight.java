package TrafficLightManager;

public class ColorTrafficLight {

    private final int tpr = 1;
    private final int c = 5;
    private final int a_ad = 3;
    private final int g = 10;
    private final int i;
    private final int d2;
    private final int v;

    public ColorTrafficLight(int i, int d2, int v) {
        this.i = i;
        this.d2 = d2;
        this.v = v;
    }

    public int getTimeGreen() {
        if (i == 0) {
            return tpr + (v / (2 * (a_ad - g))) + (d2 + c) / v;
        }
        return tpr + (v / (2 * (a_ad + g))) + (d2 + c) / v;
    }

    public int getTimeYellow() {
        if (i == 0) {
            return tpr + (v / (2 * (a_ad - g)));
        }
        return tpr + (v / (2 * (a_ad + g)));
    }

    public int getTimeRed() {
        return (d2 + c) / v;
    }

}
