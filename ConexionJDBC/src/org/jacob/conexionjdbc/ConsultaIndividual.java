package org.jacob.conexionjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ConsultaIndividual extends Activity implements OnClickListener {

	TextView tv2;
	Spinner spControl;
	ArrayAdapter<String> adElemento;
	Vector<String> nControl = new Vector<String>();
	String datos = "";
	Button btn;
	
	String url="jdbc:postgresql://tiburcio.cdmon.org:5432/bd10091168Cel2";
    Connection con=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consultaindividual);
		
		tv2=(TextView)findViewById(R.id.textView2);
		btn=(Button)findViewById(R.id.button1);
		
		spControl = (Spinner) findViewById(R.id.spinner1);
		spControl.setVisibility(View.VISIBLE);
		
		btn.setOnClickListener(this);
		
		cargaSpiner();
		
	}
	
	private void cargaSpiner(){
		
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
	             nControl.add(rs.getString(1));
	             //System.out.println(nombre+"\n"+signo+marca+"\n"+"============");
//				 tv1.append(modelo+"  "+marca+"  "+precio+"\n");
//				 tv1.append("------------------------------------"+"\n");
				 
	           }
	           con.close();
	           inst.close();
	           
	           if (nControl.size() == 0) {
					tv2.setText("No hay alumnos existentes");
					spControl.setVisibility(View.GONE);
				} else {
					adElemento = new ArrayAdapter<String>(this,
							android.R.layout.simple_spinner_item, nControl);
					spControl.setAdapter(adElemento);
				}
	         }
	      catch(Exception e){
	           System.out.println("Error: "+e.getMessage());
	      }
	}
	
	private void consultaIndividual(){
		String modeloCel =spControl.getSelectedItem().toString();
		String ordenSQL="select * from celulares where modelo="+"'"+modeloCel+"'";
		
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
			 tv2.append("Modelo: "+modelo+"\nMarca: "+marca+"\nPrecio: "+precio+"\n");
//			 tv1.append("------------------------------------"+"\n");
          }
          con.close();
          inst.close();
          
          if (nControl.size() == 0) {
				tv2.setText("No hay alumnos existentes");
				spControl.setVisibility(View.GONE);
			} else {
				adElemento = new ArrayAdapter<String>(this,
						android.R.layout.simple_spinner_item, nControl);
				spControl.setAdapter(adElemento);
			}
        }
     catch(Exception e){
          System.out.println("Error: "+e.getMessage());
     }
		
		
	}


	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.button1){
			tv2.setText("");
			consultaIndividual();
		}
		
	}
	
	

}
