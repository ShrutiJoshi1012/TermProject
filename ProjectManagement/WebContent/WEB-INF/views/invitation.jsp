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

    <title>ProjShare</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	
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
                            Invitation <small>Overview</small>
                        </h1>
                        <ol class="breadcrumb">
                            <li class="active">
                                <i class="fa fa-dashboard"></i> <a href ="${pageContext.servletContext.contextPath}/dashboard"> Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-edit"></i>Invitation!
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->
				<div class="row">
                    <div class="col-lg-offset-3 col-lg-5">
                    	 <form role="form" action = "${pageContext.servletContext.contextPath}/invitation" method = "post">
                    	 	<div class="form-group">
                    	 	 <label>Project Name</label><br/>
                    	 	<select class="form-control" name="projectID">
                    	 	<option value="0"> -- Select Project -- </option>
                    	 	<% if(person.getOwnedProjects().size()>0) 
                    	 		for(int i=0;i<person.getOwnedProjects().size();i++){ %>
                    	 	
                    	 	<option  value="<% out.print(person.getOwnedProjects().get(i).getProjectId()); %>"><% out.print(person.getOwnedProjects().get(i).getProjectDetail().getTitle()); %></option>
                    	 	<% }%>
                    	 	</select>
                            
                            </div>
                            <div class="form-group">
                                
                                <input class="form-control" id="personid" type="hidden"  name = "personid" value = "<% out.print(person.getPersonId()); %>"  >
                            </div>
                            <div class="form-group">
                                <label>Email ID</label>
                                <input class="form-control" id="email" name = "emailid"  >
                            </div>
                            
                           
                            <button type="submit" id="edit" class="btn btn-info">Send</button>
                            <button type="reset" class="btn btn-success">Cancel</button>
                             <br/>
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                             <br/>
                            <br/>
                            <br/>
                            <br/> <br/>
                            <br/>
                            <br/>
                    	 </form>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="js/plugins/morris/raphael.min.js"></script>
    <script src="js/plugins/morris/morris.min.js"></script>
    <script src="js/plugins/morris/morris-data.js"></script>
</body>
</html>