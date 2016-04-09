package cs370.team2;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
//test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
       // DisplayMetrics displaymetrics = new DisplayMetrics();
      //  getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
       // final int height = displaymetrics.heightPixels;
     //  final int width = displaymetrics.widthPixels;

      //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       // assert fab != null;
      // fab.setOnClickListener(new View.OnClickListener() {
       //  public void onClick(View view) {
            //    Log.i("nope", "Horray you have clicked and I know I have been clicked "+width + " "+ height);
          //  }
       //});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; all y ou this adds items to the action bar if it is present.
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

    public void exitButton(View view) {
        finish();
    }

    //starts the game when start button is clicked
    public void startButton(View view) {
        Intent startIntent = new Intent(this,GameActivity.class);
        startActivity(startIntent);

    }
}
