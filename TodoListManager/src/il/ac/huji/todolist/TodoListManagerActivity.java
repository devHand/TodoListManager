package il.ac.huji.todolist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.os.Bundle;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class TodoListManagerActivity extends Activity {
	
	private ArrayList<ToDoItem> toDoList;
	private ToDoItemAdapter adaptToDO;
	private ItemCursorAdapter DBAdapter;
	private Cursor cursor;
	LoadListAsync loader;
	
	private ToDoSQLite sqlite;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_list_manager);
		
		Resources res = getResources();
		
		ListView list = (ListView)findViewById(R.id.lstTodoItems);
		
		toDoList = new ArrayList<ToDoItem>();
		adaptToDO = new ToDoItemAdapter(this, 
				android.R.layout.simple_list_item_1, 
				toDoList);

		list.setAdapter(adaptToDO);
		
		sqlite = new ToDoSQLite(getApplicationContext());
		loader = new LoadListAsync(getApplicationContext(), adaptToDO);
		loader.execute();
		
		//cursor = sqlite.getTableCursor();
		//DBAdapter = new ItemCursorAdapter( this, cursor);
		
		
		//list.setAdapter(DBAdapter); 

		
		
		
		
		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode){
		case 42:
			if (resultCode == RESULT_OK)
			{
				ToDoItem newItem = new ToDoItem(data.getStringExtra("title"),(Date)data.getExtras().get("dueDate"));

				
				//SQLite:
				sqlite.addItem(newItem);
				
//				cursor = sqlite.getTableCursor();
//				DBAdapter.swapCursor(cursor);
//				DBAdapter.notifyDataSetChanged();
				loader = new LoadListAsync(getApplicationContext(), adaptToDO);
				loader.execute();
				//parse:
				//ToDoParse.addItem(newItem);
					

				
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
