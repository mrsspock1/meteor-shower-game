package pl.aga.game;

import com.almasb.fxgl.annotation.SetEntityFactory;
import com.almasb.fxgl.annotation.Spawns;
import com.almasb.fxgl.ecs.Entity;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.entity.control.OffscreenCleanControl;
import javafx.scene.image.ImageView;

/**
 * Created by Agnieszka on 2017-07-30.
 */
@SetEntityFactory
public class AsteroidFactory implements com.almasb.fxgl.entity.EntityFactory {

    @Spawns("Asteroid")
    public Entity Enemy(SpawnData data) {
        return Entities.builder()
                .from(data)
                .type(EntityType.ASTEROID)
                .viewFromTextureWithBBox("asteroid-icon.png")
                .with(new CollidableComponent(true))
                .with(new AsteroidControl())
                .build();
    }
    @Spawns("Player")
    public Entity Player(SpawnData data) {
        return Entities.builder()
                .from(data)
                .at(300, 460)
                .type(EntityType.PLAYER)
                .viewFromTextureWithBBox("App-kspaceduel-spaceship-icon.png")
                .with(new CollidableComponent(true))
                .with(new PlayerControl(), new OffscreenCleanControl())
                .build();
    }

}
