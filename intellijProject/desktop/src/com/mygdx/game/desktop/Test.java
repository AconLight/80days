package com.mygdx.game.desktop;

/**
 * Created by Wojciech on 2020-08-25.
 */
public class Test {

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            public void run() {
                for (int i = 0; i < 1000000000; i++) {
                    if(i % 100000000 == 0) {
                        System.out.println("elo");

                    }
                }
            }
        };

        new Thread(r).start();

        for (int i = 0; i < 1000000000; i++) {
            if(i % 100000000 == 0) {
                System.out.println("dupa");

            }
        }
    }
}
