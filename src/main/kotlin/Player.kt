data class Player(val name:String, var score:Int=0, val sign:String, var isPlaying: Boolean = false) {
    fun updateScore(){score++}
}
//isPlaying to check whether player still holds the turn or not