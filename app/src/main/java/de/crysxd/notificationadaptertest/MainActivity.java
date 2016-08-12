package de.crysxd.notificationadaptertest;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.findViewById(R.id.button).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        // Tell what string should be shown
        String[] entries = this.getResources().getStringArray(R.array.reminder_notifications);

        // Tell what entries should be already selected
        final boolean[] selectedEntries = new boolean[entries.length];
        selectedEntries[2] = true;

        // Tell what entries should be enabled
        boolean[] enabledEntries = new boolean[entries.length];
        enabledEntries[0] = true;
        enabledEntries[1] = true;
        enabledEntries[2] = true;
        enabledEntries[3] = true;

        // Create the adapter
        ReminderNotificationsAdapter a = new ReminderNotificationsAdapter(this, entries, selectedEntries, enabledEntries);

        // Create and show the dialog
        new AlertDialog.Builder(this)
                .setTitle("Add notification")
                .setAdapter(a, null)
                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Do what you want to do with the selected entries
                        Toast.makeText(MainActivity.this, Arrays.toString(selectedEntries), Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("Dismiss", null)
                .show();

    }
}
