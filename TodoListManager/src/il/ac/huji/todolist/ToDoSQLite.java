package il.ac.huji.todolist;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ToDoSQLite {
	private SQLiteDatabase db;
	private DBHelper helper;
	
	public ToDoSQLite(Context context){
		helper = new DBHelper(context);
		db = helper.getWritableDatabase();
	}
	
	public void addItem(ToDoItem item){
		ContentValues sqlitem = new ContentValues();
		sqlitem.put("title", item.getTitle());
		sqlitem.put("due", item.getDate().getTime());
		db.insert("todo", null, sqlitem);
	}
	
	public ToDoItem addAndReturnItem(String title,Date due)
	{
		ContentValues sqlitem = new ContentValues();
		sqlitem.put("title", title);
		sqlitem.put("due", due.getTime());
		long rowId = db.insert("todo", null, sqlitem);
		Cursor cursor = db.query("todo", new String[] {"_id"}, "ROWID like '"+ rowId + "'",  new String[]{} ,null, null, null);
		cursor.moveToFirst();
		return new ToDoItem(title, due, cursor.getInt(0));
	}
	
	public void deleteItem(int id)
	{
		db.delete("todo", "_id like '" + id + "'",
				null);
		
	}
	
	public Cursor getTableCursor()
	{
		return db.query("todo", new String[] {"_id","title", "due"}, null, null, null, null, null);
	}

	public void close()
	{
		helper.close();
	}
}
