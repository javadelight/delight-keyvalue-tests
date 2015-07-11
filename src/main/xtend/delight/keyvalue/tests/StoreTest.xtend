package delight.keyvalue.tests

import delight.keyvalue.Store

interface StoreTest {
	
	def void test(Store<String, Object> store)
	
}