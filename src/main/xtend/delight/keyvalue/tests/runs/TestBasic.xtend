package delight.keyvalue.tests.runs

import delight.keyvalue.Stores
import delight.keyvalue.tests.StoreTests
import org.junit.Test

class TestBasic {
	@Test
	def void test() {
		StoreTests.testAndStartAndStop [
			 Stores.hashMap
		]
	}
}