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
	
	<% 	edu.sjsu.cmpe275.entities.Task task= (edu.sjsu.cmpe275.entities.Task) request
				.getAttribute("task");
              
java.util.List<edu.sjsu.cmpe275.entities.Person>  projTeam= (java.util.List<edu.sjsu.cmpe275.entities.Person>) request
      				.getAttribute("sharedPersons");

%>
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
                            Proj Share <small>Overview</small>
                        </h1>
                        <ol class="breadcrumb">
                            <li class="active">
                                <i class="fa fa-dashboard"></i><a href ="${pageContext.servletContext.contextPath}/dashboard"> Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-dashboard"></i><a href ="${pageContext.servletContext.contextPath}/getallprojects">List of all projects</a>
                            </li>
						    <li class="active">
                                <i class="fa fa-dashboard"></i><a href ="${pageContext.servletContext.contextPath}/listtask/<%out.print(task.getProject().getProjectId()); %>">List of all tasks</a>
                            </li>
								
                            <li class="active">
                                <i class="fa fa-edit"></i> Update the Task!
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->
             
				<div class="row">
                    <div class="col-lg-offset-3 col-lg-5">
                    	 <form role="form" action = "${pageContext.servletContext.contextPath}/updatetask" method = "POST">
                    	 <input class="form-control" type="hidden" name="taskId" value="<% out.print(task.getTaskId());%>">
                    	  <input class="form-control" type="hidden" name="projectId" value="<% out.print(task.getProject().getProjectId());%>">
                    	 
                    	 	<div class="form-group">
                                <label>Title:</label>
                                <input class="form-control" name="title" value="<% out.print(task.getTaskDetail().getTitle());%>" placeholder="Update Your Task Name">
                            </div>

                            <div class="form-group">
                                <label>Estimated Work:</label>
                                <input class="form-control" name="estimatedwork" value="<% out.print(task.getEstimatedWork());%>" placeholder="Update Your Task Estimated Work" readonly>
                            </div>
                             <div class="form-group">
                                <label>Actual Work:</label>
                                <input class="form-control" name="actualwork" value="<% out.print(task.getActualWork());%>" placeholder="Update Your Task Actual Work">
                            </div>
                            <div class="form-group">
                                <label>Description: </label>
                                <textarea class="form-control" name="description"  placeholder="Describe the task in brief!"><% out.print(task.getTaskDetail().getDescription());%></textarea>
                            </div>
                            <div class="form-group">
                               <label>Assignees: <br></label>
                            <select class="form-group" name="assigneeid">
                            	<option value="<% out.print(task.getAssignee().getEmailid());%>"><% out.print(task.getAssignee().getName()+" - "+task.getAssignee().getEmailid());%></option>
                            	  <% if(projTeam.size()>0){for(int i=0; i < projTeam.size();i++){%>
                                    <option value="<% out.print(projTeam.get(i).getEmailid());%>"><% out.print(projTeam.get(i).getName() +" - "+ projTeam.get(i).getEmailid()); %></option>
                                    <%} }%>
                            </select>
                            </div>
                            <div class="form-group">
                                <label>Task State: </label><br>
                                <label class="radio-inline">
                                    <input type="radio" name="state" id="optionsRadiosInline1" value="Assigned" checked>Assigned
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="state" id="optionsRadiosInline1" value="Started">Started
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="state" id="optionsRadiosInline1" value="Finished">Finished
                                </label>
                            </div>
                            
                            <button type="submit" class="btn btn-info">Update Task</button>
                            <button type="reset" class="btn btn-success">Reset</button>
                    	 </form>
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