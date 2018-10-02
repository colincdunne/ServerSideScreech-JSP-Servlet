//Imports all of the below objects and classes.
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.servlet.http.Cookie;
import org.apache.tomcat.util.http.Cookies;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//Creates double variables.
	double skidMarkL = 0;
	double dragF = 0;
	double bEfficiency = 0;
	double bEff = 0;
	double totalSpeed;

	/Creates String variables.
			String skidMarkLength;
	String dragFactor;
	String brakeEfficiency;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Creates a cookie object called cookie.
		Cookie cookies[];
		//Retrieves/Gets the cookies.
		cookies = request.getCookies();

		//Sets the content type of the page
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		//Sets the header for the web page.
		printWriter.println("<h1>Speed Calculator</h1>");

		//PrintWriter reWriter = response.getWriter();

		//Gets the below parameters from the form input.
		skidMarkLength = request.getParameter("skidMark");
		dragFactor = request.getParameter("dragFactor");
		brakeEfficiency = request.getParameter("brakeEfficiency");

		//Takes the inputs and makes them doubles for the calculation.
		skidMarkL = Double.parseDouble(skidMarkLength);
		dragF = Double.parseDouble(dragFactor);
		bEfficiency = Double.parseDouble(brakeEfficiency);
		bEff = bEfficiency / 100;

		//Limits the double to two decimal places.
		DecimalFormat df = new DecimalFormat("#.##");

		//Performs the calculation.
		double speed = 30 * skidMarkL * dragF * bEff;
		//Gets the square root of the answer of the above calculation.
		double speed1 = Math.sqrt(speed);
		//Sets the answer to two decimal places.
		totalSpeed = Double.valueOf(df.format(speed1));

		//Outputs the following HTML with the entered values and the total speed.
		printWriter.println("<HTML>");
		printWriter.println("<HEAD><TITLE>Hello, " + "Speed Calculator" + "</TITLE></HEAD>");
		printWriter.println("<BODY>");
		printWriter.println("Skid Mark Length Entered: " + skidMarkLength);
		printWriter.println("<BR><BR>");
		printWriter.println("Drag Factor Entered: " + dragFactor);
		printWriter.println("<BR><BR>");
		printWriter.println("Brake Efficiency Entered (%): " + brakeEfficiency);
		printWriter.println("<BR><BR>");
		printWriter.println("Total Speed: " + totalSpeed);
		printWriter.println("</BODY></HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

		//Sets what to add to the cookies.
		Cookie c = new Cookie(skidMarkLength, "Skid Mark");
		Cookie c1 = new Cookie(dragFactor, "Drag Factor");
		Cookie c2 = new Cookie(brakeEfficiency, "Brake Efficiency");
		//Sets the age of the cookies(when they die/go away).
		c.setMaxAge(120);
		c1.setMaxAge(120);
		c2.setMaxAge(120);
		//Add the cookies.
		response.addCookie(c);
		response.addCookie(c1);
		response.addCookie(c2);

		String dataent = request.getParameter("dataSent");
		System.out.println(dataSent);

		//Creates a file and writes it to speed.txt and saves to C directory.
		try {
			FileWriter writeToFile = new FileWriter("C:/speed.txt");
			writeToFile.write("The Speed of The Vehicle is: " + totalSpeed);
			System.out.println("File has been written to the directory 'C:/speed.txt'.");
			writeToFile.close();
		}
		//If the above doesn't work, this exception os thrown.
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
