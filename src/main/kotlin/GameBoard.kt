class GameBoard {
    private val size = 3
    val empty = "___"
    var board = Array(size){Array(size){empty} }
    //var gameIsOver=false
    var moveCounter=0
    fun displayBoard(){
        board.forEach {
                row -> row.forEach {
                caseContent -> if (caseContent==empty){print("|$caseContent|")} else {print("| $caseContent |")}
        }
            println()
        }
        println()
    }
    private fun resetBoard(){
        board = Array(size){Array(size){empty} }
    }
    fun resetGame(){
        resetBoard()
        //gameIsOver=false
        moveCounter = 0
    }
    fun placeSign(x: Int, y: Int, move: String){
        if (/*!gameIsOver*/
            board[x][y]==empty
        /*&& isValidPosition(x,y)*/){
            moveCounter++
            board[x][y]=move
            displayBoard()
            /*gameIsOver = isWinningMove(x,y,move) || isDraw()
            if (gameIsOver && !isDraw()) {
                println("The winner is ${currentPlayer.name}!")
                gameIsOver=false
            }else if (isDraw()){
                println("That's a draw!")
                resetGame()
            }*/
        }
    }
    /*private fun isValidPosition(x: Int, y: Int):Boolean{
        return((x in 0..<size) && (y in 0..<size))
    }*/
    fun isWinningMove(x: Int, y: Int, move:String):Boolean{
        //check the row
        for (i in 0..<size){
            if (board[x][i]!==move){
                break
            }
            if(i==size-1){
                return true
            }
        }
        //check the column
        for(i in 0..<size){
            if (board[i][y]!=move){
                break
            }
            if (i==size-1)
                return true
        }
        //check the diagonal
        if (x==y){
            for (i in 0..<size) {
                if (board[i][i]!=move){
                    break
                }
                if (i==size-1){
                    return true
                }
            }
        }
        //check anti-diagonal
        if(x+y==size-1){
            for (i in 0..<size){
                if (board[i][(size-1)-i]!=move){
                    break
                }
                if (i==size-1){
                    return true
                }
            }
        }
        //if (moveCounter==size*size-1 && )
        return false
    }
    //getIndexOfEmpty: in case of 8 consecutive to check whether the 9th move leads to a draw or a win
    /*private fun getIndexOfEmpty(board: GameBoard):List<Int>{
        var indices = mutableListOf<Int>()
        for(i in 0..<size){
            if (board.board[i].contains(empty)){
                indices.add(i)
                indices.add(board.board[i].indexOf(empty))
            }
        }
        return indices
    }*/
    /*fun isDraw(board: GameBoard):Boolean{
        if((size*size)-1==moveCounter){
            if(!board.isWinningMove(getIndexOfEmpty(board)[0],getIndexOfEmpty(board)[1],otherPlayer.sign))
                return true
        }
        return false
        return moveCounter==(size*size)-1
    }*/
    fun printMenu(entriesMenu:MutableMap<String,String>){
        for ((k,v) in entriesMenu.entries){
            println("$k $v")
        }
    }
    fun printScore(player1: Player, player2: Player) {
        println("${player1.name} ${player1.score} - ${player2.score} ${player2.name}")
    }
}