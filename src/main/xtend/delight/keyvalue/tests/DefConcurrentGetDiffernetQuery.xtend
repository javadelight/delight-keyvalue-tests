package delight.keyvalue.tests

import delight.async.jre.Async
import delight.keyvalue.Store
import delight.functional.Success

class DefConcurrentGetDifferentQuery implements StoreTest {

	override test(Store<String, Object> store) {

		for (j: 1..50) {
			store.putSync(""+j, "e"+j);
		}

		
		Async.waitFor [ cb |
			
			val col = Async.collect(50, cb);
			
			for (i : 1 .. 50) {

				new Thread() {
					override run() {
						store.getSync(""+i);
						col.createCallback.onSuccess(Success.INSTANCE)
					}
				}.start
			

			}
		]

	}

}