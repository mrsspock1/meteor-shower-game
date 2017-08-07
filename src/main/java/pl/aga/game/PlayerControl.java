package pl.aga.game;

import com.almasb.fxgl.ecs.Control;
import com.almasb.fxgl.ecs.Entity;
import com.almasb.fxgl.ecs.component.Required;
import com.almasb.fxgl.entity.component.PositionComponent;

/**
 * Created by Agnieszka on 2017-07-30.
 */
@Required(PositionComponent.class)
public class PlayerControl extends Control {

    private PositionComponent position;

    private double speed;

    @Override
    public void onUpdate(Entity entity, double tpf) {
        speed = tpf * 200;
    }

    public void left() {
        position.translateX(-speed);
    }

    public void right() {
        position.translateX(speed);
    }
}
