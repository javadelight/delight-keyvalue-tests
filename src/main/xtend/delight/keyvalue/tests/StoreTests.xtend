package delight.keyvalue.tests

import delight.async.AsyncCommon
import delight.async.jre.Async
import delight.functional.Function
import delight.keyvalue.Store
import java.util.List

class StoreTests {

	def static void testAll(Function<Void, Store<String, Object>> factory) {
		for (test : all()) {
			val store = factory.apply(null)
			Async.waitFor [ callback |
				store.start(AsyncCommon.asSimpleCallback(callback));
			]

			test.test(store)

			Async.waitFor [ callback |
				store.stop(AsyncCommon.asSimpleCallback(callback));
			]
		}
	}

	def static List<StoreTest> all() {
		return #[
			new DefMultiDelete,
			new DefMultiSelect,
			new DefPut,
			new TestThatParellelWorkerProcessesPuts
		]
	}

}