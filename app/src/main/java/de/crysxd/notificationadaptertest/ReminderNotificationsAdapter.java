package de.crysxd.notificationadaptertest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * A {@link ArrayAdapter} to let the user select multiple notification times.
 */
public class ReminderNotificationsAdapter extends ArrayAdapter<String> implements CompoundButton.OnCheckedChangeListener {

    /**
     * A array with all currently selected entries
     */
    private boolean[] mSelected;

    /**
     * A array with all enabled entries
     */
    private boolean[] mEnabled;

    /**
     * Creates a new instance
     *
     * @param context a {@link Context}
     * @param entries all selectable entries
     * @param selectedEntries all selected entries. This array will be updated with the users selectiion
     * @param enabledEntries all enabled entries
     */
    public ReminderNotificationsAdapter(Context context, String[] entries, boolean[] selectedEntries, boolean[] enabledEntries) {
        super(context, R.layout.dialog_list_multiple_choise);

        // Check array sizes
        if(entries.length != selectedEntries.length || selectedEntries.length != enabledEntries.length) {
            throw new RuntimeException("All arrays must be the same size");
        }

        // Add all and store params
        this.addAll(entries);
        this.mSelected = selectedEntries;
        this.mEnabled = enabledEntries;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        // Create view if not provided to convert
        if(v == null) {
            v = LayoutInflater.from(this.getContext()).inflate(R.layout.dialog_list_multiple_choise, parent, false);
        }

        // Prepare text view
        TextView tv = (TextView) v.findViewById(android.R.id.text1);
        tv.setText(this.getItem(position));
        tv.setEnabled(this.isEnabled(position));

        // Prepare checkbox
        CheckBox cb = (CheckBox) v.findViewById(android.R.id.checkbox);
        cb.setTag(position);
        cb.setChecked(this.mSelected[position]);
        cb.setEnabled(this.isEnabled(position));
        cb.setOnCheckedChangeListener(this);

        // Return view
        return v;

    }

    @Override
    public boolean isEnabled(int position) {
        return this.mEnabled[position];

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        this.mSelected[(Integer) compoundButton.getTag()] = b;

    }
}
