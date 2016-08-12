# This repo contains the answer for follwing question:

community!
I need in a help with updating invisible items in ListView. It's not about item content, but item's View representation. 
Ok, let me show you my example. I have a string-array:

    <string-array name="reminder_notifications">
        <item>15 minutes before</item>
        <item>30 minutes before</item>
        <item>1 hour before</item>
        <item>1.5 hour before</item>
        <item>5 hours before</item>
        <item>8 hours before</item>
        <item>1 day before</item>
    </string-array>

In Activity i have created adapter:

    adapterNotifications = ArrayAdapter.createFromResource(this, R.array.reminder_notifications, R.layout.dialog_list_multiple_choise);

After, with some methods i calculate whitch items from string-array are avaliable for current reminder. E.g. if user at 16:00 set reminder for 16:45 then he can only pick items `15 minutes before` and `30 minutes before`. Other items should be disabled. 
So, after google i found out how to get access to invisible ListView child at certain position: 

    public View getViewByPosition(int position, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;
        if (position < firstListItemPosition || position > lastListItemPosition ) {
            return listView.getAdapter().getView(position, listView.getChildAt(position), listView);
        } else {
            final int childIndex = position - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

Now, I'm facing with last problem (i hope) - how to update item's View that i get from method above? I tried to use this:

    View v = getViewByPosition(position, lvNotifications);
    v.setEnabled(true);

But it updates View only after first opening dialog, another words i have to open the dialog window with ListView, close it and re-open. Only in that case i'll get an updated view.
I know, that my english is awful so there are screenshots below:

Main dialog. Before opening the dialog with ListView
[![Main dialog. Before opening the dialog with ListView][1]][1]

List view dialog. First opening. No items are disabled. WRONG
[![List view dialog. First opening. No items are disabled. WRONG][2]][2]

List view dialog. Second opening. 5 items are disabled. RIGHT
[![List view dialog. Second opening. 5 items are disabled. RIGHT][3]][3]

Thank you.

  [1]: http://i.stack.imgur.com/CPlKH.png
  [2]: http://i.stack.imgur.com/bfsse.png
  [3]: http://i.stack.imgur.com/tJ1wB.png
