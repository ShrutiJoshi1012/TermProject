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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script>
	function change(){
		document.getElementById('username').removeAttribute('readonly');
		document.getElementById('email').removeAttribute('readonly');
		document.getElementById('description').removeAttribute('readonly');
		document.getElementById('password').removeAttribute('readonly');
		
	}
	window.onload=function(){
	document.getElementById('edit').onclick = change;
	}
	</script>
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
                            Update Profile <small>Overview</small>
                        </h1>
                        <ol class="breadcrumb">
                            <li class="active">
                                <i class="fa fa-dashboard"></i> <a href ="${pageContext.servletContext.contextPath}/dashboard"> Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-edit"></i> Update Profile!
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->
				<div class="row">
                    <div class="col-lg-offset-3 col-lg-5">
                    	 <form role="form" action = "${pageContext.servletContext.contextPath}/updateprofile" method = "post">
                    	 	<div class="form-group">
                                <label>Username</label>
                                <input class="form-control" id="username" name = "name" value = "<% out.print(person.getName()); %>" readonly="readonly" >
                            </div>
                            <div class="form-group">
                                
                                <input class="form-control" id="personid" type="hidden"  name = "personid" value = "<% out.print(person.getPersonId()); %>"  >
                            </div>
                            <div class="form-group">
                                <label>Email ID</label>
                                <input class="form-control" id="email" name = "emailid" value = "<% out.print(person.getEmailid()); %>" readonly="readonly" >
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <input class="form-control" id="description" name= "description" value = "<% out.print(person.getDescription()); %>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label>Change Password</label>
                                <input class="form-control" id="password" type="password" name = "password" value = "<% out.print(person.getPassword()); %>" readonly="readonly" >
                            </div>
                           
                            <button type="button" id="edit" class="btn btn-info">Edit</button>
                            <button type="submit" class="btn btn-success">Save</button>
                            <br/>
                            <br/>
                            <br/>
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
	
</body>
</html>