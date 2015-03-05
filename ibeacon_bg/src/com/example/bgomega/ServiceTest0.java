package com.example.bgomega;





import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;





public class ServiceTest0 extends Activity {
  
  Button startButton;
  Button stopButton;
  common com;
  final int INTERVAL_PERIOD = 1000;
  Timer timer = new Timer();
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViews();
  	  startButton.setOnClickListener(new OnClickListener(){
      @Override
      public void onClick(View v) {
        startService(new Intent(getBaseContext(),MyService.class));
        TextView textview1=(TextView)findViewById(R.id.textView1);
        textview1.setText("スキャン中");
      }
        });
        
        stopButton.setOnClickListener(new OnClickListener(){
      @Override
      public void onClick(View v) {
    	  
        stopService(new Intent(getBaseContext(),MyService.class));
        com = (common) getApplication();
        TextView textview1=(TextView)findViewById(R.id.textView1);
        textview1.setText("スキャン終了 ");
        
      }
        });
    }
    
    protected void findViews(){
      startButton = (Button)findViewById(R.id.start_button);
      stopButton = (Button)findViewById(R.id.stop_button);
    }
   
    
}
