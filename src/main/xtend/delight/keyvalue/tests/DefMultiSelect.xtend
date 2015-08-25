package delight.keyvalue.tests

import delight.async.AsyncCommon
import delight.async.Value
import delight.async.jre.Async
import delight.functional.Success
import delight.keyvalue.Store
import delight.keyvalue.operations.StoreOperations

class DefMultiSelect implements StoreTest  {
	
	
	override void test(Store<String, Object> store) {

	
	
		Async.waitFor [ callback |
			store.put("node/child1", "one", AsyncCommon.asSimpleCallback(callback));
		]

		Async.waitFor [ callback |
			store.put("node/child2", "two", AsyncCommon.asSimpleCallback(callback));
		]
		
		Async.waitFor [ callback |
			store.put("node/child3", "three", AsyncCommon.asSimpleCallback(callback));
		]
		
		
		Async.waitFor [ callback |
			val count = new Value(0)
			
			store.performOperation(StoreOperations.getAll("node/", 0, 100), 
				AsyncCommon.embed(callback, [ res |])))
					
			

		]
	
	
		
	}
	
}