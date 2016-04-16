package cs370.team2;

import android.content.Context;
import android.graphics.Point;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Arrays;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import cs370.team2.Square;



/**
 * Created by Matthew on 3/10/2016.
 */


public class MyGLRenderer implements GLSurfaceView.Renderer{
    private static final String TAG = "MyGLRenderer";
    private final float[] mMVPMatrix = new float[16];
    private   int score = 0;
    private  int testscore = 500;
    private final float[] mViewMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private Square[] arrSquare= new Square[54];
    private int[] arrSquareVal = new int[54];

    private float[] b = {.0f,0.0f,0.0f,0.0f};

    private float[] b2 = {1f,0.8f,0.1f,0.7f};
    private float[] b3 = {.7f,1f,0.1f,0.7f};
    private float[] b4 = {.7f,0.8f,1f,0.7f};
    private float[] b5 = {1f,0.8f,1f,0.7f};
    private float[] b6 = {1f,1f,0.1f,0.7f};
    private float[] b7 = {.7f,.1f,1f,0.7f};
    private float[] b8 = {1f,1f,1f,0.7f};

    public int c = 0;
    private int h,w=0;
    //squares are .2 by .2 (Matthew)
    // .1 space left on the top and bottom of the screen (Matthew)
    //Row one (Matthew)
    // x, y, z
    private float coords0[] = {
            0.6f, 0.9f, 0.0f,   // top left
            0.6f, 0.7f, 0.0f,   // bottom left
            0.4f, 0.7f, 0.0f,   // bottom right
            0.4f, 0.9f, 0.0f }; // top right
    private float coords1[] = {
            0.6f, 0.7f, 0.0f,   // top left
            0.6f, 0.5f, 0.0f,   // bottom left
            0.4f, 0.5f, 0.0f,   // bottom right
            0.4f, 0.7f, 0.0f }; // top right
    private float coords2[] = {
            0.6f, 0.5f, 0.0f,   // top left
            0.6f, 0.3f, 0.0f,   // bottom left
            0.4f, 0.3f, 0.0f,   // bottom right
            0.4f, 0.5f, 0.0f }; // top right
    private float coords3[] = {
            0.6f, 0.3f, 0.0f,   // top left
            0.6f, 0.1f, 0.0f,   // bottom left
            0.4f, 0.1f, 0.0f,   // bottom right
            0.4f, 0.3f, 0.0f }; // top right
    private float coords4[] = {
            0.6f, 0.1f, 0.0f,   // top left
            0.6f, -0.1f, 0.0f,   // bottom left
            0.4f, -0.1f, 0.0f,   // bottom right
            0.4f, 0.1f, 0.0f }; // top right
    private float coords5[] = {
            0.6f, -0.1f, 0.0f,   // top left
            0.6f, -0.3f, 0.0f,   // bottom left
            0.4f, -0.3f, 0.0f,   // bottom right
            0.4f, -0.1f, 0.0f }; // top right
    private float coords6[] = {
            0.6f, -0.3f, 0.0f,   // top left
            0.6f, -0.5f, 0.0f,   // bottom left
            0.4f, -0.5f, 0.0f,   // bottom right
            0.4f, -0.3f, 0.0f }; // top right
    private float coords7[] = {
            0.6f, -0.5f, 0.0f,   // top left
            0.6f, -0.7f, 0.0f,   // bottom left
            0.4f, -0.7f, 0.0f,   // bottom right
            0.4f, -0.5f, 0.0f }; // top right
    private float coords8[] = {
            0.6f, -0.7f, 0.0f,   // top left
            0.6f, -0.9f, 0.0f,   // bottom left
            0.4f, -0.9f, 0.0f,   // bottom right
            0.4f, -0.7f, 0.0f }; // top right
    //Row 2 (Matthew)
    private float coords9[] = {
            0.4f, 0.9f, 0.0f,   // top left
            0.4f, 0.7f, 0.0f,   // bottom left
            0.2f, 0.7f, 0.0f,   // bottom right
            0.2f, 0.9f, 0.0f }; // top right
    private float coords10[] = {
            0.4f, 0.7f, 0.0f,   // top left
            0.4f, 0.5f, 0.0f,   // bottom left
            0.2f, 0.5f, 0.0f,   // bottom right
            0.2f, 0.7f, 0.0f }; // top right
    private float coords11[] = {
            0.4f, 0.5f, 0.0f,   // top left
            0.4f, 0.3f, 0.0f,   // bottom left
            0.2f, 0.3f, 0.0f,   // bottom right
            0.2f, 0.5f, 0.0f }; // top right
    private float coords12[] = {
            0.4f, 0.3f, 0.0f,   // top left
            0.4f, 0.1f, 0.0f,   // bottom left
            0.2f, 0.1f, 0.0f,   // bottom right
            0.2f, 0.3f, 0.0f }; // top right
    private float coords13[] = {
            0.4f, 0.1f, 0.0f,   // top left
            0.4f, -0.1f, 0.0f,   // bottom left
            0.2f, -0.1f, 0.0f,   // bottom right
            0.2f, 0.1f, 0.0f }; // top right
    private float coords14[] = {
            0.4f, -0.1f, 0.0f,   // top left
            0.4f, -0.3f, 0.0f,   // bottom left
            0.2f, -0.3f, 0.0f,   // bottom right
            0.2f, -0.1f, 0.0f }; // top right
    private float coords15[] = {
            0.4f, -0.3f, 0.0f,   // top left
            0.4f, -0.5f, 0.0f,   // bottom left
            0.2f, -0.5f, 0.0f,   // bottom right
            0.2f, -0.3f, 0.0f }; // top right
    private float coords16[] = {
            0.4f, -0.5f, 0.0f,   // top left
            0.4f, -0.7f, 0.0f,   // bottom left
            0.2f, -0.7f, 0.0f,   // bottom right
            0.2f, -0.5f, 0.0f }; // top right
    private float coords17[] = {
            0.4f, -0.7f, 0.0f,   // top left
            0.4f, -0.9f, 0.0f,   // bottom left
            0.2f, -0.9f, 0.0f,   // bottom right
            0.2f, -0.7f, 0.0f }; // top right
    //Row 3 (Matthew)
    private float coords18[] = {
            0.2f, 0.9f, 0.0f,   // top left
            0.2f, 0.7f, 0.0f,   // bottom left
            0.0f, 0.7f, 0.0f,   // bottom right
            0.0f, 0.9f, 0.0f }; // top right
    private float coords19[] = {
            0.2f, 0.7f, 0.0f,   // top left
            0.2f, 0.5f, 0.0f,   // bottom left
            0.0f, 0.5f, 0.0f,   // bottom right
            0.0f, 0.7f, 0.0f }; // top right
    private float coords20[] = {
            0.2f, 0.5f, 0.0f,   // top left
            0.2f, 0.3f, 0.0f,   // bottom left
            0.0f, 0.3f, 0.0f,   // bottom right
            0.0f, 0.5f, 0.0f }; // top right
    private float coords21[] = {
            0.2f, 0.3f, 0.0f,   // top left
            0.2f, 0.1f, 0.0f,   // bottom left
            0.0f, 0.1f, 0.0f,   // bottom right
            0.0f, 0.3f, 0.0f }; // top right
    private float coords22[] = {
            0.2f, 0.1f, 0.0f,   // top left
            0.2f, -0.1f, 0.0f,   // bottom left
            0.0f, -0.1f, 0.0f,   // bottom right
            0.0f, 0.1f, 0.0f }; // top right
    private float coords23[] = {
            0.2f, -0.1f, 0.0f,   // top left
            0.2f, -0.3f, 0.0f,   // bottom left
            0.0f, -0.3f, 0.0f,   // bottom right
            0.0f, -0.1f, 0.0f }; // top right
    private float coords24[] = {
            0.2f, -0.3f, 0.0f,   // top left
            0.2f, -0.5f, 0.0f,   // bottom left
            0.0f, -0.5f, 0.0f,   // bottom right
            0.0f, -0.3f, 0.0f }; // top right
    private float coords25[] = {
            0.2f, -0.5f, 0.0f,   // top left
            0.2f, -0.7f, 0.0f,   // bottom left
            0.0f, -0.7f, 0.0f,   // bottom right
            0.0f, -0.5f, 0.0f }; // top right
    private float coords26[] = {
            0.2f, -0.7f, 0.0f,   // top left
            0.2f, -0.9f, 0.0f,   // bottom left
            0.0f, -0.9f, 0.0f,   // bottom right
            0.0f, -0.7f, 0.0f }; // top right
    //Row 4 (Matthew)
    private float coords27[] = {
            0.0f, 0.9f, 0.0f,   // top left
            0.0f, 0.7f, 0.0f,   // bottom left
            -0.2f, 0.7f, 0.0f,   // bottom right
            -0.2f, 0.9f, 0.0f }; // top right
    private float coords28[] = {
            0.0f, 0.7f, 0.0f,   // top left
            0.0f, 0.5f, 0.0f,   // bottom left
            -0.2f, 0.5f, 0.0f,   // bottom right
            -0.2f, 0.7f, 0.0f }; // top right
    private float coords29[] = {
            0.0f, 0.5f, 0.0f,   // top left
            0.0f, 0.3f, 0.0f,   // bottom left
            -0.2f, 0.3f, 0.0f,   // bottom right
            -0.2f, 0.5f, 0.0f }; // top right
    private float coords30[] = {
            0.0f, 0.3f, 0.0f,   // top left
            0.0f, 0.1f, 0.0f,   // bottom left
            -0.2f, 0.1f, 0.0f,   // bottom right
            -0.2f, 0.3f, 0.0f }; // top right
    private float coords31[] = {
            0.0f, 0.1f, 0.0f,   // top left
            0.0f, -0.1f, 0.0f,   // bottom left
            -0.2f, -0.1f, 0.0f,   // bottom right
            -0.2f, 0.1f, 0.0f }; // top right
    private float coords32[] = {
            0.0f, -0.1f, 0.0f,   // top left
            0.0f, -0.3f, 0.0f,   // bottom left
            -0.2f, -0.3f, 0.0f,   // bottom right
            -0.2f, -0.1f, 0.0f }; // top right
    private float coords33[] = {
            0.0f, -0.3f, 0.0f,   // top left
            0.0f, -0.5f, 0.0f,   // bottom left
            -0.2f, -0.5f, 0.0f,   // bottom right
            -0.2f, -0.3f, 0.0f }; // top right
    private float coords34[] = {
            0.0f, -0.5f, 0.0f,   // top left
            0.0f, -0.7f, 0.0f,   // bottom left
            -0.2f, -0.7f, 0.0f,   // bottom right
            -0.2f, -0.5f, 0.0f }; // top right
    private float coords35[] = {
            0.0f, -0.7f, 0.0f,   // top left
            0.0f, -0.9f, 0.0f,   // bottom left
            -0.2f, -0.9f, 0.0f,   // bottom right
            -0.2f, -0.7f, 0.0f }; // top right
    //Row 5 (Matthew)
    private float coords36[] = {
            -0.2f, 0.9f, 0.0f,   // top left
            -0.2f, 0.7f, 0.0f,   // bottom left
            -0.4f, 0.7f, 0.0f,   // bottom right
            -0.4f, 0.9f, 0.0f }; // top right
    private float coords37[] = {
            -0.2f, 0.7f, 0.0f,   // top left
            -0.2f, 0.5f, 0.0f,   // bottom left
            -0.4f, 0.5f, 0.0f,   // bottom right
            -0.4f, 0.7f, 0.0f }; // top right
    private float coords38[] = {
            -0.2f, 0.5f, 0.0f,   // top left
            -0.2f, 0.3f, 0.0f,   // bottom left
            -0.4f, 0.3f, 0.0f,   // bottom right
            -0.4f, 0.5f, 0.0f }; // top right
    private float coords39[] = {
            -0.2f, 0.3f, 0.0f,   // top left
            -0.2f, 0.1f, 0.0f,   // bottom left
            -0.4f, 0.1f, 0.0f,   // bottom right
            -0.4f, 0.3f, 0.0f }; // top right
    private float coords40[] = {
            -0.2f, 0.1f, 0.0f,   // top left
            -0.2f, -0.1f, 0.0f,   // bottom left
            -0.4f, -0.1f, 0.0f,   // bottom right
            -0.4f, 0.1f, 0.0f }; // top right
    private float coords41[] = {
            -0.2f, -0.1f, 0.0f,   // top left
            -0.2f, -0.3f, 0.0f,   // bottom left
            -0.4f, -0.3f, 0.0f,   // bottom right
            -0.4f, -0.1f, 0.0f }; // top right
    private float coords42[] = {
            -0.2f, -0.3f, 0.0f,   // top left
            -0.2f, -0.5f, 0.0f,   // bottom left
            -0.4f, -0.5f, 0.0f,   // bottom right
            -0.4f, -0.3f, 0.0f }; // top right
    private float coords43[] = {
            -0.2f, -0.5f, 0.0f,   // top left
            -0.2f, -0.7f, 0.0f,   // bottom left
            -0.4f, -0.7f, 0.0f,   // bottom right
            -0.4f, -0.5f, 0.0f }; // top right
    private float coords44[] = {
            -0.2f, -0.7f, 0.0f,   // top left
            -0.2f, -0.9f, 0.0f,   // bottom left
            -0.4f, -0.9f, 0.0f,   // bottom right
            -0.4f, -0.7f, 0.0f }; // top right
    //Row 6 (Matthew)
    private float coords45[] = {
            -0.4f, 0.9f, 0.0f,   // top left
            -0.4f, 0.7f, 0.0f,   // bottom left
            -0.6f, 0.7f, 0.0f,   // bottom right
            -0.6f, 0.9f, 0.0f }; // top right
    private float coords46[] = {
            -0.4f, 0.7f, 0.0f,   // top left
            -0.4f, 0.5f, 0.0f,   // bottom left
            -0.6f, 0.5f, 0.0f,   // bottom right
            -0.6f, 0.7f, 0.0f }; // top right
    private float coords47[] = {
            -0.4f, 0.5f, 0.0f,   // top left
            -0.4f, 0.3f, 0.0f,   // bottom left
            -0.6f, 0.3f, 0.0f,   // bottom right
            -0.6f, 0.5f, 0.0f }; // top right
    private float coords48[] = {
            -0.4f, 0.3f, 0.0f,   // top left
            -0.4f, 0.1f, 0.0f,   // bottom left
            -0.6f, 0.1f, 0.0f,   // bottom right
            -0.6f, 0.3f, 0.0f }; // top right
    private float coords49[] = {
            -0.4f, 0.1f, 0.0f,   // top left
            -0.4f, -0.1f, 0.0f,   // bottom left
            -0.6f, -0.1f, 0.0f,   // bottom right
            -0.6f, 0.1f, 0.0f }; // top right
    private float coords50[] = {
            -0.4f, -0.1f, 0.0f,   // top left
            -0.4f, -0.3f, 0.0f,   // bottom left
            -0.6f, -0.3f, 0.0f,   // bottom right
            -0.6f, -0.1f, 0.0f }; // top right
    private float coords51[] = {
            -0.4f, -0.3f, 0.0f,   // top left
            -0.4f, -0.5f, 0.0f,   // bottom left
            -0.6f, -0.5f, 0.0f,   // bottom right
            -0.6f, -0.3f, 0.0f }; // top right
    private float coords52[] = {
            -0.4f, -0.5f, 0.0f,   // top left
            -0.4f, -0.7f, 0.0f,   // bottom left
            -0.6f, -0.7f, 0.0f,   // bottom right
            -0.6f, -0.5f, 0.0f }; // top right
    private float coords53[] = {
            -0.4f, -0.7f, 0.0f,   // top left
            -0.4f, -0.9f, 0.0f,   // bottom left
            -0.6f, -0.9f, 0.0f,   // bottom right
            -0.6f, -0.7f, 0.0f }; // top right

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //called once to set up the view's environment

        //set the coordinates for the squares (Matthew)
        //Column 1
        arrSquare[0]=new Square(coords0);
        arrSquare[1]=new Square(coords1);
        arrSquare[2]=new Square(coords2);
        arrSquare[3]=new Square(coords3);
        arrSquare[4]=new Square(coords4);
        arrSquare[5]=new Square(coords5);
        arrSquare[6]=new Square(coords6);
        arrSquare[7]=new Square(coords7);
        arrSquare[8]=new Square(coords8);

        //Column 2
        arrSquare[9]=new Square(coords9);
        arrSquare[10]=new Square(coords10);
        arrSquare[11]=new Square(coords11);
        arrSquare[12]=new Square(coords12);
        arrSquare[13]=new Square(coords13);
        arrSquare[14]=new Square(coords14);
        arrSquare[15]=new Square(coords15);
        arrSquare[16]=new Square(coords16);
        arrSquare[17]=new Square(coords17);

        // Column 3
        arrSquare[18]=new Square(coords18);
        arrSquare[19]=new Square(coords19);
        arrSquare[20]=new Square(coords20);
        arrSquare[21]=new Square(coords21);
        arrSquare[22]=new Square(coords22);
        arrSquare[23]=new Square(coords23);
        arrSquare[24]=new Square(coords24);
        arrSquare[25]=new Square(coords25);
        arrSquare[26]=new Square(coords26);

        //Column 4
        arrSquare[27]=new Square(coords27);
        arrSquare[28]=new Square(coords28);
        arrSquare[29]=new Square(coords29);
        arrSquare[30]=new Square(coords30);
        arrSquare[31]=new Square(coords31);
        arrSquare[32]=new Square(coords32);
        arrSquare[33]=new Square(coords33);
        arrSquare[34]=new Square(coords34);
        arrSquare[35]=new Square(coords35);

        //Column 5
        arrSquare[36]=new Square(coords36);
        arrSquare[37]=new Square(coords37);
        arrSquare[38]=new Square(coords38);
        arrSquare[39]=new Square(coords39);
        arrSquare[40]=new Square(coords40);
        arrSquare[41]=new Square(coords41);
        arrSquare[42]=new Square(coords42);
        arrSquare[43]=new Square(coords43);
        arrSquare[44]=new Square(coords44);

        //Column 6
        arrSquare[45]=new Square(coords45);
        arrSquare[46]=new Square(coords46);
        arrSquare[47]=new Square(coords47);
        arrSquare[48]=new Square(coords48);
        arrSquare[49] = new Square(coords49);
        arrSquare[50]=new Square(coords50);
        arrSquare[51]=new Square(coords51);
        arrSquare[52]=new Square(coords52);
        arrSquare[53]=new Square(coords53);


        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        float[] b1 = {.7f,0.8f,0.1f,0.7f};
        /*
        arrSquare[1].setColor(b1);
        arrSquare[8].setColor(b1);
        arrSquare[13].setColor(b1);
        arrSquare[21].setColor(b1);
        arrSquare[31].setColor(b1);
        arrSquare[40].setColor(b1);
        arrSquare[50].setColor(b1);
        arrSquare[51].setColor(b1);
        arrSquare[52].setColor(b1);


        arrSquareVal[1] = 2;
        arrSquareVal[8] = 2;
        arrSquareVal[13] = 2;
        arrSquareVal[21] = 2;
        arrSquareVal[31] = 2;
        arrSquareVal[40] = 2;
        arrSquareVal[50] = 2;
        arrSquareVal[51] = 2;
        arrSquareVal[52] = 2; */
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //called if geometry view changes ex orientation changes
        //This shouldn't happen with our app (Matthew)

        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;

        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //called on each redraw of the view
        String resp = "You lost";

        int i; //counter for loop (Matthew)

        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // Set the camera position (View matrix) (Matthew)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        //draws the grid (Matthew)
        for(i=0;i<arrSquare.length;i++) {
            arrSquare[i].draw(mMVPMatrix);

        }
    }



// <--------------------HERE------------


    // Added by Eric Horay I did something!
    public void ChangeColor(float x, float y,int h,int w) {


        Log.e("This "," IS "+w+" "+h);
        int column = 1;
        int row = 1;
        int squareToChange = 0;
        boolean inBounds = true;
        float[] a = {.5f, .5f, .5f, .5f};
        float[] b = {.0f, 0.0f, 0.0f, 0.0f};
        float[] b1 = {.7f, 0.8f, 0.1f, 0.7f};

        //this is bad code for testing it doesnt work properly
        //Eric u can use the the width and height though
        //i commented out ur previous if statements for testing u can delete mine though
        int newx = (int)(x-(w*.0833));
        int newy = (int) (y- (h*.039));
        int neww = (int)(w-(w*.1666));
        int newh = (int)(h-(h*.23));
        Log.i("new stuff is","IS "+newx +" "+ newy +" "+ neww +" "+ newh);
        if (newx > neww || x < .0833 || newy > newh || y < .039 ){// OUT OF BOUNDS
            inBounds = false;
            Log.i("OUT", "YOU CLICKED OUT OF BOUNDS."+ this.w+h);
        }
        //Find the x
        if (inBounds) {
            //if (x > 537*(w/480)) {//4, 5, 6
            if(newx> neww*.5){
                //if (x > 686*(w/480)) { //5, 6
                if(newx > neww*.666){
                    // if (x > 835*(w/480)) { //6
                    if(newx>neww*.833){
                        column = 6;
                        Log.i("6", "Column 6");
                    } else { //5
                        column = 5;
                        Log.i("5", "Column 5");
                    }
                } else { //4
                    column = 4;
                    Log.i("4", "Column 4");
                }
            }
            //else if (x > 239*(w/1080)) { //2, 3
            else if(newx > neww* .1666){
                //if (x > 388*(w/1080)) {//3
                if(newx > neww*.333){
                    column = 3;
                    Log.i("3", "Column 3");
                } else { //2
                    column = 2;
                    Log.i("2", "Column 2");
                }
            }
            //if (y > 810*(h/1776)) { //F, G, H, I
            if(newy>newh * .555){
                //if (y > 1106*(h/1776)) {//H, I
                if(newy>newh*.777){
                    //if (y > 1254*(h/1776)) { //I
                    if(newy>newh*.888){
                        row = 9;
                        Log.i("I", "Row I");
                    } else { // H
                        row = 8;
                        Log.i("H", "Row H");
                    }
                }
                // else if (y > 958*(h/1776)) { // G
                else if(newy>newh*.666){
                    row = 7;
                    Log.i("G", "Row  G");
                } else { //F
                    row = 6;
                    Log.i("F", "Row F");
                }
            }
            //else if (y > 514*(h/1776)) { //D, E
            else if(newy>newh*.333){
                //if (y > 662*(h/1776)) { //E
                if(newy>newh*.444){
                    row = 5;
                    Log.i("E", "Row E");
                } else { //D
                    row = 4;
                    Log.i("D", "Row D");
                }
            } //else if (y > 336*(h/1776)) { // C
            else if(newy>newh*.222){
                row = 3;
                Log.i("C", "Row C");
            } //else if (y > 218*(h/1776)) { //B
            else if(newy>newh*.111){
                row = 2;
                Log.i("B", "Row B");
            } else {
                Log.i("A", "Row A");
            }
            // Default row 1, A;


            //Figuring out which box it's going to change
            for (int i = 1; i < column; i++) {
                squareToChange += 9;
            }
            for (int j = 1; j < row; j++) {
                squareToChange++;
            }


            //if the square is 1, it needs to be clicked
            if(arrSquareVal[squareToChange] == 1) {
                arrSquare[squareToChange].setColor(b);
                score += 50;
                arrSquareVal[squareToChange] = 0;
            //if the square is 0, it has been clicked, and clicking it will lower score.
            } else if (arrSquareVal[squareToChange] == 0) {
                score -= 50;
            //if the square is 2, it's special and will grant extra points when pressed!
            } else if (arrSquareVal[squareToChange] == 2) {
                arrSquare[squareToChange].setColor(b);
                score += 100;
                arrSquareVal[squareToChange] = 0;
                arrSquare[squareToChange].setState(true);
            }

            // make sure the square is on
            /*
            if(Arrays.equals(arrSquare[squareToChange].getColor(), b1)) {
                arrSquare[squareToChange].setColor(b);
                score += 100;
            }
            */
        }
    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {

            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }

    /* useless
    public boolean checkColor() {
        int i;
        float a[] = { .0f, 0.0f, 0.0f, 0.0f};
        for (i = 8; i < arrSquare.length; i += 9) {
            if (arrSquare[i].getColor() != a) {
                return true;
            }


        }
        return false;4
    }*/
    public boolean incrementLevel(){
        if(score >= testscore){
            testscore= (int) (testscore*1.3);
            return true;
        }
        else
            return false;
    }
    public int getScore(){
        return score;
    }
/*
    public boolean ends(){
        if (score > 500){
            return true;
        }
        return false;
    } */
    //TIMER
    void setColorN(int square, float[] a)
    {arrSquare[square].setColor(a);}
    public int getArrVal(int p){
        return arrSquareVal[p];
    }
    public void setArrVal(int i,int p){
        arrSquareVal[i] = p;
    }
    public void setState(int i,boolean b){
        arrSquare[i].setState(b);
    }
    public boolean getState(int i){
       return arrSquare[i].getState();
    }


}
