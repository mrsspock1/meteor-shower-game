package pl.aga.game;

import com.almasb.fxgl.ecs.Control;
import com.almasb.fxgl.ecs.Entity;
import com.almasb.fxgl.ecs.component.Required;
import com.almasb.fxgl.entity.component.PositionComponent;

/**
 * Created by Agnieszka on 2017-07-30.
 */
@Required(PositionComponent.class)
public class AsteroidControl extends Control{

    private PositionComponent position;

    @Override
    public void onUpdate(Entity entity, double tpf) {
        double speed = tpf * 200;

        position.translateY(speed);
    }
    }

