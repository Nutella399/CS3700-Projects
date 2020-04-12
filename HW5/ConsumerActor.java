package cs3700;


import akka.actor.UntypedAbstractActor;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.util.LinkedList;
import java.util.Queue;

public class ConsumerActor extends AbstractBehavior<ConsumerActor.Command> {

    static Queue<Integer> queue;

    @Override
    public akka.actor.typed.javadsl.Receive<ConsumerActor.Command> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals(ConsumerActor.Run.INSTANCE, this::onRun)
                .onMessage(ConsumerActor.Load.class, this::onLoad)
                .onMessageEquals(ConsumerActor.GracefulShutdown.INSTANCE, this::onGracefulShutdown)
                .build();
    }

    interface Command{}

    private ConsumerActor(akka.actor.typed.javadsl.ActorContext<ConsumerActor.Command> context) {
        super(context);
    }

    public static Behavior<ConsumerActor.Command> create() {
        return Behaviors.setup(context -> new ConsumerActor(context));
    }

    public enum Run implements ConsumerActor.Command {
        INSTANCE
    }
    public enum GracefulShutdown implements ConsumerActor.Command {
        INSTANCE
    }

    private Behavior<ConsumerActor.Command> onRun() {
        while(!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        return this;
    }

    public static class Load implements ConsumerActor.Command {
        private Queue<Integer> queue = new LinkedList<Integer>();
        public Load(Queue<Integer> queue){
            this.queue = queue;
        }
    }

    private Behavior<ConsumerActor.Command> onLoad(ConsumerActor.Load command){
        queue = command.queue;
        return this;
    }

    private Behavior<ConsumerActor.Command> onGracefulShutdown() {
        getContext().getSystem().log().info("Initiating graceful shutdown...");
        return Behaviors.stopped(() -> getContext().getSystem().log().info("Cleanup!"));
    }
}
