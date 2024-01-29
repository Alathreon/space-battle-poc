package com.alathreon.spacebattle.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;

public class GameLoop {
    private volatile boolean running;
    private final Supplier<Double> widthSupplier;
    private final Supplier<Double> heightSupplier;
    private final Supplier<GraphicsContext> ctxSupplier;
    private final List<Spaceship> spaceships;

    public GameLoop(Supplier<Double> widthSupplier, Supplier<Double> heightSupplier, Supplier<GraphicsContext> ctxSupplier) {
        this.widthSupplier = widthSupplier;
        this.heightSupplier = heightSupplier;
        this.ctxSupplier = ctxSupplier;
        this.spaceships = new CopyOnWriteArrayList<>();
    }

    public synchronized void start() {
        if(running) throw new IllegalStateException();
        Thread thread = new Thread(this::run);
        thread.setDaemon(true);
        thread.start();
        running = true;
    }
    public  void stop() {
        running = false;
    }
    public void spawn(Player player, double x, double y) {
        Spaceship spaceship = new Spaceship(player, x, y);
        spaceships.add(spaceship);
    }
    private void run() {
        long lastTick = System.nanoTime();
        while(running) {
            long now = System.nanoTime();
            if(Math.abs(lastTick - now) > 16_000_000) {
                tick();
                render();
                lastTick = now;
            }
        }
    }
    private void tick() {
        for(Spaceship spaceship : spaceships) {
            spaceship.tick();
        }
    }
    private void render() {
        GraphicsContext ctx = ctxSupplier.get();
        double width = widthSupplier.get();
        double height = heightSupplier.get();
        ctx.clearRect(0, 0, width, height);
        for(Spaceship spaceship : spaceships) {
            ctx.setFill(Color.BLUE);
            ctx.fillOval(spaceship.x() % width -5, spaceship.y() % height -5, 10, 10);
        }
    }
}
