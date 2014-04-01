package il.ac.huji.todolist;

import java.util.List;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ToDoParse {
	
	public static void addItem(ToDoItem item) {
		ParseObject itemParse = new ParseObject("ToDo"); 
		itemParse.put("title", item.getTitle()); 
		itemParse.put("due", item.getDate());
		itemParse.saveInBackground();
	}
	
	public static void deleteItem(ToDoItem item)
	{
		ParseQuery<ParseObject> query = ParseQuery.getQuery("ToDo");
		query.whereEqualTo("title", item.getTitle());
		query.whereEqualTo("due", item.getDate());
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> items,
					ParseException e) {
				if(e==null){
					if(items.size() > 0)
					{
						Log.i("parseDel", "find in Parse kuku" );
						items.get(0).deleteInBackground();
						Log.i("parseDel", "deleted in Parse kuku");
					}
					else
					{
						Log.i("parseDel", "unable to find in Parse");
					}
				}
				else
				{
					Log.d("parseDel", "unable to delete from Parse");
				}
			}
		});
	}

}
