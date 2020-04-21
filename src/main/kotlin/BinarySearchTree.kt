class BinarySearchTree<T : Comparable<T>> {

    data class Node<T>(val data: T, val left: Node<T>? = null, val right: Node<T>? = null)

    var root: Node<T>? = null

    fun insert(value: T) {
        root = insert(value, root)
    }

    fun asSortedList(): List<T> = asSortedList(root)

    fun asLevelOrderList(): List<T> = asLevelOrderList(mutableListOf(root))

    private fun insert(value: T, node: Node<T>?): Node<T>? =
            when {
                node == null -> Node(value)
                value <= node.data -> Node(node.data, insert(value, node.left), node.right)
                else -> Node(node.data, node.left, insert(value, node.right))
            }

    private fun asSortedList(node: Node<T>?): List<T> =
            when (node) {
                null -> emptyList()
                else -> asSortedList(node.left) + node.data + asSortedList(node.right)
            }

    private fun asLevelOrderList(list: MutableList<Node<T>?>): List<T> =
            mutableListOf<T>().also {
                while (list.isNotEmpty()) {
                    list.removeAt(0)?.let { node ->
                        it.add(node.data)
                        list.addAll(listOf(node.left, node.right))
                    }
                }
            }
}
