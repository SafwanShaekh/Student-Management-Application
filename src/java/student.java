import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/student")
public class student extends HttpServlet {
    
    Connection con;
    PreparedStatement pst;
    int row;
    public void doPost(HttpServletRequest req,HttpServletResponse rsp) throws IOException,ServletException{
    
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter(); 
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/stdnt_records","root","");
            String stdname = req.getParameter("stdname");
            String stdgpa = req.getParameter("stdgpa");
            String stdbatchyear = req.getParameter("stdbtchyear");
            
            pst = con.prepareStatement("INSERT INTO `students`(`std_name`, `std_gpa`, `batch_year`) VALUES (?,?,?)");            
            pst.setString(1,stdname);
            pst.setString(2,stdgpa);
            pst.setString(3,stdbatchyear);
            row = pst.executeUpdate();
            out.println("<div>\n" +
"            <h3 class=\"data-add-sucess\">Student Data is Added Successfully</h3>\n" +
"        </div>");
            out.println("<table cellspacing=0 width=350px border=1");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th scope=\"col\">Name</th>");
            out.println("<th scope=\"col\">GPA</th>");
            out.println("<th scope=\"col\">Batch Year</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            out.println(" <tr>");
            out.println(" <td>");
            out.println(stdname);
            out.println("</td>");
            out.println(" <td>");
            out.println(stdgpa);
            out.println("</td>");
            out.println(" <td>");
            out.println(stdbatchyear);
            out.println("</td>");
            out.println("</tr>");
            out.println("</tbody>");
            out.println("</table>");
            
            
            
            
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            
            out.println("<div>\n" +
"            <h3 class=\"data-add-sucess\">Failed to Add Student Data</h3>\n" +
"        </div>");
        }
        
    }
}
