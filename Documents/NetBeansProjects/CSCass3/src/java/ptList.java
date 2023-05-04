import Controller.dbConnController;
import Controller.errorPrinterController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ptList"})
public class ptList extends HttpServlet {
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res) {
        try {
            // initialize necessary element
            Connection conn = dbConnController.getConnection();
            PrintWriter pw = res.getWriter();
            
            printPtList(conn, pw, req);
            
            // close necessary element        
            dbConnController.closeConnection();
            pw.close();
        } catch (IOException | SQLException e) {
            System.out.println(e);
        }
     }
    
    private void printPtList(Connection conn, PrintWriter pw, HttpServletRequest req){
        try ( 
            // print out the history based on successful calculation
            Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from patients");
            
            pw.print("<head>");
            pw.print("<title>PATIENT LIST</title>");
            pw.print("<link rel='stylesheet' href='style.css'>");
            pw.print("</head>");
            pw.print("<body>");
            pw.print("<div class='header'><h1>PATIENT LIST</h1></div>");
            
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1){
                    pw.print("<div class='tableWrapper'>");
                    pw.print("<table class='historyTable'>");
                    pw.print("<tr>");
                    pw.print("<th style=\"width:5%\">Num</th>");
                    pw.print("<th style=\"width:12%\">IC</th>");
                    pw.print("<th style=\"width:30%\">Name</th>");
                    pw.print("<th style=\"width:30%\">Address</th>");
                    pw.print("<th style=\"width:13%\">Contact Number</th>");
                    pw.print("<th style=\"width:10%\">Details</th>");
                    pw.print("</tr>");
                }
                pw.print("<tr>");
                pw.print("<td>"+rs.getString("rowNum")+ "</th>");
                pw.print("<td>"+rs.getString("ic")+"</th>");
                pw.print("<td>"+rs.getString("name")+ "</th>");
                pw.print("<td>"+rs.getString("address")+"</th>");
                pw.print("<td>"+rs.getString("telNum")+ "</th>");
                pw.print("<td>");
                pw.print("<form method='post'>");
                pw.print("<button type='submit' name='deltailBtn' value=" + i + "' class='tableBtn'>View</button>");
                pw.print("</form>");
                pw.print("</td>");
                pw.print("</tr>");
            }
            if (i==0){
                pw.print("<p>The table is empty</P>");
            }else{
                pw.print("</table>");
                pw.print("</div>");
            }
            pw.print("<form action='index.html' class='backBtnForm'>");
            pw.print("<button type='submit' name='backBt' class='backBtn'>Go Back</button>");
            pw.print("</form>");
            pw.print("</body>");
            
            // Close the statement and result set
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            errorPrinterController.printOutput("Failed to connect to database", pw);
        }
    }
}
