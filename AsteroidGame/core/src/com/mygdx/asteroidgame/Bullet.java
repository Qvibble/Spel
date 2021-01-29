/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.asteroidgame;

/**
 *
 * @author jespe
 */
public class Bullet extends Entity{

    public Bullet() {
        setMaxSpeed(12);
        setSprite("bullet.png");
        getSprite().setPosition(-50, -50);
    }
    
    public void remove(){
        getSprite().setPosition(-50, -50);
        setSpeedX(0);
        setSpeedY(0);
    }
    
    @Override
    public void move(){
        getSprite().translate(getSpeedX(), getSpeedY());
    }
    
}
