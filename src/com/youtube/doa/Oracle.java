package com.youtube.doa;

import javax.naming.*;
import javax.sql.*;

public class Oracle {

	private static DataSource Oracle = null;
	private static Context context = null;
	
	public static DataSource OracleConn() throws Exception {
		
		if (Oracle != null) {
			return Oracle;
		}
		
		try {
			if (context == null) {
				context = new InitialContext();
			}
			Oracle = (DataSource) context.lookup("OracleDAO");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return Oracle;
	}
	
}
