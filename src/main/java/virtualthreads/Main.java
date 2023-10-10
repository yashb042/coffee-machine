//package virtualthreads;
//
//import java.io.PrintStream;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
//public class Main {
//
//    private static final PrintStream out = System.out;
//
//    public static void main(String[] args) throws Exception {
//        Main main = new Main();
//        main.runVirtual();
//    }
//
//
//    void doParallel() throws Exception {
//
//        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
//            var callables = new ArrayList<Callable<String>>();
//            for (int i = 0; i < 3; i++) {
//                callables.add(this::callRemote);
//            }
//
//            List<String> responses = new ArrayList<>();
//            for (Future<String> future : executor.invokeAll(callables)) {
//                responses.add(future.resultNow());
//            }
//
//            responses.forEach(System.out::println);
//        }
//    }
//
//    private String callRemote() throws InterruptedException {
//        System.out.println(Thread.currentThread());
//        Thread.sleep(Duration.ofSeconds(2));
//        double random = Math.random();
//        return "" + random;
//    }
//
//    void runConventional() throws Exception {
//        for (; ; ) {
//            long start = System.currentTimeMillis();
//            try (var executor = Executors.newFixedThreadPool(1000)) {
//                for (int i = 0; i < 100_000_0; i++) {
//                    executor.submit(() -> {
//                        callExternalService();
//                        return null;
//                    });
//                }
//            }
//            long end = System.currentTimeMillis();
//            System.out.println(end - start + " ms");
//        }
//    }
//
//    void runVirtual() throws Exception {
//        for (; ; ) {
//            long start = System.currentTimeMillis();
//            try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
//                for (int i = 0; i < 100_000_0; i++) {
//                    executor.submit(() -> {
//                        callExternalService();
//                        return null;
//                    });
//                }
//            }
//            long end = System.currentTimeMillis();
//            System.out.println(end - start + " ms");
//        }
//    }
//
//    private void callExternalService() throws InterruptedException {
//        Thread.sleep(Duration.ofMillis(1));
//    }
//}
//
//
///*
///Library/Java/JavaVirtualMachines/jdk-19.jdk/Contents/Home/bin/java Main.java
// */