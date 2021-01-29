package com.mygdx.asteroidgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class AsteroidGame extends ApplicationAdapter {
    ArrayList<Asteroid> asteroids = new ArrayList<>();
    Bullet[] bullets;
    SpriteBatch batch;
    Texture img;
    
    int counter = 0;
    long start;
    long finish;

    @Override
    public void create () {
        batch = new SpriteBatch();
        asteroids.add(new Asteroid(30, asteroids));
        bullets = new Bullet[30];
        for(int i = 0; i < 30; i++){
            bullets[i] = new Bullet();
        }
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        finish = System.currentTimeMillis(); 
        if(finish - start > 3000){
            asteroids.add(new Asteroid(30, asteroids));
            start = System.currentTimeMillis();
        }

        if(Player.getInstance().getHealth() > 0){
            Player.getInstance().move();
            if(Player.getInstance().shoot(bullets[counter]) == true){
                counter++;

                if(counter == 29){
                    counter = 0;
                }
            }            
            Player.getInstance().windowCollision();  
        }

        for(Bullet b: bullets){
            b.move();
        }

        for(int i = 0; i < asteroids.size(); i++){               
            asteroids.get(i).checkCollision(Player.getInstance());
            for(Bullet b: bullets){
                asteroids.get(i).checkCollision(b);
            }
            
            asteroids.get(i).move();
        }

        batch.begin();     
            if(Player.getInstance().getHealth() > 0){
                Player.getInstance().getSprite().draw(batch);                      
            }
            
            for(Asteroid a: asteroids){
                a.getSprite().draw(batch);  
            }                             
            for(Bullet b: bullets){
                b.getSprite().draw(batch);
            }
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}
