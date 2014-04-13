package il.ac.huji.todolist;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.parse.*;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ToDoItemAdapter extends ArrayAdapter<ToDoItem>{
	private ToDoSQLite sqlit;
	public ToDoItemAdapter(Context context, int textViewResourceId,
			ToDoItem[] objects) {
        super(context, textViewResourceId, objects);
        sqlit = new ToDoSQLite(context);
    }
	
	public ToDoItemAdapter(Context context, int textViewResourceId,
            List<ToDoItem> objects) {
        super(context, textViewResourceId, objects);
        sqlit = new ToDoSQLite(context);
    }

    public View getView(final int position, final View convertView, final ViewGroup parent){
    	View v = convertView;
		if(v == null){
			LayoutInflater inflater = LayoutInflater.from(getContext());
			v = inflater.inflate(R.layout.row,null);
			
		}
		if(v != null)
		{
			TextView title = (TextView) v.findViewById(R.id.txtTodoTitle);
			title.setText(getItem(position).getTitle());
			TextView date = (TextView) v.findViewById(R.id.txtTodoDueDate);
			if(null != getItem(position).getDate())
			{
				Date duaDate = getItem(position).getDate();
				date.setText(new SimpleDateFormat("dd/MM/yyyy").format(duaDate));
				duaDate.setHours(1);
				
				Date today = new Date();
				today.setHours(0);
				today.setMinutes(0);
				today.setSeconds(0);
				if(duaDate.before(today))
				{
					title.setTextColor(Color.RED);
					date.setTextColor(Color.RED);
				}
				else
				{
					title.setTextColor(Color.BLACK);
					date.setTextColor(Color.BLACK);
				}
			}
			else
			{
				date.setText(R.string.no_due_date);
			}
		}
		v.setOnLongClickListener(new android.view.View.OnLongClickListener() {
			
			
			public boolean onLongClick(View view) {
				final ToDoItem delItem = getItem(position);
				final Dialog dialog = new Dialog(getContext());
				dialog.setContentView(R.layout.dialog);
				dialog.setTitle(delItem.getTitle());
				Button menuItemDelete = (Button) dialog.findViewById(R.id.menuItemDelete);
				menuItemDelete.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						

						sqlit.deleteItem(getItem(position).getId());
						remove(getItem(position));
						
						
						dialog.dismiss();

						
					}
				});
				final String title = getItem(position).getTitle();
				if(title.startsWith("Call "))
				{
					Button menuItemCall = (Button) dialog.findViewById(R.id.menuItemCall);
					menuItemCall.setText(title);
					menuItemCall.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							String number = title.substring(5);
							Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number)); 
							getContext().startActivity(dial);
							dialog.dismiss();
							
						}
					});
					
				}
				else
				{
					Button menuItemCall = (Button) dialog.findViewById(R.id.menuItemCall);
					menuItemCall.setVisibility(View.GONE);
				}
				
				
				dialog.show();
				

				return true;
			}
		});
		return v;
    }

}
