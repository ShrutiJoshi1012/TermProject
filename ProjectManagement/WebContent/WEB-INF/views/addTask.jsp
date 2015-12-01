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
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/> 
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script> 
    
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
                            Project Management<small>Overview</small>
                        </h1>
                        <ol class="breadcrumb">
                            <li class="active">
                                <i class="fa fa-dashboard"></i><a href = "${pageContext.servletContext.contextPath}/dashboard"> Dashboard </a>
                            </li>
                            <li class="active">
                                <i class="fa fa-dashboard"></i> <a href = "${pageContext.servletContext.contextPath}/getallprojects">List of Projects</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-edit"></i> Create A New Task!
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->
				<div class="row">
                    <div class="col-lg-offset-3 col-lg-5">
                    	 <form role="form" action = "${pageContext.servletContext.contextPath}/createtask" method = "post">
                    	 	<div class="form-group">
                                <label>Task Title:</label>
                                <input class="form-control" name = "title" value="title" placeholder="Give Your Task A Name">
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea class="form-control" name = "description" value = "description" placeholder="Desribe the task in brief!"></textarea>
                            </div>
                            <div class="form-group">
                                <label>Task State  &nbsp </label><br>
                                <label class="radio-inline">
                                    <input type="radio" name="state" id="state" value="New" checked>New
                                </label>
                            </div>
                            <div class = "form-group">
                            	<label>Estimated Units of work:</label><br>
                            	<input class="form-control" name = "estimatedWork" value = "estimatedWork" placeholder="Estimated Weeks of Work"/>
                            </div>
                            <div>
                            	<input type = "hidden" name = "actualWork" value = "actualWork">
                            </div>
                            <button type="submit" class="btn btn-info">Create Task</button>
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