package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import database.JDBCShoppingListItemDao;
import database.ShoppingListItemDao;
import model.ShoppingListItem;

@WebServlet("/list")
public class ShoppingListServlet extends HttpServlet {
	
	private ShoppingListItemDao dao = new JDBCShoppingListItemDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ShoppingListItem> allItems = this.dao.getAllItems();
		req.setAttribute("items", allItems);
		
		req.getRequestDispatcher("/WEB-INF/shoppingList/list.jsp").forward(req, resp);
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    // todo: hae tuotteen  nimi req-objektilta
		String itemTitle = req.getParameter("title");
		
	    // todo: käytä tuotenimeä luodaksesi uuden ShoppingListItem-olion
		ShoppingListItem newItem = new ShoppingListItem(itemTitle);
		
	    // todo: tallenna luomasi olio tietokantaan DAO-luokkasi avulla
		dao.addItem(newItem);
		
		resp.sendRedirect("/list");
	}
}
