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

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import cs370.team2.R;

//public class GameActivity extends AppCompatActivity {
    public class GameActivity extends AppCompatActivity implements MyDialogFragment.Communicator{

    //private GLSurfaceView mGLView;
    private MyGLSurfaceView testing;

    private int level=0;
    private int score=0;

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
                        if(testing.end){
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
                    //if the game has ended, show dialog
                    showDialog();

                }
                //TIMER MUST HAVE THREAD FUNCTIONALITY TO PREVENT LOCK
                //testing.gameRunning();
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
        */

    }

    public boolean onTouch(View v, MotionEvent event) {

        return true;
    }

    //*****Might not want rest of this??
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
