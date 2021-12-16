package wtf.cephetir.christmasx.gui.mainmenu.comp.particles;

import java.util.ArrayList;
import java.util.Random;

public class ParticleGenerator {

    public int anzahl;
    public int breite;
    public int höhe;
    public int speedX, speedY;

    public ArrayList<Particle> particles = new ArrayList<>();
    private Random random = new Random();

    public ParticleGenerator(int anzahl, int breite, int höhe, int speedX, int speedY) {
        this.anzahl = anzahl;
        this.breite = breite;
        this.höhe = höhe;
        this.speedX = speedX;
        this.speedY = speedY;
        for (int i = 0; i < anzahl; i++)
            this.particles.add(new Particle(this.random.nextInt(breite), this.random.nextInt(höhe), speedX, speedY, this));
    }

    public void drawParticles() {
        for (Particle p : this.particles) p.draw();
    }

    public void changeSpeed(int speedX, int speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
        for (Particle particle : particles) {
            particle.speedX = speedX;
            particle.speedY = speedY;
        }
    }
}