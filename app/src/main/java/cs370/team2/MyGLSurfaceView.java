//package com.example.matthew.testinggl;dont think this works with our project package
package cs370.team2;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Matthew on 3/10/2016.
 */
public class MyGLSurfaceView extends GLSurfaceView {
//TIMER VALUES
Timer tOne = new Timer();
Timer tTwo = new Timer();
Timer tThree = new Timer();
Timer tFour = new Timer();
Timer tFive = new Timer();
Timer tEmpty = new Timer();
//#of BEGINNING VALUE FOR EACH COLUMN
static int col1Val = 0;
static int col2Val = 9;
static int col3Val = 18;
static int col4Val = 27;
static int col5Val = 36;
static int col6Val = 45;
//TEMP BOOLEAN VLAUES TO TEST IF A TIMER IS SCHEDULED TO RUN
boolean tOneRun = false;
boolean tTwoRun = false;
boolean tThreeRun = false;
boolean tFourRun = false;
boolean tFiveRun = false;
boolean tSixRun = false;
private final MyGLRenderer mRenderer;
//Random values
Random rand = new Random();
int rVal = 0;
boolean win = true;
boolean testBL = false;
long timeVal = 1000;
double timeMult = 1;
boolean end = false;
    public static int he,wi;

    public MyGLSurfaceView(Context context) {
        super(context);
        //have to choose config
        super.setEGLConfigChooser(8,8,8,8,16,0);
        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer= new MyGLRenderer();
        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs){
        super(context,attrs);
        super.setEGLConfigChooser(8,8,8,8,16,0);
        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer= new MyGLRenderer();

        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

    }
    public boolean incrementLevel(){

        if(mRenderer.incrementLevel()== true)
            return true;
        else
            return false;

    }
    public int getScore(){
        if(mRenderer.ends()){
            end = true;
        }
        return mRenderer.getScore();
    }
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;


    // Added by Eric and Caleb
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float x = e.getX();
        float y = e.getY();

        Point size=new Point(); //needs to be a Point object for getSize function
        //I think there is something where the getDisplay is not in older versions so we may need to raise the min specs
        getDisplay().getSize(size); //gives size the x and y maxs of the screen
        //to get the x and y out of it use "size.x" and "size.y"



        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:

                Log.i("Yes", "Horray you have clicked and I know I have been clicked\n" +
                        "x = " + x + "\ny = " + y + "\n");
               // mRenderer.ChangeColor(x, y,he,wi);

                requestRender();
                }
        return true;
    }
//BEGIN TIMER CODE
//TIMER CODE == WORKS BUT NEED TO PASS ARRAY BETER
    public void gameRunning() {
        int i = 0;
        //dose not work
        Square gSquare[] = mRenderer.getSquares();
        //   while (win)
        // {
        // System.out.println("Hereisout" + gSquare[0].toString());
        for (int x = 0; x < 5; x++) {
            rVal = 1;//rand.nextInt(6)+1;
            if (rVal == 1 && !tOneRun) {
                for (i = col1Val; i < col1Val + 9; i++) {
                    tOne.schedule(new oneTask(i), (long) (timeVal * timeMult));
                    //SHOULD USE THIS ONE BELOW THIS LINE
                    //tOne.schedule(new oneTask(i ,gSquare[i-1], gSquare[i],  ), (long) (timeVal * timeMult))
                }
                tOneRun = true;
            }
            //return false;
        }


    }

    class oneTask extends TimerTask {
        int i;
        Square onSquare;
        Square offSquare;
        float a[] = {0.2f, 0.709803922f, 0.898039216f, 1.0f};//NEED OTHER COLORS

        oneTask(int i, Square OnSquare) {
            this.i = i;
            this.onSquare = OnSquare;
        }

        oneTask(int i, Square offSquare, Square onSquare ) {
            this.i = i;
            this.offSquare = offSquare;
            this.onSquare = onSquare;
        }
        oneTask(int i) {
            this.i = i;
        }
        public void run() {
            if (i == 9) {
                //END GAME function
                tOneRun = false;
                tOne.cancel();
                tOne.purge();
                end = true;

            }
            if (i>0)//if square number is not o change the values
                    offSquare.setColor(a);
            onSquare.setColor(a);
            System.out.println("Hereisout :HELLO HERE NOW");
        }

    }

    public void endGame(){
        end = true;
    }
public void getit(int h,int w){
    he=h;
    wi=w;
}

}
