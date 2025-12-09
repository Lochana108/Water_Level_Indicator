import java.util.Random;

class WaterLevelObserver {
    public void update(int waterLevel) {

    }
}

class Alarm extends WaterLevelObserver {
    public void update(int waterLevel) {
        System.out.println(waterLevel > 50 ? "Alarm On" : "Alarm OFF");
    }
}

class Splitter extends WaterLevelObserver {
    public void update(int waterLevel) {
        System.out.println(waterLevel > 50 ? "Splitter On" : "Splitter OFF");
    }
}

class Display extends WaterLevelObserver {
    public void update(int waterLevel) {
        System.out.println("Water Level : " + waterLevel);
    }
}

class SMSSender extends WaterLevelObserver {
    public void update(int waterLevel) {
        System.out.println("sending : " + waterLevel);
    }
}

class ControlRoom {
    private int waterLevel;

    private WaterLevelObserver[] observerArray = new WaterLevelObserver[100];
    private int nextIndex;

    public void addWaterLevelObserver(WaterLevelObserver ob) {
        observerArray[nextIndex++] = ob;
    }

    public void setWaterLevel(int waterLevel) {
        if (this.waterLevel != waterLevel) {
            this.waterLevel = waterLevel;
            notifyObject();
        }
    }

    public void notifyObject() {
        for (int i = 0; i < nextIndex; i++) {
            observerArray[i].update(waterLevel);
        }
    }
}

public class Demo {
    public static void main(String[] args) {
        ControlRoom conRoom = new ControlRoom();
        conRoom.addWaterLevelObserver(new Alarm());
        conRoom.addWaterLevelObserver(new Splitter());
        conRoom.addWaterLevelObserver(new Display());
        conRoom.addWaterLevelObserver(new SMSSender());

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
