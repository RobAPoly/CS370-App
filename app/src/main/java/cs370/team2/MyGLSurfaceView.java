package cs370.team2;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Matthew on 3/10/2016.
 */
public class MyGLSurfaceView extends GLSurfaceView {
private final MyGLRenderer mRenderer;

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
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs){
        super(context,attrs);
        super.setEGLConfigChooser(8,8,8,8,16,0);
        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer= new MyGLRenderer();

        setRenderer(mRenderer);
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

               mRenderer.ChangeColor(x, y,he,wi);

                requestRender();
                }
        return true;
    }

public void getit(int h,int w){
    he=h;
    wi=w;
}
    public void changeCol(int i, float[] a){
        mRenderer.setColorN(i,a);}
   public int getArrVal(int p){
       return mRenderer.getArrVal(p);
   }
    public void setArrVal(int i,int p){
        mRenderer.setArrVal(i,p);
    }
    public void setState(int i,boolean b){
        mRenderer.setState(i,b);
    }
    public boolean getState(int i){
        return mRenderer.getState(i);
    }
}