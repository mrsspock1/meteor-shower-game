package pl.aga.game;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.ecs.Entity;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

/**
 * Created by Agnieszka on 2017-07-30.
 */
public class AsteroidShowerApp extends GameApplication{
    private PlayerControl playerControl;
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(600);
        settings.setHeight(600);
        settings.setTitle("Deszcz meteorytów");
        settings.setVersion("0.1");
        settings.setIntroEnabled(false); // turn off intro
        settings.setMenuEnabled(false);
        settings.setProfilingEnabled(false);
        settings.setCloseConfirmation(false);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);
    }


    @Override
    protected void initInput() {
        Input input = getInput(); // get input service

        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                playerControl.left();
            }
        }, KeyCode.A);

        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                playerControl.right();
            }
        }, KeyCode.D);
    }

    private void initBackground() {
        GameEntity bg = Entities.builder()
                .viewFromNode(new ImageView("https://opengameart.org/sites/default/files/Space-Background-3.jpg"))
                .buildAndAttach(getGameWorld());

        bg.getPositionComponent().xProperty().bind(getGameScene().getViewport().xProperty());
        bg.getPositionComponent().yProperty().bind(getGameScene().getViewport().yProperty());
    }

    @Override
    protected void initGame() {
        initBackground();
        Entity player = getGameWorld().spawn("Player", getWidth() / 2, getHeight() - 200);
        playerControl = player.getControl(PlayerControl.class);

        getMasterTimer().runAtInterval(() -> {
            getGameWorld().spawn("Asteroid", Math.random() * (getWidth() - 64), 0 );
        }, Duration.seconds(0.5));

    }

    @Override
    protected void initPhysics() {
        PhysicsWorld physicsWorld = getPhysicsWorld();

        physicsWorld.addCollisionHandler(new CollisionHandler(EntityType.ASTEROID,EntityType.PLAYER) {
            @Override
            protected void onCollisionBegin(Entity asteroid, Entity player) {
                player.removeFromWorld();

            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
