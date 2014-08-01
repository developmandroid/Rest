package com.youtube.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.rest.dao.Postgre;
import com.youtube.rest.util.ToJSON;


@Path("/v1/inventory")
public class V1_Inventory {
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllPcParts() throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		String returnString= null;
		
		Response rb = null;
		
		
		try {
			
			conn = Postgre.postgreConn().getConnection();
			query = conn.prepareStatement("select * from PC_PARTS");
			ResultSet rs = query.executeQuery();
			
			
			
			ToJSON converter = new ToJSON();
			JSONArray json = new JSONArray();
			
			json = converter.toJSONArray(rs);
			query.close();
			
			returnString = json.toString();
			rb = Response.ok(returnString).build();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) conn.close();
		}
		
		return rb;
		
	
		

		
		
		
		
	}
	
	
	
	
	
	
	
	
	

}
