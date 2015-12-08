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

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.servletContext.contextPath}/resources/css/sb-admin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="${pageContext.servletContext.contextPath}/resources/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.servletContext.contextPath}/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

 
</head>
<body>
    <%
		edu.sjsu.cmpe275.entities.Person person = (edu.sjsu.cmpe275.entities.Person) request
				.getAttribute("personSessionObj");
    java.util.List<edu.sjsu.cmpe275.entities.SharedProjects>  invites= new  java.util.ArrayList<edu.sjsu.cmpe275.entities.SharedProjects>();
    		
    		if(request.getAttribute("invites") != null)
    			invites.addAll((java.util.List<edu.sjsu.cmpe275.entities.SharedProjects>) request
        	 			.getAttribute("invites"));
    %>
    		
    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/dashboard">Proj-Share!</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><button>Invitations <span class="badge"><% out.print(invites.size());%></span></button><b class="caret"></b></a>
                    <ul class="dropdown-menu message-dropdown">
                      		 <%if(invites.size()>0){
                    	for(int i=0;i<invites.size();i++){
                    	%>
                   
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                <a href="${pageContext.servletContext.contextPath}/acceptinvite/<% out.print(invites.get(i).getSharedProject().getProjectId()); %>/<% out.print(person.getPersonId()); %>" >
                                    <span class="pull-left">
                                        <img class="media-object" src = "${pageContext.servletContext.contextPath}/resources/images/accept.jpg">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong><% out.print("Project: "+invites.get(i).getSharedProject().getProjectDetail().getTitle()); %></strong>
                                        </h5>
                                         <strong><% out.print("Owner: "+invites.get(i).getSharedProject().getOwner().getName()); %></strong>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        </a>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <%} }%>
                    </ul>
                </li>
                
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>
                    <%  if(person != null){
						out.print("Welcome: "+person.getName());
		            }%>
                    <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/updateprofile"><i class="fa fa-fw fa-user"></i> Profile</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li class="active">
                        <a href="${pageContext.servletContext.contextPath}/dashboard"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                    </li>
                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i> Projects <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="demo" class="collapse">
                            <li>
                                <a href="${pageContext.servletContext.contextPath}/addproject">Create Project</a>
                            </li>
                            <li>
                                <a href="${pageContext.servletContext.contextPath}/getallprojects">List Projects</a>
                            </li>
                            <li>
                                <a href="${pageContext.servletContext.contextPath}/invitation">Invitation</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>
         <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="${pageContext.servletContext.contextPath}/resources/js/plugins/morris/raphael.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/plugins/morris/morris.min.js"></script>
   <!--   <script src="${pageContext.servletContext.contextPath}/resources/js/plugins/morris/morris-data.js"></script>-->

</body>
</html>