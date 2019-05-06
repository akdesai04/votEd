package aman.election.servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aman.election.entities.Candidate;
import aman.election.entities.Voter;
import aman.election.exceptions.ElectionException;
import aman.election.model.CandidateManager;
import aman.election.model.ElectionManager;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/signin")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	// store names for ballot form in application scope
	public void init() {
		CandidateManager cm = new CandidateManager();
		List<Candidate> candidates = null;
			candidates = cm.getCandidates();
			getServletContext().setAttribute("candidates", candidates); 
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		try {
			if (session.getAttribute("voter") != null) {
				throw new ElectionException(
						" Session from previous user has been terminated");
			}
			String voterId = request.getParameter("voterId").trim();
			request.setAttribute("voterId", voterId);
			String password = request.getParameter("password").trim();
			if (voterId.isEmpty() || password.isEmpty() ) {
				throw new ElectionException("Id and password are both required to login");
			}
			int vid = Integer.parseInt(voterId);
			ElectionManager electionManager = new ElectionManager();
			try {
				Voter voter = electionManager.authenticateVoter(vid, password);
				if ( voter == null ) {
					throw new ElectionException("Voter [" + voterId + "] cannot be authenticated");
				}
				session.setAttribute( "voter", voter );
				request.getRequestDispatcher("ballot.jsp").forward(request,
						response);
			} catch(Exception e) {
				request.setAttribute("message", "Voter [" + voterId + "] cannot be authenticated");
				request.getRequestDispatcher("signin.jsp").forward(request,
						response);
			}
			
		} catch (NumberFormatException e) {
			session.invalidate();
			request.setAttribute("message", "Voter ID must be a number");
			request.getRequestDispatcher("signin.jsp").forward(request,
					response);
		} catch (ElectionException e) {
			session.invalidate();
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("signin.jsp").forward(request,
					response);
		}
		return;
	}
}
