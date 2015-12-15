<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file = "common.jsp" %>
<% edu.sjsu.cmpe275.entities.Person projOwner = (edu.sjsu.cmpe275.entities.Person) request
				.getAttribute("projOwner");
				
java.util.List<edu.sjsu.cmpe275.entities.Person>  projTeam= (java.util.List<edu.sjsu.cmpe275.entities.Person>) request
				.getAttribute("sharedPersons");


edu.sjsu.cmpe275.entities.Project currProject = (edu.sjsu.cmpe275.entities.Project) request
.getAttribute("currProject");
 %>
   
    
</head>
<body>
		
    <div id="wrapper">
		<div id="navigation">
			
		</div>
		
        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                          <h3 class="page-header"> Project Details</h3>
                           <small> Name: &nbsp;<b><%out.print(currProject.getProjectDetail().getTitle()); %></b></small><br>
                           <small> Owner: &nbsp;<b><%out.print(currProject.getOwner().getName()); %></b></small><br>
                           <small> State:  &nbsp;<b><%out.print(currProject.getProjectDetail().getState()); %></b></small><br>
                       <br>
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
                    	 <form role="form" action = "${pageContext.servletContext.contextPath}/createtask" method = "POST">
                    	 	<div class="form-group">
                                <label>Title: </label>
                                <input class="form-control" name = "title" value="" placeholder="Give Your Task A Name" required/>
                            </div>
                            <div class="form-group">
                                <label>Description: </label>
                                <textarea class="form-control" name = "description" value = "" placeholder="Desribe the task in brief!" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>State: </label><br>
                                <label class="radio-inline">
                                    <input type="radio" name="state" id="state" value="New" checked>New
                                </label>
                            </div>
                              <div class="form-group">
                                <label>Assignee: </label><br>
                                <label class="radio-inline">
                                    <select name="assigneeid">
                                     <option value="">Select an Assingee</option>
                                     <option value="<% out.print(projOwner.getEmailid());%>"><% out.print(projOwner.getName() +" - "+ projOwner.getEmailid()); %></option>
                                    <% for(int i=0; i < projTeam.size();i++){%>
                                    <option value="<% out.print(projTeam.get(i).getEmailid());%>"><% out.print(projTeam.get(i).getName() +" - "+ projTeam.get(i).getEmailid()); %></option>
                                    <%} %>
                                    </select>
                                </label>
                            </div>
                            <div class = "form-group">
                            	<label>Estimated Units of work:</label><br>
                            	<input class="form-control" name = "estimatedwork" value = "" placeholder="Estimated Weeks of Work" message="Please enter Estimated Work..." />
                            </div>
                            <div>
                            	<input type = "hidden" name = "actualwork" value = "0">
                            	<input type = "hidden" name = "projectid" value = "<% out.print(currProject.getProjectId());%>">
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