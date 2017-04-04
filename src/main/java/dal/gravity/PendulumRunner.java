package dal.gravity;

import java.text.NumberFormat;

/** 
 * compares the values of a simple pendulum using the harmonic motion equation
 * versus the Euler algorithm approximation
 */
public class PendulumRunner {
	private static double gravOfEarth = 9.81;
	private static double gravOfJupiter = 25;
	
    public static void main (String [] args) {
	NumberFormat nf = NumberFormat.getInstance ();
	nf.setMaximumFractionDigits (3);

	double delta = (args.length == 0) ? .1 : Double.parseDouble (args[0]);
	double sLen = 10, pMass = 10, theta0 = Math.PI/30;
	GravityModel gravEarth = new GravityConstant(gravOfEarth); //set gravEarth to be gravityConstant
	GravityModel gravJupiter = new GravityConstant(gravOfJupiter);//set gravJupiter to be gravityConstant
	
	
	RegularPendulum rp = new RegularPendulum (sLen, pMass, theta0, delta, gravEarth);
	SimplePendulum sp = new SimplePendulum (sLen, pMass, theta0, gravEarth);
	RegularPendulum rpCoarse = 
	    new RegularPendulum (sLen, pMass, theta0, delta, .1, gravEarth);
	

	// print out difference in displacement in 1 second intervals
	// for 20 seconds
	int iterations = (int) (1/delta);
	System.out.println ("analytical vs. numerical displacement (fine, coarse)");
	for (int second = 1; second <= 20; second++) {
	    for (int i = 0; i < iterations; i++) rp.step ();
	    for (int i = 0; i < 10; i++) rpCoarse.step (); 
	    
	    if (second > 5)
	    {
	    	rp.setGravity(25); //now use jupiters gravity
	    	sp.setGravity(25);
	    }
	    //print out if it's jupiter gravity. 
	    else System.out.print("Jupiter: ");
	    
	    System.out.println ("t=" + second + "s: \t" + 
				nf.format (Math.toDegrees (sp.getTheta (second))) 
				+ "\t" +
				nf.format (Math.toDegrees (rp.getLastTheta ()))
				+ "\t" + 
				nf.format (Math.toDegrees (rpCoarse.getLastTheta ())));
	}//end for 	
    }
}

