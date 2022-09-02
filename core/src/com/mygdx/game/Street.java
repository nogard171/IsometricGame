package com.mygdx.game;


import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Street implements Connection<City> {
    City fromCity;
    City toCity;
    float cost;

    public Street(City fromCity, City toCity) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        cost = Vector2.dst(fromCity.x, fromCity.y, toCity.x, toCity.y);
    }

    public void render(ShapeRenderer shapeRenderer) {
        float fromX = (fromCity.x * 50) + 100;
        float fromY = (fromCity.y * 50) + 100;
        float toX = (toCity.x * 50) + 100;
        float toY = (toCity.y * 50) + 100;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rectLine(fromX, fromY, toX, toY, 4);
        shapeRenderer.end();
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public City getFromNode() {
        return fromCity;
    }

    @Override
    public City getToNode() {
        return toCity;
    }
}