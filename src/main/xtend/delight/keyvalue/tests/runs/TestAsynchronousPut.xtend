package delight.keyvalue.tests.runs

import delight.concurrency.jre.JreConcurrency
import delight.keyvalue.Stores
import delight.keyvalue.tests.StoreTests
import org.junit.Test

class TestAsynchronousPut {
	@Test
	def void test() {
		StoreTests.testAndStartAndStop [
			 Stores.enforceAsynchronousPut(10, new JreConcurrency(),
			Stores.<String, Object> hashMap);
		]
	}
}