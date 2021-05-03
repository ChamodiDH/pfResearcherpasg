$(document).ready(function()
{
 $("#alertSuccess").hide();
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateResearcherForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidResearcherIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
		{
		 url : "RsearcherAPI",
		 type : type,
		 data : $("#formResearcherinfo").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onResearcherSaveComplete(response.responseText, status);
		 }
		});
});

function onResearcherSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divPaymentGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error occurred while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error occured while saving..!");
 $("#alertError").show();
 }
 $("#hidResearcherIDSave").val("");
 $("#formResearcherinfo")[0].reset();
}



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidResearcherIDSave").val($(this).closest("tr").find('#hidResearcherIDUpdate').val());
 $("#Name").val($(this).closest("tr").find('td:eq(0)').text());
 $("#Telno").val($(this).closest("tr").find('td:eq(1)').text());
 $("#Spc").val($(this).closest("tr").find('td:eq(2)').text());
 $("#Uni").val($(this).closest("tr").find('td:eq(3)').text());
 $("#Email").val($(this).closest("tr").find('td:eq(4)').text());
});

// REMOVE 
$(document).on("click", "#btnRemove", function(event)
		{$.ajax(
				{
					 url : "ResearcherAPI",
					 type : "DELETE",
					 data : "ResearcherID=" + $(this).data("ResearcherID"),
					 dataType : "text",
					 complete : function(response, status)
					 {
					 onResearcherDeleteComplete(response.responseText, status);
					 }
					});
			});

function onResearcherDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully Removed.");
 $("#alertSuccess").show();
 $("#divPaymentGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error occured while Removing.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error occured while saving..!");
 $("#alertError").show();
 }
}

// CLIENTMODEL=========================================================================
function validateResearcherForm()
{
// ID
if ($("#ResearcherID").val().trim() == "")
 {
 return "Insert Researcher ID!";
 }
// validate name field
if ($("#Name").val().trim() == "")
 {
 return "Insert Name!";
 } 
// validate telephone field
if ($("#Telno").val().trim() == "")
{
return "Insert Telephone number!";
}
// validate telno 
var tmpTel = $("#Telno").val().trim();
if (!$.isNumeric(tmpTel))
 {
 return "Insert a valid Telephone number.";
 }


// validate specialization field
if ($("#Spc").val().trim() == "")
 {
 return "Insert Specialization.";
 }
//validate university field
if ($("#Uni").val().trim() == "")
 {
 return "Insert University.";
 }
// validate email field
if ($("#Email").val().trim() == "")
{
return "Insert Email.";
}
return true;
}