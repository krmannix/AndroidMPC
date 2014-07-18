package com.globalappinitiative.gai_summer_project;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class CustomGridAdapter extends BaseAdapter {
	private Context context;
	private final String[] colorValues;
	private GridView grid;
	public ArrayList<ImageView> images;
	public CustomGridAdapter(Context context, String[] colorValues, GridView grid) {
		this.colorValues = colorValues;
		this.context = context;
		this.grid = grid;
		this.images = new ArrayList<ImageView>();
	}
	
	public ArrayList<ImageView> getButtons() {
		return this.images;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.colorValues.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View returnView;
		
		if (convertView == null) {
			
			// Inflate grid cell
			returnView = inflater.inflate(R.layout.row_view, null);
			ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) grid.getLayoutParams();
			ImageView image = (ImageView) returnView.findViewById(R.id.grid_button);
			
			// Set correct width
			int screenWidth = ((Activity) context).getWindowManager()
                    .getDefaultDisplay().getWidth() - params.leftMargin*2 - 10;
			image.getLayoutParams().height = screenWidth/3;
			image.getLayoutParams().width = screenWidth/3;
			
			// Get color
			int color = Color.parseColor(colorValues[position].trim());
			GradientDrawable button_shape = (GradientDrawable) context.getResources().getDrawable(R.drawable.button_shape);
			button_shape.setColor(color);
			
			// Set View Selector
			StateListDrawable imageStates = new StateListDrawable();
			imageStates.addState(new int[] {android.R.attr.state_pressed},
				    button_shape);
			imageStates.addState(new int[] {android.R.attr.state_focused},
					button_shape);
			imageStates.addState(new int[] { },
					button_shape);
			image.setImageDrawable(imageStates);
			
			// Add to ArrayList of buttons
			images.add(image);
		} else {
			returnView = convertView;
		}
		
		return returnView;
	}

}
