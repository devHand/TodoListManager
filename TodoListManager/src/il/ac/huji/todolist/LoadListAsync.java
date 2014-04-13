package il.ac.huji.todolist;

import java.util.Date;

import android.database.Cursor;
import android.os.AsyncTask;

public class LoadListAsync extends AsyncTask<Void, ToDoItem, Void> {
	private Cursor cursor;	
	private ToDoSQLite sqlite;
	private ToDoItemAdapter listAdapter;
	
	public LoadListAsync(ToDoSQLite _sqlite, ToDoItemAdapter _adapetr)
	{
		super();
	
		sqlite = _sqlite;
		listAdapter = _adapetr;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		listAdapter.clear();
		
	}
	
	
	@Override
	protected Void doInBackground(Void... params) {
		cursor = sqlite.getTableCursor();
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ToDoItem item = new ToDoItem(cursor.getString(1), new Date(cursor.getLong(2)), cursor.getInt(0));
			publishProgress(item);
			cursor.moveToNext();
		}
		return null;
	}


	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
	}
	
	@Override
	protected void onProgressUpdate(ToDoItem... values) {
		super.onProgressUpdate(values);
		
		listAdapter.add(values[0]);
		listAdapter.notifyDataSetChanged();
	}





}
