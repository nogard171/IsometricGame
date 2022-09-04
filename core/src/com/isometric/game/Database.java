package com.isometric.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.w3c.dom.Text;

import java.util.HashMap;

public class Database {
    public final static Vector2 chunkSize  = new Vector2(16,16);
    public static HashMap<String, Texture> textures = new HashMap<String, Texture>();
    public static Texture textureMap;
    public static HashMap<String, TextureRegion> textureData = new HashMap<String, TextureRegion>();

    public static void load()
    {

        textures = new HashMap<String, Texture>();
        textures.put("hover",new Texture("terrain/hover.png"));
/*
        textures.put("tree0",new Texture("terrain/tree0.png"));


        textures.put("grass0",new Texture("terrain/grass0.png"));
        textures.put("grass1",new Texture("terrain/grass1.png"));
        textures.put("dirt0",new Texture("terrain/dirt0.png"));
        textures.put("sand0",new Texture("terrain/sand0.png"));
        textures.put("shallow_water0",new Texture("terrain/shallow_water0.png"));
        textures.put("deep_water0",new Texture("terrain/deep_water0.png"));*/

        textureMap =new Texture("terrain/tiles.png");
        textureData.put("grass0",new TextureRegion(textureMap, 64,0,64,64));
        textureData.put("grass1",new TextureRegion(textureMap, 0,64,64,64));
        textureData.put("dirt0",new TextureRegion(textureMap, 0,0,64,64));
        textureData.put("sand0",new TextureRegion(textureMap, 64,64,64,64));
        textureData.put("shallow_water0",new TextureRegion(textureMap, 64*3,0,64,64));
        textureData.put("deep_water0",new TextureRegion(textureMap, 64*3,64,64,64));


        textureData.put("tree0",new TextureRegion(textureMap, 0,64*2,64,128));
        textureData.put("maple_tree0",new TextureRegion(textureMap, 64,64*2,64,128));
        textureData.put("bush0",new TextureRegion(textureMap, 64*2,0,64,128));


    }
    public static void cleanup()
    {
        for(Texture text : textures.values())
        {
            text.dispose();
        }
    }
}
