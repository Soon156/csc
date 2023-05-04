/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.io.PrintWriter;

public class errorPrinterController {
    public static void printOutput(String error, PrintWriter pw){
        pw.print("<head>");
        pw.print("<title>Output</title>");
        pw.print("<link rel='stylesheet' href='style.css'>");
        pw.print("</head>");
        pw.print("<body>");
        pw.print("<div class='header'><h1>System Response</h1></div>");
        pw.print("<p class='errorDesc'>" + error +"</p>");
        pw.print("<form action='index.html' class='backBtnForm'>");
        pw.print("<button type='submit' name='backBt' class='backBtn'>Go Back</button>");
        pw.print("</form>");
        pw.print("</body>");
    }
}
