package cs370.team2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class InstructionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.instruction_layout);



    }


    public void Back(View view) {
        finish();
    }
}