package com.youxilua.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedDoc {

	public final static String TAG = SharedDoc.class.getSimpleName();

	private SharedPreferences mSharedDoc;
	private SharedPreferences.Editor sharedDocEditor;
	private SharedPreferences tempShared;
	private SharedPreferences.Editor tempSharedEdit;

	public SharedDoc(SharedPreferences sharedDoc, Context ctx) {
		Log.d(TAG, "createt sharedDoc");
		this.mSharedDoc = sharedDoc;
		sharedDocEditor = mSharedDoc.edit();
		tempShared = SharedDocFactory.getTempHash(ctx);
		this.tempSharedEdit = tempShared.edit();
	}

	private boolean checkHashCode(String key, String vaule) {
		int currentHashCode = vaule.hashCode();
		int valueHashCode = tempShared.getInt(key, -1);
		if (currentHashCode == valueHashCode) {
			Log.d(TAG, "the same string");
			return false;
		} else {
			tempSharedEdit.putInt(key, currentHashCode).commit();
			return true;
		}
	}

	public boolean insertOrUpdateDoc(String key, Object value) {

		if (value instanceof String) {
			String v = (String) value;
			if (checkHashCode(key, v)) {
				sharedDocEditor.putString(key, v);
			} else {
				return true;
			}

		} else if (value instanceof Integer) {
			sharedDocEditor.putInt(key, (Integer) value);

		} else if (value instanceof Long) {
			sharedDocEditor.putLong(key, (Long) value);

		} else if (value instanceof Float) {
			sharedDocEditor.putFloat(key, (Float) value);

		} else if (value instanceof Boolean) {
			sharedDocEditor.putBoolean(key, (Boolean) value);
		}
		return sharedDocEditor.commit();
	}

	public String getDataValue(String key) {
		return mSharedDoc.getString(key, null);
	}

	public Object getDataValue(String key, SharedType sharedType) {
		if (sharedType != null) {
			switch (sharedType) {
			case BOOLEAN:
				return Boolean.valueOf(mSharedDoc.getBoolean(key, false));
			case INT:
				return Integer.valueOf(mSharedDoc.getInt(key, -1));
			case FLOAT:
				return Float.valueOf(mSharedDoc.getFloat(key, -1f));
			case LONG:
				return Long.valueOf(mSharedDoc.getLong(key, -1L));
			default:
				return getDataValue(key);
			}
		} else {
			return getDataValue(key);
		}

	}

	public boolean removeDoc(String... keys) {
		for (String removeKey : keys) {
			sharedDocEditor.remove(removeKey);
			tempSharedEdit.remove(removeKey);
		}
		tempSharedEdit.commit();
		return sharedDocEditor.commit();
	}

	public boolean clearDoc() {
		for (String key : mSharedDoc.getAll().keySet()) {
			tempSharedEdit.remove(key);
		}
		sharedDocEditor.clear();
		tempSharedEdit.commit();
		return sharedDocEditor.commit();
	}

}
