/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.asteroidgame;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jespe
 */
public class Asteroid extends Entity{
    private float maxX;
    private float maxY;    
    private int rotationSpeed = 6;
    private long hitStart;
    private long hitFinish;
    private List<Asteroid> small = new ArrayList<>();
    
    public Asteroid(int health, List<Asteroid> small) {
        this.small = small;
        setHealth(health);
        setSprite("asteroid.png");
        
        int randomSpeed = (int) Math.floor((Math.random()*(5-1)+1));  
        setMaxSpeed(randomSpeed);

        setRandDirection();
        
        getSprite().setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
    }
    
    private void setRandDirection(){
        this.maxX = (float)(Math.random()*(0.5)+0.5);
        this.maxY = (float)(Math.random()*(0.5)+0.5);
        setSpeedX(getMaxSpeed()*this.maxX);
        setSpeedY(getMaxSpeed()*this.maxY);
    }
    
    private void split(){
        float x = getSprite().getX();
        float y = getSprite().getY();
        
        setSprite("asteroid2.png");
        getSprite().setScale(0.7f);
        getSprite().setPosition(x, y);
        setMaxSpeed(getMaxSpeed()/2);
        setSpeedY(getSpeedY()/2);
        setSpeedX(getSpeedX()/2);
        this.rotationSpeed /= 2;
        
        setHealth(20);
        
        //Creates another small asteroid
        Asteroid a = new Asteroid(20, small);
        a.setSprite("asteroid2.png");
        a.getSprite().setScale(0.7f);
        a.getSprite().setPosition(x, y);
        a.setMaxSpeed(getMaxSpeed()/2);
        a.setSpeedY(-getSpeedY()/2);
        a.setSpeedX(-getSpeedX()/2);
        this.rotationSpeed /= 2;
        small.add(a);
    }
  
    public void checkCollision(Entity e){
        hitFinish = System.currentTimeMillis();
        
        if(e instanceof Bullet){  //If bullet
            if(getSprite().getBoundingRectangle().overlaps(e.getSprite().getBoundingRectangle())){
                ((Bullet) e).remove();

                setHealth(getHealth()-10);

                if(getHealth() <= 0){  //Splits big ones
                    if(getSpriteName().equals("asteroid.png")){
                        split();                 
                    }else{  //Moves small ones off the screen if health < 0
                        getSprite().setPosition(-50, Gdx.graphics.getHeight()/2);
                        setSpeedX(0);
                        setSpeedY(0);
                    }
                }
            }
        }
        
        if(e instanceof Player){  //If player
            if(getSprite().getBoundingRectangle().overlaps(e.getSprite().getBoundingRectangle())){
                if(hitFinish - hitStart > 800){
                    e.setHealth(e.getHealth()-20);
                    
                    System.out.println("Player Health: " + e.getHealth());
                    
                    hitStart = System.currentTimeMillis();
                }
            }
        }          
    }

    public List<Asteroid> getSmall() {
        return small;
    }
  
    @Override
    public void move(){
        getSprite().rotate(this.rotationSpeed);
        
        int windowWidth = Gdx.graphics.getWidth();
        int windowHeight = Gdx.graphics.getHeight();
        
        if(getSprite().getBoundingRectangle().getX() < 0 && getSprite().getBoundingRectangle().getX() > -30){  //Left
            setSpeedX(getMaxSpeed()*this.maxX);
        }
        if(getSprite().getBoundingRectangle().getX() + getSprite().getWidth() > windowWidth){  //Right
            setSpeedX(-getMaxSpeed()*this.maxX);
        }
        if(getSprite().getBoundingRectangle().getY() + getSprite().getHeight() > windowHeight){  //Top
            setSpeedY(-getMaxSpeed()*this.maxY);
        }
        if(getSprite().getBoundingRectangle().getY() < 0){  //Bottom
            setSpeedY(getMaxSpeed()*this.maxY);
        }
        
        getSprite().translate(getSpeedX(), getSpeedY());
    }
    
}
