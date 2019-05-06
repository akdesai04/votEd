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
import aman.election.exceptions.DataInputException;
import aman.election.exceptions.ElectionException;
import aman.election.model.ElectionManager;

/**
 * Servlet implementation class VoteServlet
 */
@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VoteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Voter voter = (Voter) session.getAttribute("voter");
		try {
		if (voter == null) {
			throw new ElectionException("You must sign in before voting");
		} 
		List<Candidate> lc = (List<Candidate>) getServletContext().getAttribute("candidates");
		ElectionManager electionManager = new ElectionManager();
		for ( Candidate candidate : lc) {
			System.out.println(candidate.getName()+":"+candidate.getVotes());
			if ( request.getParameter( candidate.getCid() ) != null ) {
				
				electionManager.castBallot( voter, candidate );
				candidate.setVotes(candidate.getVotes()+1);
				System.out.println(candidate.getName()+" inside if "+candidate.getVotes());
				session.invalidate();
				// output to server console for testing and debugging purposes
				//electionManager.printVoteCount(System.out);
				request.getRequestDispatcher("/done.jsp").forward(request,	response);
				return;
			} 
		}
		// no candidate selected to vote for
		throw new ElectionException ("Invalid ballot from voter: " + voter );		
		} catch ( ElectionException | DataInputException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/errorPage.jsp").forward(request,
					response);
			return;
		}
	}
}
