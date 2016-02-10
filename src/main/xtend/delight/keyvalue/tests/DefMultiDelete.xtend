package delight.keyvalue.tests

import delight.async.AsyncCommon
import delight.async.jre.Async
import delight.functional.Success
import delight.keyvalue.Store
import delight.keyvalue.operations.StoreOperations

class DefMultiDelete implements StoreTest {
	
	
	override void test(Store<String, Object> store) {
		Async.waitFor [ callback |
			store.put("https://localhost:12012/node/child1", "one", AsyncCommon.asSimpleCallback(callback));
		]
		
		Async.waitFor [ callback |
			store.put("https://localhost:12012/node/child2", "two", AsyncCommon.asSimpleCallback(callback));
		]
		
		Async.waitFor [ callback |
			store.put("https://localhost:12012/node/child3", "three", AsyncCommon.asSimpleCallback(callback));
		]
		
		Async.waitFor [ callback |
			store.commit(AsyncCommon.asSimpleCallback(callback))
		]
		
		Async.waitFor [ callback |

			store.performOperation(StoreOperations.count("https://localhost:12012/"), AsyncCommon.embed(callback, [ count |
				
				if (count as Integer != 3) {
					callback.onFailure(new Exception('Not created correctly'))
					return
				}
				
				callback.onSuccess(Success.INSTANCE);
				
			]));

		]
		
		Async.waitFor [ callback |

			store.performOperation(StoreOperations.removeAll("https://localhost:12012/node/"), AsyncCommon.embed(callback, [
				callback.onSuccess(Success.INSTANCE)
			]));

		]
		
		Async.waitFor [ callback |

			store.performOperation(StoreOperations.count("https://localhost:12012/"), AsyncCommon.embed(callback, [ count |
				if (count as Integer > 0) {
					callback.onFailure(new Exception('Not deleted correctly'))
					return
				}
				
				callback.onSuccess(Success.INSTANCE);
				
			]));

		]
	
	
		
	}
	
}