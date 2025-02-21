package com.elvistezen.grupolucky.ui.gallery;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class StarSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private StarThread starThread;
    private ArrayList<Star> stars = new ArrayList<>();
    private Random random = new Random();

    public StarSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        // No inicializamos aquí las estrellas
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        initStars();  // Ahora se llama cuando la superficie está creada y tiene dimensiones válidas
        starThread = new StarThread(getHolder(), this);
        starThread.setRunning(true);
        starThread.start();
    }

    private void initStars() {
        // Crear estrellas iniciales solo cuando el ancho y alto estén disponibles
        stars.clear();  // Limpiar las estrellas previas si el surface se vuelve a crear
        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < 100; i++) {
            stars.add(new Star(random.nextInt(width), random.nextInt(height), random.nextInt(5) + 2));
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        starThread.setRunning(false);
        while (retry) {
            try {
                starThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Clase para representar una estrella
    private class Star {
        int x, y, size, speed;

        Star(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.speed = random.nextInt(5) + 1; // Velocidad aleatoria
        }

        void move() {
            y += speed;
            if (y > getHeight()) {
                y = 0;
                x = random.nextInt(getWidth());
            }
        }
    }

    // Hilo de animación
    private class StarThread extends Thread {
        private SurfaceHolder surfaceHolder;
        private StarSurfaceView starSurfaceView;
        private boolean running;

        public StarThread(SurfaceHolder surfaceHolder, StarSurfaceView starSurfaceView) {
            this.surfaceHolder = surfaceHolder;
            this.starSurfaceView = starSurfaceView;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            while (running) {
                Canvas canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        starSurfaceView.drawStars(canvas); // Llamar al método para dibujar las estrellas
                    }
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }

    // Método para dibujar las estrellas
    public void drawStars(Canvas canvas) {
        if (canvas != null) {
            canvas.drawColor(Color.BLACK); // Fondo del universo
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);

            // Dibujar y mover estrellas
            for (Star star : stars) {
                canvas.drawCircle(star.x, star.y, star.size, paint);
                star.move();
            }
        }
    }
}
