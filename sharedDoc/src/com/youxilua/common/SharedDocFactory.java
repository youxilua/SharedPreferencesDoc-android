package com.youxilua.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class SharedDocFactory {

	private static SharedPreferences tempShared;
	private final static String TEMPHASHSHARED = "tempHashDoc";

	private static SharedDoc newInstanceSharedDoc(Context ctx) {
		SharedPreferences tempShared = PreferenceManager
				.getDefaultSharedPreferences(ctx);
		return new SharedDoc(tempShared, ctx);
	}

	private SharedDocFactory() {
	}

	public static SharedDoc newInstanceSharedDoc(Context ctx, String name) {

		if (name == null) {
			return newInstanceSharedDoc(ctx);
		} else {
			SharedPreferences tempShared = ctx.getSharedPreferences(name,
					Context.MODE_PRIVATE);
			return new SharedDoc(tempShared, ctx);
		}

	}

	protected static SharedPreferences getTempHash(Context ctx) {
		if (tempShared == null) {
			Log.d(SharedDoc.TAG, "createt temp sharedDoc");
			tempShared = ctx.getSharedPreferences(TEMPHASHSHARED,
					Context.MODE_PRIVATE);
		}
		return tempShared;
	}

}
