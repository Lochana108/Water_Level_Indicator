import java.util.Random;

class Alarm {
    public void operateAlarm(int waterLevel) {
        System.out.println(waterLevel > 50 ? "Alarm On" : "Alarm OFF");
    }
}
class Splitter {
    public void split(int waterLevel) {
        System.out.println(waterLevel > 50 ? "Splitter On" : "Splitter OFF");
    }
}

class Display {
    public void display(int waterLevel) {
        System.out.println("Water Level : " + waterLevel);
    }
}

class SMSSender {
    public void SMSsending(int waterLevel) {
        System.out.println("sending : " + waterLevel);
    }
}

class ControlRoom {
    private int waterLevel;
    private Alarm alarm;
    private Display display;
    private SMSSender smsSender;
    private Splitter splitter;

    public void addAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public void addDisplay(Display display) {
        this.display = display;
    }

    public void addSMSsender(SMSSender smsSender) {
        this.smsSender = smsSender;
    }
    public void addSplitter(Splitter splitter) {
        this.splitter = splitter;
    }

    public void setWaterLevel(int waterLevel) {
        if (this.waterLevel != waterLevel) {
            this.waterLevel = waterLevel;
            notifyObject();
        }
    }

    public void notifyObject() {
        alarm.operateAlarm((waterLevel));
        display.display(waterLevel);
        smsSender.SMSsending(waterLevel);
        splitter.split(waterLevel);
    }
}

public class Demo {
    public static void main(String[] args) {
        ControlRoom conRoom = new ControlRoom();
        conRoom.addAlarm(new Alarm());
        conRoom.addDisplay(new Display());
        conRoom.addSMSsender(new SMSSender());
        conRoom.addSplitter(new Splitter());

        Random r = new Random();
        while (true) {
            int waterLevel = r.nextInt(101);
            conRoom.setWaterLevel(waterLevel);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }
}
