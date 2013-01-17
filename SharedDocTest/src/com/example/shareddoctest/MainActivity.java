package com.example.shareddoctest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.youxilua.common.SharedDoc;
import com.youxilua.common.SharedDocFactory;
import com.youxilua.common.SharedType;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String x1 = "helloworld";

		SharedDoc s1 = SharedDocFactory.getSharedDoc(this, null);
		s1.insertOrUpdateDoc("x1", x1);
		TextView tv = new TextView(this);
		tv.setText(s1.getDataValue("x1"));

		int i1 = 10000;
		long l1 = 20000L;
		//float d1 = 1.2222f;
		float f1 = 1.333f;

		s1.insertOrUpdateDoc("i1", i1);
		s1.insertOrUpdateDoc("l1", l1);
	//	s1.insertOrUpdateDoc("d1", d1);
		s1.insertOrUpdateDoc("f1", f1);

		TextView tv2 = new TextView(this);
		tv2.setText(getValues(s1.getDataValue("i1", SharedType.INT)));
		
		TextView tv3 = new TextView(this);
		tv3.setText(getValues(s1.getDataValue("l1", SharedType.LONG)));
		
		TextView tv4 = new TextView(this);
		//tv4.setText(getValues(s1.getDataValue("d1", SharedType.FLOAT)));
		
		TextView tv5 = new TextView(this);
		tv5.setText(getValues(s1.getDataValue("f1", SharedType.FLOAT)));
		ViewGroup.LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		addContentView(tv, lp);
		addContentView(tv2, lp);
		addContentView(tv3, lp);
		addContentView(tv4, lp);
		addContentView(tv5, lp);
		

	}

	public String getValues(Object o) {
		return String.valueOf(o);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
