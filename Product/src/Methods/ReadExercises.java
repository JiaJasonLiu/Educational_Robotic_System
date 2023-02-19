package Methods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//this class contains methods regarding the exercises
public class ReadExercises {
	private List<String> EExercise = new ArrayList<>();
	private List<String> MExercise = new ArrayList<>();
	private List<String> HExercise = new ArrayList<>();
	private static int level = 0;
	private static int quesNum = 0;

//	set level for difficulty	
	public static void setLevel(int num) {
		level = num;
	}

//	get level for difficulty
	public static int getLevel() {
		return level;
	}

//	get number of question
	public static int getQuesNum() {
		return quesNum;
	}

//	increase the number of the question
	public static void increaseQuesNum() {
		quesNum++;
	}

//	decrease the number of the question
	public static void decreaseQuesNum() {
		quesNum--;
	}

//	reset the number of the question
	public static void resetQuesNum() {
		quesNum = 0;
	}

//	Gets all the exercises at a Easy level
	public String getEasyExercises() throws IOException {
		String content = FilesUtil.readTextFile("Exercises.txt");
		String[] arr = content.split(";");
		if (arr.length == 0)
			return "No Exercise Available";

		getExercise(arr, "Easy", EExercise);
//		When no more exercises at available, then it outputs "No Exercise Available"
		if (quesNum >= EExercise.size()) {
			quesNum = EExercise.size();
			return "No Exercise Available";
		}
		return "Easy:" + EExercise.get(quesNum);

	}

//	Gets all the exercises at a Medium level
	public String getMediumExercises() throws IOException {
		String content = FilesUtil.readTextFile("Exercises.txt");
		String[] arr = content.split(";");
		if (arr.length == 0)
			return "No Exercise Available";

		getExercise(arr, "Medium", MExercise);
//		When no more exercises at available, then it outputs "No Exercise Available"
		if (quesNum >= MExercise.size()) {
			quesNum = MExercise.size();
			return "No Exercise Available";
		}
		return "Medium:" + MExercise.get(quesNum);
	}

//	Gets all the exercises at a Hard level
	public String getHardExercises() throws IOException {
		String content = FilesUtil.readTextFile("Exercises.txt");
		String[] arr = content.split(";");
		if (arr.length == 0)
			return "No Exercise Available";

		getExercise(arr, "Hard", HExercise);
//		When no more exercises at available, then it outputs "No Exercise Available"
		if (quesNum >= HExercise.size()) {
			quesNum = HExercise.size();
			return "No Exercise Available";
		}
		return "Hard:" + HExercise.get(quesNum);
	}

//	Gets all the exercises and turns them into a list
	public void getExercise(String[] arr, String type, List<String> list) {
		for (int i = 0; i < arr.length; i++) {
			String[] arr2 = arr[i].split(":");
			if (arr2[0].equals(type)) {
				list.add(arr2[1]);
			}
		}
	}

//	Sorts the list of exercises from Easy, Medium to Hard
	public Queue<String> sortExercise(String a) {
		Queue<String> exercises = new LinkedList<>();
		String[] exercise = a.split(";");
		for (int i = 0; i < 3; i++) {
			String type = null;
			if (i == 0)
				type = "Easy";
			if (i == 1)
				type = "Medium";
			if (i == 2)
				type = "Hard";
			for (int j = 0; j < exercise.length; j++) {
				String[] temp = exercise[j].split(":");
				if (temp[0].equals(type)) {
					exercises.add(temp[0]);
					exercises.add(temp[1]);
				}
			}
		}
		return exercises;
	}
}
