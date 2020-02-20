package Lesson_5;


import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

public class Race {
    private ArrayBlockingQueue<String> stages; 
    public ArrayBlockingQueue<String> getStages(){return stages; }
    public Race(Stage... stages){
        this.stages = new ArrayBlockingQueue<>(Arrays.asList(stages));
    }
}
