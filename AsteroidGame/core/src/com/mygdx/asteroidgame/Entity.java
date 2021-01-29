/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.asteroidgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author jespe
 */
abstract class Entity {
    private Sprite sprite;
    private String spriteName;
    private int health;
    private float speedX;
    private float speedY;
    private int maxSpeed;

    protected final String getSpriteName(){
        return spriteName;
    }
    
    protected final Sprite getSprite() {
        return sprite;
    }

    protected final void setSprite(String sprite) {
        this.sprite = new Sprite(new Texture(sprite));
        this.spriteName = sprite;
    }

    protected final int getHealth() {
        return health;
    }

    protected final void setHealth(int health) {
        this.health = health;
    }

    protected final float getSpeedX() {
        return speedX;
    }

    protected final void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    protected final float getSpeedY() {
        return speedY;
    }

    protected final void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    protected final int getMaxSpeed() {
        return maxSpeed;
    }

    protected final void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    
    protected void move(){
        
    }
}
