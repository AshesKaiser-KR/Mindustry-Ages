package ages.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.entities.*;
import mindustry.graphics.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;


public class AgesFx {
    public static final Effect

    carbondust = new Effect(420, e -> randLenVectors(e.id, 8, 6f + e.fin() * 5f, (x, y) -> {
        color(Color.black);
        Fill.circle(e.x + x, e.y + y, 2f);
    })),

    bulletAccel = new Effect(30, e -> {
        color(Pal.lightFlame);
        stroke(e.fout() * 2f);
        Lines.circle(e.x, e.y, 12f * e.finpow());
    }),

    bulletFire = new Effect(40, e -> {
        color(Pal.lightFlame, Pal.darkFlame, e.fin());

        Fill.circle(e.x, e.y, 2f);
    });
}
