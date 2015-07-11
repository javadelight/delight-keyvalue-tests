package delight.keyvalue.tests.runs

import delight.keyvalue.Stores
import delight.keyvalue.jre.AsyncMapsJre
import delight.keyvalue.tests.StoreTests
import org.junit.Test

class TestDivideWork {
	
	@Test
	def void test() {
		StoreTests.testAndStartAndStop [
			AsyncMapsJre.divideWork(4, Stores.hashMap)
		]
	}
	
}