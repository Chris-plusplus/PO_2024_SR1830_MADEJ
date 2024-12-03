package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private List<Simulation> simulations;
    private List<Thread> threads = new ArrayList<>();
    private static final int THREAD_POOL_SIZE = 4;
    private ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    private static final long TERMINATION_AWAIT_TIME = 10;
    private static final TimeUnit TERMINATION_AWAIT_TIME_UNIT = TimeUnit.SECONDS;


    public SimulationEngine(List<Simulation> simulations){
        this.simulations = List.copyOf(simulations);
    }

    public void runSync() {
        for(var simulation : simulations){
            simulation.run();
        }
    }

    public void runAsync() {
        threads.clear();
        for(var simulation : simulations){
            threads.add(new Thread(simulation));
        }
        for(var thread : threads){
            thread.start();
        }
    }

    public void runAsyncInThreadPool() {
        for(var simulation : simulations){
            threadPool.submit(simulation);
        }
    }

    public void awaitSimulationsEnd() throws InterruptedException {
        for(var thread : threads){
            thread.join();
        }

        threadPool.shutdown();
        if(!threadPool.awaitTermination(TERMINATION_AWAIT_TIME, TERMINATION_AWAIT_TIME_UNIT)) {
            threadPool.shutdownNow();
            threadPool.awaitTermination(1, TERMINATION_AWAIT_TIME_UNIT);
        }
    }
}
