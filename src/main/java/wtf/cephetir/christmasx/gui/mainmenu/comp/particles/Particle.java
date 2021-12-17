package wtf.cephetir.christmasx.gui.mainmenu.comp.particles;

import net.minecraft.util.MathHelper;
import wtf.cephetir.christmasx.ChristmasX;
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

    public Particle(int x, int y, ParticleGenerator generator) {
        this.x = x;
        this.y = y;
        this.size = genRandom(0.7F, 0.8F);
        this.pg = generator;
    }

    public void draw() {
        if (ChristmasX.getInstance().config.speedX == 0 && ChristmasX.getInstance().config.speedY == 0) {
            if (x >= pg.breite) {
                x = -1;
                reset = true;
            }

            if (y >= pg.höhe) {
                y = -1;
                reset = true;
            }
        } else if (ChristmasX.getInstance().config.speedX == 0) {

            if (x >= pg.breite) {
                x = -1;
                reset = true;
            }

            if (y >= pg.höhe) {
                y = -1;
                reset = true;
            }

            this.y += random.nextInt(ChristmasX.getInstance().config.speedY);
        } else if (ChristmasX.getInstance().config.speedY == 0) {

            if (x >= pg.breite) {
                x = -1;
                reset = true;
            }

            if (y >= pg.höhe) {
                y = -1;
                reset = true;
            }

            this.x += random.nextInt(ChristmasX.getInstance().config.speedX);
        } else if (ChristmasX.getInstance().config.speedX >= 1) {
            if (ChristmasX.getInstance().config.speedY >= 1) {
                if (x >= pg.breite) {
                    x = -1;
                    reset = true;
                }

                if (y >= pg.höhe) {
                    y = -1;
                    reset = true;
                }

                this.x += random.nextInt(ChristmasX.getInstance().config.speedX);
                this.y += random.nextInt(ChristmasX.getInstance().config.speedY);
            } else {
                if (x >= pg.breite) {
                    x = -1;
                    reset = true;
                }

                if (y <= -1) {
                    y = pg.höhe;
                    reset = true;
                }

                this.x += random.nextInt(ChristmasX.getInstance().config.speedX);
                this.y -= random.nextInt(ChristmasX.getInstance().config.speedY * -1);
            }
        } else if (ChristmasX.getInstance().config.speedY >= 1) {
            if (x <= -1) {
                x = pg.breite;
                reset = true;
            }

            if (y >= pg.höhe) {
                y = -1;
                reset = true;
            }

            this.x -= random.nextInt(ChristmasX.getInstance().config.speedX * -1);
            this.y += random.nextInt(ChristmasX.getInstance().config.speedY);
        } else {
            if (x <= -1) {
                x = pg.breite;
                reset = true;
            }

            if (y <= -1) {
                y = pg.höhe;
                reset = true;
            }

            this.x -= random.nextInt(ChristmasX.getInstance().config.speedX * -1);
            this.y -= random.nextInt(ChristmasX.getInstance().config.speedY * -1);
        }

        int xx = (int) (MathHelper.cos(0.1F * (this.x + this.k)) * 10.0F);
        RenderUtils.drawBorderedCircle(this.x + xx, this.y, this.size, 0, 0xffFFFFFF);
    }


    public float genRandom(float min, float max) {
        return (float) (min + Math.random() * (max - min + 1.0F));
    }
}