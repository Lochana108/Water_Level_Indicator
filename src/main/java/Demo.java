import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

class WaterLevelObserver extends JFrame {
    WaterLevelObserver(String title){
        super(title);
        setSize(300,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
    }
    public void update(int waterLevel) {

    }
}

class Alarm extends WaterLevelObserver {
    private JLabel lblAlarm;
    Alarm(){
        super("Alarm Window");
        lblAlarm = new JLabel("");
        lblAlarm.setFont(new Font("",1,25));
        add(lblAlarm);
        setVisible(true);
    }
    public void update(int waterLevel) {
        lblAlarm.setText(waterLevel>=50 ? "ON":"OFF");
    }
}

class Splitter extends WaterLevelObserver {
    private JLabel lblSplitter;
    Splitter(){
        super("Splitter Window");
        lblSplitter = new JLabel("");
        lblSplitter.setFont(new Font("",1,25));
        add(lblSplitter);
        setVisible(true);
    }
    public void update(int waterLevel) {
        lblSplitter.setText(waterLevel>=50?"Splitter ON":"Splitter OFF");
    }
}

class Display extends WaterLevelObserver {
    private JLabel lblDisplay;
    Display(){
        super("Display Window");
        lblDisplay = new JLabel("");
        lblDisplay.setFont(new Font("",1,25));
        add(lblDisplay);
        setVisible(true);
    }
    public void update(int waterLevel) {
        lblDisplay.setText("Water Level is : "+waterLevel);
    }
}

class SMSSender extends WaterLevelObserver {
    private JLabel SMSLable;
    SMSSender(){
        super("SMS Window");
        SMSLable = new JLabel("");
        SMSLable.setFont(new Font("",1,25));
        add(SMSLable);
        setVisible(true);
    }
    public void update(int waterLevel) {
        SMSLable.setText("Sending : "+waterLevel);
    }
}

class WaterLevelObserverble {
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
    private WaterLevelObserverble waterLevelObserverble;

    WaterTank(){
        setSize(300,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        waterLevelObserverble =new WaterLevelObserverble();
        waterLevelObserverble.addWaterLevelObserver(new Alarm());
        waterLevelObserverble.addWaterLevelObserver(new Splitter());
        waterLevelObserverble.addWaterLevelObserver(new Display());
        waterLevelObserverble.addWaterLevelObserver(new SMSSender());

        waterLevelSlider = new JSlider(JSlider.VERTICAL,0,100,50);
        waterLevelSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int waterLevel = waterLevelSlider.getValue();
                waterLevelObserverble.setWaterLevel(waterLevel);
            }
        });
        add(waterLevelSlider);
    }

}
public class Demo {
    public static void main(String[] args) {
        new WaterTank().setVisible(true);


    }
}
