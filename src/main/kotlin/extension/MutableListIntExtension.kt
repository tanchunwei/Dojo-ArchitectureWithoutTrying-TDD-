package extension

class MutableListIntExtension{
    companion object{
        fun MutableList<Int>.total(): Int {
            var total = 0
            for (price in this) {
                total += price
            }
            return total
        }
    }
}