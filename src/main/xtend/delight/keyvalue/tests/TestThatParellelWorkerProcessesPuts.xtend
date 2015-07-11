package delight.keyvalue.tests

import delight.async.AsyncCommon
import delight.async.jre.Async
import delight.keyvalue.Store
import delight.keyvalue.jre.AsyncMapsJre

class TestThatParellelWorkerProcessesPuts implements StoreTest {
	

	override void test(Store<String, Object> store) {
		
		val map = AsyncMapsJre.divideWork(4, store)
		
		
		Async.waitFor [ callback | 
			map.start(AsyncCommon.asSimpleCallback(callback))
		]
		
		
		Async.waitFor [ callback | 
			map.put("1", "one", AsyncCommon.asSimpleCallback(callback))
		]
		
		Async.waitFor [ callback | 
			map.put("1", "one", AsyncCommon.asSimpleCallback(callback))
		]
		
		Async.waitFor [ callback |
			map.stop(AsyncCommon.asSimpleCallback(callback))
		]
	
		
	}
	
	
}