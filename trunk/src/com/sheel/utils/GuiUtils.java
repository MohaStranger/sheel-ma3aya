/**
 * 
 */
package com.sheel.utils;

import com.sheel.app.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

/**
 * Class used to contain common methods relevant to UI
 * for showing consistent alerts and components
 * 
 * @author 
 *		Passant El.Agroudy (passant.elagroudy@gmail.com)
 *@author 
 *			Hossam_Amer (hossam.amer12@gmail.com)
 *
 */
public final class GuiUtils extends Activity{
	
	
	/**
	 * Current context of the application
	 */
	Context mContext; 
	
	/**
	 * Categories display names
	 */
	public  String [] swpeCats;
	
	/**
	 * Okay word
	 */
	public  String okay;
	/**
	 * Yes word
	 */
	public  String Yes;
	
	/**
	 * No word
	 */
	public  String No;
	/**
	 * Pending confirmation Dialog
	 */
	public  String loadingConfirmation;
	
	/**
	 * Already confirmed status
	 */
	public  String alreadyConfirmed;
	
	/**
	 * Confirmed by two users status
	 */
	public  String confirmedByTwoUsers;
	
	/**
	 * Confirmed by another person status
	 */
	public  String confirmedByAnotherPerson;
	
	/**
	 * Confirmation Mail dialog box
	 */
	public  String confirmationMail;
	
	/**
	 * Mode of writing
	 */
	public String writingMode;
	
	/**
	 * Strings for displaying the flight
	 */
	public String flightWord;
	public String flightFrom;
	public String flightTo;
	public String flightOn;
	
	/**
	 * Constructor for creating and getting the Swyper GUI utilities.
	 * @param mContext
	 * 				Current context of the application
	 */
	
	public GuiUtils(Context mContext) {
		
		this.mContext = mContext;
		
		//============Add your resources here=============
		
		swpeCats = mContext.getResources().getStringArray(R.array._swyperCats);
		okay = mContext.getResources().getString(R.string._hossamOk);
		Yes =  mContext.getResources().getString(R.string._hossamYes);
		No =  mContext.getResources().getString(R.string._hossamNo);
		loadingConfirmation =  mContext.getResources().getString(R.string._hossamConfirmPending);
		alreadyConfirmed =  mContext.getResources().getString(R.string._hossamAlreadyConfirmed);
		confirmedByTwoUsers =  mContext.getResources().getString(R.string._hossamConfirmedByTwoUsers);
		confirmedByAnotherPerson =  mContext.getResources().getString(R.string._hossamConfirmedByAnotherPerson);
		confirmationMail =  mContext.getResources().getString(R.string._hossamConfirmationMail);
		writingMode = mContext.getResources().getString(R.string._hossamWritingmode);
		flightWord = mContext.getResources().getString(R.string._hossamFlight);
		flightFrom = mContext.getResources().getString(R.string._hossamFlightFrom);
		flightTo = mContext.getResources().getString(R.string._hossamFlightTo);
		flightOn = mContext.getResources().getString(R.string._hossamFlightOn);
		
	}
	
	
	/**
	 * 
	 * =======Some Getters=================
	 * 
	 */
	
	public String[] getSwpeCats() {
		return swpeCats;
	}
	public String getOkay() {
		return okay;
	}
	public String getYes() {
		return Yes;
	}
	public String getNo() {
		return No;
	}
	public String getLoadingConfirmation() {
		return loadingConfirmation;
	}
	public String getAlreadyConfirmed() {
		return alreadyConfirmed;
	}
	public String getConfirmedByTwoUsers() {
		return confirmedByTwoUsers;
	}
	public String getConfirmedByAnotherPerson() {
		return confirmedByAnotherPerson;
	}
	public String getConfirmationMail() {
		return confirmationMail;
	}
	public String getWritingMode() {
		return writingMode;
	}
	
	
	
	/**
	 * Used to show consistent pop up for the user when no results are 
	 * found with the possibility of diverting to 2 different views
	 * 
	 * @param
	 * 		the activity where the pop up should be displayed over
	 * @param message
	 * 		main message displayed in the pop up
	 * @param commandRes1
	 * 		Order on the first button
	 * @param res1
	 * 		Layout that the first button should divert to upon clicking
	 * @param commandRes2
	 * 		Order on the second button
	 * @param res2
	 * 		Layout that the second button should divert to
	 * 
	 * @author 
	 *		Passant El.Agroudy (passant.elagroudy@gmail.com)
	 * @author 
	 * 		Hossam Amer
	 */
	public void showAlertWhenNoResultsAreAvailable(final Activity activity, String message , String commandRes1, final Class<?> res1 , String commandRes2,final Class<?> res2) {
		System.out.println("Should show alert message");
		
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage(message)
		       .setCancelable(true)		       
		       .setPositiveButton(commandRes1, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   dialog.cancel();
		        	   Intent intent = new Intent(activity.getApplicationContext(),res1);
		        	   activity.startActivity(intent);
		           }
		       
		       });
		     /*  .setNegativeButton(commandRes2, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   dialog.cancel();
		        	   Intent intent = new Intent(activity.getApplicationContext(),res2);
		        	   activity.startActivity(intent);
		           }
		       
		       });
		      */ 
			
		 builder.create();
		 builder.show();
	}// end showAlertWhenNoResultsAreAvailable
	
	/**
     * Used to add an image to the left side of a text view.
     * 
     * @param activity
     * 		Activity containing the text view
     * @param textViewId 
     * 		ID of the text view from (R.id) collection
     * @param imgId
     * 		ID of the resource image used from (R.drawable) collection
     * @param mode 
     * 		indicates size of icon.
     * 		<ul>
     * 			<li>0: small    (40X40px)</li>
     * 			<li>1: medium   (50X50px)</li>
     * 			<li>2: large    (80X80px)</li>
     * 			<li>3: (33X33px)</li>
     * 		</ul>
     * @author 
     * 		Passant El.Agroudy (passant.elagroudy@gmail.com)
     */
    public  void setIconForATextField(Activity activity, int textViewId , int imgId , int mode){
    	Drawable img = activity.getApplicationContext().getResources().getDrawable(imgId );
    	TextView textView = (TextView)activity.findViewById(textViewId);
    	setIconForATextField(img, textView, mode);
    }// end SetIconForATextField
    
    /**
     * Used to add an image to the left side of a text view.
     * 
     * @param appContext
     * 		Application context. It can be retrieved from the activity by 
     * 		<code>activity.getApplicationContext()</code>
     * @param parent
     * 		Component (View) containing the text view. Example: the row in
     * 		list containing the text view
     * @param textViewId 
     * 		ID of the text view from (R.id) collection
     * @param imgId
     * 		ID of the resource image used from (R.drawable) collection
     * @param mode 
     * 		indicates size of icon.
     * 		<ul>
     * 			<li>0: small    (40X40px)</li>
     * 			<li>1: medium   (50X50px)</li>
     * 			<li>2: large    (80X80px)</li>
     * 			<li>3: (33X33px)</li>
     * 		</ul>
     * 
     * @author 
     *		Passant El.Agroudy (passant.elagroudy@gmail.com)
     */
    public void setIconForATextField(Context appContext, View parent,  int textViewId , int imgId , int mode){
    	
    	Drawable img = appContext.getResources().getDrawable(imgId );
    	TextView textView = (TextView)parent.findViewById(textViewId);
    	setIconForATextField(img, textView, mode);
    	
    }// end SetIconForATextField
    
    /**
     * Used to add an image to the left side of a text view.
     * 
     * @param appContext
     * 		Application context. It can be retrieved from the activity by 
     * 		<code>activity.getApplicationContext()</code>
     * @param parent
     * 		Component (View) containing the text view. Example: the row in
     * 		list containing the text view
     * @param textView
     * 		text view that will have the image added to it
     * @param imgId
     * 		ID of the resource image used from (R.drawable) collection
     * @param mode 
     * 		indicates size of icon.
     * 		<ul>
     * 			<li>0: small    (40X40px)</li>
     * 			<li>1: medium   (50X50px)</li>
     * 			<li>2: large    (80X80px)</li>
     * 			<li>3: (33X33px)</li>
     * 		</ul>
     * 
     * @author 
     *		Passant El.Agroudy (passant.elagroudy@gmail.com)
     */
    public void setIconForATextField(Context appContext, View parent,  TextView textView , int imgId , int mode){
    	
    	Drawable img = appContext.getResources().getDrawable(imgId );
       	setIconForATextField(img, textView, mode);
    	
    }// end SetIconForATextField
	
	/**
	 * Used to add an image to the left side of a text view.
	 * 
	 * @param img
	 * 		Image to be added to the text view
	 * @param textView
	 * 		Text view that that image will be added to its left
	 * @param mode 
     * 		indicates size of icon.
     * 		<ul>
     * 			<li>0: small    (40X40px)</li>
     * 			<li>1: medium   (50X50px)</li>
     * 			<li>2: large    (80X80px)</li>
     * 			<li>3: (33X33px)</li>
     * 		</ul>
     * 
	 * @author 
	 *		Passant El.Agroudy (passant.elagroudy@gmail.com)
	 */
    public void setIconForATextField(Drawable img , TextView textView , int mode) {
    	switch(mode){
    	case 0:img.setBounds( 0, 0, 40, 40 ); break;
    	case 1:img.setBounds( 0, 0, 50, 50 ); break;
    	case 2:img.setBounds( 0, 0, 80, 80 ); break;
    	case 3:img.setBounds( 0, 0, 33, 33 ); break;
    	default:img.setBounds( 0, 0, 50, 50 ); break;
    	}// end switch : specify size according to mode
    	//In case of left to right put the icon on the left, put it on the right otherwise
    	
    	if(this.writingMode.equals("LeftToRight"))
    		textView.setCompoundDrawables(img, null, null, null);
    	else
    		textView.setCompoundDrawables(null, null, img, null);
    }// end setIconForATextField


	public String getFlightWord() {
		return flightWord;
	}


	public String getFlightFrom() {
		return flightFrom;
	}


	public String getFlightTo() {
		return flightTo;
	}


	public String getFlightOn() {
		return flightOn;
	}


}// end class