package com.jery.snoopy;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class EditFriendsActivity extends Activity {

	public static final String TAG = EditFriendsActivity.class.getSimpleName();

	protected List<ParseUser> mUsers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_friends);
	}

	@Override
	protected void onResume() {
		super.onResume();

		ParseQuery<ParseUser> query = ParseUser.getQuery();
		query.addAscendingOrder(ParseConstants.KEY_USERNAMES);
		query.setLimit(1000);
		query.findInBackground(new FindCallback<ParseUser>() {

			@Override
			public void done(List<ParseUser> users, ParseException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					mUsers = users;
					String[] usernames = new String[mUsers.size()];
					int i = 0;
					for (ParseUser user : mUsers) {
						usernames[i] = user.getUsername();
						i++;
					}
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							EditFriendsActivity.this,
							android.R.layout.simple_list_item_checked,
							usernames);
				}
				else {
					Log.e(TAG,e.getMessage());
					AlertDialog.Builder builder = new AlertDialog.Builder(
							EditFriendsActivity.this);
					builder.setMessage(e.getMessage())
							.setTitle(R.string.error_title)
							.setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_friends, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
