<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Proj-Share</title>
</head>
<body>
	<%
		edu.sjsu.cmpe275.entities.Project project= (edu.sjsu.cmpe275.entities.Project) request
		.getAttribute("project");
	%>
	<div id="wrapper">
		<div id="navigation">
			<%@ include file="common.jsp"%>
		</div>

		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						 <h3 class="page-header"> Project Details</h3>
                           <small> Name: &nbsp<b><%out.print(project.getProjectDetail().getTitle()); %></b></small><br>
                           <small> Owner: &nbsp<b><%out.print(project.getOwner().getName()); %></b></small><br>
                           <small> State:  &nbsp<b><%out.print(project.getProjectDetail().getState()); %></b></small><br>
                       <br>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i><a
								href="${pageContext.servletContext.contextPath}/dashboard">
									Dashboard</a></li>
							<li class="active"><i class="fa fa-dashboard"></i><a
								href="${pageContext.servletContext.contextPath}/getallprojects">
									List of all Projects</a></li>
							<li class="active"><i class="fa fa-edit"></i> Update
								Project!</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-offset-3 col-lg-5">
						<form role="form"
							action="${pageContext.servletContext.contextPath}/updateproject"
							method="post">
							<input class="form-control" type="hidden" name="projectid"
								value="<%out.print(project.getProjectId());%>"> <input
								class="form-control" type="hidden" name="ownerid"
								value="<%out.print(project.getOwner().getEmailid());%>">
							<div class="form-group">
								<label>Project Title:</label> <input class="form-control"
									placeholder="Give Your Project A Name" name="title"
									value="<%out.print(project.getProjectDetail().getTitle());%>">
							</div>
							<div class="form-group">
								<label>Description</label>
								<textarea class="form-control"
									placeholder="Desribe the project in brief!" name="description">
									<%
										out.print(project.getProjectDetail().getDescription());
									%>
								</textarea>
							</div>
							<div class="form-group">

								<div class="form-group">
									<label>Current State:</label> <input class="form-control"
										name="prev_state"
										value="<%out.print(project.getProjectDetail().getState());%>"
										readonly />
								</div>
								<label>Enter New State: &nbsp </label><br>
								<%
									if(project.getProjectDetail().getState().equals("Planning")){
								%>

								<label class="radio-inline"> <input type="radio"
									name="new_state" value="Ongoing">Ongoing
								</label> <label class="radio-inline"> <input type="radio"
									name="new_state" value="Cancelled">Cancelled
								</label> <label class="radio-inline"> <input type="radio"
									name="new_state" value="Completed">Completed
								</label>
								<%
									} else
								%>
								<%
									if(project.getProjectDetail().getState().equals("Ongoing")){
								%>


								<label class="radio-inline"> <input type="radio"
									name="new_state" value="Cancelled">Cancelled
								</label> <label class="radio-inline"> <input type="radio"
									name="new_state" value="Completed">Completed
								</label>
								<%
									}
								%>
								<%
									if(project.getValidstate()!=null && project.getValidstate().equals("unfinishedtask")){
								%>
								<label style="color: red;">All Tasks should be in
									assigned state with an assignee and have the estimated units of
									work filled in. </label>
								<%
									} else
								%>
								<%
									if(project.getValidstate()!=null && project.getValidstate().equals("unfinishedtak")){
								%>
								<label style="color: red;">Every task should either be
									in finished or cancelled state with at least one
									finished task </label>
								<%
									}
								%>
							</div>
							<button type="submit" class="btn btn-info">Update</button>
							<button type="reset" class="btn btn-success">Reset</button>
						</form>
					</div>
				</div>
			</div>

		</div>

	</div>

</body>
</html>