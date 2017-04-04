package dal.gravity;

public class GravityConstant implements GravityModel {
	private double g; 
	
	public GravityConstant(double input) 
	{
		g = input; 
	}

	@Override
	public double getGravitationalField() {
		// TODO Auto-generated method stub
		return 0;
	}
}
