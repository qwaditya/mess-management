/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jdbc;
import java.io.*;
 import javax.servlet.*;
 import javax.servlet.http.*;
 import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 1405362
 */
@WebServlet(name = "Expense", urlPatterns = {"/Expense"})
public class Expense extends HttpServlet{
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
         PrintWriter out = res.getWriter();
         res.setContentType("text/html");
         out.println("<html>");
          out.println("<body style='background-color: red;'>");
         try {
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             String connectionUrl="jdbc:sqlserver://localhost:1433;user=sa;password=p@ssw0rd;"+"databaseName=newdatabase;";
             Connection con = DriverManager.getConnection(connectionUrl); 
             // Here dsnname- mydsn,user id- system(for oracle 10g),password is pintu.
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from expense2");
             out.println("<table border=1 width=35% height=35%>");
             out.print("<center><h1>Expense Details:</h1></center>");
             out.println("<tr><th>EXPENSE_DATE</th><th>EXPENSE_TYPE</th><th>AMOUNT</th></tr>");
             while (rs.next()) {
                 String n = rs.getString("expense_date");
                 String nm = rs.getString("expense_type");
                 String s = rs.getString("amount"); 
                 out.println("<tr><td>" + n + "</td><td>" + nm + "</td><td>" + s + "</td></tr>"); 
             }
             out.println("</table>");
             out.println("</body>");
             out.println("</html>");
             con.close();
            }
             catch (Exception e) {
             out.println("error");
         }
     }
 }
