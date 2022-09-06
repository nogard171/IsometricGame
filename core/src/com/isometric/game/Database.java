package com.isometric.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public class Database {
    public final static Vector2 chunkSize  = new Vector2(16,16);
    public static HashMap<String, Texture> textures = new HashMap<String, Texture>();
    public static Texture textureMap;
    public static Texture uiTextureMap;
    public static HashMap<String, TextureRegion> textureData = new HashMap<String, TextureRegion>();

    public static void load()
    {

        textures = new HashMap<String, Texture>();
        textures.put("hover",new Texture("terrain/hover.png"));
        textures.put("stone_item",new Texture("user interface/stone_item.png"));

        uiTextureMap  =new Texture("user interface/tileset.png");
        textureData.put("panel_0,0",new TextureRegion(uiTextureMap, 0,0,2,2));
        textureData.put("panel_1,0",new TextureRegion(uiTextureMap, 2,0,2,2));
        textureData.put("panel_2,0",new TextureRegion(uiTextureMap, 4,0,2,2));
        textureData.put("panel_0,1",new TextureRegion(uiTextureMap, 0,2,2,2));
        textureData.put("panel_1,1",new TextureRegion(uiTextureMap, 2,2,2,2));
        textureData.put("panel_2,1",new TextureRegion(uiTextureMap, 4,2,2,2));
        textureData.put("panel_0,2",new TextureRegion(uiTextureMap, 0,4,2,2));
        textureData.put("panel_1,2",new TextureRegion(uiTextureMap, 2,4,2,2));
        textureData.put("panel_2,2",new TextureRegion(uiTextureMap, 4,4,2,2));

        textureData.put("icon_0,0",new TextureRegion(uiTextureMap, 0+9,0,3,3));
        textureData.put("icon_1,0",new TextureRegion(uiTextureMap, 3+9,0,3,3));
        textureData.put("icon_2,0",new TextureRegion(uiTextureMap, 6+9,0,3,3));
        textureData.put("icon_0,1",new TextureRegion(uiTextureMap, 0+9,3,3,3));
        textureData.put("icon_1,1",new TextureRegion(uiTextureMap, 3+9,3,3,3));
        textureData.put("icon_2,1",new TextureRegion(uiTextureMap, 6+9,3,3,3));
        textureData.put("icon_0,2",new TextureRegion(uiTextureMap, 0+9,6,3,3));
        textureData.put("icon_1,2",new TextureRegion(uiTextureMap, 3+9,6,3,3));
        textureData.put("icon_2,2",new TextureRegion(uiTextureMap, 6+9,6,3,3));

        textureData.put("red_0,0",new TextureRegion(uiTextureMap, 0+18,0,3,3));
        textureData.put("red_1,0",new TextureRegion(uiTextureMap, 3+18,0,3,3));
        textureData.put("red_2,0",new TextureRegion(uiTextureMap, 6+18,0,3,3));
        textureData.put("red_0,1",new TextureRegion(uiTextureMap, 0+18,3,3,3));
        textureData.put("red_1,1",new TextureRegion(uiTextureMap, 3+18,3,3,3));
        textureData.put("red_2,1",new TextureRegion(uiTextureMap, 6+18,3,3,3));
        textureData.put("red_0,2",new TextureRegion(uiTextureMap, 0+18,6,3,3));
        textureData.put("red_1,2",new TextureRegion(uiTextureMap, 3+18,6,3,3));
        textureData.put("red_2,2",new TextureRegion(uiTextureMap, 6+18,6,3,3));

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
