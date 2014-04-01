package il.ac.huji.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ToDoSQLite {
	private SQLiteDatabase db;
	
	public ToDoSQLite(Context context){
		DBHelper helper = new DBHelper(context);
		db = helper.getWritableDatabase();
	}
	
	public void addItem(ToDoItem item){
		ContentValues sqlitem = new ContentValues();
		sqlitem.put("title", item.getTitle());
		sqlitem.put("due", item.getDate().getTime());
		db.insert("todo", null, sqlitem);
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

}
