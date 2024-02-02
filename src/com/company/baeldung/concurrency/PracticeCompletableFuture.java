package com.company.baeldung.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PracticeCompletableFuture {
    public static Future<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hi");
            return null;
        });
        return completableFuture;
    }

    public static Future<Integer> compute() {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        Executors.newSingleThreadExecutor().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete(5);
            return null;
        });
        return completableFuture;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<String> completableFuture = calculateAsync();
        System.out.println(completableFuture.get());

        Future<String> completableFuture1 = CompletableFuture.completedFuture("Hello");
        System.out.println(completableFuture1.get());

        CompletableFuture<String> futureGood = CompletableFuture.supplyAsync(() -> "Good");
        System.out.println(futureGood.get());

        CompletableFuture<String> futureMorning = futureGood.thenApply(s -> s+ " morning");
        System.out.println(futureMorning.get());

        CompletableFuture<Void> future = futureMorning.thenAccept(s -> System.out.println(s + "!"));
        future.get();

        CompletableFuture<Void> futureFinish = future.thenRun(() -> System.out.println("Finish."));
        futureFinish.get();

        CompletableFuture<String> combiningFutures = CompletableFuture.supplyAsync(() -> "Hi").thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "!!!"));
        System.out.println(combiningFutures.get());

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);
        System.out.println(completableFuture2.get());

        CompletableFuture<Void> future1 = CompletableFuture.supplyAsync(() -> "good")
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " day!"),
                        (s1, s2) -> System.out.println(s1 + s2));

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> beautiful = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> "World");

        //CompletableFuture<Void> combineFuture = CompletableFuture.allOf(hello, beautiful, world);
        String combined = Stream.of(hello, beautiful, world).map(CompletableFuture::join).collect(Collectors.joining(" "));
        System.out.println(combined);

        String name = null;
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Error");
            }
            return "Hello, " + name;
        }).handle((s, t) -> s != null ? s: "Hello, Stranger!");
        System.out.println(stringCompletableFuture.get());

        /*CompletableFuture<String> completableFuture3 = new CompletableFuture<>();
        completableFuture3.completeExceptionally(new RuntimeException("Error!"));
        completableFuture3.get();*/

        CompletableFuture<String> completableFuture4 = CompletableFuture.supplyAsync(() -> "Bye");
        CompletableFuture<String> completableFuture5 = completableFuture4.thenApplyAsync(s -> s + " bye");
        System.out.println(completableFuture5.get());

    }
}
