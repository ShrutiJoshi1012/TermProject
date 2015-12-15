<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>

	<div id="wrapper">
		<div id="navigation">
			<%@ include file="common.jsp"%>
		</div>

		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Project Management <small>Overview</small>
						</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i><a
								href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a>
							</li>
							<li class="active"><i class="fa fa-edit"></i> Projects List!
							</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->
				<div class="col-lg-12">
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th>Project Name</th>
									<th>Description</th>

									<th>Team Members</th>
									<th>View Progress Report</th>
									<th>Project State</th>
									<th>Update Project</th>


									<th>Add Tasks</th>
									<th>List Tasks</th>
								</tr>
							</thead>
							<tbody>
								<%
									if (person.getOwnedProjects().size() > 0){
								                                    	for(int i =0; i<person.getOwnedProjects().size();  i++) {
								%>

								<tr>
									<td>
										<%
											out.print(person.getOwnedProjects().get(i).getProjectDetail().getTitle());
										%>
									</td>
									<td>
										<%
											out.print(person.getOwnedProjects().get(i).getProjectDetail().getDescription());
										%>
									</td>
									<td>
										<button type="submit" class="btn btn-info" data-toggle="modal"
											data-target="#ownteam_<%out.print(person.getOwnedProjects().get(i).getProjectId());%>" />View
										Team
										</button>
									</td>

									<td>
										<button type="submit" class="btn btn-info" data-toggle="modal"
											data-target="#ownreport_<%out.print(person.getOwnedProjects().get(i).getProjectId());%>" />View
										Progress Report
										</button>
									</td>
									<td>
										<%
											out.print(person.getOwnedProjects().get(i).getProjectDetail().getState());
										%>
									</td>
									<%
										if(person.getOwnedProjects().get(i).getProjectDetail().getState().equals("Completed")||person.getOwnedProjects().get(i).getProjectDetail().getState().equals("Cancelled")){
									%>
									<td><p>
											Project Status ->
											<%
										out.print(person.getOwnedProjects().get(i).getProjectDetail().getState());
									%>
										</p></td>
									<%
										}else{
									%>
									<td><a
										href="${pageContext.servletContext.contextPath}/updateproject/<%out.print(person.getOwnedProjects().get(i).getProjectId());%>"><button
												type="submit" class="btn btn-primary">Update
												Project</button></a></td>
									<%
										}
									%>
									<%
										if(person.getOwnedProjects().get(i).getProjectDetail().getState().equals("Planning")||person.getOwnedProjects().get(i).getProjectDetail().getState().equals("Ongoing")){
									%>
									<td><a
										href="${pageContext.servletContext.contextPath}/createtask/<% out.print(person.getOwnedProjects().get(i).getProjectId()); %>"><button
												type="submit" class="btn btn-warning">Create Task</button></a></td>
									<%
										} else{
									%>
									<td>
										<p>
											Project State ->
											<%
											out.print(person.getOwnedProjects().get(i).getProjectDetail().getState());
										%>
											<br> Cannot Add Tasks!
										</p>

									</td>
									<%
										}
									%>
									<td><a
										href="${pageContext.servletContext.contextPath}/listtask/<%out.print(person.getOwnedProjects().get(i).getProjectId());%>"><button
												type="submit" class="btn btn-danger">List Task</button></a></td>

								</tr>
								<!-- Modal -->
								<div
									id="ownteam_<%out.print(person.getOwnedProjects().get(i).getProjectId());%>"
									class="modal fade" role="dialog">
									<div class="modal-dialog">

										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Team Members</h4>
											</div>
											<div class="modal-body">
											
												<%
													out.print(person.getOwnedProjects().get(i).getProjectDetail().getTitle());
												%>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
											</div>
										</div>

									</div>
								</div>

								<!-- Modal -->
								<div
									id="ownreport_<%out.print(person.getOwnedProjects().get(i).getProjectId());%>"
									class="modal fade" role="dialog">
									<div class="modal-dialog">

										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Progress Report</h4>
											</div>
											<div class="modal-body" style="height: 400px;">
												
												<label>Total Planned Tasks :</label>
												<%
													out.print(person.getOwnedProjects().get(i).getTotal_planned_tasks());
												%>
												<br /> <label>Total Cancelled Tasks :</label>
												<%
													out.print(person.getOwnedProjects().get(i).getTotal_cancelled_tasks());
												%>
												<br /> <label>Total Finished Tasks :</label>
												<%
													out.print(person.getOwnedProjects().get(i).getFinsished_tasks());
												%>
												<br /> <label>Total UnFinished Tasks :</label>
												<%
													out.print(person.getOwnedProjects().get(i).getUnfinsished_tasks());
												%>
												<br /> Score board <br /> <label style="width: 25%">Name</label><label
													style="width: 25%">Task Count</label> <br />
												<% if(person.getOwnedProjects().get(i).getMembers().size()>0){ 
													for(int own=0;own<person.getOwnedProjects().get(i).getMembers().size();own++ )
													{
												%>
												<label style="width: 25%"> <% out.print(person.getOwnedProjects().get(i).getMembers().get(own).getName());%></label>
												<label style="width: 25%"> <% out.print(person.getOwnedProjects().get(i).getMembers().get(own).getTaskCount());%></label>
												<br />
												<% } }%>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
											</div>
										</div>

									</div>
								</div>
								<%
									} }
								%>
								<%
									if(person.getSharedProjects().size() > 0){
								                                    		for(int i = 0; i < person.getSharedProjects().size();i++ ){
								%>

								<tr>
									<td>
										<%
											out.print(person.getSharedProjects().get(i).getProjectDetail().getTitle());
										%>
									</td>
									<td>
										<%
											out.print(person.getSharedProjects().get(i).getProjectDetail().getDescription());
										%>
									</td>

									<td>
										<button type="submit" class="btn btn-info" data-toggle="modal"
											data-target="#shareteam_<%out.print(person.getSharedProjects().get(i).getProjectId());%>" />View
										Team
										</button>
									</td>
									<td>
										<button type="submit" class="btn btn-info" data-toggle="modal"
											data-target="#sharereport_<%out.print(person.getSharedProjects().get(i).getProjectId());%>" />View
										Progress Report
										</button>
									</td>
									<td>
										<%
											out.print(person.getSharedProjects().get(i).getProjectDetail().getState());
										%>
									</td>
									<td>Shared Project <br> Cannot Be Updated!
									</td>
									<%
										if(person.getSharedProjects().get(i).getProjectDetail().getState().equals("Planning") || person.getSharedProjects().get(i).getProjectDetail().getState().equals("Ongoing")){
									%>
									<td><a
										href="${pageContext.servletContext.contextPath}/createtask/<% out.print(person.getSharedProjects().get(i).getProjectId()); %>"><button
												type="submit" class="btn btn-warning">Create Task</button></a></td>
									<%
										}else{
									%>
									<td>Project State -> <%
										out.print(person.getSharedProjects().get(i).getProjectDetail().getState());
									%> <br> Cannot Add Tasks!
									</td>
									<%
										}
									%>
									<td><a
										href="${pageContext.servletContext.contextPath}/listtask/<%out.print(person.getSharedProjects().get(i).getProjectId());%>"><button
												type="submit" class="btn btn-danger">List Task</button></a></td>
								</tr>

								<!-- Modal -->
								<div
									id="shareteam_<%out.print(person.getSharedProjects().get(i).getProjectId());%>"
									class="modal fade" role="dialog">
									<div class="modal-dialog">

										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Team Members</h4>
											</div>
											<div class="modal-body">
												
												<%
													out.print(person.getSharedProjects().get(i).getProjectDetail().getTitle());
												%>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
											</div>
										</div>

									</div>
								</div>
								<!-- Modal -->
								<div
									id="sharereport_<%out.print(person.getSharedProjects().get(i).getProjectId());%>"
									class="modal fade" role="dialog">
									<div class="modal-dialog">

										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Progress Report</h4>
											</div>
											<div class="modal-body">
											
												<label>Total Planned Tasks :</label>
												<%
													out.print(person.getSharedProjects().get(i).getTotal_planned_tasks());
												%>
												<br /> <label>Total Cancelled Tasks :</label>
												<%
													out.print(person.getSharedProjects().get(i).getTotal_cancelled_tasks());
												%>
												<br /> <label>Total Finished Tasks :</label>
												<%
													out.print(person.getSharedProjects().get(i).getFinsished_tasks());
												%>
												<br /> <label>Total UnFinished Tasks :</label>
												<%
													out.print(person.getSharedProjects().get(i).getUnfinsished_tasks());
												%>
												<br /> Score board <br /> <label style="width: 25%">Name</label><label
													style="width: 25%">Task Count</label> <br />
												<% if(person.getSharedProjects().get(i).getMembers().size()>0){ 
													for(int own=0;own<person.getSharedProjects().get(i).getMembers().size();own++ )
													{
												%>
												<label style="width: 25%"> <% out.print(person.getSharedProjects().get(i).getMembers().get(own).getName());%></label>
												<label style="width: 25%"> <% out.print(person.getSharedProjects().get(i).getMembers().get(own).getTaskCount());%></label>
												<br />
												<% } }%>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
											</div>
										</div>

									</div>
								</div>


								<%
									}}
								%>
							</tbody>
						</table>
					</div>
				</div>

			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->


</body>
</html>