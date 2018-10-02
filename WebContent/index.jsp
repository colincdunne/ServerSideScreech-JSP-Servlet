<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Speed Calculator</title>
</head>
<body>
	<H1>Speed Calculator</H1>

	<FORM ACTION="HelloServlet" METHOD="POST">
		Please enter length of skid marks:
		<INPUT TYPE="TEXT" NAME="skidMark">
		<BR><BR>
		Please enter drag factor:
		<INPUT TYPE="TEXT" NAME="dragFactor">
		<BR><BR>
		Please enter brake efficiency (%):
		<INPUT TYPE="TEXT" NAME="brakeEfficiency">
		<BR><BR>
		<select name="surface">
    		<option value="Asphalt">Asphalt</option>
    		<option value="Concrete">Concrete</option>
    		<option value="Snow">Snow</option>
    		<option value="Gravel">Gravel</option>
  		</select>
		<BR><BR>
		<INPUT TYPE="SUBMIT" value="Submit">
	</FORM>

	<!--Creates a simple expression with 4 ints.-->
	<%!
		int numOne = 1;
		int numTwo = 2;
		int numThree = 4;
		int numTotal;
	%>

	<!--Takes the above ints and add them together and assigns them to the total variable.-->
	<%
		total = numOne + numTwo + numThree;
	%>

	<!--The total variable and below text and then displayed on the web page on load.-->
	<%=
		"Total Calculated Speed is: " + numTotal
	%>

    <!--Dislays the entered values of the text boxes.-->
	<%
        String skMark = request.getParameter("skidMark");
        out.print("Skid Mark Length Entered: "+ skMark);
        session.setAttribute("skSession", skMark);

        String dFactor = request.getParameter("dragFactor");
        out.print("Drag Factor Entered: "+ dFactor);
        session.setAttribute("dfSession", dFactor);

        String bEfficiency = request.getParameter("brakeEfficiency");
        out.print("Drag Factor Entered: "+ bEfficiency);
        session.setAttribute("beSession", bEfficiency);
	%>

	<$
	    String skidData = (String)session.getAttribute("skSession");
        out.print("You Entered The Following Skid Mark Length: " + skidData);

        String dragData = (String)session.getAttribute("dfSession");
        out.print("You Entered The Following Drag Factor: " + dragData);

        String brakeData = (String)session.getAttribute("beSession");
        out.print("You Entered The Following Brake Efficiency: " + brakeData);
	$>

    <!--Displays the number of visitors to the page(This was done in testing of the session info task).-->
	<%
        //This would return null for the first time.
        Integer counter= (Integer)application.getAttribute("numVisitCount");
        if( counter ==null || counter == 0 ){
        counter = 1;
        }
        else {
        counter = counter+ 1;
        }
        application.setAttribute("numVisitCount", counter);
    %>
    <h3>Total number of visitors to this page: <%= counter%></h3>

</body>
</html>