package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Researcher {
	
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadgetdb", "root", "123qwebnm");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	
	public String insertResearcher(String Name, String Telno, String Spc , String Uni, String Email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into researcher  ('id', 'name', 'telno', 'specialization', 'university','email')"
					+ " values (?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Name);
			preparedStmt.setInt(3,Integer.parseInt(Telno));
			preparedStmt.setString(4,Spc);
			preparedStmt.setString(5, Uni);
			preparedStmt.setString(5, Email);
			//execute the statement
			preparedStmt.execute();
			con.close();
			String newResearcher = readResearcher();
			output = "{'status:'success', 'data': '" + newResearcher + "'}";
		} catch (Exception e) {
			output =  "{'status':'error', 'data': 'Error while inserting the Researcher.'}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String readResearcher() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Researcher ID</th><th>Researcher Name</th><th>Telno</th><th>Specialization</th><th>University</th><th>Email</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from researcher";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String ResearcherID = Integer.toString(rs.getInt("id"));
				String Name = rs.getString("name");
				String Telno = Integer.toString(rs.getInt("telno"));
				String Speciallization = rs.getString("specialization");
				String University = rs.getString("university");
				String Email = rs.getString("email");
// Add into the html table
				output += "<tr><td><input id='hidResearcherIDupdate' name='hidResearcherIDupdate' type='hidden' value='"+ ResearcherID 
						+"'>" +ResearcherID  + "</td>";
				output += "<td>" + Name + "</td>";
				output += "<td>" + Telno + "</td>";
				output += "<td>" + Speciallization + "</td>";
				output += "<td>" + University + "</td>";
				output += "<td>" + Email + "</td>";

// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-ResearcherID='"
						+  ResearcherID + "'>" + "</td></tr>"; 
				
				
			}
			con.close();
// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Researcher details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String updateResearcher(String ResearcherID, String Name, String Telno, String Spc,
			String Uni, String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
// create a prepared statement
			String query = "UPDATE researcher SET name=?, telno=?, specialization=?, university=?, email=?  WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setString(1, Name);
			preparedStmt.setInt(2, Integer.parseInt(Telno));
			preparedStmt.setString(3, Spc);
			preparedStmt.setString(4, Uni);
			preparedStmt.setString(4, email);
			preparedStmt.setInt(5, Integer.parseInt(ResearcherID));

// execute the statement
			preparedStmt.execute();
			con.close();
			String newResearcher = readResearcher();
			output = "{'status':'success', 'data': '" +newResearcher + "'}"; 
		} catch (Exception e) {
			output = "{'status':'error', 'data': 'Error while updating the Researcher details.'}"; 
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String deleteResearcher(String ResearcherID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
// create a prepared statement
			String query = "delete from researcher where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, Integer.parseInt(ResearcherID));
// execute the statement
			preparedStmt.execute();
			con.close();
			String newResearcher = readResearcher();
			 output = "{'status':'success', 'data: '" +newResearcher + "'}"; 
		} catch (Exception e) {
			output =  "{'status':'error', 'data': 'Error while deleting the researcher.'}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
	


