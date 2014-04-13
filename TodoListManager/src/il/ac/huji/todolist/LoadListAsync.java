package il.ac.huji.todolist;

import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

public class LoadListAsync extends AsyncTask<Void, ToDoItem, Void> {
	private Context context;
	private Cursor cursor;	
	private ToDoSQLite sqlite;
	private ToDoItemAdapter listAdapter;
	
	public LoadListAsync(Context _context, ToDoItemAdapter _adapetr)
	{
		super();
		context = _context;
		sqlite = new ToDoSQLite(context);
		listAdapter = _adapetr;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		listAdapter.clear();
		
	}
	
	
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		cursor = sqlite.getTableCursor();
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}
	
	@Override
	protected void onProgressUpdate(ToDoItem... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		listAdapter.add(values[0]);
		listAdapter.notifyDataSetChanged();
	}





}
