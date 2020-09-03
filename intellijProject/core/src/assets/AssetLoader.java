package assets;

import boost.GameObject;
import boost.SpriteObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.applet.AudioClip;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AssetLoader {
    private static HashMap<String, Texture> assets = new HashMap<>();
    private static HashMap<String, HashMap<String, Animation>> animations = new HashMap<>();

    public static Music soundtrack, soundtrack_menu;
    public static Sound pistol, jump, swap, end, gun, tarcza, cannonStart;



    public static SpriteObject getAsset(String name) {
        SpriteObject gameObject = new SpriteObject();
        Texture tex = assets.get(name);
        gameObject.addActor(new Image(tex));
        gameObject.setBounds(0, 0, tex.getWidth(), tex.getHeight());
        return gameObject;
    }

    public static SpriteObject getAnimation(String name, float index) {
        SpriteObject gameObject = new SpriteObject(index);
        for (String key : animations.get(name).keySet()) {
            TextureRegion tex = animations.get(name).get(key).getKeyFrames()[0];
            gameObject.setBounds(0, 0, tex.getRegionWidth(), tex.getRegionHeight());
            break;
        }
        gameObject.animations.putAll(animations.get(name));
        return gameObject;
    }

    public static SpriteObject getAnimation(String name) {
        SpriteObject gameObject = new SpriteObject();
        for (String key : animations.get(name).keySet()) {
            TextureRegion tex = animations.get(name).get(key).getKeyFrames()[0];
            gameObject.setBounds(0, 0, tex.getRegionWidth(), tex.getRegionHeight());
            break;
        }
        gameObject.animations.putAll(animations.get(name));
        return gameObject;
    }

    public static SpriteObject getAsset(String name, float index) {
        SpriteObject gameObject = new SpriteObject(index);
        Texture tex = assets.get(name);
        gameObject.addActor(new Image(tex));
        gameObject.setBounds(0, 0, tex.getWidth(), tex.getHeight());
        return gameObject;
    }

    public static void createAsset(String path, String name) {
        Gdx.app.log("createAsset", path);
        assets.put(name, new Texture(Gdx.files.internal(path)));
    }

    public static void load() {
        createAsset("badlogic.jpg", "badlogic");
        createAsset("graphics/sprites/dot.png", "dot");
        createAsset("graphics/sprites/dot2.png", "dot2");

        loadAnimations();

        // soundtrack = Gdx.audio.newMusic(Gdx.files.internal("sounds/soundtrack.wav"));
        // pistol = Gdx.audio.newSound(Gdx.files.internal("sounds/shot.wav"));


    }


    public static void createAnimation(String path, String name) {
        HashMap<String, Animation> myAnimations = new HashMap<>();

        List<String> dirs = getDirs("graphics/animations/" + path);
        Gdx.app.log("dupa", "dupa");
        for (String child : dirs) {
            Gdx.app.log("AssetLoader", child);
            List<String> dirs2 = getFiles(child);
            TextureRegion[] textures = new TextureRegion[dirs2.size()];
            int i = 0;
            for (String childTexture : dirs2) {
                Gdx.app.log("AssetLoader ------  ", childTexture);
                textures[i] = new TextureRegion(new Texture(childTexture));
                i++;
            }
            Animation animation = new Animation(1f, textures);
            animation.setPlayMode(Animation.PlayMode.LOOP);
            String[] parts = child.split("/");

            myAnimations.put(parts[parts.length - 1], animation);
        }

        animations.put(name, myAnimations);
    }

    public static void loadAnimations() {
        System.out.println("siema");
        getDirs(".");
//        createAnimation("jade", "jade");
//        createAnimation("jade2", "jade2");

    }


    public static List<String> getDirs(String path) {
        List<String> dirs = new ArrayList<>();
        System.out.println("current path: " + Paths.get(".").toAbsolutePath());
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {

            dirs = walk.filter(Files::isDirectory)
                    .map(x -> path + "/" + x.getFileName().toString()).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        dirs.remove(0);
        System.out.println(dirs);
        return dirs;
    }

    public static List<String> getFiles(String path) {
        List<String> dirs = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(path).toAbsolutePath())) {

            dirs = walk.filter(Files::isRegularFile)
                    .map(x -> path + "/" + x.getFileName().toString()).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dirs;
    }


}
