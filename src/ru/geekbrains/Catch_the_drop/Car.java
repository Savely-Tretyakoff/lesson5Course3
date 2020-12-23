package ru.geekbrains.Catch_the_drop;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;


class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier cb;
    private CountDownLatch cd1;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier cb, CountDownLatch cd1) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cb = cb;
        this.cd1 = cd1;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            cb.await();
            cb.await();
            for (int j = 0; j < race.getStages().size(); j++) {
                race.getStages().get(j).go(this);
            }

            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        }
    }

