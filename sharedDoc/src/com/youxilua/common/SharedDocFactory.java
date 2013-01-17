package com.youxilua.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class SharedDocFactory {

	private volatile static SharedPreferences tempShared;
	private final  static String TEMPHASHSHARED = "tempHashDoc";
	
	private static SharedDoc getDefaultSharedDoc(Context ctx) {
		SharedPreferences tempShared = PreferenceManager
				.getDefaultSharedPreferences(ctx);
		return new SharedDoc(tempShared, ctx);
	}

	private SharedDocFactory() {
	}

	public static SharedDoc getSharedDoc(Context ctx, String name) {

		if (name == null) {
			return getDefaultSharedDoc(ctx);
		} else {
			SharedPreferences tempShared = ctx.getSharedPreferences(name,
					Context.MODE_PRIVATE);
			return new SharedDoc(tempShared, ctx);
		}

	}

	protected static SharedPreferences getTempHash(Context ctx) {
		if (tempShared == null) {
			Log.d(SharedDoc.TAG, "createt temp sharedDoc");
			synchronized(SharedDoc.class){
				if(tempShared == null){
					tempShared = ctx.getSharedPreferences(TEMPHASHSHARED,
							SharedConfig.SHAREDTEMPMODE);
				}
			}
		}
		return tempShared;
	}

}
