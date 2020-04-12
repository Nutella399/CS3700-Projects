package cs3700;

import akka.actor.typed.ActorSystem;
import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        LinkedList<String> list = new LinkedList<String>();
        ReentrantLock lock = new ReentrantLock();
        Condition con = lock.newCondition();
        ItemIsolation itemi = new ItemIsolation();
        ItemAtomic itema = new ItemAtomic();
        /*
        Thread plOne = new Thread(new ProducerLock(list, lock, con, "p1"));
        Thread plTwo = new Thread(new ProducerLock(list, lock, con, "p2"));
        Thread plThree = new Thread(new ProducerLock(list, lock, con, "p3"));
        Thread plFour = new Thread(new ProducerLock(list, lock, con, "p4"));
        Thread plFive = new Thread(new ProducerLock(list, lock, con, "p5"));
        Thread clOne = new Thread(new ConsumerLock(list, lock, con, "c1"));
        Thread clTwo = new Thread(new ConsumerLock(list, lock, con, "c2"));

        long startTimeL = System.currentTimeMillis();
        plOne.start();
        plTwo.start();
        plThree.start();
        plFour.start();
        plFive.start();
        clOne.start();
        clTwo.start();
        clTwo.join();
        System.out.println("End Time Lock A: " + (System.currentTimeMillis() - startTimeL));

        Thread ploOne = new Thread(new ProducerLock(list, lock, con, "p1"));
        Thread ploTwo = new Thread(new ProducerLock(list, lock, con, "p2"));
        Thread cloOne = new Thread(new ConsumerLock(list, lock, con, "c1"));
        Thread cloTwo = new Thread(new ConsumerLock(list, lock, con, "c2"));
        Thread cloThree = new Thread(new ConsumerLock(list, lock, con, "c3"));
        Thread cloFour = new Thread(new ConsumerLock(list, lock, con, "c4"));
        Thread cloFive = new Thread(new ConsumerLock(list, lock, con, "c5"));

        long startTimeLo = System.currentTimeMillis();
        ploOne.start();
        ploTwo.start();
        cloOne.start();
        cloTwo.start();
        cloThree.start();
        cloFour.start();
        cloFive.start();
        cloFive.join();
        System.out.println("End Time Lock B: " + (System.currentTimeMillis() - startTimeLo));

        Thread piOne = new Thread(new ProducerIsolation(itemi, "p1"));
        Thread piTwo = new Thread(new ProducerIsolation(itemi, "p2"));
        Thread piThree = new Thread(new ProducerIsolation(itemi, "p3"));
        Thread piFour = new Thread(new ProducerIsolation(itemi, "p4"));
        Thread piFive = new Thread(new ProducerIsolation(itemi, "p5"));
        Thread ciOne = new Thread(new ConsumerIsolation(itemi, "c1"));
        Thread ciTwo = new Thread(new ConsumerIsolation(itemi, "c2"));

        long startTimeI = System.currentTimeMillis();
        piOne.start();
        piTwo.start();
        piThree.start();
        piFour.start();
        piFive.start();
        ciOne.start();
        ciTwo.start();
        ciTwo.join();
        System.out.println("End Time Isolation A: " + (System.currentTimeMillis() - startTimeI));

        Thread pisOne = new Thread(new ProducerIsolation(itemi, "p1"));
        Thread pisTwo = new Thread(new ProducerIsolation(itemi, "p2"));
        Thread cisOne = new Thread(new ConsumerIsolation(itemi, "c1"));
        Thread cisTwo = new Thread(new ConsumerIsolation(itemi, "c2"));
        Thread cisThree = new Thread(new ConsumerIsolation(itemi, "c3"));
        Thread cisFour = new Thread(new ConsumerIsolation(itemi, "c4"));
        Thread cisFive = new Thread(new ConsumerIsolation(itemi, "c5"));

        long startTimeIs = System.currentTimeMillis();
        pisOne.start();
        pisTwo.start();
        cisOne.start();
        cisTwo.start();
        cisThree.start();
        cisFour.start();
        cisFive.start();
        cisFive.join();
        System.out.println("End Time Isolation B: " + (System.currentTimeMillis() - startTimeIs));
        */
        Thread paOne = new Thread(new ProducerAtomic(itema, "p1"));
        Thread paTwo = new Thread(new ProducerAtomic(itema, "p2"));
        Thread paThree = new Thread(new ProducerAtomic(itema, "p3"));
        Thread paFour = new Thread(new ProducerAtomic(itema, "p4"));
        Thread paFive = new Thread(new ProducerAtomic(itema, "p5"));
        Thread caOne = new Thread(new ConsumerAtomic(itema, "c1"));
        Thread caTwo = new Thread(new ConsumerAtomic(itema, "c2"));

        long startTimeA = System.currentTimeMillis();
        paOne.start();
        paTwo.start();
        paThree.start();
        paFour.start();
        paFive.start();
        caOne.start();
        caTwo.start();
        caTwo.join();
        System.out.println("End Time Atomic A: " + (System.currentTimeMillis() - startTimeA));

        Thread patOne = new Thread(new ProducerAtomic(itema, "p1"));
        Thread patTwo = new Thread(new ProducerAtomic(itema, "p2"));
        Thread catOne = new Thread(new ConsumerAtomic(itema, "c1"));
        Thread catTwo = new Thread(new ConsumerAtomic(itema, "c2"));
        Thread catThree = new Thread(new ConsumerAtomic(itema, "c3"));
        Thread catFour = new Thread(new ConsumerAtomic(itema, "c4"));
        Thread catFive = new Thread(new ConsumerAtomic(itema, "c5"));

        long startTimeAt = System.currentTimeMillis();
        patOne.start();
        patTwo.start();
        catOne.start();
        catTwo.start();
        catThree.start();
        catFour.start();
        catFive.start();
        catFive.join();
        System.out.println("End Time Atomic B: " + (System.currentTimeMillis() - startTimeAt));


        /*
        Queue<Integer> queueA = new LinkedList<Integer>();
        long startTimeAc = System.currentTimeMillis();

        ActorSystem<ProducerActor.Command> Producers = ActorSystem.create(ProducerActor.create(), "ProducerSystem");
        ActorSystem<ConsumerActor.Command> Consumers = ActorSystem.create(ConsumerActor.create(), "ConsumerSystem");

        Producers.tell(new ProducerActor.Load(queueA));
        Producers.tell(ProducerActor.Run.INSTANCE);
        Producers.tell(ProducerActor.Run.INSTANCE);
        Producers.tell(ProducerActor.Run.INSTANCE);
        Producers.tell(ProducerActor.Run.INSTANCE);
        Producers.tell(ProducerActor.Run.INSTANCE);
        TimeUnit.SECONDS.sleep(10);
        Consumers.tell(new ConsumerActor.Load(queueA));
        Consumers.tell(ConsumerActor.Run.INSTANCE);
        Consumers.tell(ConsumerActor.Run.INSTANCE);
        while(!queueA.isEmpty()) {

        }
        if(queueA.isEmpty()) {
            Producers.tell(ProducerActor.GracefulShutdown.INSTANCE);
            Consumers.tell(ConsumerActor.GracefulShutdown.INSTANCE);
            System.out.println("End Time Actors A: " + (System.currentTimeMillis() - startTimeAc));
        }
        */

        ActorSystem<ProducerActor.Command> ProducersB = ActorSystem.create(ProducerActor.create(), "ProducerSystem");
        ActorSystem<ConsumerActor.Command> ConsumersB = ActorSystem.create(ConsumerActor.create(), "ConsumerSystem");
        Queue<Integer> queueB = new LinkedList<Integer>();
        long startTimeAct = System.currentTimeMillis();

        ProducersB.tell(new ProducerActor.Load(queueB));
        ProducersB.tell(ProducerActor.Run.INSTANCE);
        ProducersB.tell(ProducerActor.Run.INSTANCE);
        TimeUnit.SECONDS.sleep(10);
        ConsumersB.tell(new ConsumerActor.Load(queueB));
        ConsumersB.tell(ConsumerActor.Run.INSTANCE);
        ConsumersB.tell(ConsumerActor.Run.INSTANCE);
        ConsumersB.tell(ConsumerActor.Run.INSTANCE);
        ConsumersB.tell(ConsumerActor.Run.INSTANCE);
        ConsumersB.tell(ConsumerActor.Run.INSTANCE);
        while(!queueB.isEmpty()) {

        }
        if(queueB.isEmpty()) {
            ProducersB.tell(ProducerActor.GracefulShutdown.INSTANCE);
            ConsumersB.tell(ConsumerActor.GracefulShutdown.INSTANCE);
            System.out.println("End Time Actors B: " + (System.currentTimeMillis() - startTimeAct));
        }

    }
}