package com.example.tamagichi;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Sprite2  {
    Bitmap sprite;
    float x;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    float y; //точка на экране
    float dx,dy;
    float tx,ty;
    float height,widths;
    Paint paint;
    final int Rows=1;
    final int Colums=1;
    int currentFrame;
    int direction=0;
    boolean isFirst=true;//номер строки
    private float canvasWidth;
    private float canvasHeight;

    public Sprite2(Bitmap sprite,float x,float y){

        this.sprite=sprite;
        this.x=x;
        this.y=y;
        paint=new Paint();
        widths= sprite.getWidth();
        height=sprite.getHeight();

    }


    void Control(){

        if (x<10||x>canvasWidth*3/4){
            dx=-dx;
        }
        if (y<10||y>canvasHeight+height){
            dy=-dy;
        }
    }
    void draw(Canvas canvas){
        if (isFirst){ canvasWidth=canvas.getWidth();
            canvasHeight=canvas.getHeight();
            canvasWidth=canvas.getWidth();
            isFirst=!isFirst;}
        float GlobalX= canvas.getWidth();
        float GlobalY = canvas.getHeight();
        float kx=  (GlobalX/widths);
        float ky=  (GlobalX/widths);
        int framex=(int)(currentFrame*widths);
        int framey=(int)(direction*height);
        Rect src = new Rect(framex,framey,framex+(int)widths,framey+(int)height);
        Rect dsc = new Rect((int)x,(int)y,(int)(x+widths*kx*1/3),(int)(y+height*ky*1/3));
        canvas.drawBitmap(sprite,src,dsc,paint);
        x+=dx;

        Control();
    }
    void Calculate(){


        float x1 = tx - x;
        float y1 = ty - y;
        float speed=40;
        dx = x1 / (float) Math.sqrt(x * x + y * y) * speed;
        dy = y1 / (float) Math.sqrt(x * x + y * y) * speed;
    }

    public void setTx(float tx) {
        this.tx = tx;
        Calculate();
    }

    public void setTy(float ty) {
        this.ty = ty;
        Calculate();
    }

}
