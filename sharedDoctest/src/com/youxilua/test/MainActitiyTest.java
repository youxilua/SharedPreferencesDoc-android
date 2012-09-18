package com.youxilua.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.jayway.android.robotium.solo.Solo;
import com.youxilua.MainActivity;
import com.youxilua.common.SharedDoc;
import com.youxilua.common.SharedDocFactory;

public class MainActitiyTest extends
		ActivityInstrumentationTestCase2<MainActivity> {
	Solo testSolo;
	public MainActitiyTest() {
		super("com.youxilua", MainActivity.class);
	}
	AQuery testQuery;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		testQuery = new AQuery(getActivity());
		testSolo = new Solo(getInstrumentation(),getActivity());
	}
	
	
	@Smoke
	public void test(){
		SharedDoc sdb = SharedDocFactory.newInstanceSharedDoc(getActivity(),"helloworld");
		SharedDoc sdb2 = SharedDocFactory.newInstanceSharedDoc(getActivity(),"helloworld2");
		sdb2.insertOrUpdateDoc("test_lib", "helloworld2");
		sdb2.insertOrUpdateDoc("test_lib", "helloworld2");
		sdb.insertOrUpdateDoc("test_lib", "helloworld");
		sdb.insertOrUpdateDoc("test_lib2", "helloworld2");
		sdb.insertOrUpdateDoc("test_lib2", "helloworld3");
//		sdb.insertOrUpdateDoc("int", 1);
//		sdb.insertOrUpdateDoc("long", System.currentTimeMillis());
//		sdb.insertOrUpdateDoc("boolean", true);
//		sdb.insertOrUpdateDoc("float", 3.14f);
		assertEquals("helloworld", sdb.getDataValue("test_lib"));
		assertEquals("helloworld", sdb.getDataValue("test_lib"));
		assertEquals("helloworld2", sdb2.getDataValue("test_lib"));
		sdb.removeDoc("test_lib");
		assertNull(sdb.getDataValue("test_lib"));
		
//		testQuery.id(R.id.test_shared).text("test->"+sdb.getDataValue("test_lib"));
//		testQuery.id(R.id.textView1).text("int"+sdb.getDataValue("int")+"long"+sdb.getDataValue("long")
//				+"boolean"+sdb.getDataValue("boolean")+"float"+ sdb.getDataValue("float")+"null"+sdb.getDataValue("null"));
//		final String result = null;
//		testQuery.ajax("http://www.baidu.com", String.class,
//				new AjaxCallback<String>() {
//
//					@Override
//					public void callback(String url, String object,
//							AjaxStatus status) {
//						super.callback(url, object, status);
//						Log.d("callback","value-->"+ object);
//						testQuery.id(com.youxilua.R.id.textView1).text("null->" +
//								""+object);
//						
//					}
//				});
	
		testSolo.sleep(5000);
		
		
	}
	
	

}
