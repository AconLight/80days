package com.mygdx.collisions;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Wojciech on 2020-06-30.
 */
public interface CollisionHandler {
    public Vector2 handle(CollisionInfo info, CollisionInfo info2);
}
