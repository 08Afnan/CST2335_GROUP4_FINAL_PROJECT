package com.example.flighttracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.flighttracker.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding activityMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.container,new MainFragment(),API_KEYS.FRAGMENT_MAIN).addToBackStack(API_KEYS.FRAGMENT_MAIN).commit();
    }



   public static void createToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount()==0)
        {
            finish();
        }
        else
        {
            getSupportFragmentManager().popBackStack();
        }

    }

    public   static void snackBar(Context context, String message, View view) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

   public static void showAlertDialog(Context context, String title, String message, String[] buttonTitles,PositiveClickListener positiveClickListener,Flight flight) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title).setMessage(message).setPositiveButton(buttonTitles[0], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(positiveClickListener!=null)
                        {
                            positiveClickListener.onUserConfirmation(flight);
                        }

                    }
                }).setNegativeButton(buttonTitles[1], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



}
