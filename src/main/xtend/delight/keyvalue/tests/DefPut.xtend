package delight.keyvalue.tests

import delight.async.AsyncCommon
import delight.async.jre.Async
import delight.concurrency.jre.JreConcurrency
import delight.keyvalue.Store
import delight.keyvalue.Stores

class DefPut implements StoreTest {

	
	override void test(Store<String, Object> store) {

		val map = Stores.enforceAsynchronousPut(10, new JreConcurrency(),
			store);

		Async.waitFor [ callback |
			map.start(AsyncCommon.asSimpleCallback(callback));
		]

		Async.waitFor [ callback |
			map.put("1", "one", AsyncCommon.asSimpleCallback(callback));
		]

		Async.waitFor [ callback |
			map.put("2", "two", AsyncCommon.asSimpleCallback(callback));
		]

		Async.waitFor [ callback |
			map.stop(AsyncCommon.asSimpleCallback(callback));
		]

	}
	

}
