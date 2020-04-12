package cs3700;

import akka.actor.UntypedAbstractActor;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SieveActor extends AbstractBehavior<SieveActor.Command> {

    static ArrayList<Boolean> prime;
    static Incrementer incrementer;

    @Override
    public akka.actor.typed.javadsl.Receive<SieveActor.Command> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals(SieveActor.Run.INSTANCE, this::onRun)
                .onMessage(SieveActor.Load.class, this::onLoad)
                .onMessageEquals(SieveActor.GracefulShutdown.INSTANCE, this::onGracefulShutdown)
                .build();
    }

    interface Command{}

    private SieveActor(akka.actor.typed.javadsl.ActorContext<SieveActor.Command> context) {
        super(context);
    }

    public static Behavior<SieveActor.Command> create() {
        return Behaviors.setup(context -> new SieveActor(context));
    }

    public enum Run implements SieveActor.Command {
        INSTANCE
    }
    public enum GracefulShutdown implements SieveActor.Command {
        INSTANCE
    }

    private Behavior<SieveActor.Command> onRun() {
        int p = incrementer.getCounter();
        while(p*p <= incrementer.n) {
            if(prime.get(p) == true) {
                for(int i = p*p; i < incrementer.n; i += p) {
                    prime.set(i, false);
                }
            }
            incrementer.counter = incrementer.counter +1;
            p = incrementer.counter +1;
        }
        return this;
    }

    public static class Load implements Command {
        private ArrayList<Boolean> prime = new ArrayList<Boolean>();
        private Incrementer incrementer = new Incrementer();

        public Load(ArrayList<Boolean> prime, Incrementer incrementer){
            this.prime = prime;
            this.incrementer = incrementer;
        }
    }

    private Behavior<SieveActor.Command> onLoad(Load command){
        prime = command.prime;
        incrementer = command.incrementer;
        return this;
    }

    private Behavior<SieveActor.Command> onGracefulShutdown() {
        getContext().getSystem().log().info("Initiating graceful shutdown...");
        return Behaviors.stopped(() -> getContext().getSystem().log().info("Cleanup!"));
    }
}
