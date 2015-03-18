package org.jacob.conexionjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ConsultaGeneral extends Activity {
	
	TextView tv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consultageneral);
		
		tv1=(TextView)findViewById(R.id.textView1);
		tv1.setText("");
		
		String url="jdbc:postgresql://tiburcio.cdmon.org:5432/bd10091168Cel2";
	    Connection con=null;
	    String ordenSQL="select * from celulares";

	     try
	         {
	           Class.forName("org.postgresql.Driver").newInstance();
	           System.out.println("driver...");
	           con=DriverManager.getConnection(url,"android","android");
	           System.out.println("Conectado...");
	           Statement inst=con.createStatement();
	           ResultSet rs=inst.executeQuery(ordenSQL);
	           while (rs.next()){
	             String modelo=rs.getString(1);
	             String marca=rs.getString(2);
				 String precio=rs.getString(3);
	             //System.out.println(nombre+"\n"+signo+marca+"\n"+"============");
				 tv1.append(modelo+"  "+marca+"  "+precio+"\n");
				 tv1.append("------------------------------------"+"\n");
				 
	           }
	           con.close();
	           inst.close();
	         }
	      catch(Exception e){
	           Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
	      }
	}
}
