<%@page import="com.Researcher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Researcher Details</title>
<link rel="stylesheet" href="Views/bootstrap.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Researcher.js"></script>

<style>
h1 {
	text-align: left;
	text-transform: uppercase;
	color: #4CAF50;
}
</style>

</head>
<body>

	<h1>GadgetBadget System - Researcher</h1>
	

	<h2>Add Researcher Details </h2>
	<br>

	<!--add form-->

	<div class="addform">
		<form name="formResearcherinfo" id="formResearcherinfo" class="form-horizontal" action="ResearcherDetails.jsp" method="post">
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Researcher ID :</label>
				<div class="col-sm-10">
					<input type="text" id="ResearcherID" name="ResearcherID" class="form-control" >
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Full Name :</label>
				<div class="col-sm-10">
					<input type="text" id="Name" name="Name" class="form-control" >
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Telephone no :</label>
				<div class="col-sm-10">
					<input type="text" id="Telno" name="Telno" class="form-control" >
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Specialization :</label>
				<div class="col-sm-10">
					<input type="text" id="Spc" name="Spc" class="form-control" >
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">University :</label>
				<div class="col-sm-10">
					<input type="text" id="Uni" name="Uni" class="form-control" >
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Email :</label>
				<div class="col-sm-10">
					<input type="text" id="Email" name="Email" class="form-control" >
				</div>
			</div>
			
<br>			
			<div class="form-group">
				<input type="button" id="btnSave" name="btnSave" class="btn btn-primary " value ="save">
				<input type="hidden" id="hiddenResearcherIDsave" name="hiddenResearcherIDsave" value="">
			</div>
	
		</form>
		
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
 <div id="divPaymentGrid">
 	<%{
 		Researcher ResearcherObj = new Researcher();
 		out.print(ResearcherObj.readResearcher());}
 	%>
</div>	
</div>
	

</body>
</html>