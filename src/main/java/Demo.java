import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

interface WaterLevelObserver {
    public void update(int waterLevel);
}

class Alarm extends JFrame implements WaterLevelObserver {
    private JLabel lblAlarm;

    Alarm() {
        super("Alarm Window");
        setSize(300, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        lblAlarm = new JLabel("");
        lblAlarm.setFont(new Font("", 1, 25));
        add(lblAlarm);
        setVisible(true);
    }

    public void update(int waterLevel) {
        lblAlarm.setText(waterLevel >= 50 ? "ON" : "OFF");
    }
}

class Splitter extends JFrame implements WaterLevelObserver {
    private JLabel lblSplitter;

    Splitter() {
        super("Splitter Window");
        setSize(300, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        lblSplitter = new JLabel("");
        lblSplitter.setFont(new Font("", 1, 25));
        add(lblSplitter);
        setVisible(true);
    }

    public void update(int waterLevel) {
        lblSplitter.setText(waterLevel >= 50 ? "Splitter ON" : "Splitter OFF");
    }
}

class Display extends JFrame implements WaterLevelObserver {
    private JLabel lblDisplay;

    Display() {
        super("Display Window");
        setSize(300, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        lblDisplay = new JLabel("");
        lblDisplay.setFont(new Font("", 1, 25));
        add(lblDisplay);
        setVisible(true);
    }

    public void update(int waterLevel) {
        lblDisplay.setText("Water Level is : " + waterLevel);
    }
}

class SMSSender extends JFrame implements WaterLevelObserver {
    private JLabel SMSLable;

    SMSSender() {
        super("SMS Window");
        setSize(300, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        SMSLable = new JLabel("");
        SMSLable.setFont(new Font("", 1, 25));
        add(SMSLable);
        setVisible(true);
    }

    public void update(int waterLevel) {
        SMSLable.setText("Sending : " + waterLevel);
    }
}

interface WaterLevelObserverbleInterface {
    public void setWaterLevel(int waterLevel);
}

class WaterLevelObserverble implements WaterLevelObserverbleInterface {
    private int waterLevel;

    private ArrayList<WaterLevelObserver> observerList = new ArrayList<>();

    public void addWaterLevelObserver(WaterLevelObserver ob) {
        observerList.add(ob);
    }

    public void setWaterLevel(int waterLevel) {
        if (this.waterLevel != waterLevel) {
            this.waterLevel = waterLevel;
            notifyObject();
        }
    }

    public void notifyObject() {
        for (WaterLevelObserver ob : observerList) {
            ob.update(waterLevel);
        }
    }
}

class WaterTank extends JFrame {
    private JSlider waterLevelSlider;
    private WaterLevelObserverbleInterface waterLevelObserverbleInterface;

    WaterTank(WaterLevelObserverbleInterface waterLevelObserverbleInterface) {
        this.waterLevelObserverbleInterface = waterLevelObserverbleInterface;
        setTitle("Water Tank");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        waterLevelSlider = new JSlider(JSlider.VERTICAL, 0, 100, 50);
        waterLevelSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int waterLevel = waterLevelSlider.getValue();
                waterLevelObserverbleInterface.setWaterLevel(waterLevel);
            }
        });
        add(waterLevelSlider);
    }
}

public class Demo {
    public static void main(String[] args) {
        WaterLevelObserverble waterLevelObserverble = new WaterLevelObserverble();
        waterLevelObserverble.addWaterLevelObserver(new Alarm());
        waterLevelObserverble.addWaterLevelObserver(new Splitter());
        waterLevelObserverble.addWaterLevelObserver(new Display());
        waterLevelObserverble.addWaterLevelObserver(new SMSSender());

        new WaterTank(waterLevelObserverble).setVisible(true);

    }
}
