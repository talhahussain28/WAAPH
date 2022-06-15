package waaph.gb.com.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import waaph.gb.com.activities.BottomNavigationActivity;
import waaph.gb.com.activities.MainActivity;

public abstract class BaseActivity extends AppCompatActivity {

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private ProgressDialog dialog;

    void splashThread(final Activity activity, int splashTimer, final int userType,
                      final boolean userLoggedIn) {
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (userLoggedIn) {
                    // User Logged In
                    intentStartActivityWithFinish(activity, BottomNavigationActivity.class);
//                    switch (userType){
//                        case ACC_TYPE_EMPLOYEE:
//                            intentStartActivityWithFinish(activity, EmployeeMainActivity.class);
//                            break;
//                        case ACC_TYPE_MANAGEMENT:
//                            intentStartActivityWithFinish(activity, ManagementMainActivity.class);
//                            break;
//                    }
                } else {
                    // Redirect to Login Screen
                    intentStartActivityWithFinish(activity, MainActivity.class);
                }

            }
        }, splashTimer);
    }

    /**
     * Function to check internet connection
     */
    public boolean internetConnectionAvailable(int timeOut) {
        InetAddress inetAddress = null;
        Future<InetAddress> future = Executors.newSingleThreadExecutor().submit(new Callable<InetAddress>() {
            @Override
            public InetAddress call() {
                try {
                    return InetAddress.getByName("google.com");
                } catch (UnknownHostException e) {
                    return null;
                }
            }
        });
        try {
            inetAddress = future.get(timeOut, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        future.cancel(true);
        return inetAddress != null && !inetAddress.equals("");
    }

    protected abstract void linkXML();

    protected abstract void setOnClickListener();

    protected abstract void initialize();

    public void intentStartActivity(Activity activity, Class targetActivty) {
        Intent intent = new Intent(activity, targetActivty);
        startActivity(intent);
    }

    public void intentStartActivityWithData(Activity activity, Class targetActivty, String data) {
        Intent intent = new Intent(activity, targetActivty);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    public void intentStartActivityWithFinish(Activity activity, Class targetActivty) {
        Intent intent = new Intent(activity, targetActivty);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    public void replaceFragment(int container, Fragment targetFragment) {
        fragmentManager.beginTransaction()
                .replace(container, targetFragment, targetFragment.getClass().getSimpleName())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commitAllowingStateLoss();
    }
    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showSnackBar(String text, int duration) {
        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), text, duration);
        View view = snack.getView();
        snack.setActionTextColor(Color.WHITE);
//        TextView tv = view.findViewById(com.google.android.material.R.id.snackbar_text);
//        TextView tv = view.findViewById(android.support.design.R.id.snackbar_text);
//        tv.setTextColor(Color.WHITE);
//        view.setBackground(view.getResources().getDrawable(R.color.black_overlay));
        snack.show();
    }

    public void setLog(String tag, String print) {
        Log.d(tag, print);
    }
/*
    public void showCustomDialog(String title, String message) {
        new DialogAlert(false,
                this,
                title, R.color.colorBlack,
                message, R.color.colorBlack,
                "", R.color.colorLightGrey, R.color.colorBlack, false,
                "Cancel", R.color.colorFacebook, R.color.colorWhite, true,
                false, dialog -> {
            dialog.dismiss();
            return null;
        }, dialog -> null).show();
    }*/

    public void showDialog(String message) {
        dialog = new ProgressDialog(this);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
    }
    public void dismissDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
    public void showKeyBoardEditText(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public void showKeyBoard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        }
    }
    public void hideKeyBoard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    private void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null)
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    public void alertDialog(String title, String message, final OnClickListener listener) {
        new AlertDialog.Builder(this)
//                .setIcon(R.mipmap.ic_launcher_round)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onClick(dialogInterface, i);
                    }
                })
                .show();
    }

    public interface OnClickListener {
        /**
         * This method will be invoked when a button in the dialog is clicked.
         *
         * @param dialog the dialog that received the click
         * @param which  the button that was clicked (ex.
         *               {@link DialogInterface#BUTTON_POSITIVE}) or the position
         *               of the item clicked
         */
        void onClick(DialogInterface dialog, int which);
    }
}
