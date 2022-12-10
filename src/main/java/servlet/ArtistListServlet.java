package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ArtistDao;
import model.Artist;

@WebServlet("/artists")
public class ArtistListServlet extends HttpServlet {

	private ArtistDao dao = new ArtistDao();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Artist> artists = this.dao.getAllArtists();
		
		req.setAttribute("artists", artists);
		req.getRequestDispatcher("/WEB-INF/artistList.jsp").forward(req, resp);
		// haetaan Artistit ja välitetään ne JSP-sivulle
		resp.getWriter().println(artists);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// todo: hae tuotteen  nimi req-objektilta
				String artistName = req.getParameter("name");
				
			    // todo: käytä tuotenimeä luodaksesi uuden ShoppingListItem-olion
				Artist newArtist = new Artist(artistName);
				
			    // todo: tallenna luomasi olio tietokantaan DAO-luokkasi avulla
				dao.addArtist(newArtist);
				
				resp.sendRedirect("/artists");
	}
}
