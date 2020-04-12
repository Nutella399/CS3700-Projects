package cs3700;

import akka.actor.typed.ActorSystem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Sieve sieve = new Sieve();
        long startTimeA = System.currentTimeMillis();
        sieve.solve(1000000);
        System.out.println("End Time without Actor: " + (System.currentTimeMillis() - startTimeA));



        long startTimeAct = System.currentTimeMillis();
        sieve.solveWithActor(1000000);
        System.out.println("End Time with Actor: " + (System.currentTimeMillis() - startTimeAct));

    }
}