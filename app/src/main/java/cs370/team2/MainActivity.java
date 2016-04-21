package cs370.team2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
//test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        System.exit(0);
    }

    //starts the game when start button is clicked
    public void startButton(View view) {
        Intent startIntent = new Intent(this,GameActivity.class);
        startActivity(startIntent);

    }

    public void instructionButton(View view) {
        Intent startIntent = new Intent(this,InstructionActivity.class);
        startActivity(startIntent);
    }

    public void highScoreButton(View view) {
        Intent startIntent = new Intent(this,HighScoreActivity.class);
        startActivity(startIntent);
    }
}
