//package com.example.matthew.testinggl;dont think this works with our project package
package cs370.team2;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created by Matthew on 3/10/2016.
 */
public class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer2 mRenderer;
    public MyGLSurfaceView(Context context) {
        super(context);
        //have to choose config
        super.setEGLConfigChooser(8,8,8,8,16,0);
        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer= new MyGLRenderer2();
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
        mRenderer= new MyGLRenderer2();

        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

    }

}
