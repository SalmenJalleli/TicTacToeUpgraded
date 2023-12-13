import java.util.Scanner
import kotlin.system.exitProcess

var currentPlayer=Player("",0," ")
var otherPlayer=currentPlayer.copy()
var playerInput = ""
//added description comment
fun main() {
    outer@ while (true) {
        println("Welcome to TicTacToe")
        val myBoard = GameBoard()
        val scanner = Scanner(System.`in`)
        println("What is the name of Player1? :")
        val player1 = Player(scanner.nextLine(), sign = "X")
        println("${player1.name} plays with ${player1.sign}.")
        println("What is the name of Player2? :")
        val player2 = Player(scanner.nextLine(), sign = "O")
        println("${player2.name} plays with ${player2.sign}.")
        myBoard.displayBoard()
        outer1@ while (/*!myBoard.gameIsOver*/true) {
            assignTurn(player1, player2)
            println("Input ${currentPlayer.name}:")
            playerInput = scanner.nextLine()
            outer2@ while (currentPlayer.isPlaying) { //check the validity of the input of the current player either "m" or move coordinates
                if (playerInputValidity(playerInput)){
                    if (playerInput == "m") {
                        myBoard.printMenu()
                        println("Choose an entry:")
                        when (scanner.nextLine()) {
                            "1" -> {
                                myBoard.displayBoard()
                                println("Input ${currentPlayer.name}:")
                                playerInput = scanner.nextLine()
                                continue@outer2
                            }
                            "2" -> {
                                myBoard.printScore(player1,player2)
                                //myBoard.displayBoard()
                                println("Input ${currentPlayer.name}:")
                                playerInput = scanner.nextLine()
                                continue@outer2
                            }
                            "3" -> {
                                myBoard.resetGame()
                                continue@outer
                            }
                            "4" -> exitProcess(0)//break
                            "5" -> {
                                println("Copyrights Reserved, TicTacToeKotlin version 1.0, developed by Salman Jalali")
                                println("Input ${currentPlayer.name}:")
                                playerInput = scanner.nextLine()
                                continue@outer2
                            }
                        }
                    } else {
                        val coordinates = playerInput.split(",")
                        if (myBoard[coordinates[0].toInt() - 1, coordinates[1].toInt() - 1] !=myBoard.empty){
                            println("Case occupied!")
                            println("Input ${currentPlayer.name}:")
                            playerInput = scanner.nextLine()
                            continue@outer2
                        }else{
                            myBoard.placeSign(coordinates[0].toInt() - 1, coordinates[1].toInt() - 1, currentPlayer.sign)
                            if(myBoard.isWinningMove(coordinates[0].toInt() - 1, coordinates[1].toInt() - 1, currentPlayer.sign)) {
                                println("the winner is ${currentPlayer.name}!")
                                currentPlayer.updateScore()
                                myBoard.resetGame()
                                println("Input ${currentPlayer.name}:")
                                playerInput = scanner.nextLine()
                                continue@outer2
                            }
                            else if (myBoard.isDraw(myBoard)){
                                println("That's a draw!")
                                assignTurn(player1,player2)
                                myBoard.resetGame()
                                println("Input ${currentPlayer.name}:")
                                playerInput = scanner.nextLine()
                                continue@outer2
                            }
                            else {
                                assignTurn(player1,player2)
                                println("Input ${currentPlayer.name}:")
                                playerInput = scanner.nextLine()
                                continue@outer2
                            }
                        }
                    }
                }
                else{
                    println("Invalid input, please enter a valid entry!")
                    println("Input ${currentPlayer.name}:")
                    playerInput = scanner.nextLine()
                    continue@outer2
                }
            }
        }
    }
}

private operator fun GameBoard.get(i: Int,j:Int): String {
    return (this.board)[i][j]
}

fun assignTurn(p1:Player,p2:Player){
    if (currentPlayer==p1){
        currentPlayer=p2
        otherPlayer=p1
        //p2.isPlaying=true
    }else{
        currentPlayer=p1
        otherPlayer=p2
        //p1.isPlaying=true
    }
    currentPlayer.isPlaying=true
}
fun playerInputValidity(input: String):Boolean= ((input=="m") || (Regex("[1-3],[1-3]").matches(input)))

/*var input = scanner.nextLine()
val pattern = "^[1-3],$[1-3]"
if (input=="m"){
    myBoard.printMenu()
}
else if (Regex(pattern).matches(input)){
    var list = input.split(',')
    var row = list[0]
    var col = list[1]
    myBoard.placeSign(row.toInt()-1,col.toInt()-1,currentPlayer)
}else{
    println("Invalid input, please give a valid entry!")
}*/