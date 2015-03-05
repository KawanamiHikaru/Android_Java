package com.example.bgomega;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;











import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyService extends Service {

		private BluetoothAdapter mBluetoothAdapter;
		/** BLE 機器検索のタイムアウト(ミリ秒) */
		private static final long SCAN_PERIOD = 3000;
		private static final String TAG = "Myservice";
		  final int INTERVAL_PERIOD = 6000;
		  Timer timer = new Timer();
		  common Common;
		Handler mHandler = new Handler(); // (1)
		 public static final String ACTION = "SimpleService Action";
		  int flag=0;

	
  
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

 
	protected void onCreate(Bundle savedInstanceState) {
	

	}
	 @Override
	 public int onStartCommand(Intent intent, int flags, int startId) {
		 Log.d(TAG, "onStartCommand");
		 Common = (common) getApplication();
		    Common.init();
		    Common.x=0;
		 
		 // スキャン開始  
		    timer.scheduleAtFixedRate(new TimerTask(){
		    	@Override
		    	public void run() {
		    		final BluetoothManager bluetoothManager =
		    				(BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
				 			mBluetoothAdapter = bluetoothManager.getAdapter();
				 				
				 			mHandler.postDelayed(new Runnable() {
				 				@Override
				 				        public void run() {
				 					// タイムアウト
				 				        	Log.d(TAG, "タイムアウト");
				 				        	mBluetoothAdapter.stopLeScan(mLeScanCallback);
				 				        }
				 				    }, SCAN_PERIOD);
				 				   
				        	 mBluetoothAdapter.startLeScan(mLeScanCallback);
				      	  Common.x++;	
				          Log.d(TAG, "x="+Common.x);
				          if(Common.x==15){	
				         timer.cancel(); 		
				         }	
				        }   	   
				      }, 0, INTERVAL_PERIOD);
				     return START_STICKY;
		 }	
	 
	 @Override
	  public void onDestroy() {
	    super.onDestroy();
	    if(timer != null){
	      timer.cancel();
	    }
	    Log.d(TAG, "onDestroy");
	  }
	
	
	private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
	    @Override
	    public void onLeScan(final BluetoothDevice device, int rssi,byte[] scanRecord) {
	    	 if (scanRecord.length > 30) {
	    		 Log.d(TAG,"scan");
	             //このif文でiBeaconかどうかを判別
	    		 if ((scanRecord[5] == (byte) 0x4c) && (scanRecord[6] == (byte) 0x00)
	                     && (scanRecord[7] == (byte) 0x02) && (scanRecord[8] == (byte) 0x15)) {
	         Log.d(TAG, "receive!!!");
	  
	         getScanData(scanRecord);
	         Log.d(TAG, "device name:"+device.getName() );
	         Log.d(TAG, "device address:"+device.getAddress() );
	   
	         flag++;
	           }
	          }
	 	     }      
	 	   }
	
	 	  
	 	  
	 	
	    
	    
	;

	
	private void getScanData( byte[] scanRecord ){
		if(scanRecord.length > 30)
		{
		   
		    {
		            String uuid = Integer.toHexString(scanRecord[9] & 0xff) 
		            + Integer.toHexString(scanRecord[10] & 0xff)
		            + Integer.toHexString(scanRecord[11] & 0xff)
		            + Integer.toHexString(scanRecord[12] & 0xff)
		            + "-"
		            + Integer.toHexString(scanRecord[13] & 0xff)
		            + Integer.toHexString(scanRecord[14] & 0xff)
		            + "-"
		            + Integer.toHexString(scanRecord[15] & 0xff)
		            + Integer.toHexString(scanRecord[16] & 0xff)
		            + "-"
		            + Integer.toHexString(scanRecord[17] & 0xff)
		            + Integer.toHexString(scanRecord[18] & 0xff)
		            + "-"
		            + Integer.toHexString(scanRecord[19] & 0xff)
		            + Integer.toHexString(scanRecord[20] & 0xff)
		            + Integer.toHexString(scanRecord[21] & 0xff)
		            + Integer.toHexString(scanRecord[22] & 0xff)
		            + Integer.toHexString(scanRecord[23] & 0xff)
		            + Integer.toHexString(scanRecord[24] & 0xff);

		            String major = Integer.toHexString(scanRecord[25] & 0xff) + Integer.toHexString(scanRecord[26] & 0xff);
		            String minor = Integer.toHexString(scanRecord[27] & 0xff) + Integer.toHexString(scanRecord[28] & 0xff);
		            
		            Log.d(TAG, "UUID:"+uuid );
		            Log.d(TAG, "major:"+major );
		            Log.d(TAG, "minor:"+minor );
		        }
		}}}
	


	
	
		
		
		
	

 

   
  
	