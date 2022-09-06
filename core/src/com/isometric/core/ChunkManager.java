package com.isometric.core;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.isometric.game.GameLogic;

import java.util.HashMap;
import java.util.LinkedList;

public class ChunkManager {
    public int chunksRendered = 0;
    HashMap<Vector2, Chunk> chunkHashMap = new HashMap<>();
    LinkedList<Chunk> chunksToRender = new LinkedList<Chunk>();
    int viewDistance = 3;
    Vector2 previousView = new Vector2(-100, -100);

    public void setup() {
        int count = 100;
        for (int x = 0; x < count; x++) {
            for (int y = 0; y < count; y++) {
                Chunk c = new Chunk(new Vector2(x, y));
                c.setup();
                chunkHashMap.put(c.getIndex(), c);
            }
        }
    };

    public void update(Vector2 viewIndex) {
        if (!previousView.equals(viewIndex)) {
            chunksToRender.clear();
            chunksRendered = 0;
            float startX = viewIndex.x - viewDistance;
            float startY = viewIndex.y - viewDistance;
            float endX = viewIndex.x + viewDistance;
            float endY = viewIndex.y + viewDistance;
            for (int x = (int) endX; x >= startX; x--) {
                for (int y = (int) endY; y >= startY; y--) {
                    Vector2 key = new Vector2(x, y);
                    Chunk c = chunkHashMap.get(key);
                    if (c != null) {
                        chunksToRender.add(c);
                        chunksRendered++;
                    }
                }
            }
            previousView = viewIndex;
        }
    }

    public void render(SpriteBatch batch, Vector2 viewIndex) {
        /*for (Chunk c : chunksToRender) {
            c.render(batch);
        }*/

        int length = chunksToRender.size();
        for(int i = 0; i < length; i++) {
            Chunk c = chunksToRender.get(i);
            c.render(batch);
        }
    }
}
