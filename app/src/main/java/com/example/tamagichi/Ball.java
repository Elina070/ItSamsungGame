package com.example.tamagichi;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

public class Ball extends  SpriteEnemy{
Matrix matrix;

    public Ball(Bitmap sprite, float x, float y) {
        super(sprite, x, y);
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
        float ky=  (GlobalX/width);

        Rect src = new Rect(frameX, frameY, frameX + (int)width, frameY + (int)height);
        Rect dst = new Rect((int)x, (int)y, (int)(x+width*kx*1/4), (int)(y+height*ky*1/4));
        canvas.drawBitmap(sprite, src, dst, paint);
        y += dy;
    }
    void calculate(){
        float y1 = ty - y;
        float speed = 30;
        dy = y1 / (float) Math.sqrt(y1 * y1 + y1 * y1) * speed;
    }
    public void setTy(float ty) {
        this.ty = ty;
        calculate();
    }

}
