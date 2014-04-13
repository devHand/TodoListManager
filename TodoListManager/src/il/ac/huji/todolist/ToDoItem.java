package il.ac.huji.todolist;

import java.util.Date;

public class ToDoItem {
	private String title;
	private Date date;
	private int id;
	
	public ToDoItem(String _title, Date _date, int _id)
	{
		title = _title;
		date = _date;
		id = _id;
	}
	public ToDoItem(String _title, Date _date)
	{
		title = _title;
		date = _date;
		id = 0;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
