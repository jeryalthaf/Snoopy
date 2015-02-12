package com.jery.snoopy;

import com.parse.Parse;
import com.parse.ParseObject;

import android.app.Application;

public class SnoopyApplication extends Application {

	@Override
	public void onCreate(){
		super.onCreate();
		// Enable Local Datastore.
		Parse.enableLocalDatastore(this);
		 
		Parse.initialize(this, "WzErnYLh8TTFE5sVRc0USFt35ATqPsNPSQr8VBRy", "3D5ZyvsHXXn0jjasce26fCzHy8ydoNMyMS0rR4vv");
		ParseObject testObject = new ParseObject("TestObject");
		testObject.put("foo", "bar");
		testObject.saveInBackground();
	}
}
