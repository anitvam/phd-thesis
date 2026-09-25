interface Engine {
    fun step(id: Int, sensorData: Sensors): Actuators
    fun subscribe(node1: Int, node2: Int): Boolean
    fun unsubscribe(node1: Int, node2: Int): Boolean
    fun addNode(id: Int): Boolean
    fun removeNode(id: Int): Boolean
}