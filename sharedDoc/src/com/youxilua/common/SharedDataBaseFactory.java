package com.youxilua.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class SharedDataBaseFactory {
	
	private static SharedPreferences tempShared;
	private final static String TEMPHASHSHARED = "tempHashDoc";
	public static SharedDataBase newInstanceSharedDatabase(Context ctx){
		SharedPreferences tempShared = PreferenceManager.getDefaultSharedPreferences(ctx);
		return new SharedDataBase(tempShared,ctx);
	}
	
	public static SharedDataBase newInstanceSharedDatabase(Context ctx,String name){
		SharedPreferences tempShared = ctx.getSharedPreferences(name, Context.MODE_PRIVATE);
		return new SharedDataBase(tempShared,ctx);
	}
	
	protected static SharedPreferences getTempHash(Context ctx) {
		if(tempShared == null){
			Log.d(SharedDataBase.TAG, "createt temp sharedDoc");
			tempShared = ctx.getSharedPreferences(TEMPHASHSHARED, Context.MODE_PRIVATE);
		}
		return tempShared;
	}
	
	
}
