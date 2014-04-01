package il.ac.huji.todolist;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ItemCursorAdapter extends CursorAdapter {
	private ToDoSQLite sqlit;
	public ItemCursorAdapter(Context context, Cursor c) {
		super(context, c, 0);
		sqlit = new ToDoSQLite(context);
	}

	@Override
	public void bindView(View view, final Context context, Cursor cursor) {
		if (view != null) {
			final int id = cursor.getInt(0);
			final TextView title = (TextView) view
					.findViewById(R.id.txtTodoTitle);
			title.setText(cursor.getString(1));
			final TextView date = (TextView) view
					.findViewById(R.id.txtTodoDueDate);
			Date duaDate = new Date(cursor.getLong(2));
			final ToDoItem item = new ToDoItem(cursor.getString(1), new Date(cursor.getLong(2)));
			if (null != duaDate) {
				date.setText(new SimpleDateFormat("dd/MM/yyyy").format(duaDate));
				duaDate.setHours(1);

				Date today = new Date();
				today.setHours(0);
				today.setMinutes(0);
				today.setSeconds(0);
				if (duaDate.before(today)) {
					title.setTextColor(Color.RED);
					date.setTextColor(Color.RED);
				} else {
					title.setTextColor(Color.BLACK);
					date.setTextColor(Color.BLACK);
				}
			} else {
				date.setText(R.string.no_due_date);
			}

			view.setOnLongClickListener(new android.view.View.OnLongClickListener() {

				public boolean onLongClick(View view) {
					
					final Dialog dialog = new Dialog(context);
					dialog.setContentView(R.layout.dialog);
					dialog.setTitle(item.getTitle());
					Button menuItemDelete = (Button) dialog
							.findViewById(R.id.menuItemDelete);
					menuItemDelete.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							
							//SQLite:
							sqlit.deleteItem(id);
							
							//parse:
							ToDoParse.deleteItem(item);
							
							
							getCursor().requery();
							
							notifyDataSetChanged();
							dialog.dismiss();
						}
					});
					if (title.getText().toString().startsWith("Call ")) {
						Button menuItemCall = (Button) dialog
								.findViewById(R.id.menuItemCall);
						menuItemCall.setText(title.getText());
						menuItemCall.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								String number = title.getText().toString()
										.substring(5);
								Intent dial = new Intent(Intent.ACTION_DIAL,
										Uri.parse("tel:" + number));
								context.startActivity(dial);
								dialog.dismiss();

							}
						});

					} else {
						Button menuItemCall = (Button) dialog
								.findViewById(R.id.menuItemCall);
						menuItemCall.setVisibility(View.GONE);
					}

					dialog.show();

					return true;
				}
			});

		}

	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.row, null);
		return v;
	}

}
