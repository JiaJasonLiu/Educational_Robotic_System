package application;

import java.util.concurrent.TimeUnit;
import jssc.SerialPort;
import jssc.SerialPortException;
//this class is communicates to the arm, to make it function
class UArm {
	SerialPort serialPort;
	int LimitXMin = -300;
	int LimitXMax = 300;
	int LimitYMin = 80;
	int LimitYMax = 300;
	int LimitZMin = 25;
	int LimitZMax = 250;

	public UArm(String port) throws SerialPortException {
		serialPort = new SerialPort(port);
	}

//	Makes a connection with the Uarm, with all the information that is used to send information
	public void setPort() {
		try {
			System.out.println("Port opened: " + serialPort.openPort());
			System.out.println("Params setted: " + serialPort.setParams(SerialPort.BAUDRATE_115200,
					SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE));
		} catch (SerialPortException e) {
		}
	}

//	sends information to the Uarm, to make it move
	void goToPosition(int x, int y, int z) throws Exception {
//		Checks if it is going over the limit, if so, change coordinates
//		that can be read by the arm and doesn't go over
		if (x > LimitXMax) {
			x = LimitXMax;
			hitLimit();
		}
		if (x < LimitXMin) {
			x = LimitXMin;
			hitLimit();
		}
		if (y < LimitYMin) {
			y = LimitYMin;
			z += 100;
			hitLimit();
		}
		if (y > LimitYMax) {
			y = LimitYMax;
			hitLimit();
		}
		if (z < LimitZMin) {
			if (y <= 160) {
				z = LimitZMin + 10;
			} else {
				z = LimitZMin - 20;
			}
			hitLimit();
		}
		if (z > LimitZMax) {
			z = LimitZMax;
			hitLimit();
		}
		// the F would be the speed in mm/min
		String run = "#1 G0 X" + x + " Y" + y + " Z" + z + " F60\n";
		try {
			serialPort.writeBytes(run.getBytes());
			TimeUnit.MILLISECONDS.sleep(700);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	Setting the state of the pump, activate or not
	void pump(int p) throws Exception {
		String pump = "#1 M231 V" + p + "\n";
		try {
			serialPort.writeBytes(pump.getBytes());
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	Makes a beep when the limit is hit
	void hitLimit() throws Exception {
		String limit = "#1 M210 F2800 T0.5\n";
		try {
			serialPort.writeBytes(limit.getBytes());

		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
