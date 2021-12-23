package wtf.cephetir.christmasx.gui.mainmenu.comp.particles;

import net.minecraft.util.MathHelper;
import wtf.cephetir.christmasx.config.ChristmasConfig;
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
        if (ChristmasConfig.speedX == 0 && ChristmasConfig.speedY == 0) {
            if (x >= pg.breite) {
                x = -1;
                reset = true;
            }

            if (y >= pg.höhe) {
                y = -1;
                reset = true;
            }
        } else if (ChristmasConfig.speedX == 0) {

            if (x >= pg.breite) {
                x = -1;
                reset = true;
            }

            if (y >= pg.höhe) {
                y = -1;
                reset = true;
            }

            this.y += random.nextInt(ChristmasConfig.speedY);
        } else if (ChristmasConfig.speedY == 0) {

            if (x >= pg.breite) {
                x = -1;
                reset = true;
            }

            if (y >= pg.höhe) {
                y = -1;
                reset = true;
            }

            this.x += random.nextInt(ChristmasConfig.speedX);
        } else if (ChristmasConfig.speedX >= 1) {
            if (ChristmasConfig.speedY >= 1) {
                if (x >= pg.breite) {
                    x = -1;
                    reset = true;
                }

                if (y >= pg.höhe) {
                    y = -1;
                    reset = true;
                }

                this.x += random.nextInt(ChristmasConfig.speedX);
                this.y += random.nextInt(ChristmasConfig.speedY);
            } else {
                if (x >= pg.breite) {
                    x = -1;
                    reset = true;
                }

                if (y <= -1) {
                    y = pg.höhe;
                    reset = true;
                }

                this.x += random.nextInt(ChristmasConfig.speedX);
                this.y -= random.nextInt(ChristmasConfig.speedY * -1);
            }
        } else if (ChristmasConfig.speedY >= 1) {
            if (x <= -1) {
                x = pg.breite;
                reset = true;
            }

            if (y >= pg.höhe) {
                y = -1;
                reset = true;
            }

            this.x -= random.nextInt(ChristmasConfig.speedX * -1);
            this.y += random.nextInt(ChristmasConfig.speedY);
        } else {
            if (x <= -1) {
                x = pg.breite;
                reset = true;
            }

            if (y <= -1) {
                y = pg.höhe;
                reset = true;
            }

            this.x -= random.nextInt(ChristmasConfig.speedX * -1);
            this.y -= random.nextInt(ChristmasConfig.speedY * -1);
        }

        int xx = (int) (MathHelper.cos(0.1F * (this.x + this.k)) * 10.0F);
        RenderUtils.drawBorderedCircle(this.x + xx, this.y, this.size, 0, 0xffFFFFFF);
    }


    public float genRandom(float min, float max) {
        return (float) (min + Math.random() * (max - min + 1.0F));
    }
}