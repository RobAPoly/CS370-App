//package com.example.matthew.testinggl;dont think this works with our project package
package cs370.team2;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Matthew on 3/10/2016.
 */
public class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer mRenderer;
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

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:

                Log.i("Yes", "Horray you have clicked and I know I have been clicked\n" +
                        "x = " + x + "\ny = " + y + "\n");
                mRenderer.ChangeColor(x, y);

                requestRender();
        }
        return true;
    }

}
