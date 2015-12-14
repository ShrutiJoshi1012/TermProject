<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>    
	<% 
	java.util.List<edu.sjsu.cmpe275.entities.Task>  tasks= (java.util.List<edu.sjsu.cmpe275.entities.Task>) request
					.getAttribute("tasks");
	edu.sjsu.cmpe275.entities.Project  project= (edu.sjsu.cmpe275.entities.Project) request
	.getAttribute("project");

%>
</head>
<body>
    <div id="wrapper">
		<div id="navigation">
			<%@ include file = "common.jsp" %>
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
                            <li class="active">
                                <i class="fa fa-dashboard"></i><a href = "${pageContext.servletContext.contextPath}/dashboard">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-edit"></i> Projects List!
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
                                <% if (person.getOwnedProjects().size() > 0){
                                    	for(int i =0; i<person.getOwnedProjects().size();  i++) {%>
                                    	
                                    <tr>
                                    	<td>
                                    		<% out.print(person.getOwnedProjects().get(i).getProjectDetail().getTitle());%>
                                    	</td>
                                    	<td>
                                    		<% out.print(person.getOwnedProjects().get(i).getProjectDetail().getDescription());%>
                                    	</td>
                                    	<td>
                                    		<button type = "submit" class = "btn btn-info" data-toggle="modal" data-target="#team_<%out.print(person.getOwnedProjects().get(i).getProjectId());%>"/>View Team</button>
                                    	</td>
                                    	
                                    	<td>
                                    		<button type = "submit" class = "btn btn-info" data-toggle="modal" data-target="#report_<%out.print(person.getOwnedProjects().get(i).getProjectId());%>"/>View Progress Report</button>
                                    	</td>
                                    	<td>
                                    		<% out.print(person.getOwnedProjects().get(i).getProjectDetail().getState());%>
                                    	</td>
                                    	<% if(person.getOwnedProjects().get(i).getProjectDetail().getState().equals("Completed")||person.getOwnedProjects().get(i).getProjectDetail().getState().equals("Cancelled")){ %>
                                    	  <td><p>Project Status -> <%out.print(person.getOwnedProjects().get(i).getProjectDetail().getState()); %></p></td>
                                    	<%}else{ %>
                                    	<td>
                                    		<a href = "${pageContext.servletContext.contextPath}/updateproject/<%out.print(person.getOwnedProjects().get(i).getProjectId());%>"><button type="submit" class="btn btn-primary">Update Project</button></a>
                                    	</td>
                                    	<%} %>
          								  <% if(person.getOwnedProjects().get(i).getProjectDetail().getState().equals("Planning")||person.getOwnedProjects().get(i).getProjectDetail().getState().equals("Ongoing")){ %>                      	
          								<td>
          								    <a href = "${pageContext.servletContext.contextPath}/createtask/<% out.print(person.getOwnedProjects().get(i).getProjectId()); %>"><button type="submit" class="btn btn-warning">Create Task</button></a>
    
          								</td>
          								<%} else{%>
          								<td>
          								    <p>Project State -> <%out.print(person.getOwnedProjects().get(i).getProjectDetail().getState()); %> <br> Cannot Add Tasks!</p>
    
          								</td>
          								<%} %>
          								 <td>
          								    <a href = "${pageContext.servletContext.contextPath}/listtask/<%out.print(person.getOwnedProjects().get(i).getProjectId());%>"><button type="submit" class="btn btn-danger">List Task</button></a>
          								</td>

                                    </tr>
                                    <!-- Modal -->
										<div id="team_<%out.print(person.getOwnedProjects().get(i).getProjectId());%>" class="modal fade" role="dialog">
										  <div class="modal-dialog">
										
										    <!-- Modal content-->
										    <div class="modal-content">
										      <div class="modal-header">
										        <button type="button" class="close" data-dismiss="modal">&times;</button>
										        <h4 class="modal-title">Modal Header</h4>
										      </div>
										      <div class="modal-body">
										       	 <p><b><%out.print(person.getOwnedProjects().get(i).getProjectDetail().getTitle());%></b></p>
										        <p><b>Team Members</b></p>
										        <p>Owner:</p><% out.print(person.getName()); %><br>
										       
										      </div>
										      <div class="modal-footer">
										        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
										      </div>
										    </div>
										
										  </div>
										</div>
										
										<!-- Report Modal -->
										<!-- Modal -->
										<div id="report_<%out.print(person.getOwnedProjects().get(i).getProjectId());%>" class="modal fade" role="dialog">
										  <div class="modal-dialog">
										
										    <!-- Modal content-->
										    <div class="modal-content">
										      <div class="modal-header">
										        <button type="button" class="close" data-dismiss="modal">&times;</button>
										        <h4 class="modal-title">Progress Report</h4>
										      </div>
										      <div class="modal-body">
										      	<!-- Estimated Work -->
										       	<div class="progress">
												  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="<% out.print(tasks.get(i).getEstimatedWork()); %>"
												  aria-valuemin="0" aria-valuemax="100" style="width:40%">
												    Estimated Work <%out.print(tasks.get(i).getEstimatedWork()); %>
												  </div>
												</div>
												<!-- Actual Work -->
												<div class="progress">
												  <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="<% out.print(tasks.get(i).getActualWork()); %>"
												  aria-valuemin="0" aria-valuemax="100" style="width:60%">
												    Actual Work <% out.print(tasks.get(i).getActualWork()); %>
												  </div>
												</div>
										      </div>
										      <div class="modal-footer">
										        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
										      </div>
										    </div>
										
										  </div>
										</div>
								  
																			  
                                    <% } }%>
                                    <% if(person.getSharedProjects().size() > 0){
                                    		for(int i = 0; i < person.getSharedProjects().size();i++ ){
                                    	%>
                                    	
                                    <tr>
                                    	<td><% out.print(person.getSharedProjects().get(i).getProjectDetail().getTitle());%></td>
                                    	<td><% out.print(person.getSharedProjects().get(i).getProjectDetail().getDescription());%></td>
                                    	
                                    	<td>
                                    		<button type = "submit" class = "btn btn-info" data-toggle="modal" data-target="#team_<%out.print(person.getSharedProjects().get(i).getProjectId());%>"/>View Team</button>
                                    	</td>
                                    	<td>
                                    		<button type = "submit" class = "btn btn-info" data-toggle="modal" data-target="#report_<%out.print(person.getSharedProjects().get(i).getProjectId());%>"/>View Progress Report</button>
                                    	</td>
                                    	<td><% out.print(person.getSharedProjects().get(i).getProjectDetail().getState()); %></td>
                                    	<td>Shared Project <br> Cannot Be Updated!</td>
                                    	<% if(person.getSharedProjects().get(i).getProjectDetail().getState().equals("Planning") || person.getSharedProjects().get(i).getProjectDetail().getState().equals("Ongoing")){ %>
                                    	<td>
          								    <a href = "${pageContext.servletContext.contextPath}/createtask/<% out.print(person.getSharedProjects().get(i).getProjectId()); %>"><button type="submit" class="btn btn-warning">Create Task</button></a>
    
          								</td>
                                    	<%}else{ %>
                                    	<td>Project State -> <%out.print(person.getSharedProjects().get(i).getProjectDetail().getState()); %> <br> Cannot Add Tasks!</td>
                                    	<%} %>
                                    	<td>
          								    <a href = "${pageContext.servletContext.contextPath}/listtask/<%out.print(person.getSharedProjects().get(i).getProjectId());%>"><button type="submit" class="btn btn-danger">List Task</button></a>
          								</td>
                                    </tr>
                                    	
                                    
                                    	<!-- Modal -->
										<div id="team_<%out.print(person.getSharedProjects().get(i).getProjectId());%>" class="modal fade" role="dialog">
										  <div class="modal-dialog">
										
										    <!-- Modal content-->
										    <div class="modal-content">
										      <div class="modal-header">
										        <button type="button" class="close" data-dismiss="modal">&times;</button>
										        <h4 class="modal-title">Modal Header</h4>
										      </div>
										      <div class="modal-body">
										    
										       <%out.print(person.getSharedProjects().get(i).getProjectDetail().getTitle());%>
										       <p>Owner :</p><%out.print(person.getSharedProjects().get(i).getOwner()); %>
										       <p>Team Members :</p>
										       <% 
										       		int projectId = person.getSharedProjects().get(i).getProjectId(); 
										       		int task_projectId = tasks.get(i).getProject().getProjectId();
										       		out.print(projectId);
										       		out.print(task_projectId);
										       	%>
										      
										      </div>
										      <div class="modal-footer">
										        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
										      </div>
										    </div>
										
										  </div>
										</div>
										
										<!-- Report Modal -->
										<!-- Modal -->
										<div id="report_<%out.print(person.getSharedProjects().get(i).getProjectId());%>" class="modal fade" role="dialog">
										  <div class="modal-dialog">
										
										    <!-- Modal content-->
										    <div class="modal-content">
										      <div class="modal-header">
										        <button type="button" class="close" data-dismiss="modal">&times;</button>
										        <h4 class="modal-title">Modal Header</h4>
										      </div>
										      <div class="modal-body">
										       	Report
										       <%out.print(person.getSharedProjects().get(i).getProjectDetail().getTitle());%>
										      </div>
										      <div class="modal-footer">
										        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
										      </div>
										    </div>
										
										  </div>
										</div>
								  
                                    
                                    	<% }} %>
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