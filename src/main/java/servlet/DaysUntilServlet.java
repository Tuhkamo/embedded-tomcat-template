package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/daysUntil")
public class DaysUntilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		int year = Integer.parseInt(req.getParameter("year"));
		int month = Integer.parseInt(req.getParameter("month"));
		int day = Integer.parseInt(req.getParameter("day"));
		
		LocalDate today = LocalDate.now();
		LocalDate givenDate = LocalDate.of(year, month, day);
		
		// servletillä voidaan välittää Java-arvoja JSP-sivuille attribuutteina
		req.setAttribute("number", ChronoUnit.DAYS.between(today, givenDate));
		req.setAttribute("date", givenDate);
		
		// välitetään pyyntö edelleen sivulle christmas.jsp
		req.getRequestDispatcher("/WEB-INF/daysUntil.jsp").forward(req, resp);
		
	}
}
