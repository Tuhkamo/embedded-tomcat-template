package servlet;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.DAYS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/christmas")
public class ChristmasCountdownServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		
		LocalDate today = LocalDate.now();
		LocalDate christmas = LocalDate.of(today.getYear(), Month.DECEMBER, 25);
		
		if (ChronoUnit.DAYS.between(today, christmas) <= 0) {
			christmas = christmas.plusYears(1);
		}
		
		// servletillä voidaan välittää Java-arvoja JSP-sivuille attribuutteina
		req.setAttribute("daysUntil", ChronoUnit.DAYS.between(today, christmas));
		
		// välitetään pyyntö edelleen sivulle christmas.jsp
		req.getRequestDispatcher("/WEB-INF/christmas.jsp").forward(req, resp);
		
	}
}