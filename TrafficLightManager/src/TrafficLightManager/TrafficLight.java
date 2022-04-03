package TrafficLightManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class TrafficLight {

    private final String name, path = "C:\\Users\\Jefferson\\Documents\\NetBeansProjects\\TrafficLightManager\\src\\TrafficLightManager\\IMG\\";
    private int currentState = 0;
    JPanel panelTrafficLight;
    JPanel panelSensor;
    private final ColorTrafficLight colorTrafficLight;

    public TrafficLight(String name, JPanel centralPanel, ColorTrafficLight colorTrafficLight) {
        panelTrafficLight = new JPanel(new BorderLayout());
        panelSensor = new JPanel(new BorderLayout());
        JButton jSemaforo = new JButton(name);
        JCheckBox sensor = new JCheckBox();
        jSemaforo.setIcon(new ImageIcon(path + "red.png"));
        jSemaforo.setBackground(new Color(255, 250, 250));
        jSemaforo.setFocusable(false);
        jSemaforo.setForeground(Color.BLACK);
        jSemaforo.setBorder(null);
        panelTrafficLight.add(jSemaforo);
        panelSensor.add(sensor);
        centralPanel.add(panelTrafficLight);
        centralPanel.add(panelSensor);
        this.name = name;
        this.colorTrafficLight= colorTrafficLight;
    }

    public JCheckBox getSensor() {
        for (Component child : panelSensor.getComponents()) {
            if (child instanceof JCheckBox) {
                return ((JCheckBox) child);
            }
        }
        return null;
    }
    
    public void cycle() {
        for (Component child : panelTrafficLight.getComponents()) {
            if (child instanceof JButton) {
                if (currentState == 0) {
                    ((JButton) child).setIcon(new ImageIcon(path + "green.png"));
                    currentState = 1;
                    rest(colorTrafficLight.getTimeGreen());
                }
                if (currentState == 1) {
                    ((JButton) child).setIcon(new ImageIcon(path + "yellow.png"));
                    currentState = 2;
                    rest(colorTrafficLight.getTimeYellow());
                }
                if (currentState == 2) {
                    ((JButton) child).setIcon(new ImageIcon(path + "red.png"));
                    currentState = 0;
                    rest(colorTrafficLight.getTimeRed());
                }
            }
        }
    }
    
    public void rest(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void changeIcon() {
        for (Component child : panelTrafficLight.getComponents()) {
            if (child instanceof JButton) {
                ((JButton) child).setIcon(new ImageIcon(path + "red.png"));
            }
        }
    }

    public void setcurrentState(int currentState) {
        this.currentState = currentState;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void printStatus() {
        System.out.println(name + " with time of " + colorTrafficLight.getTimeGreen() + " g second(s)"
        + colorTrafficLight.getTimeYellow()+ " y second(s)"
        + colorTrafficLight.getTimeRed()+ " r second(s)");
    }
}
