package delight.keyvalue.tests.runs

import delight.keyvalue.Stores
import delight.keyvalue.jre.StoresJre
import delight.keyvalue.tests.StoreTests
import org.junit.Test

class TestDivideWork {
	
	@Test
	def void test() {
		StoreTests.testAndStartAndStop [
			StoresJre.divideWork(4, Stores.hashMap)
		]
	}
	
}