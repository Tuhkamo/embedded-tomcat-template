package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AlbumDao;
import model.Album;

@WebServlet("/albums")
public class AlbumListServlet extends HttpServlet {

	private AlbumDao dao = new AlbumDao();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = Integer.parseInt(req.getParameter("ArtistId"));
		
		List<Album> albums = this.dao.getAlbums(id);
		
		req.setAttribute("albums", albums);
		req.getRequestDispatcher("/WEB-INF/albumList.jsp").forward(req, resp);
		resp.getWriter().println(albums);
	}
}
