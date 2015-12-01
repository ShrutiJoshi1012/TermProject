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
                        <h2 align = "center">List of Tasks</h2>
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
                                	<tr>
                                        <td>Task 1</td>
                                        <td>Lklsjldf sbkdbkhf bkdfhk</td>
                                        <td>Abc, Pqr</td>
                                        <td>Planning</td>
                                        <td>10 weeks</td>
                                        <td>11 weeks</td>
                                        <td><a href = "${pageContext.servletContext.contextPath}/updatetask"><button type="submit" class="btn btn-success">Update</button></a></td>
                                        <td><button type="submit" class="btn btn-danger">Delete</button></td>
                                    </tr>
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