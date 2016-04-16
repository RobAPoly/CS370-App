//package com.example.matthew.testinggl; dont think this works with our project pkackage
package cs370.team2;

import android.app.DialogFragment;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import cs370.team2.R;

//public class GameActivity extends AppCompatActivity {
    public class GameActivity extends AppCompatActivity implements MyDialogFragment.Communicator{
    private int level=0;
    private int score=0;
    //private GLSurfaceView mGLView;
    private MyGLSurfaceView testing;
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
    //ThreadGroup squareGroup;
    Thread colOne;
    Thread colTwo;
    Thread colThree;
    Thread colFour;
    Thread colFive;
    Thread colSix;
    //private final MyGLRenderer mRenderer;
    //Random values
    Random rand = new Random();
    //int rVal = 0;
    boolean win = true;
    boolean testBL = false;
    long timeVal = 2000;
    double timeMult = 1;
    boolean end = false;
    int sqMin = 0, sqMax = 3;
    int oldLevel=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
         int height = displaymetrics.heightPixels;
         int width = displaymetrics.widthPixels;

        testing = new MyGLSurfaceView(this);
        testing.getit(height,width);

        //Makes activity ignore xml file
        //ignoring its layout Im eric
        // testing on how to combine xml and openGL caleb
        //mGLView=new MyGLSurfaceView(this);


        //setContentView(mGLView);

        setContentView(R.layout.content_game);
       // mGLView = (MyGLSurfaceView)findViewById(R.id.glSurfaceViewID);
        testing = (MyGLSurfaceView)findViewById(R.id.glSurfaceViewID);
        final TextView t = (TextView)findViewById(R.id.levelnum);


        //Thread to update UI with level and score
        final Thread t1 = new Thread() {

            @Override
            //test to update score and level using functions of glrendere
             public void run() {
                try {
                    while (!isInterrupted()) {
                        if(end){
                            interrupt();

                        }
                        Thread.sleep(1);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                updateLevelNumber(level);
                                updateScore();




                                }

                        });
                    }
                } catch (InterruptedException e) {
                    //if the game has ended, show dialogl
                    showDialog();

                }
            }
        };

        t1.start();

        //think everything below this is garbage
        //except maybe toolbar
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        /*
        if(testing.test() == true){
            String u = "You lose";
            Toast.makeText(this,u,Toast.LENGTH_LONG).show();
        }
        *///BEGIN TIMER STUFF
        colOne = new Thread() {
            float a[] = {0.2f, 0.709803922f, 0.898039216f, 1.0f};//BLUE
            float b[] = {.0f, 0.0f, 0.0f, .0f};//BLACK
            int i;
            public void run() {
                while(!interrupted()) {
                    for(i = col1Val; i<col1Val+9; i++){
                        try {Thread.sleep(timeVal);}
                        catch (InterruptedException e)
                        {e.printStackTrace();}
                        // if the colored square was pressed, restart the loop
                        if(testing.getState(i-1) == false){
                            if(i>col1Val) {
                                testing.changeCol(i - 1, b);
                                // if the square wasnt pressed, set the value of it to 0
                                testing.setArrVal(i - 1, 0);

                                testing.changeCol(i, a);
                                // the new colored square will be a valued square
                                testing.setArrVal(i, 2);
                                // if colored square reached last square, end the game
                                if(i == col1Val+8){
                                    end = true;
                                    return; }
                            }
                        }
                        else{
                            // if the colored square is pressed, set the state back to false
                            testing.setState(i-1,false);
                            i = col1Val;
                            if(level>oldLevel) {


                            }
                            //return;
                        }
                    }}}};
        colTwo = new Thread() {
            float a[] = {0.2f, 0.709803922f, 0.898039216f, 1.0f};//BLUE
            float b[] = {.0f, 0.0f, 0.0f, .0f};//BLACK
            int i;
            public void run() {
                while(!interrupted()) {
                    for(i = col2Val; i<col2Val+9; i++){
                        try {Thread.sleep(timeVal);}
                        catch (InterruptedException e)
                        {e.printStackTrace();}
                        // if the colored square was pressed, restart the loop
                        if(testing.getState(i-1) == false){
                            if(i>col2Val) {
                                testing.changeCol(i - 1, b);
                                // if the square wasnt pressed, set the value of it to 0
                                testing.setArrVal(i - 1, 0);
                                testing.changeCol(i, a);
                                // the new colored square will be a valued square
                                testing.setArrVal(i, 2);
                                // if colored square reached last square, end the game
                                if(i == col2Val+8){
                                    end = true;
                                    return; }
                            }
                        }
                        else{
                            // if the colored square is pressed, set the state back to false
                            testing.setState(i-1,false);
                            i = col2Val;
                            if(level>oldLevel){
                            increaseMax();
                            increaseSpeed();
                                oldLevel++;
                            }
                            //return;
                        }
                    }}}};
        colThree = new Thread() {
            float a[] = {0.2f, 0.709803922f, 0.898039216f, 1.0f};//BLUE
            float b[] = {.0f, 0.0f, 0.0f, .0f};//BLACK
            int i;
            public void run() {
                while(!interrupted()) {
                    for(i = col3Val; i<col3Val+9; i++){
                        try {Thread.sleep(timeVal);}
                        catch (InterruptedException e)
                        {e.printStackTrace();}
                        // if the colored square was pressed, restart the loop
                        if(testing.getState(i-1) == false){
                            if(i>col3Val) {
                                testing.changeCol(i - 1, b);
                                // if the square wasnt pressed, set the value of it to 0
                                testing.setArrVal(i - 1, 0);

                                testing.changeCol(i, a);
                                // the new colored square will be a valued square
                                testing.setArrVal(i, 2);
                                // if colored square reached last square, end the game
                                if(i == col3Val+8){
                                    end = true;
                                    return; }
                            }
                        }
                        else{
                            // if the colored square is pressed, set the state back to false
                            testing.setState(i-1,false);
                            i = col3Val;
                            if(level>oldLevel) {
                                increaseMax();
                                increaseSpeed();
                                oldLevel++;
                            }
                            //return;
                        }
                    }}}};
        colFour = new Thread() {
            float a[] = {0.2f, 0.709803922f, 0.898039216f, 1.0f};//BLUE
            float b[] = {.0f, 0.0f, 0.0f, .0f};//BLACK
            int i;
            public void run() {
                while(!interrupted()) {
                    for(i = col4Val; i<col4Val+9; i++){
                        try {Thread.sleep(timeVal);}
                        catch (InterruptedException e)
                        {e.printStackTrace();}
                        // if the colored square was pressed, restart the loop
                        if(testing.getState(i-1) == false){
                        if(i>col4Val) {
                            testing.changeCol(i - 1, b);
                            // if the square wasnt pressed, set the value of it to 0
                            testing.setArrVal(i - 1, 0);

                            testing.changeCol(i, a);
                            // the new colored square will be a valued square
                            testing.setArrVal(i, 2);
                            // if colored square reached last square, end the game
                            if(i == col4Val+8){
                                end = true;
                                return; }
                        }
                        }
                    else{
                            // if the colored square is pressed, set the state back to false
                            testing.setState(i-1,false);
                            i = col4Val;
                            if(level>oldLevel) {
                                increaseMax();
                                increaseSpeed();
                                oldLevel++;
                            }
                            //return;
                        }
                    }}}};
        colFive = new Thread() {
            float a[] = {0.2f, 0.709803922f, 0.898039216f, 1.0f};//BLUE
            float b[] = {.0f, 0.0f, 0.0f, .0f};//BLACK
            int i;
            public void run() {
                while(!interrupted()) {
                    for(i = col5Val; i<col5Val+9; i++){
                        try {Thread.sleep(timeVal);}
                        catch (InterruptedException e)
                        {e.printStackTrace();}
                        // if the colored square was pressed, restart the loop
                        if(testing.getState(i-1) == false){
                            if(i>col5Val) {
                                testing.changeCol(i - 1, b);
                                // if the square wasnt pressed, set the value of it to 0
                                testing.setArrVal(i - 1, 0);

                                testing.changeCol(i, a);
                                // the new colored square will be a valued square
                                testing.setArrVal(i, 2);
                                // if colored square reached last square, end the game
                                if(i == col5Val+8){
                                    end = true;
                                    return; }
                            }
                        }
                        else{
                            // if the colored square is pressed, set the state back to false
                            testing.setState(i-1,false);
                            i = col5Val;
                            if(level>oldLevel) {
                                increaseMax();
                                increaseSpeed();
                                oldLevel++;
                            }
                            //return;
                        }
                    }}}};
        colSix = new Thread() {
            float a[] = {0.2f, 0.709803922f, 0.898039216f, 1.0f};//BLUE
            float b[] = {.0f, 0.0f, 0.0f, 0.0f};//BLACK
            int i;
            public void run() {
                while(!interrupted()) {
                    for(i = col6Val; i<col6Val+9; i++){
                        try {Thread.sleep(timeVal);}
                        catch (InterruptedException e)
                        {e.printStackTrace();}
                        // if the colored square was pressed, restart the loop
                        if(testing.getState(i-1) == false){
                            if(i>col6Val) {
                                testing.changeCol(i - 1, b);
                                // if the square wasnt pressed, set the value of it to 0
                                testing.setArrVal(i - 1, 0);

                                testing.changeCol(i, a);
                                // the new colored square will be a valued square
                                testing.setArrVal(i, 2);
                                // if colored square reached last square, end the game
                                if(i == col6Val+8){
                                    end = true;
                                    return; }
                            }
                        }
                        else{
                            // if the colored square is pressed, set the state back to false
                            testing.setState(i-1,false);
                            i = col6Val;
                            if(level>oldLevel) {
                                increaseMax();
                                increaseSpeed();
                                oldLevel++;
                            }
                            //return;
                        }
                    }}}};
       //colOne.start();
        colTwo.start();
        // colThree.start();
        //colFour.start();
         //colFive.start();
        //colSix.start();
    }
//TO DO LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL}

    //Call this method to begin anew block once
    //an old block is destroyed along with its timer
    void startSQThread()
    {int current = activeCount();
        int randVal= rand.nextInt(6)+1;
        if (current < sqMax) {
            if (randVal == 1 && !colOne.isAlive()) {
                //colOne.start();
            }
            else if (randVal == 2 && !colTwo.isAlive())
                colTwo.start();
            else if (randVal == 3 && !colThree.isAlive())
                colThree.start();
            else if (randVal == 4 && !colFour.isAlive())
                colFour.start();
            else if (randVal == 5 && !colFive.isAlive())
                colFive.start();
            else if(!colSix.isAlive())
                colSix.start();
        }
    }

    //used in sq thread
    int activeCount()
    {   int onSq = 0;
        if(colOne.isAlive())
            onSq++;
        if(colTwo.isAlive())
            onSq++;
        if(colThree.isAlive())
            onSq++;
        if(colFour.isAlive())
            onSq++;
        if(colFive.isAlive())
            onSq++;
        return onSq;
    }

    //Checks score and increases max accordingly
    void increaseMax()
    {for(int i = activeCount(); i<level % 200 && sqMax != 6; i++ ) {sqMax++; }
        if(activeCount() < sqMax )
            startSQThread();
    }

    //increase speed of squares by making sleep time less
    //use This after increaseLevel
    //changes to denominator can and should be made
    //hell, any other equation will do
    void increaseSpeed(){
        timeVal = timeVal/ (long)1.8;

    }

    //use this function to reset the counter
    //it sets the for loop to colXVal
    //so it begins again at its apropriate vaariable
    //send block int that has been hit






   /* private final void Update() {
        new Thread(new Runnable() {
                    @Override
                    public void run() {
            //runOnUiThread(new Runnable() {

              //  public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    final TextView t = (TextView)findViewById(R.id.levelnum);
                        final TextView y = (TextView)findViewById(R.id.score);
                    t.post(new Runnable() {
                        @Override
                        public void run() {
                            t.setText("Hi");
                            y.setText("200");
                        }
                    });


                }
            }).start();


        }
*/
    //private final Handler mHandler = new Handler();
    public void updateLevelNumber(int ex){
        TextView textView = (TextView) findViewById(R.id.levelnum);
        if(testing.incrementLevel()== true){
            level++;

        }
        textView.setText(Integer.toString(ex));

    }

    public void updateScore(){
        TextView textView = (TextView) findViewById(R.id.score);
        //sc = testing.getScore();
        textView.setText(Integer.toString(testing.getScore()));

    }
    // dialog fragment functions
    public void showDialog(){
        final DialogFragment myFragment = new MyDialogFragment();
        myFragment.show(getFragmentManager(), "Game Over");
    }

    public void MainMenu(){

        finish();
    }

    public void TryAgain(){
    recreate();
    }


}
