package il.ac.huji.todolist;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class AddNewTodoItemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_todo_item);
		
		final TextView addtext = (TextView)findViewById(R.id.edtNewItem);
		
		final DatePicker calendar = (DatePicker) findViewById(R.id.datePicker);
		
		Button okButton = (Button) findViewById(R.id.btnOK);
		Button cancelButton = (Button) findViewById(R.id.btnCancel);
		
		
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String title = addtext.getText().toString();
				Date date = new GregorianCalendar(calendar.getYear(), calendar.getMonth(),calendar.getDayOfMonth()).getTime();
				
				Intent result = new Intent(); 
				result.putExtra("title", title); 
				result.putExtra("dueDate", date);
				setResult(RESULT_OK, result); 

				finish();
			}
		});
		
		
		
	}



}
