package cs3700;

import akka.actor.typed.Behavior;
import akka.actor.typed.delivery.ProducerController;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import akka.actor.typed.javadsl.ActorContext;
import akka.io.Dns;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerActor extends AbstractBehavior<ProducerActor.Command> {

    static Queue<Integer> queue;

    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals(Run.INSTANCE, this::onRun)
                .onMessage(Load.class, this::onLoad)
                .onMessageEquals(GracefulShutdown.INSTANCE, this::onGracefulShutdown)
                .build();
    }

    interface Command{}

    private ProducerActor(ActorContext<Command> context) {
        super(context);
    }

    public static Behavior<Command> create() {
        return Behaviors.setup(context -> new ProducerActor(context));
    }

    public enum Run implements Command {
        INSTANCE
    }
    public enum GracefulShutdown implements Command{
        INSTANCE
    }

    private Behavior<ProducerActor.Command> onRun() {
        for (int i = 1; i <= 100; i++) {
            queue.add(i);
        }
        return this;
    }

    public static class Load implements Command {
        private Queue<Integer> queue = new LinkedList<Integer>();
        public Load(Queue<Integer> queue){
            this.queue = queue;
        }
    }

    private Behavior<Command> onLoad(Load command){
        queue = command.queue;
        return this;
    }

    private Behavior<Command> onGracefulShutdown() {
        getContext().getSystem().log().info("Initiating graceful shutdown...");
        return Behaviors.stopped(() -> getContext().getSystem().log().info("Cleanup!"));
    }


}
