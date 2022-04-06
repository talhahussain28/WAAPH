package waaph.gb.com.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

public abstract class BaseFragment extends Fragment {

    private FragmentManager fragmentManager;
    private ProgressDialog dialog;

    /**
     * Function to check internet connection
     */
    protected boolean internetConnectionAvailable(int timeOut) {
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

    protected abstract void linkXML(View view);

    protected abstract void setOnClickListener();

    protected abstract void initialize();

    protected void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    protected void changeFragment(int container, Fragment targetFragment, Bundle bundle) {
        targetFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(container, targetFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
//        transaction.commitAllowingStateLoss();
    }

    protected void changeFragment(int container, Fragment targetFragment) {
        fragmentManager.beginTransaction().replace(container, targetFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
//        transaction.commitAllowingStateLoss();
    }

    protected void addFragment(int container, Fragment targetFragment, String TAG) {
        fragmentManager.beginTransaction()
                .add(container, targetFragment, TAG)
                .commitAllowingStateLoss();
//        transaction.commitAllowingStateLoss();
    }

    public void replaceFragment(int container, Fragment targetFragment) {
        fragmentManager.beginTransaction()
                .replace(container, targetFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commitAllowingStateLoss();
    }

    protected void changeFragmentNoHistory(int container, Fragment fragment, String TAG) {
        fragmentManager.beginTransaction()
//                .setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter, R.anim.pop_exit)
                .replace(container, fragment, TAG)
                .commitAllowingStateLoss();
    }

    protected void intentStartActivityWithFinish(Activity activity, Class targetActivty) {
        Intent intent = new Intent(activity, targetActivty);
        startActivity(intent);
        activity.finish();
    }

    protected void intentStartActivity(Activity activity, Class targetActivty) {
        Intent intent = new Intent(activity, targetActivty);
        startActivity(intent);
    }

    protected void intentStartActivityWithData(Activity activity, Class targetActivty, String data) {
        Intent intent = new Intent(activity, targetActivty);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    protected void closeFragment() {
        fragmentManager.popBackStack();
    }

    protected Boolean getActivityNullCheck() {
        if (getActivity() != null) {
            return true;
        }
        return false;
    }

    protected void setToolbarTitle(Toolbar toolbar, TextView toolbar_title, String title) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_title.setText(title);
    }

    protected void setLog(String tag, String print) {
        Log.d(tag, print);
    }

    protected void showToast(String message) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }

//    protected void showToast(Context context, String message) {
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
//    }

    public void showSnackBar(String text) {
        if (getActivity() != null) {
            Snackbar snack = Snackbar.make((getActivity()).findViewById(android.R.id.content), text,
                    Snackbar.LENGTH_LONG);
            View view = snack.getView();
            TextView tv = view.findViewById(androidx.core.R.id.text);
//            TextView tv = view.findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.WHITE);
            snack.show();
        }
    }

    protected void showDialog(Context context, String message) {
        dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.show();
    }

    protected void dismissDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    protected void hideKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected void showKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    protected void alertDialog(String title, String message, final OnClickListener listener) {
        if (getActivity() != null) {
            new AlertDialog.Builder(getActivity())
//                    .setIcon(R.mipmap.ic_launcher_round)
                    .setTitle(title)
                    .setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            listener.onClick(dialogInterface, i);
                        }
                    })
                    .show();
        }
    }

    public interface OnClickListener {
        /**
         * This method will be invoked when a button in the dialog is clicked.
         *
         * @param dialog the dialog that received the click
         * @param which the button that was clicked (ex.
         *              {@link DialogInterface#BUTTON_POSITIVE}) or the position
         *              of the item clicked
         */
        void onClick(DialogInterface dialog, int which);
    }
}
