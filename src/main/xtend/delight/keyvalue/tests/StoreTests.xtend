package delight.keyvalue.tests

import java.util.List

class StoreTests {
	
	def static List<StoreTest> all() {
		return #[
			new TestMultiDelete,
			new TestMultiSelect,
			new TestThatAsynchronousPutMapCanBeStopped,
			new TestThatParellelWorkerProcessesPuts
		]
	}
	
}