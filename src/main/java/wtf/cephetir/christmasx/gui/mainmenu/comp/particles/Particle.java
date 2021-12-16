package wtf.cephetir.christmasx.gui.mainmenu.comp.particles;

import net.minecraft.util.MathHelper;
import wtf.cephetir.christmasx.utils.RenderUtils;

import java.util.Random;

public class Particle {

    public int x;
    public int y;
    public int k;
    public ParticleGenerator pg;
    public boolean reset;
    public float size;
    private final Random random = new Random();
    public int speedX, speedY;

    public Particle(int x, int y, int speedX, int speedY, ParticleGenerator generator) {
        this.x = x;
        this.y = y;
        this.size = genRandom(0.7F, 0.8F);
        this.pg = generator;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void draw() {
        //Reset
        if (x >= pg.breite) {
            x = -1;
            reset = true;
        }

        if (y >= pg.höhe) {
            y = -1;
            reset = true;
        }

        this.x += random.nextInt(speedX);
        this.y += random.nextInt(speedY);

        int xx = (int) (MathHelper.cos(0.1F * (this.x + this.k)) * 10.0F);
        RenderUtils.drawBorderedCircle(this.x + xx, this.y, this.size, 0, 0xffFFFFFF);
    }


    public float genRandom(float min, float max) {
        return (float) (min + Math.random() * (max - min + 1.0F));
    }
}