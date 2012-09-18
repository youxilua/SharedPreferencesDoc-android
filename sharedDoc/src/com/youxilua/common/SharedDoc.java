package com.youxilua.common;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


public class SharedDoc{
	
	public final static String TAG = SharedDoc.class.getSimpleName();
	
	private SharedPreferences mSharedDoc;
	private SharedPreferences.Editor sharedDocEditor;
	private SharedPreferences tempShared;
	public SharedDoc(SharedPreferences sharedDoc,Context ctx){
		Log.d(TAG, "createt sharedDoc");
		this.mSharedDoc = sharedDoc;
		sharedDocEditor = mSharedDoc.edit();
		tempShared = SharedDocFactory.getTempHash(ctx);
	}
	
	private boolean checkHashCode(String key,String vaule){
		int currentHashCode = vaule.hashCode();
		int valueHashCode = tempShared.getInt(key, -1);
		if(currentHashCode == valueHashCode){
			Log.d(TAG, "the same string");
			return false;
		}else{
			tempShared.edit().putInt(key, currentHashCode).commit();
			return true;
		}
	}
	
	public boolean insertOrUpdateDoc(String key, Object value) {
		
		if (value instanceof String) {
			String v = (String) value;
			if(checkHashCode(key,v)){
				sharedDocEditor.putString(key, v);
			}else{
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
	
	
	public Object getDataValue(String key){
		Map<String, ?> tempData = mSharedDoc.getAll();
		return tempData.get(key);
	}
	
	public boolean removeDoc(String ... keys){
		for (String removeKey : keys) {
			sharedDocEditor.remove(removeKey);
		}
		return sharedDocEditor.commit();
	}
	
	public boolean clearDoc() {
		sharedDocEditor.clear();
	    return	sharedDocEditor.commit();
	}


	
	

}
