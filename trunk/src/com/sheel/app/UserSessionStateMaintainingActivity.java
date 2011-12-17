package com.sheel.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.sheel.datastructures.FacebookUser;
import com.sheel.datastructures.enums.SharedValuesBetweenActivities;
import com.sheel.webservices.FacebookWebservice;

/**
 * Base class for all activities inside the app to maintain critical
 * information of user and session that must be passed between all 
 * activities
 * 
 * @author 
 * 		Passant El.Agroudy (passant.elagroudy@gmail.com)
 *
 */
public class UserSessionStateMaintainingActivity extends Activity {

	/**
	 * Constant used for tracing purposes "class name (package name)"
	 */
	private final String TAG_CLASS_PACKAGE = "UserSessionStateMaintainingActivity (com.sheel.app): ";
	
	
	/**
	 * It handles all requests to facebook API. Moreover, it has
	 * all related info to logged in user and session
	 */
	private  static FacebookWebservice fbService = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (fbService != null)
			Log.e(TAG_CLASS_PACKAGE,"Info read from static facebook service: userId = " + fbService.getFacebookUser()+" , accessToken= "+  fbService.getUserAccessToken() + " ,accessExpiry= " + fbService.getUserAccessTokenExpiryTime());
		else
			Log.e(TAG_CLASS_PACKAGE,"Facebook service is not initialized yet");
		
	
		// do any logic you want
		
	}// end onCreate
	
	@Override 
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
    	/*This method must be called for facebook login to function properly*/
		getFacebookService().authorizeCallback(requestCode, resultCode, data);
    }// end onActivityResult
      
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/**
		 * This method is used to create the main menu of the app
		 * @author 
		 * 		Passant El.Agroudy (passant.elagroudy@gmail.com)
		 */
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.layout.menu_main, menu);
	    return true;
	}// end onCreateOptionsMenu
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/**
		 * This method is used to handle clicks of the app main menu
		 * @author 
		 * 		Passant El.Agroudy (passant.elagroudy@gmail.com)
		 */
		
		Class<?> typeOfNextActivity = null;
		
		switch (item.getItemId()) {
			case R.id.menu_main_search: 
				typeOfNextActivity = GetUserInfoActivity.class;
				break;				
			case R.id.menu_main_declare:
				typeOfNextActivity = InsertOfferActivity.class;
				break;					
			case R.id.menu_main_myoffers:
				typeOfNextActivity = MyOffersActivity.class;
		    	Toast.makeText(this, "7as7as: Activated Now ;)", Toast.LENGTH_SHORT).show();
				break;					
			case R.id.menu_main_logout: 
				if (getFacebookService() != null)
					getFacebookService().logout(this);
					/* because the session is static, if not set to null, the app
					 * will never login again until it is closed then re-opened*/
				setFacebookService(null);
				startActivity(new Intent(this, SheelMaayaaActivity.class));
				return true;
			default:
				return super.onOptionsItemSelected(item);
				
		}// end switch: according to selected action -> decide next activity
		
		Intent intent = new Intent(this, typeOfNextActivity);
		startActivity(intent);
		return true;
	}// end onOptionsItemSelected
	
	/**
	 * Returns class responsible for handling all requests to facebook API. 
	 * Moreover, it has all related info to logged in user and session.
	 * <br><br>
	 * <b>Note:</b> 
	 * <ul>
	 * 		<li>In case you want details of logged in, use 
	 * 		{@link FacebookWebservice#getFacebookUser()} method.</li>
	 * 		<li>In case you want logged-in user facebook ID, use 
	 * 		{@link FacebookUser#getUserId()} method.</li>
	 *      <li>In case you want details about session, use 
	 * 		{@link FacebookWebservice#isSessionValid()} method.</li>
	 * </ul>
	 * 
	 * <b>Example:</b><br>
	 * <code>
	 * 		if (getFacebookService() != null) <br>
	 * 		String userFacebookId = getFacebookService().getFacebookUser().getUserId();
	 * </code> <br>
	 * 
	 * @return
	 * 		Object used for interacting with facebook
	 * 
	 * @author 
	 * 		Passant El.Agroudy (passant.elagroudy@gmail.com)
	 */
	public FacebookWebservice getFacebookService(){
		return fbService;
	}// end getFacebookService
	
	/**
	 * IMPORTANT: Before leaving any activity, you must call this  method to pass
	 * the important details about session and user 
	 *	 
	 * @param typeOfNextActivity
	 * 		Class type of new activity you intend to navigate to. Write activity name
	 * 		then call .getClass() method
	 * 
	 * @return
	 * 		Intent containing data needed for the next activity. Use 
	 * 		{@link Activity#startActivity(Intent)} to start the new activity
	 */
	public Intent setSessionInformationBetweenActivities (Class<?> typeOfNextActivity){

		//return new Intent(this, typeOfNextActivity);
		   Intent mIntent = new Intent(this, typeOfNextActivity);
		   
		   if (fbService != null){
			// Pass variable to detailed view activity using the intent
			   if (fbService.getFacebookUser() != null)
			mIntent.putExtra(SharedValuesBetweenActivities.userFacebookId.name(), fbService.getFacebookUser().getUserId());
			mIntent.putExtra(SharedValuesBetweenActivities.userAccessToken.name(), fbService.getUserAccessToken());
			mIntent.putExtra(SharedValuesBetweenActivities.accessTokenExpiry.name(), fbService.getUserAccessTokenExpiryTime());
		   }
		   
			return mIntent;
	}// end SetSessionInformationBetweenActivities
	
	
	public void setFacebookService(FacebookWebservice fbServ){
		fbService = fbServ;
	}
	
	
}// end class
