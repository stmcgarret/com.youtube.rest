package com.youtube.util;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
 
import java.sql.ResultSet;
import org.owasp.esapi.ESAPI;

//import java.sql.Types;

public class ToJSON {

	public JSONArray toJSONArray(ResultSet rs) throws Exception {
		
		JSONArray json = new JSONArray();
		
		try {
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			String temp = null;
			
			// loop through rows first
			while (rs.next()) {
				//figure out # of cols
				int numColumns = rsmd.getColumnCount();
				//convert each row to JSON object
				JSONObject obj = new JSONObject();
				
				// loop through columns
				for (int i = 1; i < numColumns+1; i++) {
					String columnName = rsmd.getColumnName(i);
					
					if(rsmd.getColumnType(i) == java.sql.Types.ARRAY) {
						obj.put(columnName, rs.getArray(columnName));
					} else if (rsmd.getColumnType(i) == java.sql.Types.BIGINT) {
						obj.put(columnName, rs.getInt(columnName));
					} else if (rsmd.getColumnType(i) == java.sql.Types.BOOLEAN) {
						obj.put(columnName, rs.getBoolean(columnName));
					} else if (rsmd.getColumnType(i) == java.sql.Types.BLOB) {
						obj.put(columnName, rs.getBlob(columnName));
					} else if (rsmd.getColumnType(i) == java.sql.Types.DOUBLE) {
						obj.put(columnName, rs.getDouble(columnName));
					} else if (rsmd.getColumnType(i) == java.sql.Types.FLOAT) {
						obj.put(columnName, rs.getFloat(columnName));
					} else if (rsmd.getColumnType(i) == java.sql.Types.INTEGER) {
						obj.put(columnName, rs.getInt(columnName));
					} else if (rsmd.getColumnType(i) == java.sql.Types.NVARCHAR) {
						obj.put(columnName, rs.getNString(columnName));
					} else if (rsmd.getColumnType(i) == java.sql.Types.VARCHAR) {
						// This code will make sure that VARCHAR2 is in its base state and is not encoded
						// as would be the case if a script was attempted to be injected.
						temp = rs.getString(columnName);
						temp = ESAPI.encoder().canonicalize(temp);
						temp = ESAPI.encoder().encodeForHTML(temp);
						obj.put(columnName, temp);
						//obj.put(columnName, rs.getString(columnName));
					} else if (rsmd.getColumnType(i) == java.sql.Types.TINYINT) {
						obj.put(columnName, rs.getInt(columnName));
					} else if (rsmd.getColumnType(i) == java.sql.Types.SMALLINT) {
						obj.put(columnName, rs.getInt(columnName));
					} else if (rsmd.getColumnType(i) == java.sql.Types.DATE) {
						obj.put(columnName, rs.getDate(columnName));
					} else if (rsmd.getColumnType(i) == java.sql.Types.TIMESTAMP) {
						obj.put(columnName, rs.getTimestamp(columnName));
					} else if (rsmd.getColumnType(i) == java.sql.Types.NUMERIC) {
						obj.put(columnName, rs.getBigDecimal(columnName));
					} else {
						obj.put(columnName, rs.getObject(columnName));
					}			
				} // end for
				
				json.put(obj);
			} // end while
		}
		
		catch (Exception e){
			e.printStackTrace();
		}
		return json;
	}
	
}
