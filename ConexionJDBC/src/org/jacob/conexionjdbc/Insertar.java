package org.jacob.conexionjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insertar extends Activity implements OnClickListener {

	EditText modelo,marca,precio;
	Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insertar);
		
		modelo=(EditText)findViewById(R.id.editText1);
		marca=(EditText)findViewById(R.id.editText2);
		precio=(EditText)findViewById(R.id.editText3);
		
		btn=(Button)findViewById(R.id.button1);	
		btn.setOnClickListener(this);
	}
	
	private void insertar(String modelo,String marca, String precio){
		//189.226.38.239:5432
		String url="jdbc:postgresql://tiburcio.cdmon.org:5432/bd10091168Cel2";
	    Connection con=null;
	    String ordenSQL="insert into celulares values('"+modelo+"','"+marca+"',"+precio+");";

	     try
	         {
	           Class.forName("org.postgresql.Driver").newInstance();
	           System.out.println("driver...");
	           con=DriverManager.getConnection(url,"android","android");
	           System.out.println("Conectado...");
	           Statement inst=con.createStatement();
	           inst.executeUpdate(ordenSQL);
	           //System.out.println("Inserción lista...");
	           Toast.makeText(this, "Celular Agregado", Toast.LENGTH_SHORT).show();
	           con.close();
	           inst.close();
	         }
	      catch(Exception e){
	           Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
	      }
	     
	     Intent inten=new Intent(this,MainActivity.class);
	     startActivity(inten);
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.button1){
			String mod=modelo.getText().toString().trim();
			String mar=marca.getText().toString().trim();
			String pre=precio.getText().toString().trim();
			
			if(mod.compareTo("")==0 || mar.compareTo("")==0 || pre.compareTo("")==0){
				Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show();
			}else{
				insertar(mod, mar, pre);
			}
		}
	}
}
