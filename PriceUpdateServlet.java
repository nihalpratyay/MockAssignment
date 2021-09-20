package com.Agiliztech.StockApp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PriceUpdateServlet")
public class PriceUpdateServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw= response.getWriter();
		String pname=request.getParameter("pn");
		String pprice=request.getParameter("np");
		Double price=Double.parseDouble(pprice);
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("product");
		EntityManager manager=factory.createEntityManager();
		EntityTransaction transaction =manager.getTransaction();
		transaction.begin();
		
		Query query1=manager.createQuery("update ProductDetailsDTO set pPrice='"+price+"' where pName='"+pname+"'");
		int rows=query1.executeUpdate();
		if (rows==1) {
			pw.print("Product Price has been Successfully Updated");
		} else {
			pw.print("<p style='color:red'>This Product doesn't Exist</p>");
			RequestDispatcher rd=request.getRequestDispatcher("priceupdate.html");
            rd.include(request, response);

		}
		transaction.commit();
		manager.close();
		factory.close();
	}

}
