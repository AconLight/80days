package boost;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MyScene {
    public Stage stage;
    protected GameObjectRenderer renderer;
    protected Boolean canClearScreen;

    public MyScene() {
        this.stage = new Stage(new ExtendViewport(1600, 1600));
        this.renderer = new GameObjectRenderer(stage);
    }

    public void act() {
        stage.act();
        renderer.render();
    }
}
