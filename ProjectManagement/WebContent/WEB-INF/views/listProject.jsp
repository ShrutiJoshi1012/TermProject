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

    <title>InfoSoft</title>

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
                                        <th>Project State</th>
                                        <th>Update Project</th>
                                        <th>Delete Project</th>
                                        <th>Add Tasks</th>
                                        <th>List Tasks</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                    	<% if (person.getOwnedProjects().size() > 0){
                                    	for(int i =0; i<person.getOwnedProjects().size();  i++) {%>
                                    	<td>
                                    		<% out.print(person.getOwnedProjects().get(i).getProjectDetail().getTitle());%>
                                    	</td>
                                    	<td>
                                    		<% out.print(person.getOwnedProjects().get(i).getProjectDetail().getDescription());%>
                                    	</td>
                                    	<td>
                                    		<button type = "submit" class = "btn btn-info"/>View Team Members</button>
                                    	</td>
                                    	<td>
                                    		<% out.print(person.getOwnedProjects().get(i).getProjectDetail().getState());%>
                                    	</td>
                                    	<td>
                                    		<a href = "${pageContext.servletContext.contextPath}/updateproject"><button type="submit" class="btn btn-primary">Update Project</button></a>
                                    	</td>
          								<td>
          									<button type="submit" class="btn btn-success">Delete Project</button>
          								</td>                          	
          								<td>
          								    <a href = "${pageContext.servletContext.contextPath}/createtask/<% out.print(person.getOwnedProjects().get(i).getProjectId()); %>"><button type="submit" class="btn btn-warning">Create Task</button></a>
    
          								</td>
          								 <td>
          								    <a href = "${pageContext.servletContext.contextPath}/listtask/<%out.print(person.getOwnedProjects().get(i).getProjectId());%>"><button type="submit" class="btn btn-danger">List Task</button></a>
    
          								</td>
    
                                    </tr>
                                    <% } }%>
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