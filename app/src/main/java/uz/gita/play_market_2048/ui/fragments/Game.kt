package uz.gita.play_market_2048.ui.fragments

import android.util.Log
import uz.gita.play_market_2048.domain.local.SharedPrev
import uz.gita.play_market_2048.ui.activitys.GameActivity
import kotlin.math.pow
import kotlin.random.Random

class Game(val game: GameActivity) {


    private var newElement = arrayListOf<Int>()

    private val sharedPrev = SharedPrev.getInstance(game).shared

    var score = 0

    var best = 0

    var record = 0

    val matrix = arrayOf(
        arrayOf(0, 0, 0, 0),
        arrayOf(0, 0, 0, 0),
        arrayOf(0, 0, 0, 0),
        arrayOf(0, 0, 0, 0)
    )

    init {
        newElement.add(2)
        addNumb()
        addNumb()
        if (sharedPrev.getBoolean("game", false)) {
            for (i in 0 until 16) matrix[i / 4][i % 4] = sharedPrev.getInt("numb$i", 2)
            score = sharedPrev.getInt("score", 0)
            best = sharedPrev.getInt("best", 0)
        }
        record()
        sortelements()
    }

    private fun sortelements() {

        var bool = true
        while (bool) {
            val count = newElement[newElement.lastIndex]
            if (record >= count.toDouble().pow(3.0).toInt()) {
                val numb = count * 2
                if (!(newElement.contains(numb)))
                    newElement.add(numb)
            } else bool = false
            Log.d("TTT", "${newElement[newElement.lastIndex]}")
        }

        bool = true
        while (bool) {

            if (newElement.size > 1) {
                val b = newElement[0]
                var remove = true
                for (i in matrix.indices) {
                    for (j in matrix[i].indices) {
                        if (b == matrix[i][j]) remove = false
                    }
                }
                if (remove) newElement.remove(b)
                else return
            } else return
        }

    }

    fun trash() {
        var bool = true
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] == record && bool) {
                    bool = false
                } else {
                    matrix[i][j] = 0
                }
            }
        }
        addNumb()
        game.describeMatrixToViews()
    }

    fun newGame() {
        score = 0
        game.tr()
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                matrix[i][j] = 0
            }
        }
        newElement.apply {
            clear()
            add(2)
        }
        addNumb()
        addNumb()
        record()
        game.describeMatrixToViews()
    }

    private fun addNumb() {
        val emptyList = ArrayList<Pair<Int, Int>>()
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] == 0) emptyList.add(Pair(i, j))
            }
        }
        if (emptyList.size > 0) {
            val random = Random.nextInt(0, emptyList.size)
            val element = newElement[Random.nextInt(0, newElement.size)]
            matrix[emptyList[random].first][emptyList[random].second] = element
        }
    }

    fun moveToLeft() {
        var bool = false
        for (i in matrix.indices) {
            val row = arrayListOf<Int>()
            for (j in matrix[i].indices) {
                if (matrix[i][j] == 0) continue
                var ist = true
                for (k in j + 1 until matrix[i].size) {
                    if (matrix[i][j] == matrix[i][k]) {
                        ist = false
                        row.add(matrix[i][j] * 2)
                        score += matrix[i][j] * 2
                        matrix[i][k] = 0
                        break
                    } else if (matrix[i][k] != 0) {
                        break
                    }
                }
                if (ist) row.add(matrix[i][j])
            }
            val end = row.size
            for (j in 0 until 4 - end) {
                row.add(0)
            }

            for (j in matrix.indices) {
                if (matrix[i][j] != row[j]) {
                    bool = true
                    break
                }
            }

            for (j in 0 until 4) {
                matrix[i][j] = row[j]
            }
        }
        if (bool) addNumb()
        best = if (best < score) score else best
        record()
        game.describeMatrixToViews()
        finish()
    }

    fun moveToRight() {
        var bool = false

        for (i in matrix.indices) {
            val row = arrayListOf<Int>()
            for (j in matrix[i].size - 1 downTo 0) {
                if (matrix[i][j] == 0) continue
                var ist = true
                for (k in j - 1 downTo 0) {
                    if (matrix[i][j] == matrix[i][k]) {
                        ist = false
                        row.add(0,matrix[i][j] * 2)
                        score += matrix[i][j] * 2
                        matrix[i][k] = 0
                        break
                    } else if (matrix[i][k] != 0) {
                        break
                    }
                }
                if (ist) row.add(0, matrix[i][j])
            }
            val end = row.size
            for (j in 0 until 4 - end) {
                row.add(0, 0)
            }

            for (j in matrix.indices) {
                if (matrix[i][j] != row[j]) {
                    bool = true
                    break
                }
            }

            for (j in 0 until 4) {
                matrix[i][j] = row[j]
            }
        }
        if (bool) addNumb()
        best = if (best < score) score else best
        record()
        game.describeMatrixToViews()
        finish()
    }

    fun moveToUp() {

        var bool = false

        for (i in matrix[0].indices) {
            val row = arrayListOf<Int>()

            for (j in matrix.indices) {
                if (matrix[j][i] == 0) continue
                var ist = true
                for (k in j + 1 until matrix.size) {
                    if (matrix[j][i] == matrix[k][i]) {
                        ist = false
                        row.add(matrix[j][i] * 2)
                        score += matrix[j][i] * 2
                        matrix[k][i] = 0
                        break
                    } else if (matrix[k][i] != 0) {
                        break
                    }
                }
                if (ist) row.add(matrix[j][i])
            }

            val end = row.size
            for (j in 0 until 4 - end) {
                row.add(0)
            }

            for (j in matrix.indices) {
                if (matrix[j][i] != row[j]) {
                    bool = true
                    break
                }
            }

            for (j in matrix.indices) {
                matrix[j][i] = row[j]
            }

        }
        if (bool) addNumb()
        best = if (best < score) score else best
        record()
        game.describeMatrixToViews()
        finish()
    }

    fun moveToDown() {
        var bool = false
        for (i in matrix[0].indices) {
            val row = arrayListOf<Int>()
            for (j in matrix.size - 1 downTo 0) {
                if (matrix[j][i] == 0) continue
                var ist = true
                for (k in j - 1 downTo 0) {
                    if (matrix[j][i] == matrix[k][i]) {
                        ist = false
                        row.add(0, matrix[j][i] * 2)
                        score += matrix[j][i] * 2
                        matrix[k][i] = 0
                        break
                    } else if (matrix[k][i] != 0) break
                }
                if (ist) row.add(0, matrix[j][i])
            }

            val end = row.size
            for (j in 0 until 4 - end) {
                row.add(0, 0)
            }

            for (j in matrix.indices) {
                if (matrix[j][i] != row[j]) {
                    bool = true
                    break
                }
            }

            for (j in matrix.indices) {
                matrix[j][i] = row[j]
            }

        }
        if (bool) addNumb()
        best = if (best < score) score else best
        record()
        game.describeMatrixToViews()
        finish()
    }

    private fun record() {
        var count = 0;
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (count < matrix[i][j]) count = matrix[i][j]
            }
        }
        record = count

        count = newElement[newElement.size - 1]
        if (record >= count.toDouble().pow(3.0).toInt()) {
            if (!(newElement.contains(count * 2)))
                newElement.add(count * 2)
        }

        if (newElement.size > 1) {
            val b = newElement[0]
            var bool = true
            for (i in matrix.indices) {
                for (j in matrix[i].indices) {
                    if (b == matrix[i][j]) bool = false
                }
            }
            if (bool) newElement.remove(b)
        }
    }

    private fun finish() {

        for (i in matrix.indices) {
            for (j in matrix.indices) {
                if (matrix[i][j] == 0) return
            }
        }

        for (i in matrix.indices) {
            for (j in 0 until 3) {
                if (matrix[i][j] == matrix[i][j + 1]) return
                if (matrix[j][i] == matrix[j + 1][i]) return
            }
        }
        game.game_over()
    }

}
