package cs370.team2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Caleb on 4/8/2016.
 */

// Tried 2 different fragments/ We can decide what one is best
public class MyDialogFragment extends DialogFragment implements View.OnClickListener{
    //public class MyDialogFragment extends DialogFragment {
    @Nullable

    Button tryagain, menu;
    Communicator comm;
// allows this class and gameactibity to talk to each other
    public void onAttach(Activity a){
        super.onAttach(a);
        comm = (Communicator) a;
    }
    //creates the dialog
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog,null);
       tryagain = (Button)view.findViewById(R.id.tryagain);
        menu = (Button)view.findViewById(R.id.mainmenu);
        tryagain.setOnClickListener(this);
        menu.setOnClickListener(this);
        setCancelable(false);

        return view;
    }

    @Override
    // programs each button
    public void onClick(View v) {
        if(v.getId() == R.id.tryagain){
            comm.TryAgain();
            dismiss();

        }
        else
        {
            comm.MainMenu();
            dismiss();

        }

    }

    interface Communicator
    {
        public void MainMenu();
        public void TryAgain();
    }


            /*
            Communicator comm;
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder theDialog = new AlertDialog.Builder(getActivity());

        theDialog.setTitle("Game Over");
        //theDialog.setMessage("Game over");
        setCancelable(false);
        theDialog.setPositiveButton("Main Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                comm.MainMenu();
               // dismiss();
            }
        });

        theDialog.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                comm.TryAgain();
                //dismiss();
            }
        });
        AlertDialog dialog = theDialog.create();
        return dialog;
    }

    interface Communicator
    {
        public void MainMenu();
        public void TryAgain();
    }

    public void onAttach(Activity a){
        super.onAttach(a);
        comm = (Communicator) a;
    }
    */
}
