package com.youtube.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.doa.*;
import com.youtube.util.ToJSON;

@Path("/v1/inventory")
public class V1_inventory {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllInventory() throws Exception {

		PreparedStatement query = null;
		String returnString = null;
		Connection conn = null;
		Response rb = null;

		try {
			conn = Oracle.OracleConn().getConnection();
			query = conn.prepareStatement("SELECT * FROM OB_SURFBOARDS");
			
			ResultSet rs = query.executeQuery();
			ToJSON converter = new ToJSON();
			JSONArray json = new JSONArray();
			
			json = converter.toJSONArray(rs);
						
			returnString = json.toString();
			rb = Response.ok(returnString).build();

			query.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (conn != null) {
				query.close();
			}
		}
		return rb; 
	}  
}
