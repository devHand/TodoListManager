package il.ac.huji.todolist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import android.os.Bundle;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TodoListManagerActivity extends Activity {
	
	private ArrayList<ToDoItem> toDoList;
	private ToDoItemAdapter adaptToDO;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_list_manager);
	
		Resources res = getResources();
		
		toDoList = new ArrayList<ToDoItem>();
		ListView list = (ListView)findViewById(R.id.lstTodoItems);
		
		
		adaptToDO = new ToDoItemAdapter(this, 
												android.R.layout.simple_list_item_1, 
												toDoList);
		
		list.setAdapter(adaptToDO);
		
		
		
		
		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode){
		case 42:
			if (resultCode == RESULT_OK)
			{
				ToDoItem newItem = new ToDoItem(data.getStringExtra("title"),(Date)data.getExtras().get("dueDate"));
				adaptToDO.add(newItem);
				adaptToDO.notifyDataSetChanged();
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.todo_list_manager, menu);
		return true;
	}
	

	
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case R.id.menuItemAdd: 
			Intent intent = new Intent(getApplicationContext(), AddNewTodoItemActivity.class);
			startActivityForResult(intent, 42);
				
//				
		}
		return true;
	}

}
