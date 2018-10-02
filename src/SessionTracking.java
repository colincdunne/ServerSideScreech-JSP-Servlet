//Imports all of the below objects and classes.

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class SessionTracking extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Creates a session object (If none has been made already).
        HttpSession session = request.getSession(true);
        //Gets the sessions created time.
        Date createTime = new Date(session.getCreationTime());
        // Gets any previous access times to the web page.
        Date lastAccessTime = new Date(session.getLastAccessedTime());

        //Sets the title of the page.
        String title = "Welcome Back to my website, we're glad to see you again!";
        //Creates a variable to count number of visitors.
        Integer numVisitorCount = new Integer(0);
        //Creates a variable to manage the visitor count key.
        String numVisitCountKey = new String("numVisitorCount");
        //Creates a string to contain the UserID.
        String userIDKey = new String("userID");
        //Creates UserID.
        String userID = new String("ABCD");

        // Checks to see if this is users first visit to the web page.
        if (session.isNew()) {
            title = "Welcome to my website - Enjoy your stay";
            session.setAttribute(userIDKey, userID);
        } else {
            numVisitorCount = (Integer) session.getAttribute(numVisitCountKey);
            numVisitorCount = numVisitorCount + 1;
            userID = (String) session.getAttribute(userIDKey);
        }
        session.setAttribute(numVisitCountKey, numVisitorCount);

        //Sets response content and type.
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //Creates a string variable with the following HTML attached.
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";

        //Outputs the following HTML.
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<h2 align=\"center\">Session Infomation</h2>\n" +
                "<table border=\"1\" align=\"center\">\n" +
                "<tr bgcolor=\"#949494\">\n" +
                "  <th>Session info</th><th>value</th></tr>\n" +
                "<tr>\n" +
                "  <td>id</td>\n" +
                "  <td>" + session.getId() + "</td></tr>\n" +
                "<tr>\n" +
                "  <td>Creation Time</td>\n" +
                "  <td>" + createTime +
                "  </td></tr>\n" +
                "<tr>\n" +
                "  <td>Time of Last Access</td>\n" +
                "  <td>" + lastAccessTime +
                "  </td></tr>\n" +
                "<tr>\n" +
                "  <td>User ID</td>\n" +
                "  <td>" + userID +
                "  </td></tr>\n" +
                "<tr>\n" +
                "  <td>Number of visits</td>\n" +
                "  <td>" + numVisitorCount + "</td></tr>\n" +
                "</table>\n" +
                "</body></html>");
    }
}