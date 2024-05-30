package com.example.tamagichi;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class SpriteEnemy {
    Bitmap sprite;
    float x, y; //координаты точки на экране
    float dx, dy;
    float tx;



    float ty;

    float height, width; //высота и ширина одного кадра
    Paint paint;
    int currentFrame; //текущий кадр из строки
    int direction = 0; // направление - номер строки с кадрами
    private float canvasWidth;
    private float canvasHeight;
    boolean isFirst = true;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


    public SpriteEnemy(Bitmap sprite, float x, float y){
        this.sprite = sprite; //общая картинка со всеми кадрами
        this.x = x;
        this.y = y;
        paint = new Paint();
        width = sprite.getWidth();
        height = sprite.getHeight() ;
    }
    void draw(Canvas canvas){
       float GlobalX= canvas.getWidth();
       float GlobalY = canvas.getHeight();
        int frameX = (int)(currentFrame * width);
        int frameY = (int)(direction * height);
        float kx=  (GlobalX/width);
        float ky=  (GlobalY/width);

        Rect src = new Rect(frameX, frameY, frameX + (int)width, frameY + (int)height);
        Rect dst = new Rect((int)x, (int)y, (int)(x+width*kx*1/6), (int) (y+height*ky*1/8));
        canvas.drawBitmap(sprite, src, dst, paint);
        x += dx;
        y += dy;


    }

    void calculate(){
        float x1 = tx - x;
        float speed = 40;
        dx = x1 / (float) Math.sqrt(x1 * x1 + x1 * x1) * speed;
    }


    public void setTx(float tx) {
        this.tx = tx;
        calculate();
    }
}