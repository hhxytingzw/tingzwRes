package com.tingzw.gallery;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

public class Gallery extends Activity {
	private android.widget.Gallery gallery;
	private int[] resIds={
		R.drawable.pic_01,R.drawable.pic_02,R.drawable.pic_03,R.drawable.pic_04,R.drawable.pic_05,			
		R.drawable.pic_06,R.drawable.pic_07,R.drawable.pic_08,R.drawable.pic_09		
			
	};
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        gallery=(android.widget.Gallery) this.findViewById(R.id.gallery);
        ImageAdapter imageAdapter=new ImageAdapter(this);
        gallery.setAdapter(imageAdapter);
        final Context ctx=this;
        gallery.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
			    Toast.makeText(ctx,resIds[position%resIds.length]+"", Toast.LENGTH_SHORT).show();  
			}
		
        });
        
    }
    
    public class ImageAdapter extends BaseAdapter{
        private Context context;
        private int mGralleryItemBg;
        
        public ImageAdapter(Context context)
        {
        	this.context=context; 
        	TypedArray typedArray=obtainStyledAttributes(R.styleable.Gallery);
        	mGralleryItemBg=typedArray.getResourceId(R.styleable.Gallery_android_galleryItemBackground,0);
        }
		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public Object getItem(int position) {
			return resIds[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View arg1, ViewGroup arg2) {
			
			ImageView imageView=new ImageView(context);
			imageView.setImageResource(resIds[position%resIds.length]);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setLayoutParams(new android.widget.Gallery.LayoutParams(LayoutParams.FILL_PARENT, 200));
			imageView.setBackgroundResource(mGralleryItemBg);
			return imageView;
		}
    }
}