package ic2.content;

import mindustry.content.Bullets;
import mindustry.content.Fx;
import mindustry.ctype.ContentList;
import mindustry.entities.bullet.ArtilleryBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.graphics.Pal;

public class ExBullets implements ContentList{
    public static BulletType

    artilleryBig;

    @Override
    public void load(){
        artilleryBig = new ArtilleryBulletType(2f, 200){{
            hitEffect = Fx.massiveExplosion;
            knockback = 1.2f;
            lifetime = 120f;
            width = height = 22f;
            collidesAir = false;
            splashDamage = 50f;
            splashDamageRadius = 10f * 0.75f;
            backColor = Pal.gray;
            shrinkY = 0.5f;
            shrinkX = 0.5f;
            fragBullet = Bullets.fragExplosive;
            fragBullets = 8;
            despawnEffect = Fx.fireballsmoke;
            trailEffect = Fx.artilleryTrail;
        }};
    }
}

