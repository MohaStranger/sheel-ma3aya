package com.sheel.listeners;

import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.view.ViewStub.OnInflateListener;
import android.widget.Button;

import com.sheel.app.R;


/**
 * InflateListener is the listener used when the Stub is inflated
 * 
 * It is used to save the position of the buttons inside their tags
 * 
 * @author Hossam_Amer
 *
 */

public class InflateListener implements OnInflateListener {
	
	private static final String TAG = InflateListener.class.getName();

	
	/**
	 *  Position Clicked
	 */
	int mPos;
	
	
	public InflateListener(int position)
	{
		mPos = position;
	}
	
	public void onInflate(ViewStub stub, View inflated) {
		// TODO Auto-generated method stub

		Log.e(TAG, "Stub: " + stub);
		Log.e(TAG, "Inflated: " + inflated);
		
		// <=== List your buttons here ===>
		Button btn_call = (Button) inflated.findViewById(R.id.details_button_call);
		
		Log.e(TAG, "Button: " + btn_call);
		Log.e(TAG, "Button: " + (Button) stub.findViewById(R.id.btn_call));
		btn_call.setTag(mPos);
		
		Log.e(TAG, "Button: " + btn_call.getId());
		
		Button btn_confirm = (Button) inflated.findViewById(R.id.details_button_confirm);
		btn_confirm.setTag(mPos);
		
		
		Button btn_send_sms = (Button) inflated.findViewById(R.id.details_button_sendSms);
		btn_send_sms.setTag(mPos);
		
		
		Log.e(TAG, mPos + "");
		Log.e(TAG, "Buttons are now indexed!");
		
		
	}
 

}
