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
                        <h3 class="page-header"> Project Details</h3>
                           <small> Name: &nbsp<b><%out.print(project.getProjectDetail().getTitle()); %></b></small><br>
                           <small> Owner: &nbsp<b><%out.print(project.getOwner().getName()); %></b></small><br>
                           <small> State:  &nbsp<b><%out.print(project.getProjectDetail().getState()); %></b></small><br>
                       <br>
                        <ol class="breadcrumb">
                            <li class="active">
                                <i class="fa fa-dashboard"></i><a href = "${pageContext.servletContext.contextPath}/dashboard"> Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-dashboard"></i><a href = "${pageContext.servletContext.contextPath}/getallprojects"> List of all projects</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-edit"></i> List Tasks!
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->
		     	<div>
		     		<div class="col-lg-9">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th>Task Name</th>
                                        <th>Description</th>
                                        <th>Assignee</th>
                                        <th>Task State</th>
                                        <th>Estimated Unit</th>
                                        <th>Actual</th>
                                        <th>Update Task</th>
                                        <th>Delete Task</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%if(tasks.size()>0)
                                	for(int i=0;i<tasks.size();i++){
                                	%>
                                	<tr>
                                        
                                        <td><%out.print(tasks.get(i).getTaskDetail().getTitle()); %></td>
                                        <td><%out.print(tasks.get(i).getTaskDetail().getDescription()); %></td>
                                        <td><%if(tasks.get(i).getAssignee() !=null) out.print(tasks.get(i).getAssignee().getName()); %></td>
                                        <td><%out.print(tasks.get(i).getTaskDetail().getState()); %></td>
                                        <td><%out.print(tasks.get(i).getEstimatedWork()); %></td>
                                        <td><%out.print(tasks.get(i).getActualWork()); %></td>
                                        <% if(project.getProjectDetail().getState().equals("Completed")||project.getProjectDetail().getState().equals("Cancelled")){ %>
                                        <td>Read-Only</td>
                                        <td>Read-Only</td>
                                        <% } else{ %>
                                        <td><a href = "${pageContext.servletContext.contextPath}/updatetask/<%out.print(tasks.get(i).getProject().getProjectId());%>/<%out.print(tasks.get(i).getTaskId());%>"><button type="submit" class="btn btn-success">Update</button></a></td>
                                        <% 
                                        if(tasks.get(i).getProject().getOwner().getPersonId() == person.getPersonId()){ %>
                                        <td><a href = "${pageContext.servletContext.contextPath}/canceltask/<%out.print(project.getProjectId());%>/<%out.print(tasks.get(i).getTaskId());%>"><button type="submit" class="btn btn-warning">Cancel</button></a></td>
                                    	<%} else{%>
                                    	<td><button type = "submit" class = "btn btn-danger" readonly>Only Owner Can Cancel It!</button></td>
                                    	<%} }%>
                                    </tr>
                                    <%} %>
                                </tbody>
                            </table>    
		     	</div>   	
		     </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->	
</body>
</html>