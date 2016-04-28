package cs370.team2;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.widget.TextView;
import java.util.Random;


    public class GameActivity extends AppCompatActivity implements MyDialogFragment.Communicator{
    private int level=0;
    //private GLSurfaceView mGLView;
    private MyGLSurfaceView mGLView;
    //#of BEGINNING VALUE FOR EACH COLUMN
    static int col1Val = 0;
    static int col2Val = 9;
    static int col3Val = 18;
    static int col4Val = 27;
    static int col5Val = 36;
    static int col6Val = 45;
    //ThreadGroup squareGroup;
    boolean activeArr[] = new boolean[6];
    Thread colOne;
    Thread colTwo;
    Thread colThree;
    Thread colFour;
    Thread colFive;
    Thread colSix;
    //private final MyGLRenderer mRenderer;
    //Random values
    Random rand = new Random();
    double timeVal = 1500;
    boolean end = false;
    int oldLevel=0;
    boolean ended=false;
    final static DialogFragment myFragment = new MyDialogFragment();
    //For saving scores Matthew
    SharedPreferences prefs;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
         int height = displaymetrics.heightPixels;
         int width = displaymetrics.widthPixels;

        mGLView= new MyGLSurfaceView(this);
        mGLView.getit(height, width);

        prefs = this.getSharedPreferences("Scores", Context.MODE_PRIVATE);
        editor = prefs.edit();

        //Makes activity ignore xml file
        //ignoring its layout Im eric
        // testing on how to combine xml and openGL caleb
        setContentView(R.layout.content_game);
        mGLView = (MyGLSurfaceView)findViewById(R.id.glSurfaceViewID);

        TextView highScore = (TextView) findViewById(R.id.highScorenum);
        highScore.setText(Integer.toString(prefs.getInt("first",0)));

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
                    int moveOne,moveTwo;

                    //adds score to high scores if it's high enough
                    if(mGLView.getScore()>prefs.getInt("first",0))
                    {
                        moveOne=prefs.getInt("first",0);
                        editor.putInt("first",mGLView.getScore());
                        moveTwo=prefs.getInt("second",0);
                        editor.putInt("second",moveOne);
                        moveOne=prefs.getInt("third",0);
                        editor.putInt("third",moveTwo);
                        moveTwo=prefs.getInt("fourth",0);
                        editor.putInt("fourth",moveOne);
                        editor.putInt("fifth",moveTwo);
                    }
                    else if(mGLView.getScore()>prefs.getInt("second",0))
                    {
                        moveOne=prefs.getInt("second",0);
                        editor.putInt("second",mGLView.getScore());
                        moveTwo=prefs.getInt("third",0);
                        editor.putInt("third",moveOne);
                        moveOne=prefs.getInt("fourth",0);
                        editor.putInt("fourth",moveTwo);
                        editor.putInt("fifth",moveOne);
                    }
                    else if(mGLView.getScore()>prefs.getInt("third",0))
                    {
                        moveOne=prefs.getInt("third",0);
                        editor.putInt("third",mGLView.getScore());
                        moveTwo=prefs.getInt("fourth",0);
                        editor.putInt("fourth",moveOne);
                        editor.putInt("fifth",moveTwo);
                    }
                    else if(mGLView.getScore()>prefs.getInt("fourth",0))
                    {
                        moveOne=prefs.getInt("fourth",0);
                        editor.putInt("fourth", mGLView.getScore());
                        editor.putInt("fifth",moveOne);
                    }
                    else if(mGLView.getScore()>prefs.getInt("fifth",0))
                    {
                        editor.putInt("fifth", mGLView.getScore());
                    }
                    editor.commit();
                    if(!ended)
                    showDialog();

                }
            }
        };

        t1.start();


        //BEGIN TIMER STUFF
        colOne = new Thread() {
            float a[] = {0.2f, 0.709803922f, 0.898039216f, 1.0f};//BLUE
            float b[] = {.0f, 0.0f, 0.0f, .0f};//BLACK
            int i;
            public void run() {
                while(!interrupted()) {
                    for(i = col1Val; i<col1Val+9; i++){
                        try {Thread.sleep((int)timeVal);}
                        catch (InterruptedException e)
                        {e.printStackTrace();}
                        if(i>col1Val) {
                        // if the colored square was pressed, restart the loop
                        if(!mGLView.getState(i-1)){

                                mGLView.changeCol(i - 1, b);
                                // if the square wasnt pressed, set the value of it to 0
                                mGLView.setArrVal(i - 1, 0);

                                mGLView.changeCol(i, a);
                                // the new colored square will be a valued square
                                mGLView.setArrVal(i, 2);
                                // if colored square reached last square, end the game
                                if(i == col1Val+8){
                                    end = true;
                                    return; }
                            }

                        else {
                            // if the colored square is pressed, set the state back to false
                            mGLView.setState(i - 1, false);
                            i = col1Val-1;
                            if(level>oldLevel) {
                                startSQThread();
                                increaseSpeed();
                                oldLevel++;

                            }

                        }}
                        else
                        {
                            mGLView.changeCol(i, a);
                            // the new colored square will be a valued square
                            mGLView.setArrVal(i, 2);

                        }
                    }}}};
        colTwo = new Thread() {
            float a[] = {0.2f, 0.709803922f, 0.898039216f, 1.0f};//BLUE
            float b[] = {.0f, 0.0f, 0.0f, .0f};//BLACK
            int i;
            public void run() {
                while(!interrupted()) {
                    for(i = col2Val; i<col2Val+9; i++){
                        try {Thread.sleep((int)timeVal);}
                        catch (InterruptedException e)
                        {e.printStackTrace();}
                        if(i>col2Val) {
                        // if the colored square was pressed, restart the loop
                        if(!mGLView.getState(i-1)){

                                mGLView.changeCol(i - 1, b);
                                // if the square wasnt pressed, set the value of it to 0
                                mGLView.setArrVal(i - 1, 0);
                                mGLView.changeCol(i, a);
                                // the new colored square will be a valued square
                                mGLView.setArrVal(i, 2);
                                // if colored square reached last square, end the game
                                if(i == col2Val+8){
                                    end = true;
                                    return; }
                            }

                        else{
                            // if the colored square is pressed, set the state back to false
                            mGLView.setState(i - 1, false);
                            i = col2Val-1;
                            if(level>oldLevel){
                            startSQThread();
                            increaseSpeed();
                                oldLevel++;
                            }

                        }}
                        else
                        {
                            mGLView.changeCol(i, a);
                            // the new colored square will be a valued square
                            mGLView.setArrVal(i, 2);
                        }
                    }}}};
        colThree = new Thread() {
            float a[] = {0.2f, 0.709803922f, 0.898039216f, 1.0f};//BLUE
            float b[] = {.0f, 0.0f, 0.0f, .0f};//BLACK
            int i;
            public void run() {
                while(!interrupted()) {
                    for(i = col3Val; i<col3Val+9; i++){
                        try {Thread.sleep((int)timeVal);}
                        catch (InterruptedException e)
                        {e.printStackTrace();}
                        if(i>col3Val) {
                        // if the colored square was pressed, restart the loop
                        if(!mGLView.getState(i-1)){

                                mGLView.changeCol(i - 1, b);
                                // if the square wasnt pressed, set the value of it to 0
                                mGLView.setArrVal(i - 1, 0);

                                mGLView.changeCol(i, a);
                                // the new colored square will be a valued square
                                mGLView.setArrVal(i, 2);
                                // if colored square reached last square, end the game
                                if(i == col3Val+8){
                                    end = true;
                                    return; }
                            }

                        else{
                            // if the colored square is pressed, set the state back to false
                            mGLView.setState(i - 1, false);
                            i = col3Val-1;
                            if(level>oldLevel) {
                                startSQThread();
                                increaseSpeed();
                                oldLevel++;
                            }

                        }}
                        else
                        {
                            mGLView.changeCol(i, a);
                            // the new colored square will be a valued square
                            mGLView.setArrVal(i, 2);
                        }
                    }}}};
        colFour = new Thread() {
            float a[] = {0.2f, 0.709803922f, 0.898039216f, 1.0f};//BLUE
            float b[] = {.0f, 0.0f, 0.0f, .0f};//BLACK
            int i;
            public void run() {
                while(!interrupted()) {
                    for(i = col4Val; i<col4Val+9; i++){
                        try {Thread.sleep((int)timeVal);}
                        catch (InterruptedException e)
                        {e.printStackTrace();}
                        if(i>col4Val) {
                        // if the colored square was pressed, restart the loop
                        if(!mGLView.getState(i-1)){

                            mGLView.changeCol(i - 1, b);
                            // if the square wasnt pressed, set the value of it to 0
                            mGLView.setArrVal(i - 1, 0);

                            mGLView.changeCol(i, a);
                            // the new colored square will be a valued square
                            mGLView.setArrVal(i, 2);
                            // if colored square reached last square, end the game
                            if(i == col4Val+8){
                                end = true;
                                return; }
                        }

                    else{
                            // if the colored square is pressed, set the state back to false
                            mGLView.setState(i - 1, false);
                            i = col4Val-1;
                            if(level>oldLevel) {
                                startSQThread();
                                increaseSpeed();
                                oldLevel++;
                            }

                        }}
                        else
                        {
                            mGLView.changeCol(i, a);
                            // the new colored square will be a valued square
                            mGLView.setArrVal(i, 2);
                        }
                    }}}};
        colFive = new Thread() {
            float a[] = {0.2f, 0.709803922f, 0.898039216f, 1.0f};//BLUE
            float b[] = {.0f, 0.0f, 0.0f, .0f};//BLACK
            int i;
            public void run() {
                while(!interrupted()) {
                    for(i = col5Val; i<col5Val+9; i++){
                        try {Thread.sleep((int)timeVal);}
                        catch (InterruptedException e)
                        {e.printStackTrace();}
                        if(i>col5Val) {
                        // if the colored square was pressed, restart the loop
                        if(!mGLView.getState(i-1)){

                                mGLView.changeCol(i - 1, b);
                                // if the square wasnt pressed, set the value of it to 0
                                mGLView.setArrVal(i - 1, 0);

                                mGLView.changeCol(i, a);
                                // the new colored square will be a valued square
                                mGLView.setArrVal(i, 2);
                                // if colored square reached last square, end the game
                                if(i == col5Val+8){
                                    end = true;
                                    return; }
                            }

                        else{
                            // if the colored square is pressed, set the state back to false
                            mGLView.setState(i - 1, false);
                            i = col5Val-1;
                            if(level>oldLevel) {
                                startSQThread();
                                increaseSpeed();
                                oldLevel++;
                            }

                        }}
                        else
                        {
                            mGLView.changeCol(i, a);
                            // the new colored square will be a valued square
                            mGLView.setArrVal(i, 2);
                            i = col5Val;
                        }
                    }}}};
        colSix = new Thread() {
            float a[] = {0.2f, 0.709803922f, 0.898039216f, 1.0f};//BLUE
            float b[] = {.0f, 0.0f, 0.0f, 0.0f};//BLACK
            int i;
            public void run() {
                while(!interrupted()) {
                    for(i = col6Val; i<col6Val+9; i++){
                        try {Thread.sleep((int)timeVal);}
                        catch (InterruptedException e)
                        {e.printStackTrace();}
                        if(i>col6Val) {
                        // if the colored square was pressed, restart the loop
                        if(!mGLView.getState(i-1)){

                                mGLView.changeCol(i - 1, b);
                                // if the square wasnt pressed, set the value of it to 0
                                mGLView.setArrVal(i - 1, 0);

                                mGLView.changeCol(i, a);
                                // the new colored square will be a valued square
                                mGLView.setArrVal(i, 2);
                                // if colored square reached last square, end the game
                                if(i == col6Val+8){
                                    end = true;
                                    return; }
                            }

                        else{
                            // if the colored square is pressed, set the state back to fals
                            mGLView.setState(i - 1, false);
                            i = col6Val-1;
                            if(level>oldLevel) {
                                startSQThread();
                                increaseSpeed();
                                oldLevel++;
                            }

                        }}
                        else
                        {
                            mGLView.changeCol(i, a);
                            // the new colored square will be a valued square
                            mGLView.setArrVal(i, 2);
                        }
                    }}}};
       colOne.start();

    }

    //Call this method to begin anew block once
    //an old block is destroyed along with its timer
    void startSQThread()
    {   int randVal = 0;//= rand.nextInt(6)+1;
        int acOld = activeCount();
            if (acOld < 6) {
                randVal = rand.nextInt(6);
                while (activeArr[randVal])
                {
                    randVal++;
                    if (randVal == 6)
                        randVal = 0;
                }
                    chooseThread(randVal);
            }
    }

    void chooseThread(int tNum)
    {
        if (tNum == 0)
            colOne.start();
        else if (tNum == 1 )
            colTwo.start();
        else if (tNum == 2 )
            colThree.start();
        else if (tNum == 3 )
            colFour.start();
        else if (tNum == 4 )
            colFive.start();
        else
            colSix.start();
    }

    //used in sq thread
    int activeCount()
    {   int onSq = 0;
        if(colOne.isAlive())
        { onSq++; activeArr[0] = true;}
        if(colTwo.isAlive())
        {   onSq++;activeArr[1] = true;}
        if(colThree.isAlive())
        {   onSq++;activeArr[2] = true;}
        if(colFour.isAlive())
        {   onSq++;activeArr[3] = true;}
        if(colFive.isAlive())
        {   onSq++;activeArr[4] = true;}
        if(colSix.isAlive())
        {   onSq++;activeArr[5] = true;}
        return onSq;
    }

    //increase speed of squares by making sleep time less
    //use This after increaseLevel
    //changes to denominator can and should be made
    //hell, any other equation will do
    void increaseSpeed(){

        if(level == 1) {
            timeVal *= (3.0/4.0);
        }
        else if (level == 2 || level == 3) {
            timeVal *= (5.0/6.0);
        }
        else if (level == 4 || level == 5) {
            timeVal *= (7.0/8.0);
        }
        else {
            timeVal *= (9.0/10.0);
        }

    }

    public void updateLevelNumber(int ex){
        TextView textView = (TextView) findViewById(R.id.levelnum);
        if(mGLView.incrementLevel()){
            level++;

        }
        textView.setText(Integer.toString(ex));

    }

    public void updateScore(){
        TextView textView = (TextView) findViewById(R.id.score);
        textView.setText(Integer.toString(mGLView.getScore()));

    }
    // dialog fragment functions
    public void showDialog(){

        myFragment.show(getFragmentManager(), "Game Over");
    }

    public void MainMenu(){

        finish();
    }

    public void TryAgain(){
    recreate();
    }
//
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            ended=true;
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


}
