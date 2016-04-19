package cs370.team2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class HighScoreActivity extends Activity {

    //getting scores Matthew
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Integer[] arrScores= new Integer[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.highscore_layout);

        prefs = this.getSharedPreferences("Scores", Context.MODE_PRIVATE);
        editor = prefs.edit();


        arrScores[0] = prefs.getInt("first", 0);
        arrScores[1]=prefs.getInt("second",0);
        arrScores[2]=prefs.getInt("third",0);
        arrScores[3]=prefs.getInt("fourth",0);
        arrScores[4]=prefs.getInt("fifth",0);
        ListAdapter theAdaptor = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,arrScores);
        ListView scoreList= (ListView)findViewById(R.id.scoreList);
        scoreList.setAdapter(theAdaptor);

    }


    public void Back(View view) {
        finish();
    }

    public void resetScores(View view)
    {
        editor.putInt("first",0);
        editor.putInt("second",0);
        editor.putInt("third",0);
        editor.putInt("fourth",0);
        editor.putInt("fifth",0);
        editor.commit();
        finish();
    }
}