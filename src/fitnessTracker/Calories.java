package fitnessTracker;

/**
 * Class Calories - static methods for calculating calories
 * is part of the fitnessTracker Package
 * package reference should be changed according to domain package name
 * @author AED Team
 * @version 1.0
 *
 */

class Calories{
	
	/**
	 * Calculates calories of activity from the parameters below
	 * Assumes validity of parameters (these should be validated externally)
	 * @param weight - weight of athlete in Kgs
	 * @param height - height of athlete in centimeters
	 * @param sex - sex of athlete ('M' or 'F') 
	 * @param age - age of athlete in years
	 * @param MET - MET of activity performed
	 * @param time - time of activity in hours
	 * @return calories of activity
	 */
	static int calculateCalories(int weight, int height, String sex, int age, int MET, int time ) {		
		int BMR = 0;
		int calories = 0;
			
		if (sex.equals("Masculino")) {
			BMR = (int) ( ( 13.75f * weight ) + ( 5.0f * height ) - ( 6.76f * age ) + 66 );			
		} else {
			BMR = (int) ( ( 9.56f * weight ) + ( 1.85f * height ) - ( 4.68f * age ) + 655);
		}
			
		calories = (int) ( ( BMR / 24.0f ) * MET * time );
		return calories;
	}
}
