package com.youtube.rest.status;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.*;
import com.youtube.doa.*;

@Path("/v1/status")
public class V1_status {

	private static final String apiVersion = "00.01.00";

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Web Services</p>";
	}

	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version is: </p>" + apiVersion;
	}

	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDataBaseStatus() throws Exception {

		PreparedStatement query = null;
		String returnString = null;
		String myString = null;
		Connection conn = null;

		try {
			conn = Oracle.OracleConn().getConnection();
			/*
			 * query = conn .prepareStatement(
			 * "select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') DATETIME" +
			 * " FROM SYS.DUAL");
			 */
			query = conn
					.prepareStatement("select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') DATETIME FROM OB_SURFBOARDS");

			ResultSet rs = query.executeQuery();

			// while (rs.next()) {myString = rs.getString("DATETIME");

			while (rs.next()) {
				myString = rs.getString("DATETIME");
			}

			query.close();

			returnString = "<p>Database Status</p> "
					+ "<p>Database Date/Time return: " + myString + "</p>";

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (conn != null) {
				query.close();
			}
		}
		return returnString;
	}

}
