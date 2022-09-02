package com.isometric.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.isometric.game.Database;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class Chunk {
    Random r;
    HashMap<String, Texture> textures = new HashMap<String, Texture>();
    private TiledMap map;
    private TiledMapRenderer renderer;
    private Vector2 index;
    private Vector2 position;
    private Object[][] tiles;
    // public Polygon bounds;
    private Object[][] objects;

    public Chunk() {
    }

    public Chunk(Vector2 newIndex) {
        this.index = newIndex;
        Vector2 size = Database.chunkSize;
        size = new Vector2((size.x) * this.index.x, (size.y) * this.index.y);
        float posX = (((size.x - size.y) * 32));
        float posY = ((size.y + size.x) * 16);
        this.position = new Vector2(posX, posY);
    }

    public void setup() {
        r = new Random();
        Vector2 size = Database.chunkSize;
        tiles = new Object[(int) size.y][(int) size.x];
        objects = new Object[(int) size.y][(int) size.x];
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                double d = ImprovedNoise.noise(((x + (this.index.x * size.x)) / 100) * 1, 0, ((y + (this.index.y * size.y)) / 100) * 1);
                float posX = (((x - y) * 32));
                float posY = ((y + x) * 16);
                Vector2 tilePosition = new Vector2(posX - 32, posY - 32);
                Object tile = new Object();
                tile.setTilePosition(tilePosition);
                Object obj = new Object();
                obj.setTilePosition(tilePosition);
                float g = r.nextFloat();
                if(g<0.4f)
                {
                    tile.setTexture("grass1");
                }
                else
                {
                    tile.setTexture("grass0");
                }

                float t = (float) d;//r.nextFloat();
                if (t < 0.2f) {
                    tile.setTexture("deep_water0");
                } else if (t < 0.35f) {
                    tile.setTexture("shallow_water0");
                } else if (t < 0.4f) {
                    tile.setTexture("sand0");
                } else if (t < 0.5f) {
                    //tile.setTexture("dirt0");
                }

                if (tile.getTexture() == "grass0") {
                    float o = r.nextFloat();
                    if (o < 0.1f) {
                        float o2 = r.nextFloat();
                        float offset = o2 * 4;
                        posX = (((x - y) * 32)) + offset;
                        posY = ((y + x) * 16);
                        tilePosition = new Vector2(posX - 32, posY-64);
                        obj.setTilePosition(tilePosition);
                        obj.setTexture("bush0");
                    }
                    else if (o < 0.15f) {
                        float o2 = r.nextFloat();
                        float offset = o2 * 4;
                        posX = (((x - y) * 32)) + offset;
                        posY = ((y + x) * 16);
                        tilePosition = new Vector2(posX - 32, posY);
                        obj.setTilePosition(tilePosition);
                        obj.setTexture("tree0");
                    }
                    else if (o < 0.175f) {
                        float o2 = r.nextFloat();
                        float offset = o2 * 4;
                        posX = (((x - y) * 32)) + offset;
                        posY = ((y + x) * 16);
                        tilePosition = new Vector2(posX - 32, posY);
                        obj.setTilePosition(tilePosition);
                        obj.setTexture("maple_tree0");
                    }
                }
                tiles[y][x] = tile;
               objects[y][x] = obj;
            }

        }

    }

    public Vector2 getIndex() {
        return this.index;
    }

    public Object getTileAtIndex(Vector2 index) {
        Vector2 size = Database.chunkSize;
        if (index.x >= 0 && index.y >= 0 && index.x < size.x - 1 && index.y < size.y - 1) {
            return tiles[(int) index.y][(int) index.x];
        }
        return null;
    }
    public void render(SpriteBatch batch) {
/*
        Vector2 size = Database.chunkSize;
        for (int y = (int) size.y - 1; y >= 0; y--) {
            for (int x = (int) size.x - 1; x >= 0; x--) {
                Object tile = tiles[y][x];
                if (tile != null) {
                    if (tile.getTexture() != "air") {
                        Texture texture = textures.get(tile.getTexture());
                        if (texture == null) {
                            texture = Database.textures.get(tile.getTexture());
                            textures.put(tile.getTexture(), texture);
                        }
                        batch.draw(texture, this.position.x + tile.getTilePosition().x, this.position.y + tile.getTilePosition().y);
                    }
                }
                Object obj = objects[y][x];
                if (obj != null) {
                    if (obj.getTexture() != "air") {
                        Texture texture = textures.get(tile.getTexture());//
                        if (texture == null) {
                            texture = Database.textures.get(tile.getTexture());
                            textures.put(tile.getTexture(), texture);
                        }
                        batch.draw(texture, this.position.x + obj.getTilePosition().x, this.position.y + obj.getTilePosition().y);
                    }
                }
            }
        }
        //batch.end();*/
        Vector2 size = Database.chunkSize;
        for (int y = (int) size.y - 1; y >= 0; y--) {
            for (int x = (int) size.x - 1; x >= 0; x--) {
                Object tile = tiles[y][x];
                if (tile != null) {
                    if (tile.getTexture() != "air") {
                        TextureRegion textureRegion = Database.textureData.get(tile.getTexture());
                        batch.draw(textureRegion, this.position.x + tile.getTilePosition().x, this.position.y + tile.getTilePosition().y);
                    }
                }
                Object obj = objects[y][x];
                if (obj != null) {
                    if (obj.getTexture() != "air") {
                        TextureRegion textureRegion = Database.textureData.get(obj.getTexture());
                        batch.draw(textureRegion, this.position.x + obj.getTilePosition().x, this.position.y + obj.getTilePosition().y);
                    }
                }
            }
        }
    }
}
