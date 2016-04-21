package cs370.team2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * Created by Caleb on 4/8/2016.
 */


    public class MyDialogFragment extends DialogFragment {
    @Nullable

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
            }
        });

        theDialog.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                comm.TryAgain();
                dismiss();
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

}
