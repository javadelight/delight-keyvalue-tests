package delight.keyvalue.tests

import delight.async.AsyncCommon
import delight.async.jre.Async
import delight.keyvalue.Store
import delight.keyvalue.operations.StoreOperations
import java.util.List
import delight.functional.Success

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
			
			store.performOperation(StoreOperations.getAll("node/", 0, 100), 
				AsyncCommon.embed(callback, [ res |
					
					
					if ((res as List<Object>).size() != 3) {
						callback.onFailure(new Exception("Invalid number of results."))
						return
					}
					
					callback.onSuccess(Success.INSTANCE)
				]))
					
			

		]
	
	
		
	}
	
}