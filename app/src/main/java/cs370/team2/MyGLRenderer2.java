package cs370.team2;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;
import android.widget.Toast;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import cs370.team2.Square;



/**
 * Created by Matthew on 3/10/2016.
 */


public class MyGLRenderer2 implements GLSurfaceView.Renderer{
    private static final String TAG = "MyGLRenderer";
    private final float[] mMVPMatrix = new float[16];
    private  static int score = 0;
    private static int testscore =500;
    private final float[] mViewMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private Square[] arrSquare= new Square[54];
    //squares are .2 by .2 (Matthew)
    // .1 space left on the top and bottom of the screen (Matthew)
    //Row one (Matthew)
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
        arrSquare[0]=new Square(coords0);
        arrSquare[1]=new Square(coords1);
        arrSquare[2]=new Square(coords2);
        arrSquare[3]=new Square(coords3);
        arrSquare[4]=new Square(coords4);
        arrSquare[5]=new Square(coords5);
        arrSquare[6]=new Square(coords6);
        arrSquare[7]=new Square(coords7);
        arrSquare[8]=new Square(coords8);
        arrSquare[9]=new Square(coords9);
        arrSquare[10]=new Square(coords10);
        arrSquare[11]=new Square(coords11);
        arrSquare[12]=new Square(coords12);
        arrSquare[13]=new Square(coords13);
        arrSquare[14]=new Square(coords14);
        arrSquare[15]=new Square(coords15);
        arrSquare[16]=new Square(coords16);
        arrSquare[17]=new Square(coords17);
        arrSquare[18]=new Square(coords18);
        arrSquare[19]=new Square(coords19);
        arrSquare[20]=new Square(coords20);
        arrSquare[21]=new Square(coords21);
        arrSquare[22]=new Square(coords22);
        arrSquare[23]=new Square(coords23);
        arrSquare[24]=new Square(coords24);
        arrSquare[25]=new Square(coords25);
        arrSquare[26]=new Square(coords26);
        arrSquare[27]=new Square(coords27);
        arrSquare[28]=new Square(coords28);
        arrSquare[29]=new Square(coords29);
        arrSquare[30]=new Square(coords30);
        arrSquare[31]=new Square(coords31);
        arrSquare[32]=new Square(coords32);
        arrSquare[33]=new Square(coords33);
        arrSquare[34]=new Square(coords34);
        arrSquare[35]=new Square(coords35);
        arrSquare[36]=new Square(coords36);
        arrSquare[37]=new Square(coords37);
        arrSquare[38]=new Square(coords38);
        arrSquare[39]=new Square(coords39);
        arrSquare[40]=new Square(coords40);
        arrSquare[41]=new Square(coords41);
        arrSquare[42]=new Square(coords42);
        arrSquare[43]=new Square(coords43);
        arrSquare[44]=new Square(coords44);
        arrSquare[45]=new Square(coords45);
        arrSquare[46]=new Square(coords46);
        arrSquare[47]=new Square(coords47);
        arrSquare[48]=new Square(coords48);
        arrSquare[49]=new Square(coords49);
        arrSquare[50]=new Square(coords50);
        arrSquare[51]=new Square(coords51);
        arrSquare[52]=new Square(coords52);
        arrSquare[53]=new Square(coords53);

        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);


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
        score+=100;
        float[] b = {.7f,0.7f,0.7f,0.7f};
        int i; //counter for loop (Matthew)
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // Set the camera position (View matrix) (Matthew)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        arrSquare[8].setColor(b);
        arrSquare[17].setColor(b);
        arrSquare[26].setColor(b);
        arrSquare[35].setColor(b);
        arrSquare[44].setColor(b);
        arrSquare[53].setColor(b);


        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        //draws the grid (Matthew)
        for(i=0;i<arrSquare.length;i++) {
            arrSquare[i].draw(mMVPMatrix);

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
    public boolean checkColor() {
        int i;
        float a[] = { 0.2f, 0.709803922f, 0.898039216f, 1.0f};
        for (i = 8; i < arrSquare.length; i += 9) {
            if (arrSquare[i].getColor() != a) {
                return true;
            }

        }
        return false;
    }
    public boolean incrementLevel(){
        if(score >= testscore){
            testscore+=500;
            return true;
        }
        else
            return false;
    }
    public int getScore(){
        return score;
    }
}
