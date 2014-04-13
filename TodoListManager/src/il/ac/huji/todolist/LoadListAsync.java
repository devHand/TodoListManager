package il.ac.huji.todolist;

import android.os.AsyncTask;

public class LoadListAsync extends AsyncTask<Void, ToDoItem, Void> {

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}
	
	
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		return null;
		ToDoItem item = new ToDoItem()
		publishProgress(values)
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
	}





}
