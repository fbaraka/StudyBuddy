package com.feras.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.*;

@Controller
public class HomeController {
    @RequestMapping("/")

    public ModelAndView helloWorld() throws ClassNotFoundException, SQLException {
        return new
                ModelAndView("welcome","message","Hello World");
    }

    @RequestMapping("/user-registration")

    public String userRegistration(@RequestParam("Name") String Name) throws ClassNotFoundException, SQLException {


        // prep for step 3 -- reference database -- this is optional because we can assign directly in the connection
        String url = "jdbc:mysql://localhost:3306/studybuddy";
        String userName = "FB";
        String password = "Toed123mys";
        String query = "SELECT * FROM users";


        // step 2
        // load and register driver
        // using static method in example before
        Class.forName("com.mysql.jdbc.Driver");

        // step 3 -- creating connection
        // using Connection interface with DriverManager CLASS and getConnection method (parameters are the variables from prep, could just put the strings in below)
        // add exception to method signature
        Connection con = DriverManager.getConnection(url,userName,password);

        // step 4 -- creating statement
        // assign the connection object to a method called createStatement
        PreparedStatement prepStmt = con.prepareStatement("INSERT INTO users(userName) values (?)");
        prepStmt.setString(1, Name);




        // step 5 (optional) -- retrieve results / execute query
        ResultSet rs = prepStmt.executeQuery(query);
        prepStmt.execute();

        // step 6 (optional) -- process results
        // we need to use the next() method to move past the column headers for the first row of data in our table
        rs.next();


        rs.close();
        con.close();

        return "registrationSuccess";

    }
}
