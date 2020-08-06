package com.mygdx.collisions.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.collisions.CollisionHandler;
import com.mygdx.collisions.CollisionInfo;

import java.math.BigDecimal;

/**
 * Created by Wojciech on 2020-06-30.
 */
public class AccSquaresHandler implements CollisionHandler {

    @Override
    public Vector2 handle(CollisionInfo info, CollisionInfo info2) {
        Vector2 res = new Vector2();
        Gdx.app.log("AccSquaresHandler", "" + info.getPosition().x);
        if (info.getPosition().x + info.getRectSize().x > info2.getPosition().x && info.getPosition().x < info2.getPosition().x + info2.getRectSize().x) {
            if (info.getPosition().y + info.getRectSize().y > info2.getPosition().y && info.getPosition().y < info2.getPosition().y + info2.getRectSize().y) {
                float dx1 = -(info.getPosition().x + info.getRectSize().x - info2.getPosition().x);
                float dy1 = -(info.getPosition().y + info.getRectSize().y - info2.getPosition().y);
                float dx2 = info2.getPosition().x + info2.getRectSize().x - info.getPosition().x;
                float dy2 = info2.getPosition().y + info2.getRectSize().y - info.getPosition().y;
//                float dr2 = dx*dx + dy*dy;
                if (Math.min(Math.abs(dy1), Math.abs(dy2)) < Math.min(Math.abs(dx1), Math.abs(dx2))) {
                    if (Math.abs(dy1) > Math.abs(dy2)) {
                        res.y += dy2;
                    } else {
                        res.y += dy1;
                    }
                } else {
                    if (Math.abs(dx1) > Math.abs(dx2)) {
                        res.x += dx2;
                    } else {
                        res.x += dx1;
                    }
                }
            }
        }
        return res;
    }
}
