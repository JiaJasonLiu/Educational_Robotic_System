package Exercises;

//this class sets and gets the level and the exercise
public class Exercise {
	private String typeEx;
	private String exercise;

	public Exercise(String typeEx, String exercise) {
		this.setTypeEx(typeEx);
		this.setExercise(exercise);
	}

	public String getTypeEx() {
		return typeEx;
	}

	public void setTypeEx(String typeEx) {
		this.typeEx = typeEx;
	}

	public String getExercise() {
		return exercise;
	}

	public void setExercise(String exercise) {
		this.exercise = exercise;
	}

	public String toString() {
		return this.getTypeEx() + ":" + this.getExercise();
	}
}
