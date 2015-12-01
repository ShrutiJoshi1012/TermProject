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
                                <i class="fa fa-dashboard"></i><a href ="${pageContext.servletContext.contextPath}/dashboard"> Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-dashboard"></i><a href ="${pageContext.servletContext.contextPath}/getallprojects">List of all projects</a>
                            </li>
						    <li class="active">
                                <i class="fa fa-dashboard"></i><a href ="${pageContext.servletContext.contextPath}/listtask">List of all tasks</a>
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
                    	 <form role="form" action = "/ProjectManagementUI/listTask" method = "get">
                    	 	<div class="form-group">
                                <label>Task Title:</label>
                                <input class="form-control" placeholder="Update Your Task Name">
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea class="form-control" placeholder="Desribe the task in brief!"></textarea>
                            </div>
                            <div class="form-group">
                               <label>Assignees: <br></label>
                               <label>
                                  <input type="checkbox" value="">Abc
                               </label>
                               <label>
                                  <input type="checkbox" value=""> Pqr
                               </label>
                               <label>
                                  <input type="checkbox" value=""> XYZ
                               </label>
                            </div>
                            <div class="form-group">
                                <label>Task State  &nbsp </label><br>
                                <label class="radio-inline">
                                    <input type="radio" name="optionsRadiosInline" id="optionsRadiosInline1" value="option1" checked>Assigned
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="optionsRadiosInline" id="optionsRadiosInline1" value="option1">Started
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="optionsRadiosInline" id="optionsRadiosInline1" value="option1">Finished
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="optionsRadiosInline" id="optionsRadiosInline1" value="option1">Cancelled
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