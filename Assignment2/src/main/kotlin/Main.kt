
val COLORS = listOf('R', 'B', 'Y', 'G', 'O', 'W')

fun provideFeedback(secretCode: List<Char>, guess: List<Char>): Pair<Int, Int> {

    val correctPosition = secretCode.zip(guess).count { (s, g) -> s == g }
    val correctColor = COLORS.sumBy { color ->
        minOf(secretCode.count { it == color }, guess.count { it == color })
    } - correctPosition
    return Pair(correctPosition, correctColor)
}

fun main() {
    var secretCode: List<Char> = emptyList()
    var currentPlayer = 1
    var attempts = 0

    println("Welcome to game!")

    print("Player 1:Choose four colors")
    secretCode = readLine()?.toUpperCase()?.toList() ?: return

    while (true) {
        print("Player 2: enter your guess: ")
        val guess = readLine()?.toUpperCase()?.toList()

        if (guess == null || guess.size != 4 || guess.any { it !in COLORS }) {
            println("Invalid input. Please enter a valid guess.")
            continue
        }

        attempts++

        val feedback = provideFeedback(secretCode, guess)

        if (feedback.first == 4) {
            println("Congratulations! Player 2 guessed the correct choices $secretCode in $attempts attempts.")
            break
        } else {
            println("Feedback: ${feedback.first} correct position, ${feedback.second} correct color but in the wrong position.")
        }
    }

    println("Thanks for playing!")
}
