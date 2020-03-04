package bankOcr

data class Block(val value: String) {
    companion object {
        val one = Block("   \n" +
                "  |\n" +
                "  |\n" +
                "   ")
        val two = Block(" _ \n" +
                " _|\n" +
                "|_ \n" +
                "   ")
    }
}