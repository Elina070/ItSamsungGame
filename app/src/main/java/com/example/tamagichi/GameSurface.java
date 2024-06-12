package com.example.tamagichi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {
    Bitmap b1, b2, b3, b4, b5,b6,b7,flower,q, b8,load, happy,back4, back5,eat, dark, done, normal, sad,hand, r, confused, sleep, walk, water,back11,back2,trees,left,right,resert,home,myach,food;//спрайт
    SurfaceHolder holder;
    SurfaceThread1 thread;//поток
    Bitmap back;//фон
    Paint paint;
    Matrix matrix;
    Boolean clickPosiible=false;
    int j = 0;// кнопки
    int sost = 1;// состояние спрайта
    int mm = 0;// пузырики
    int drawzn = 0;
    boolean night=false;
    float coordx, coordy;
    Bitmap images;
    Sprite sprite;
    Sprite2 sprite2;
    Ball ball,ball1,ball2;
    float curX, curY,curXt;
    Live live=new Live();
    int globalX;
SpriteEnemy spriteEnemy,spriteEnemy1,spriteEnemy3,spriteEnemy2,spriteEnemy11;
    int c=0;

    MediaPlayer mPlayer= MediaPlayer.create(this.getContext(), R.raw.meow);
    int globalY;
    public GameSurface(Context context) {
        super(context);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               sost=4;
            }
        }, 0, 1000*60*10);//10 mинут

        trees = BitmapFactory.decodeResource(getResources(),R.drawable.tree);
        flower=BitmapFactory.decodeResource(getResources(),R.drawable.flower);
        confused = BitmapFactory.decodeResource(getResources(), R.drawable.confused);
        sleep = BitmapFactory.decodeResource(getResources(), R.drawable.sleep);
        left= BitmapFactory.decodeResource(getResources(), R.drawable.left);
        load= BitmapFactory.decodeResource(getResources(), R.drawable.load);
        q=BitmapFactory.decodeResource(getResources(), R.drawable.q);
        right= BitmapFactory.decodeResource(getResources(), R.drawable.right);
        walk = BitmapFactory.decodeResource(getResources(), R.drawable.walk);
        happy = BitmapFactory.decodeResource(getResources(), R.drawable.happy);
        normal = BitmapFactory.decodeResource(getResources(), R.drawable.normal);
        myach = BitmapFactory.decodeResource(getResources(), R.drawable.ball1);
        sad = BitmapFactory.decodeResource(getResources(), R.drawable.sad);
        done = BitmapFactory.decodeResource(getResources(), R.drawable.done);
        eat = BitmapFactory.decodeResource(getResources(), R.drawable.eat);
        back2 = BitmapFactory.decodeResource(getResources(), R.drawable.back2);
        back = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        b7=BitmapFactory.decodeResource(getResources(), R.drawable.up);
        b1 = BitmapFactory.decodeResource(getResources(), R.drawable.b);
        b2 = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        b4 = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        b8=BitmapFactory.decodeResource(getResources(), R.drawable.game3);
        back5=BitmapFactory.decodeResource(getResources(), R.drawable.back5);
        b5=BitmapFactory.decodeResource(getResources(), R.drawable.nightb);
        r = BitmapFactory.decodeResource(getResources(), R.drawable.r);
        resert=BitmapFactory.decodeResource(getResources(), R.drawable.resert);
        food=BitmapFactory.decodeResource(getResources(), R.drawable.food);
        home=BitmapFactory.decodeResource(getResources(), R.drawable.home);
        holder = getHolder();
        b3 = BitmapFactory.decodeResource(getResources(), R.drawable.d);
        b6 = BitmapFactory.decodeResource(getResources(), R.drawable.day);
        holder.addCallback(this);
        back4=BitmapFactory.decodeResource(getResources(), R.drawable.back4);
        water = BitmapFactory.decodeResource(getResources(), R.drawable.water);
        back11 = BitmapFactory.decodeResource(getResources(), R.drawable.back1);
        images = BitmapFactory.decodeResource(getResources(), R.drawable.sprites);
        hand= BitmapFactory.decodeResource(getResources(), R.drawable.hand);
        dark= BitmapFactory.decodeResource(getResources(), R.drawable.night);
        int x = globalX; int y=globalY;
        curX=x/3;
        curXt=x-100;
        curY=y/3;
        ball=new Ball(myach,0,0);
        ball1=new Ball(myach,back.getWidth()/2,0);
        ball2=new Ball(myach,back.getWidth(),0);
        curY= (float) (y*1/2.5);
        sprite=new Sprite(images, -100, -100);
        sprite2=new Sprite2(happy,-100,-100);
        spriteEnemy =new SpriteEnemy(trees,0,0);//верх   (чётные сверху, нечётные снизу)
        spriteEnemy1 =new SpriteEnemy(trees,0,0);//низ
        spriteEnemy2 =new SpriteEnemy(trees,0,0);//верх
        spriteEnemy3 =new SpriteEnemy(trees,0,0);//низ
        spriteEnemy11 =new SpriteEnemy(flower,-100,-100);
    }

    public void play(){
        mPlayer.start();
    }
    public void stop(){
       mPlayer.stop();
    }


    //draw
    @Override
    public void draw(Canvas canvas) {
        //обьявление перемнных:
        super.draw(canvas);
         globalY=canvas.getHeight();
         globalX=canvas.getWidth();
        matrix = new Matrix();
        matrix.reset();
        float x = canvas.getWidth();
        float y = canvas.getHeight();
        float yb = back.getHeight();
        float xb = back.getWidth();
        float kx = x / xb;
        float ky = y / yb;// коэфиценты на которые будет растянута картинка в зависимости от экрана

        //фон
        matrix.setScale(kx, ky, 0, 0);
       //рисуем фон
        //матрицу резерт
        //коэфиценты растяжения кнопок
        int kbx=(int)canvas.getWidth()*1/4;
        int kCat=(int)canvas.getWidth()*1/2;

        Bitmap newb1 = Bitmap.createScaledBitmap(b1,kbx , kbx, false);//лучшее что я сделала в этом проете
        Bitmap newb2 = Bitmap.createScaledBitmap(b2,kbx , kbx, false);
        Bitmap newb3 = Bitmap.createScaledBitmap(b3,kbx , kbx, false);
        Bitmap newb4 = Bitmap.createScaledBitmap(b4,kbx , kbx, false);
        Bitmap newbq = Bitmap.createScaledBitmap(q,kbx*1/2 , kbx*1/2, false);
        Bitmap newleft = Bitmap.createScaledBitmap(left,kbx , kbx, false);
        Bitmap newbright = Bitmap.createScaledBitmap(right,kbx , kbx, false);
        Bitmap newhome = Bitmap.createScaledBitmap(home,kbx , kbx, false);
        Bitmap newresert = Bitmap.createScaledBitmap(resert,kbx , kbx, false);
        Bitmap newb5 = Bitmap.createScaledBitmap(b5,kbx , kbx, false);
        Bitmap newb6 = Bitmap.createScaledBitmap(b6,kbx , kbx, false);
        Bitmap newb7= Bitmap.createScaledBitmap(b7,kbx , kbx, false);
        Bitmap newb8= Bitmap.createScaledBitmap(b8,kbx , kbx, false);
        canvas.drawBitmap(back, matrix, paint);
        canvas.drawBitmap(newb8,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()-newb5.getHeight()),paint);
        canvas.drawBitmap(newb5,0,(int)(canvas.getHeight()-newb5.getHeight()),paint);
        canvas.drawBitmap(newb6,(int)(newb1.getWidth()),(int)(canvas.getHeight()-newb5.getHeight()),paint);
        canvas.drawBitmap(newb7,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()-newb5.getHeight()),paint);
        canvas.drawBitmap(newb1,0,(int)(canvas.getHeight()/1.6),paint);
        canvas.drawBitmap(newb2,(int)(newb1.getWidth()),(int)(canvas.getHeight()/1.6),paint);
        canvas.drawBitmap(newb3,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()/1.6),paint);
        canvas.drawBitmap(newb4,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()/1.6),paint);
        canvas.drawBitmap(newleft,0,(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
        canvas.drawBitmap(newbright,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
        canvas.drawBitmap(newresert,(int)(newb1.getWidth()),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
        canvas.drawBitmap(newhome,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
        canvas.drawBitmap(newbq,0,0,paint);


        //изменение состояний спрайта
        if (sost == 0) {
            Bitmap newCat = Bitmap.createScaledBitmap(happy,kCat , kCat, false);
            canvas.drawBitmap(newCat,(canvas.getWidth()/2)-newCat.getWidth()/2,canvas.getHeight()/2-newCat.getHeight()*7/6,paint);
        }
        if (sost == 1) {
            Bitmap newCat = Bitmap.createScaledBitmap(normal,kCat , kCat, false);
            canvas.drawBitmap(newCat,(canvas.getWidth()/2)-newCat.getWidth()/2,canvas.getHeight()/2-newCat.getHeight()*7/6,paint);
        }
        if (sost == 2) {
            Bitmap newCat = Bitmap.createScaledBitmap(sad,kCat , kCat, false);
            canvas.drawBitmap(newCat,(canvas.getWidth()/2)-newCat.getWidth()/2,canvas.getHeight()/2-newCat.getHeight()*7/6,paint);
        }
        if (sost == 3) {
            Bitmap newCat = Bitmap.createScaledBitmap(confused,kCat , kCat, false);
            canvas.drawBitmap(newCat,(canvas.getWidth()/2)-newCat.getWidth()/2,canvas.getHeight()/2-newCat.getHeight()*7/6,paint);
        }
        if (sost == 4) {
            Bitmap newCat = Bitmap.createScaledBitmap(sleep,kCat , kCat, false);
            canvas.drawBitmap(newCat,(canvas.getWidth()/2)-newCat.getWidth()/2,canvas.getHeight()/2-newCat.getHeight()*7/6,paint);
        }
        if (sost == 6) {
            Bitmap newCat = Bitmap.createScaledBitmap(done,kCat , kCat, false);
            canvas.drawBitmap(newCat,(canvas.getWidth()/2)-newCat.getWidth()/2,canvas.getHeight()/2-newCat.getHeight()*7/6,paint);
        }
        if (sost == 7) {
            Bitmap newCat = Bitmap.createScaledBitmap(eat,kCat , kCat, false);
            canvas.drawBitmap(newCat,(canvas.getWidth()/2)-newCat.getWidth()/2,canvas.getHeight()/2-newCat.getHeight()*7/6,paint);

        }



        //dearth
        if (live.getLive() <=0) {
            clickPosiible=true;

            canvas.drawBitmap(back, matrix, paint);
            canvas.drawBitmap(newbq,0,0,paint);
            canvas.drawBitmap(newb8,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb5,0,(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb6,(int)(newb1.getWidth()),(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb7,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb1,0,(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newb2,(int)(newb1.getWidth()),(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newb3,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newb4,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()/1.6),paint);

            canvas.drawBitmap(newleft,0,(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newbright,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newresert,(int)(newb1.getWidth()),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newhome,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newbq,0,0,paint);
        }

        if (j==5){
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPaint(paint);
            class DelayThread extends Thread {//режим ожидания для изменения спрайта
                public void run() {
                   try {
                        Thread.sleep(50000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    j=4;
                }}

            DelayThread delayThread = new DelayThread();
            delayThread.start();
        }


        //действия  при нажати кнопок
        if (j == 1) {// j - это сигнал для того чтобы спрайт поел
            live.LiveCPlus();///
            Bitmap food1 = Bitmap.createScaledBitmap(food,  (kbx*1/2), kbx*1/4, false);
            canvas.drawBitmap(food1, globalX*1/5, globalY*1/2, paint);//отрисовка еды
            class DelayThread extends Thread {//режим ожидания для изменения спрайта
                public void run() {
                    sost = 7;try {
                        Thread.sleep(2000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sost = 1;

                    j = 0; // спрайт ест
                    try {
                        Thread.sleep(2000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }play();
                    sost = 0;}}
            DelayThread delayThread = new DelayThread();
            delayThread.start();
        }



            if (j==2) {
                if (mm >= 1 && mm < 100) {//мытьё
                    sost = 3;
                    live.LiveCPlus();
                    Random rand = new Random();
                    Bitmap neww1 = Bitmap.createScaledBitmap(water,kbx , kbx, false);

                    canvas.drawBitmap(neww1, rand.nextInt(canvas.getWidth() ), rand.nextInt(canvas.getHeight()*1/2), paint);//
                    canvas.drawBitmap(neww1, rand.nextInt(canvas.getWidth() ), rand.nextInt(canvas.getHeight()*1/2), paint);//
                    canvas.drawBitmap(neww1, rand.nextInt(canvas.getWidth() ), rand.nextInt(canvas.getHeight()*1/2), paint);//
                    canvas.drawBitmap(neww1, rand.nextInt(canvas.getWidth() ), rand.nextInt(canvas.getHeight()*1/2), paint);//
                    canvas.drawBitmap(neww1, rand.nextInt(canvas.getWidth() ), rand.nextInt(canvas.getHeight()*1/2), paint);//
                    canvas.drawBitmap(neww1, rand.nextInt(canvas.getWidth() ), rand.nextInt(canvas.getHeight()*1/2), paint);//
                    canvas.drawBitmap(neww1, rand.nextInt(canvas.getWidth() ), rand.nextInt(canvas.getHeight()*1/2), paint);//
                    canvas.drawBitmap(neww1, rand.nextInt(canvas.getWidth() ), rand.nextInt(canvas.getHeight()*1/2), paint);//
                    canvas.drawBitmap(neww1, rand.nextInt(canvas.getWidth() ), rand.nextInt(canvas.getHeight()*1/2), paint);//
                    mm = mm + 1;
                }

            }

        if (j==8){
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.GREEN);
            canvas.drawBitmap(back5,matrix,paint);

            Random randomGenerator = new Random();
            canvas.drawBitmap(newb8,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb7,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newleft,0,(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newbright,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newhome,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newb5,0,(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb5,0,(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb6,(int)(newb1.getWidth()),(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb1,0,(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newb2,(int)(newb1.getWidth()),(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newb3,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newb4,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newleft,0,(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newbright,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newresert,(int)(newb1.getWidth()),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newhome,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newbq,0,0,paint);
            sprite2.draw(canvas);
            if (sprite2.getX()<-50){
                sprite2=new Sprite2(happy, globalX*1/3, (float) (globalY*1/2.5));
                sprite2.draw(canvas);
            }

            ball.draw(canvas);
            ball1.draw(canvas);
            ball2.draw(canvas);
            c=10000;
            ball.setTy(c);
            ball1.setTy(c);
            ball2.setTy(c);
            if (  Math.abs(sprite2.getX()-ball.getX())<=globalX*1/12 &&
                    Math.abs(sprite2.getY()-ball.getY())<=globalY*1/12
            ){
                j=0;
            }
            if (  Math.abs(sprite2.getX()-ball1.getX())<=globalX*1/12 &&
                    Math.abs(sprite2.getY()-ball1.getY())<=globalY*1/12){
                j=0;
            }
            if (  Math.abs(sprite2.getX()-ball2.getX())<=globalX*1/12 &&
                    Math.abs(sprite2.getY()-ball2.getY())<=globalY*1/12){
               j=0;
            }
            int randomInt1,randomInt2,randomInt3,randomInt4 ;
            randomInt1 = randomGenerator.nextInt(globalX);
            randomInt2 = randomGenerator.nextInt(globalX);
            randomInt3 = randomGenerator.nextInt(globalX);
            randomInt4= randomGenerator.nextInt(b1.getHeight()*2);

            while (Math.abs(randomInt2-randomInt1)<globalY/6){
                randomInt1 = randomGenerator.nextInt(globalX);
                randomInt2 = randomGenerator.nextInt(globalX);
            }
            while (Math.abs(randomInt3-randomInt1)<globalY/6){
                randomInt1 = randomGenerator.nextInt(globalX);
                randomInt3 = randomGenerator.nextInt(globalX);
            }
            while (Math.abs(randomInt3-randomInt2)<globalY/6){
                randomInt2 = randomGenerator.nextInt(globalX);
                randomInt3 = randomGenerator.nextInt(globalX);
            }

            if (ball2.getY()>globalY){

                ball2=new Ball(myach,randomInt1,-randomInt4);
                ball2.draw(canvas);
                ball2.setTy(c);
            }
            if (ball.getY() > globalY){

                ball=new Ball(myach,randomInt2,-randomInt4);
                ball.draw(canvas);
                ball.setTy(c);
             }
}

        if (night==true){
            canvas.drawBitmap(dark,matrix,paint);
        }


if (j==7){
    Paint paint = new Paint();
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(Color.GREEN);
    if (sprite.getX()<-50){
        sprite=new Sprite(images, globalX*1/3, (float) (globalY*2/3.6));
        sprite.draw(canvas);
    }
    Random randomGenerator = new Random();
    int randomInt = randomGenerator.nextInt(back.getHeight()*1/6);
    canvas.drawPaint(paint);
    canvas.drawBitmap(back2,matrix,paint);
    canvas.drawBitmap(newb8,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()-newb5.getHeight()),paint);
    canvas.drawBitmap(newb7,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()-newb5.getHeight()),paint);
    canvas.drawBitmap(newb5,0,(int)(canvas.getHeight()-newb5.getHeight()),paint);
    canvas.drawBitmap(newb6,(int)(newb1.getWidth()),(int)(canvas.getHeight()-newb5.getHeight()),paint);
    canvas.drawBitmap(newb5,0,(int)(canvas.getHeight()-newb5.getHeight()),paint);
    canvas.drawBitmap(newb1,0,(int)(canvas.getHeight()/1.6),paint);
    canvas.drawBitmap(newb2,(int)(newb1.getWidth()),(int)(canvas.getHeight()/1.6),paint);
    canvas.drawBitmap(newb3,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()/1.6),paint);
    canvas.drawBitmap(newb4,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()/1.6),paint);
    canvas.drawBitmap(newleft,0,(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
    canvas.drawBitmap(newbright,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
    canvas.drawBitmap(newresert,(int)(newb1.getWidth()),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
    canvas.drawBitmap(newhome,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
    Bitmap newback2 = Bitmap.createScaledBitmap(back2,kbx*4 , (int) (kbx*4.9), false);
    canvas.drawBitmap(newbq,0,0,paint);


        sprite.draw(canvas);

    spriteEnemy.draw(canvas);
    spriteEnemy1.draw(canvas);
    spriteEnemy2.draw(canvas);
    spriteEnemy3.draw(canvas);
    spriteEnemy1.setTx(-10000);
    spriteEnemy.setTx(-10000);
    spriteEnemy2.setTx(-1000);
    spriteEnemy3.setTx(-10000);



    if (  Math.abs(sprite.getX()-spriteEnemy.getX())<=globalX*1/10 &&
            Math.abs(sprite.getY()-spriteEnemy.getY())<=globalY*1/10
    ){

        sprite=new Sprite(images, globalX*1/3, (float) (globalY*2/3.6));
        sprite.draw(canvas);
        j=0;

    }

    if (  Math.abs(sprite.getX()-spriteEnemy1.getX())<=globalX*1/10 &&
            Math.abs(sprite.getY()-spriteEnemy1.getY())<=globalY*1/10
    ){

        sprite=new Sprite(images, globalX*1/3, (float) (globalY*2/3.6));
        sprite.draw(canvas);
        j=0;

    }
    if (  Math.abs(sprite.getX()-spriteEnemy2.getX())<=globalX*1/10 &&
            Math.abs(sprite.getY()-spriteEnemy2.getY())<=globalY*1/10
    ){

        sprite=new Sprite(images, globalX*1/3, (float) (globalY*2/3.6));
        sprite.draw(canvas);
        j=0;

    }


    randomInt = randomGenerator.nextInt(globalY*1/2);
    int randomInt1 = randomGenerator.nextInt(globalX * 1 / 5);


    if (spriteEnemy.getX()<-globalX*1/3){
         randomInt1 = randomGenerator.nextInt(globalX * 1 / 5);
        randomInt = randomGenerator.nextInt(globalY*1/2);
        spriteEnemy=new SpriteEnemy(trees, globalX+randomInt1*2, 0+randomInt);//верх
        spriteEnemy.draw(canvas);
    spriteEnemy.setTx(0);}

    if (spriteEnemy1.getX() < -globalX*1/3){//низ
        randomInt1 = randomGenerator.nextInt(globalX * 1 / 5);
        randomInt = randomGenerator.nextInt(globalY*1/2);
        spriteEnemy1=new SpriteEnemy(trees, globalX+randomInt1, randomInt);
        spriteEnemy1.draw(canvas);
        spriteEnemy1.setTx(0);}

    if (spriteEnemy2.getX() <-globalX*1/3){//верх
         randomInt1 = randomGenerator.nextInt(globalX * 1 / 5);
        randomInt = randomGenerator.nextInt(globalY*1/2);
        spriteEnemy2=new SpriteEnemy(trees, globalX+randomInt1*2, 0+randomInt);
        spriteEnemy2.draw(canvas);
        spriteEnemy2.setTx(0);}
    if (sprite.getX()==spriteEnemy.getX()){j=0;}
    }

        if (j==3){
            canvas.drawBitmap(back, matrix, paint);
            canvas.drawBitmap(newb8,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb5,0,(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb7,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb6,(int)(newb1.getWidth()),(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb1,0,(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newb2,(int)(newb1.getWidth()),(int)(canvas.getHeight()/1.6),paint);

            canvas.drawBitmap(newb3,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newb4,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newleft,0,(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newbright,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newresert,(int)(newb1.getWidth()),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newhome,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            Bitmap newCat = Bitmap.createScaledBitmap(normal,kCat , kCat, false);
            canvas.drawBitmap(newbq,0,0,paint);
            canvas.drawBitmap(newCat,(canvas.getWidth()/2)-newCat.getWidth()/2,canvas.getHeight()/2-newCat.getHeight()*7/6,paint);
        }


        if (j==4){
            canvas.drawBitmap(back, matrix, paint);
            canvas.drawBitmap(newb8,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb1,0,(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newb7,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb5,0,(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb6,(int)(newb1.getWidth()),(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb2,(int)(newb1.getWidth()),(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newb3,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newb4,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newleft,0,(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newbright,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newresert,(int)(newb1.getWidth()),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newhome,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            Bitmap newCat = Bitmap.createScaledBitmap(normal,kCat , kCat, false);

            canvas.drawBitmap(newbq,0,0,paint);
            canvas.drawBitmap(newCat,(canvas.getWidth()/2)-newCat.getWidth()/2,canvas.getHeight()/2-newCat.getHeight()*7/6,paint);
        }
        if (j==10) {
            canvas.drawBitmap(back4,matrix,paint);
            canvas.drawBitmap(newb8,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb1,0,(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newb7,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb5,0,(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb6,(int)(newb1.getWidth()),(int)(canvas.getHeight()-newb5.getHeight()),paint);
            canvas.drawBitmap(newb2,(int)(newb1.getWidth()),(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newb3,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newb4,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()/1.6),paint);
            canvas.drawBitmap(newleft,0,(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newbright,(int)(newb1.getWidth()*3),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newresert,(int)(newb1.getWidth()),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            canvas.drawBitmap(newhome,(int)(newb1.getWidth()*2),(int)(canvas.getHeight()/1.6)+(int)newb4.getWidth(),paint);
            Bitmap newCat = Bitmap.createScaledBitmap(normal,kCat , kCat, false);
            Random randomGenerator = new Random();
            canvas.drawBitmap(newbq,0,0,paint);
            int randomInt = randomGenerator.nextInt(globalX*1/4);
            if (sprite.getX()<0){
                sprite=new Sprite(images, globalX*1/4, (float) (globalY*1/2.8));
                sprite.draw(canvas);
            }
            sprite.draw(canvas);
            if (sprite.getY()<=globalY*1/6){
                sprite.setTy(10000);}
           if (sprite.getY()>globalY*1/2.8){
                sprite.stop(); // прыжок
            }

            spriteEnemy11.draw(canvas);

            spriteEnemy11.setTx(-10000);

            if (  Math.abs(sprite.getX()-spriteEnemy11.getX())<=globalX*1/10 &&
                    Math.abs(sprite.getY()-spriteEnemy11.getY())<=globalY*1/10
            ){

                sprite=new Sprite(images, globalX*1/4, (float) (globalY*1/6));
                sprite.draw(canvas);
                j=0;

            }


            if (spriteEnemy11.getX()<-globalX*1/3-randomInt){

                spriteEnemy11=new SpriteEnemy(flower, globalX+randomInt*2, (float) (globalY*1/2.8));
                spriteEnemy11.draw(canvas);
                randomInt = randomGenerator.nextInt(60 - 40 + 1) + 20;
                spriteEnemy11.setScore(randomInt);
                spriteEnemy11.setTx(0);}

            if (sprite.getX()==spriteEnemy11.getX()){j=0;}




        }
        if (j==11){
            canvas.drawBitmap(load,matrix,paint);
            class DelayThread extends Thread {
                public void run() {
                    try {
                        Thread.sleep(15000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    j = 0;
                    }}
            DelayThread delayThread = new DelayThread();
            delayThread.start();
        }

        if (drawzn==1){
            int k=(int)canvas.getWidth()*1/9;
            Bitmap newHand = Bitmap.createScaledBitmap(hand,k , k, false);
            //matrix.setScale(1/2, 1/2, coordx, coordy+100);
            canvas.drawBitmap(newHand, coordx,coordy, paint);}}

    @SuppressLint("SuspiciousIndentation")
    @Override
    public boolean onTouchEvent(MotionEvent event)//кнопки
    {
        drawzn=1;
        coordx = event.getX();
        coordy = event.getY();
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float backX= back.getWidth();
                float backY = back.getHeight();




                if (x>globalX*3/11&&x<globalX*6.5/11&&y>globalY*1/8&&y<globalY*7/15) {
                    play();
                //на спрайт можно кликать и он будет изменять состояние 2
                    class DelayThread extends Thread {

                        public void run() {
                            sost = 7;
                            try {
                                Thread.sleep(5000);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            sost = 1;}
                    }  DelayThread delayThread = new DelayThread();
                    delayThread.start();
                }
                if (x>globalX*6.5/11&&x<globalX*8/11&&y>globalY*3.5/15&&y<globalY*7/15) {
                    //на спрайт можно кликать и он будет изменять состояние 1
                    class DelayThread extends Thread {

                        public void run() {
                            sost = 2;
                            try {
                                Thread.sleep(3000);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            sost = 1;}
                    }  DelayThread delayThread = new DelayThread();
                    delayThread.start();
                }


                    if (x>0 && x<globalX*1/4 && y> globalY*0.6 && y< globalY*0.75 ) {//1 кнопка ////////////
                        j = 1;
                        sost=1;
                        live.LiveCPlus();

                    }
                   if (x>globalX*1/4 && x<globalX*2/4 && y> globalY*0.6 && y< globalY*0.75 ) {//2 кнопка
                      j=2;
                     mm=1;
                        live.LiveCPlus();
                   }
                    if (x>globalX*2/4 && x<globalX*3/4 && y> globalY*0.6 && y< globalY*0.75 ) {//3 кнопка
                        j=7;//2game
                        live.LiveCPlus();
                    }
                    if (x>globalX*3/4 && x<globalX&&y> globalY*0.6 && y< globalY*0.75 ) {//4 кнопка
                        j=8;
                        live.LiveCPlus();

                   }
//
                 if (x>0 && x<globalX*1/4 && y> globalY*0.75 && y< globalY*6/7){//вверх влево
                      sprite.setTy(0);
                       sprite2.setTx(0);

                    }
//
                  if (x>globalX*1/4 && x<globalX*2/4 && y> globalY*0.75 && y< globalY*6/7 && clickPosiible==true){  //резерт
                        j=5;
                     live.setLive(1000);
//
                   }
                    if (x>globalX*2/4 && x<globalX*3/4 && y> globalY*0.75 && y< globalY*6/7){  //домой
                        j=0;
                        live.LiveCPlus();

                    }

                    if (x>globalX*3/4 && x<globalX && y> globalY*0.75 && y< globalY*6/7){//вниз вправо
                        sprite.setTy(100000);
                       sprite2.setTx(globalX);
                     }


                if (x>0 && x<globalX*1/4 && y>globalY*0.9 ) {//5 кнопка ////////////
                  night=true;

                }
                if (x>globalX*1/4 && x<globalX*2/4 && y>globalY*0.9 ) {//6 кнопка ////////////
                    night=false;
                    sost=1;

                }

                if ( x>globalX*3/4 && x<globalX&& y>globalY*0.9 ) {//6 кнопка ////////////
                 j=10;

                }
                if ( x>globalX*2/4 && x<globalX*3/4&& y>globalY*0.9 ) {//6 кнопка ////////////

                    sprite.setTy(globalY*1/9);

                }

                if ( x>0 && x<globalX*1/8&& y<globalX*1/8) {// информация ////////////

                   j=11;

                }


                    return true;
        }return false;}
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {thread = new SurfaceThread1(holder, this);thread.setRunning(true);thread.start();}
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {


    }
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {thread.setRunning(false);boolean retry = true;while (retry) {try {thread.join();retry = false;} catch (InterruptedException e) {throw new RuntimeException(e);}}}

    public static class Live{
        int live=20000;
        public int getLive() {
            return live;
        }
        public int LiveCMin() {
            return live-1000;
        }

        public int LiveCPlus() {
            return live+1000;
        }

        public void setLive(int live) {
            this.live = live;
        }

        // 1 минута - -100 очков, 14*24*60=20160
    }
}