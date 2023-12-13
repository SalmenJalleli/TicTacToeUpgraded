data class Player(val name:String, var score:Int=0, val sign:String, var isPlaying: Boolean = false) {
    fun updateScore(){score++}
}