package application;

import java.io.IOException;
import Methods.FilesUtil;
import Methods.Log;

//this class is to execute the steps
public class Run extends UArmClientController {

	UArm uarm;

//	Sends information to the Arm so that it can move to the inputed direction
	public Run(UArm u) throws IOException, Exception {

		this.uarm = u;
		int x = 1;
		int y = 140;
		int z = 145;
		int temp;
//		reading from the StepsList
		FilesUtil.fileExist("StepsList.txt");
		String content = FilesUtil.readTextFile("StepsList.txt");
		FilesUtil.fileExist("Log.txt");
		Log log = new Log();
		log.printlog(content);
		String[] arr = content.split(";");
//		Searches for each one of the directions and then reads their values
//		that is placed after them
		for (int i = 0; i < arr.length; i++) {

//			Searches for the word Right
			if (arr[i].contains("Right")) {
				String[] arr2 = arr[i].split(":");
				temp = Integer.parseInt(arr2[1]);
				if (temp != 0) {
					x += temp * 2.5;
				}
				uarm.goToPosition(x, Math.abs(y), z);

			}

//			Searches for the word Left
			if (arr[i].contains("Left")) {
				String[] arr2 = arr[i].split(":");
				temp = Integer.parseInt(arr2[1]);
				if (temp != 0) {
					x -= temp * 2.5;
				}
				uarm.goToPosition(x, Math.abs(y), z);

			}

//			Searches for the word Front
			if (arr[i].contains("Front")) {
				String[] arr2 = arr[i].split(":");
				temp = Integer.parseInt(arr2[1]);
				if (temp != 0) {
					y += temp * 1.3;

				}
				uarm.goToPosition(x, y, z);

			}

//			Searches for the word Back
			if (arr[i].contains("Back")) {
				String[] arr2 = arr[i].split(":");
				temp = Integer.parseInt(arr2[1]);
				if (temp != 0) {
					if ((x > -5 && x <= 5) && (y > 0 && y <= 140)) {
						y -= temp;
					} else {

						y -= temp * 2;
						z += temp / 2;
					}
				}
				uarm.goToPosition(x, y, z);
			}

//			Searches for the word Up
			if (arr[i].contains("Up")) {
				String[] arr2 = arr[i].split(":");
				temp = Integer.parseInt(arr2[1]);
				if (temp != 0) {
					z += temp * 3;
				}
				uarm.goToPosition(x, Math.abs(y), z);

			}

//			Searches for the word Down
			if (arr[i].contains("Down")) {
				String[] arr2 = arr[i].split(":");
				temp = Integer.parseInt(arr2[1]);
				if (temp != 0) {
					z -= temp * 3;
				}
				uarm.goToPosition(x, Math.abs(y), z);
			}
		}
	}
}
