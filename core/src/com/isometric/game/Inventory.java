package com.isometric.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.isometric.core.Clickable;
import com.isometric.core.GameInput;
import com.isometric.core.ItemSlot;
import com.isometric.core.Panel;

import java.util.HashMap;

public class Inventory {
    private static BitmapFont font;
    public Vector2 position = new Vector2(0, 0);
    Clickable titleClickable;
    Clickable panelClickable;
    Clickable closeClickable;
    Clickable bagOneClickable;
    Vector2 offset;
    boolean moved = true;
    Texture panelTexture;
    Texture inventoryBagTexture;
    Texture goldCoinTexture;
    Texture silverCoinTexture;
    Texture copperCoinTexture;
    Clickable[] bagClickable;
    int selectedBag = 0;
    private boolean show = true;
    private HashMap<Vector2, ItemSlot> itemSlots = new HashMap<>();

    public void setup() {

        goldCoinTexture = new Texture("user interface/gold_coin_icon.png");
        silverCoinTexture = new Texture("user interface/silver_coin_icon.png");
        copperCoinTexture = new Texture("user interface/copper_coin_icon.png");


        panelTexture = new Texture("user interface/inventory_panel.png");
        inventoryBagTexture = new Texture("user interface/inventory_bag_icon.png");
        panelClickable = new Clickable(0, 0, panelTexture.getWidth(), panelTexture.getHeight());
        titleClickable = new Clickable(panelClickable.getBounds().x, panelClickable.getBounds().y + panelTexture.getHeight() - 25, 100, 25);
        closeClickable = new Clickable(panelClickable.getBounds().x + panelTexture.getWidth() - 16, panelClickable.getBounds().y + panelTexture.getHeight() - 24, 16, 16);

        bagOneClickable = new Clickable(panelClickable.getBounds().x + 5, panelClickable.getBounds().y + 16, 40, 20);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                ItemSlot slot = new ItemSlot();
                slot.setPosition(
                        new Vector2(this.position.x
                                + (x * 37) + 10, this.position.y
                                + (y * 36) + 8)
                );
                itemSlots.put(new Vector2(x, y), slot);
            }
        }
        bagClickable = new Clickable[5];
        for (int x = 0; x < bagClickable.length; x++) {
            Clickable bagTemp = new Clickable(panelClickable.getBounds().x + 5 + (x * 17), panelClickable.getBounds().y + 16, 40, 20);
            bagClickable[x] = bagTemp;
        }
    }

    public void update() {
        if (show) {
            panelClickable.update();
            titleClickable.update();
            closeClickable.update();

            bagOneClickable.update();


            if (closeClickable.isMousePressed(Input.Buttons.LEFT)) {

                show = !show;
            }
            if (titleClickable.isMouseDragged(Input.Buttons.LEFT)) {
                Vector2 mouse = GameInput.getInvertMousePosition();
                if (offset == null) {
                    offset = new Vector2(position.x - mouse.x, position.y - mouse.y);
                }
                position = new Vector2(
                        Math.max(0,
                                Math.min(
                                        Gdx.graphics.getWidth() - panelClickable.getBounds().width,
                                        mouse.x + offset.x)
                        ),
                        Math.max(0,
                                Math.min(
                                        Gdx.graphics.getHeight() - panelClickable.getBounds().height,
                                        mouse.y + offset.y)
                        )
                );
                moved = true;
            } else {
                offset = null;
            }
            int x = 0;
            for (Clickable bag : bagClickable) {
                if (moved) {
                    bag.setPosition(new Vector2(panelClickable.getBounds().x + 5 + (x * 17), panelClickable.getBounds().y + 17));
                }

                bag.update();
                if (bag.isMousePressed(Input.Buttons.LEFT)) {
                    selectedBag = x;
                }
                x++;
            }
            if (moved) {
                panelClickable.setPosition(new Vector2(position.x, position.y));
                titleClickable.setPosition(new Vector2(position.x, position.y + panelClickable.getBounds().height - 25));
                closeClickable.setPosition(new Vector2(position.x + panelTexture.getWidth() - 16, position.y + panelTexture.getHeight() - 24));
                for (ItemSlot slot : itemSlots.values()) {
                    slot.update(position);
                }


                moved = false;
            }
        }
    }

    public void render(SpriteBatch batch) {
        if (show) {
            int x = 0;
            for (Clickable bag : bagClickable) {
                if (x != selectedBag) {
                    batch.setColor(new Color(0.75f, 0.75f, 0.75f, 1f));
                    font.setColor(new Color(1, 1, 1, 0.74f));
                } else {
                    batch.setColor(new Color(1, 1, 1, 1));
                    font.setColor(new Color(1, 1, 1, 1));
                }
                batch.draw(inventoryBagTexture, bag.getPosition().x, bag.getPosition().y);
                font.draw(batch, "" + (x + 1), bag.getPosition().x + 3, bag.getPosition().y + 15);
                x++;
            }
            batch.setColor(new Color(1, 1, 1, 1));
            font.setColor(Color.WHITE);
            batch.draw(panelTexture, panelClickable.getPosition().x, panelClickable.getPosition().y);
            font.draw(batch, "Inventory", titleClickable.getPosition().x + 5, titleClickable.getPosition().y + 20);
            font.draw(batch, "X", closeClickable.getPosition().x + 2, closeClickable.getPosition().y + 14);


            batch.draw(copperCoinTexture, panelClickable.getPosition().x + panelTexture.getWidth() - 26, panelClickable.getPosition().y + 15);
            font.draw(batch, "" + PlayerDatabase.copper, panelClickable.getPosition().x + panelTexture.getWidth() - (PlayerDatabase.copper > 9 ? 44 : 36), panelClickable.getPosition().y + 29);
            if (PlayerDatabase.silver > 0) {
                batch.draw(silverCoinTexture, panelClickable.getPosition().x + panelTexture.getWidth() - 62, panelClickable.getPosition().y + 15);
                font.draw(batch, "" + PlayerDatabase.silver, panelClickable.getPosition().x + panelTexture.getWidth() - (PlayerDatabase.silver > 9 ? 80 : 72), panelClickable.getPosition().y + 29);
            }
            if (PlayerDatabase.gold > 0) {
                batch.draw(goldCoinTexture, panelClickable.getPosition().x + panelTexture.getWidth() - 97, panelClickable.getPosition().y + 15);
                font.draw(batch, "" + PlayerDatabase.gold, panelClickable.getPosition().x + panelTexture.getWidth() - (PlayerDatabase.gold > 9 ? 115 : 108), panelClickable.getPosition().y + 29);
            }
        }
    }

    public void toggle() {
        this.show = !this.show;
    }

    public void show() {
        this.show = true;
    }
}
