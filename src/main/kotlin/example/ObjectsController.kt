package example

import server.annotations.Controller
import server.annotations.Endpoint

data class DataObject(val name: String, val amt: Int)

@Controller(baseUrl = "/objects")
class ObjectsController {

    @Endpoint
    fun getObject(): DataObject {
        return DataObject("Test", 23)
    }

    @Endpoint(route = "/list")
    fun getObjects(): List<DataObject> {
        return listOf(
            DataObject("Object 1", 23),
            DataObject("Object 2", 24),
            DataObject("Object 3", 25),
            DataObject("Object 4", 26)
        )
    }
}
