package il.ac.huji.todolist;

import java.util.Date;

public class ToDoItem {
	private String title;
	private Date date;
	
	public ToDoItem(String _title, Date _date)
	{
		title = _title;
		date = _date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
