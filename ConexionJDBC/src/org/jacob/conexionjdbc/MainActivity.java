package org.jacob.conexionjdbc;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements OnClickListener {

	Button btnGen, btnInv, btnInser;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnGen=(Button)findViewById(R.id.button1);
        btnInv=(Button)findViewById(R.id.button2);
        btnInser=(Button)findViewById(R.id.button3);
        
        btnGen.setOnClickListener(this);
        btnInser.setOnClickListener(this);
        btnInv.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			Intent intent=new Intent(this,ConsultaGeneral.class);
			startActivity(intent);
			break;

		case R.id.button2:
			Intent intent2=new Intent(this,ConsultaIndividual.class);
			startActivity(intent2);
			break;
			
		case R.id.button3:
			Intent intent3=new Intent(this,Insertar.class);
			startActivity(intent3);
			break;
		}
		
	}
}
