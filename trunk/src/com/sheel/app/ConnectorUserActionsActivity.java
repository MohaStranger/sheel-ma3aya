package com.sheel.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sheel.webservices.FacebookWebservice;

/**
 * This activity displays the home page that will be displayed to the user after
 * registering
 * 
 * @author 
 * 		Nada Emad
 * @author
 * 		Passant El.Agroudy (passant.elagroudy@gmail.com)
 * 
 */
public class ConnectorUserActionsActivity extends UserSessionStateMaintainingActivity {

	/**
	 * Constant used for tracing purposes "class name (package name)"
	 */
	private final String TAG_CLASS_PACKAGE = "ConnectorUserActionsActivity (com.sheel.app): ";
	public static final String LOGGED_ID_KEY = "LoggedID";
	public static String LoggedID;
	
	TextView LoggedUserField;
	
	/**
	 *  Called when the activity is first created. 
	 *  
	 *  @author 
	 *  	Passant El.Agroudy (passant.elagroudy@gmail.com)
	 *  @author
	 *  	Hossam Amer
	 * */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connector_user_action);		
		
		if (getFacebookService() == null){
			Log.e(TAG_CLASS_PACKAGE,"onCreate: facebookservice is not initialized");			
			setFacebookService(new FacebookWebservice());
			getFacebookService().login(this, true, false);
		}// end if : previous activity was welcome(login)

		Bundle extras = getIntent().getExtras(); 
		if (extras != null) LoggedID = extras.getString(LOGGED_ID_KEY);
		
		LoggedUserField = (TextView) findViewById(R.id.userID);
		LoggedUserField.setText("User ID: " + LoggedID);
	}// end onCreate

	/**
	 * This is called when the declare button is clicked
	 * 
	 * @param v
	 */

	public void onClick_DeclareOffer(View v) {
	
		Intent intent =setSessionInformationBetweenActivities(InsertOfferActivity.class);
		 
		try{
			long userId = Long.parseLong(LoggedID);
			intent.putExtra("userId", userId);
		}catch(Exception e){
			
		}
		startActivity(intent);
		 
	}// end onClick_DeclareOffer

	/**
	 * This is called when the search button is clicked
	 * 
	 * @param v
	 * 		
	 */

	public void onClick_SearchOffers(View v) {		
		Intent intent =setSessionInformationBetweenActivities(GetUserInfoActivity.class);
		
		try {
		long userId = Long.parseLong(LoggedID);
		intent.putExtra("userId", userId);
		}catch(Exception e) {
			
		}
		startActivity(intent);
	}// end onClick_SearchOffers
	
	/**
	 * This is called when Logout button is clicked
	 * 
	 * @param v
	 * 		clicked button
	 */
	public void onClick_logOut(View v){
		if (getFacebookService() != null)
		getFacebookService().logout(this);
		/* because the session is static, if not set to null, the app
		 * will never login again until it is closed then re-opened*/
		setFacebookService(null);
		startActivity(new Intent(this, SheelMaayaaActivity.class));
	}// end onClick_logOut
	
	public void onClick_test(View v){
		
		FacebookWebservice fbServ = getFacebookService();
		System.out.println("Register intergrate: " + "facebookservice after login: "+ fbServ );
		fbServ.getUserInformation(true);
		
	}

}