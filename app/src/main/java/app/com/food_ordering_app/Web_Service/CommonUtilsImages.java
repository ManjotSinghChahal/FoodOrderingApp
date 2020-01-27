package app.com.food_ordering_app.Web_Service;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class CommonUtilsImages {

	@SuppressWarnings("deprecation")
	public static Bitmap getCircularBitmap(Bitmap bitmap) {
		
		Bitmap output;

	    if (bitmap.getWidth() > bitmap.getHeight()) {
	        output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Config.ARGB_8888);
	    } else {
	        output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Config.ARGB_8888);
	    }

	    Canvas canvas = new Canvas(output);

	    final int color = 0xff424242;
	    final Paint paint = new Paint();
	    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

	    float r = 0;

	    if (bitmap.getWidth() > bitmap.getHeight()) {
	        r = bitmap.getHeight() / 2;
	    } else {
	        r = bitmap.getWidth() / 2;
	    }

	    paint.setAntiAlias(true);
	    canvas.drawARGB(0, 0, 0, 0);
	    paint.setColor(color);
	    canvas.drawCircle(r, r, r, paint);
	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	    canvas.drawBitmap(bitmap, rect, rect, paint);
	    return output;

	}
	 public static ArrayList<HashMap<String, String>> filter(String text,ArrayList<HashMap<String, String>> arr,String key) {
	     // TODO Auto-generated method stub
	     ArrayList<HashMap<String,String>> compare;
	     text = text.toLowerCase(Locale.getDefault());


	     if (text.length() == 0) {
	      compare= new ArrayList<HashMap<String,String>>();
	      compare.clear();
	      compare.addAll( arr);

	     } else {
	      compare= new ArrayList<HashMap<String,String>>();
	      compare.clear();
	      for (int i = 0; i < arr.size(); i++) {

	       if ( arr.get(i).get(key).toLowerCase(Locale.getDefault()).contains(text)) {

	        // compare.addAll(global.getParent_List().get(i).get(GlobalConstants.parent_name));
	        compare.add( arr.get(i));
	       }
	      }
	     }

		 Log.e("compare",compare.toString());
		 return compare;

	    }

}
