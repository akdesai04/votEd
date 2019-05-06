package aman.election.model;

import java.io.PrintStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import aman.election.entities.Candidate;
import aman.election.entities.Voter;
import aman.election.exceptions.DataInputException;
import aman.election.exceptions.ElectionException;

public class ElectionManager { 
	
    public ElectionManager() {
       super();
    }
    
    // user login: returns a Voter if user authenticated or null otherwise  
	public Voter authenticateVoter(int voterId, String password) throws ElectionException  {
		    // complete this method to return either an authenticated Voter
		    // or null
		
		EntityManager em = Persistence.createEntityManagerFactory( "AmanElectionJPA" ).createEntityManager();
		
	//	TypedQuery<Voter> q2 =
	//		      em.createQuery("SELECT v FROM Voter v WHERE v.VPK = "+voterId+" and v.PASSWORD = '"+password+"'", Voter.class);
		TypedQuery<Voter> q2 =
				em.createNamedQuery("getVoter", Voter.class);
		q2.setParameter("vid", voterId);
		q2.setParameter("password", password);
		return q2 != null ? q2.getSingleResult() : null;
	}

	// user votes: set Voter.hasVoted to true or 1
	//  and 1 to count of votes for the candidate	
	public void castBallot(Voter v, Candidate c ) throws ElectionException, DataInputException {
	// complete this method process one ballot form
    // throw exceptions as appropriate
		EntityManager em = Persistence.createEntityManagerFactory( "AmanElectionJPA" ).createEntityManager();
		em.getTransaction().begin();
	    em.createQuery("UPDATE Voter AS v SET v.voted = ?1 WHERE v.vid = ?2").setParameter(1, true).
	    setParameter(2, v.getVid()).executeUpdate();
	    
	    em.createQuery("UPDATE Candidate AS c SET c.votes = ?1 WHERE c.cid = ?2").setParameter(1, c.getVotes()+1).
	    setParameter(2, c.getCid()).executeUpdate();
	    em.getTransaction().commit();
		return;
	}
	
	// for testing and debugging only: 
	// print current vote count for each candidate to the server console 	
	public void printVoteCount(List<Candidate> candidates ) throws ElectionException {
	// complete this mprintVoteCountethod
		for(Candidate candidate: candidates) {
			System.out.println(candidate.getName()+":"+candidate.getVotes());
		}

		return;
	}
}
