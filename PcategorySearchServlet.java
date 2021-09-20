package com.Agiliztech.StockApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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


@WebServlet("/PcategorySearchServlet")
public class PcategorySearchServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw= response.getWriter();
		String pcategory=request.getParameter("cn");
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("product");
		EntityManager manager=factory.createEntityManager();
		Query query1=manager.createQuery("from ProductDetailsDTO where pCategory='"+pcategory+"'");
		List<ProductDetailsDTO> lpdto=query1.getResultList();
		int count=0;
		for (ProductDetailsDTO productDetailsDTO : lpdto) {
			count++;
			pw.print(" Product Details are: Product Name= "+productDetailsDTO.getpName()+" Product Category: "+productDetailsDTO.getpCategory()+" Product Company: "+productDetailsDTO.getpCompany()+" Quantity Present= "+productDetailsDTO.getpQuantity()+" Product Price= "+productDetailsDTO.getpPrice());
		}
		if(count==0) {
		pw.print("<p style='color:red'>No Products Available in this Category</p>");
		RequestDispatcher rd=request.getRequestDispatcher("pcategory.html");
        rd.include(request, response);
		}

		manager.close();
		factory.close();
	}

}
