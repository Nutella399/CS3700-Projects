package cs3700;

import akka.actor.typed.ActorSystem;

import java.util.ArrayList;

public class Sieve {

    public Sieve() {

    }

    public void solve(int n) {
        boolean prime[] = new boolean[n+1];
        for(int i = 0; i < n; i++) {
            prime[i] = true;
        }

        for(int p  = 2; p*p <= n; p++) {
            if(prime[p] == true) {
                for(int i = p*p; i < n; i +=p) {
                    prime[i] = false;
                }
            }
        }

        for(int i = 2; i <= n; i++) {
            if(prime[i] == true) {
                System.out.println(i + " ");
            }
        }
    }

    public void solveWithActor(int n) {
        boolean done = false;
        ActorSystem<SieveActor.Command> Sieve = ActorSystem.create(SieveActor.create(), "SieveSystem");
        ArrayList<Boolean> prime = new ArrayList<Boolean>();
        Incrementer incrementer = new Incrementer(n);

        for(int i = 0; i < n; i++) {
            prime.add(i, true);
        }
        int p = incrementer.getCounter();
        int q = p*p;
        Sieve.tell(new SieveActor.Load(prime, incrementer));
        for(int i = 0; i < 4; i++) {
            Sieve.tell(SieveActor.Run.INSTANCE);
        }
       while(q < n) {
            p = incrementer.getCounter();
            q = p*p;
            System.out.print("");
        }
        Sieve.tell(SieveActor.GracefulShutdown.INSTANCE);

        for(int i = 2; i < n; i++) {
            if(prime.get(i) == true) {
                System.out.println(i + " ");
            }
        }



    }
}
