import java.util.Scanner
import kotlin.system.exitProcess

var currentPlayer=Player("",0," ")
var playerInput = ""
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
        while (true) {
            assignTurn(player1, player2)
            println("Input ${currentPlayer.name}:")
            playerInput = scanner.nextLine()
            outer1@ while (currentPlayer.isPlaying){//player keeps playing as long as he gives a valid input.
                if (playerInputValidity(playerInput)){//check the validity of the input of the current player (is it either "m" or a move coordinates?)
                    if (playerInput == "m") {
                        myBoard.printMenu(mutableMapOf("1." to "Back","2." to "Score","3." to "New Game","4." to "Exit","5." to "About"))
                        println("Choose an entry:")
                        when (scanner.nextLine()) {
                            "1" -> {
                                myBoard.displayBoard()
                                println("Input ${currentPlayer.name}:")
                                playerInput = scanner.nextLine()
                                continue@outer1
                            }
                            "2" -> {
                                myBoard.printScore(player1,player2)
                                println("Input ${currentPlayer.name}:")
                                playerInput = scanner.nextLine()
                                continue@outer1
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
                                continue@outer1
                            }
                        }
                    } else {
                        val coordinates = playerInput.split(",")
                        if (myBoard[coordinates[0].toInt() - 1, coordinates[1].toInt() - 1] !=myBoard.empty){
                            println("Case occupied!")
                            println("Input ${currentPlayer.name}:")
                            playerInput = scanner.nextLine()
                            continue@outer1
                        }else{
                            myBoard.placeSign(coordinates[0].toInt() - 1, coordinates[1].toInt() - 1, currentPlayer.sign)
                            if(myBoard.isWinningMove(coordinates[0].toInt() - 1, coordinates[1].toInt() - 1, currentPlayer.sign)) {
                                println("the winner is ${currentPlayer.name}!")
                                currentPlayer.updateScore()
                                myBoard.resetGame()
                                myBoard.printMenu(mutableMapOf("1." to "Score","2." to "New Game","3." to "Exit","4." to "About"))
                                println("Input ${currentPlayer.name}:")
                                when (scanner.nextLine()) {
                                    "1" -> {
                                        myBoard.printScore(player1,player2)
                                        println("Input ${currentPlayer.name}:")
                                        playerInput = scanner.nextLine()
                                        continue@outer1
                                    }
                                    "2" -> {
                                        myBoard.resetGame()
                                        continue@outer
                                    }
                                    "3" -> exitProcess(0)//break
                                    "4" -> {
                                        println("Copyrights Reserved, TicTacToeKotlin version 1.0, developed by Salman Jalali")
                                        println("Input ${currentPlayer.name}:")
                                        playerInput = scanner.nextLine()
                                        continue@outer1
                                    }
                                }
                                println("Input ${currentPlayer.name}:")
                                playerInput = scanner.nextLine()
                                continue@outer1
                            }
                            else if (myBoard.moveCounter==9 && !myBoard.isWinningMove(coordinates[0].toInt() - 1, coordinates[1].toInt() - 1, currentPlayer.sign)){
                                println("That's a draw!")
                                assignTurn(player1,player2)
                                myBoard.resetGame()
                                println("Input ${currentPlayer.name}:")
                                playerInput = scanner.nextLine()
                                continue@outer1
                            }
                            else {
                                assignTurn(player1,player2)
                                println("Input ${currentPlayer.name}:")
                                playerInput = scanner.nextLine()
                                continue@outer1
                            }
                        }
                    }
                }
                else{
                    println("Invalid input, please enter a valid entry!")
                    println("Input ${currentPlayer.name}:")
                    playerInput = scanner.nextLine()
                    continue@outer1
                }
            }
        }
    }
}
private operator fun GameBoard.get(i: Int,j:Int): String {
    return (this.board)[i][j]
}
fun assignTurn(p1:Player,p2:Player){
    currentPlayer = if (currentPlayer==p1){
        p2
    }else{
        p1
    }
    currentPlayer.isPlaying=true
}
fun playerInputValidity(input: String):Boolean= ((input=="m") || (Regex("[1-3],[1-3]").matches(input)))