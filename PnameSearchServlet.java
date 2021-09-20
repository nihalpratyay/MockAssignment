package com.Agiliztech.StockApp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PnameSearchServlet")
public class PnameSearchServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw= response.getWriter();
		String pname=request.getParameter("pn");
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("product");
		EntityManager manager=factory.createEntityManager();
		Query query1=manager.createQuery("from ProductDetailsDTO where pName='"+pname+"'");
		ProductDetailsDTO pdto= (ProductDetailsDTO) query1.getSingleResult();
		if (pdto!=null) {
			pw.print("Product Details are: "+"Product Name="+pdto.getpName()+" Product Category= "+pdto.getpCategory()+" Product company=  "+pdto.getpCompany()+" Product Quantity: "+pdto.getpQuantity()+" Product Price: "+pdto.getpPrice());
			
		} else {
			pw.print("<p style='color:red'>Product not Available</P>");
			RequestDispatcher rd=request.getRequestDispatcher("pname.html");
            rd.include(request, response);

		}
		manager.close();
		factory.close();
	}

}
