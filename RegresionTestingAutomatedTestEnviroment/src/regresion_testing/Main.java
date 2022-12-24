package regresion_testing;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import regresion_testing.dynamic_generator.DynamicGenerator;

public class Main {

	public static void main(String[] args) {
		Result res = JUnitCore.runClasses(DynamicGenerator.class);

		for (Failure fail : res.getFailures()) {
			System.out.println(fail.toString());
		}

		System.out.println(res.wasSuccessful());
	}

}
