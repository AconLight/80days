package com.mygdx.backgroundProcess;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Created by Wojciech on 2020-08-25.
 */
public class BackgroundProcess {

    private static ArrayList<Function> tasks;

    public static void createBackgroundProcess() {
        tasks = new ArrayList<>();
    }

    public static void addTask(Function task) {
        tasks.add(task);
    }

    public static void executeOne() {
        long time = System.currentTimeMillis();
        while (tasks.size() > 0 && System.currentTimeMillis() - time < 30) {
            Gdx.app.log("BackgroundProcess", "executeOne " + tasks.size());
            Function task = tasks.get(0);
            tasks.remove(0);
            task.apply(new Object());
        }
    }
}
