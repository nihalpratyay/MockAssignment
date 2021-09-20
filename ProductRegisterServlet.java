package com.Agiliztech.StockApp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ProductRegisterServlet")
public class ProductRegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String Pname=request.getParameter("pn");
		String Pcategory=request.getParameter("pc");
		String Pcompany=request.getParameter("cn");
		String Pquantity=request.getParameter("pq");
		String Pprice=request.getParameter("prp");
		Integer quantity=Integer.parseInt(Pquantity);
		Double price=Double.parseDouble(Pprice);

		EntityManagerFactory factory=Persistence.createEntityManagerFactory("product");
		EntityManager manager=factory.createEntityManager();
		EntityTransaction transaction=manager.getTransaction();
		transaction.begin();
		
		ProductDetailsDTO pdto=new ProductDetailsDTO();
		pdto.setpName(Pname);
		pdto.setpCategory(Pcategory);
		pdto.setpCompany(Pcompany);
		pdto.setpQuantity(quantity);
		pdto.setpPrice(price);
		manager.persist(pdto);
		transaction.commit();
		manager.close();
		factory.close();
		pw.print("<h1>Product added Successfully </h1>");
		
		
	}

}
