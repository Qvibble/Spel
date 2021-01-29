/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.asteroidgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 *
 * @author jespe
 */
public class Player extends Entity{
    private float angle = (float)Math.PI/2;
    private float x;
    private float y;
    private long start;
    private long finish;
    
    private static Player instance;

    public static Player getInstance() {
        if(instance == null){
            instance = new Player(100, 4, "player.png");
        }
        
        return instance;
    }
    
    private Player(int health, int speed, String sprite) {
        setHealth(health);
        setMaxSpeed(speed);
        setSprite(sprite);
        
        getSprite().setPosition(30, 30);
    }
    
    public void windowCollision(){
        int windowWidth = Gdx.graphics.getWidth();
        int windowHeight = Gdx.graphics.getHeight();
        
        if(getSprite().getBoundingRectangle().getX() < 0){  //Left
            getSprite().setX(0);
        }
        if(getSprite().getBoundingRectangle().getX() + getSprite().getWidth() > windowWidth){  //Right
            getSprite().setX(Gdx.graphics.getWidth()-getSprite().getWidth());
        }
        if(getSprite().getBoundingRectangle().getY() + getSprite().getHeight() > windowHeight){  //Top
            getSprite().setY(windowHeight-getSprite().getHeight());
        }
        if(getSprite().getBoundingRectangle().getY() < 0){  //Bottom
            getSprite().setY(0);
        }
    }
    
    public boolean shoot(Bullet bullet){
        finish = System.currentTimeMillis();
        
        if(finish - start > 250){
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                float xPos = getSprite().getX();
                float yPos = getSprite().getY();

                bullet.getSprite().setPosition(xPos + getSprite().getWidth()/2 - bullet.getSprite().getWidth()/2,
                        yPos + getSprite().getHeight()/2 - bullet.getSprite().getHeight()/2);
                bullet.setSpeedX(bullet.getMaxSpeed()*x);
                bullet.setSpeedY(bullet.getMaxSpeed()*y);  

                start = System.currentTimeMillis();
                
                return true;
            }    
        }
        
        return false;
    }
    
    @Override
    public void move(){
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            getSprite().translate(getMaxSpeed()*x, getMaxSpeed()*y);
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            angle += 0.1;
        }else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            angle -= 0.1;
        }  
        
        getSprite().setRotation((float)Math.toDegrees(angle) - 90);
        
        x = (float)Math.cos((float)angle);
        y = (float)Math.sin((float)angle);
    }
}
