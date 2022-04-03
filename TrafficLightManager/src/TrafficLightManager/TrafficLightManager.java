package TrafficLightManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class TrafficLightManager extends JFrame {

    private final ThreadGroup tg = new ThreadGroup("Thread Father");
    private final ArrayList<TrafficLight> listTrafficLight;
    private final ArrayList<Thread> threads;
    private BorderLayout layout;
    private GridLayout content;
    private TrafficLight top, left, right, bottom;
    private final int numberTrafficLight;
    private int position;

    public void loadTrafficLight(JPanel centralPanel) {
        System.out.println("Creating Thread");
        top = new TrafficLight("trafficLight top ", centralPanel, new ColorTrafficLight(1, 75, 40));
        left = new TrafficLight("trafficLight left ", centralPanel, new ColorTrafficLight(1, 105, 60));
        right = new TrafficLight("trafficLight right ", centralPanel, new ColorTrafficLight(1, 75, 40));
        bottom = new TrafficLight("trafficLight bottom", centralPanel, new ColorTrafficLight(1, 75, 60));

        System.out.println(top.getName() + "\n"
                + left.getName() + "\n"
                + right.getName() + "\n"
                + bottom.getName());

        listTrafficLight.add(top);
        listTrafficLight.add(left);
        listTrafficLight.add(right);
        listTrafficLight.add(bottom);
        manager();
        System.out.println("");
    }

    public void manager() {
        for (int i = 0; i < numberTrafficLight; i++) {
            threads.add(new Thread(tg, listTrafficLight.get(i).getName()) {
                @Override
                public void run() {
                    while (true) {
                        if (listTrafficLight.get(position).getSensor().isSelected()) {
                            listTrafficLight.get(position).printStatus();
                            listTrafficLight.get(position).cycle();
                        }
                        position++;
                        if (position == numberTrafficLight) {
                            position = 0;
                        }
                    }
                }
            });
        }
    }

    public void starttrafficLight() {
        for (int i = 0; i < numberTrafficLight; i++) {
            try {
                threads.get(i).start();
                threads.get(i).join();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }

    public void createLayout() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int numberRows = (int) Math.ceil(numberTrafficLight / 2.0);
        String path = "C:\\Users\\Jefferson\\Documents\\NetBeansProjects\\TrafficLightManager\\src\\TrafficLightManager\\IMG\\";

        layout = new BorderLayout();
        content = new GridLayout(numberRows, 2);

        JPanel panel = new JPanel(layout);
        JPanel centralPanel = new JPanel(content);

        JButton synchronize = new JButton();
        JButton desynchronize = new JButton();

        synchronize.setIcon(new ImageIcon(path + "synchronize.png"));
        desynchronize.setIcon(new ImageIcon(path + "desynchronize.png"));
        synchronize.setBackground(new Color(220, 220, 220));
        desynchronize.setBackground(new Color(220, 220, 220));
        synchronize.setForeground(Color.BLACK);
        desynchronize.setForeground(Color.BLACK);
        synchronize.setFocusable(false);
        desynchronize.setFocusable(false);

        //panel.add(desynchronize, BorderLayout.SOUTH);
        //panel.add(synchronize, BorderLayout.NORTH);
        panel.add(centralPanel, BorderLayout.CENTER);
        getContentPane().add(panel);
        /*
        synchronize.addActionListener((ActionEvent e) -> {
            for (int i = 0; i < numberTrafficLight; i++) {
                listTrafficLight.get(i).changeIcon();
                listTrafficLight.get(i).setSeconds(1);
                System.out.println("The Time of the " + listTrafficLight.get(i).getName() + " was changed to "
                        + listTrafficLight.get(i).getSeconds() + " second(s)");
                panel.updateUI();
            }
        });

        desynchronize.addActionListener((ActionEvent e) -> {
            for (int i = 0; i < numberTrafficLight; i++) {
                listTrafficLight.get(i).changeIcon();
                listTrafficLight.get(i).setSeconds(new Random().nextInt(20) + 1);
                System.out.println("The Time of the " + listTrafficLight.get(i).getName() + " was changed to "
                        + listTrafficLight.get(i).getSeconds() + " second(s)");
                panel.updateUI();
            }
        });*/

        loadTrafficLight(centralPanel);

        pack();
        setSize(700, 600);
        setLocation(350, 100);
        setVisible(true);
        starttrafficLight();
    }

    public TrafficLightManager(int numberTrafficLight) {
        this.numberTrafficLight = numberTrafficLight;
        listTrafficLight = new ArrayList();
        threads = new ArrayList();
    }

    public static void main(String[] args) {
        TrafficLightManager trafficLightManager = new TrafficLightManager(4);
        trafficLightManager.createLayout();
    }
}
