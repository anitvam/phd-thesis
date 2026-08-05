fun ticTacToe(gridSize: Int = 3) = mas {
  require(gridSize > 0)
  environment { 
    from(GridEnvironment(gridSize))
    actions { action(Put) } 
  }
  player(mySymbol="x", otherSymbol="o", gridSize=gridSize)
  player(mySymbol="o", otherSymbol="x", gridSize=gridSize)
}

fun MasScope.player(mySymbol: String, otherSymbol: String, gridSize: Int) = 
  agent("$mySymbol-agent") {
    beliefs {
      alignment("vertical",dx=0,dy=1)
      alignment("horizontal",dx=1,dy=0)
      alignment("diagonal",dx=1,dy=1) 
      alignment("antidiagonal",dx=1,dy=-1)
      setOf("vertical", "horizontal", "diagonal", "antidiagonal").forEach { 
        rule { aligned(L) impliedBy it(L) } 
      }
    }
    plans {
      detectVictory(mySymbol, gridSize)
      detectDefeat(mySymbol, otherSymbol, gridSize)
      makeWinningMove(mySymbol, gridSize)
      preventOtherFromWinning(mySymbol, otherSymbol, gridSize)
      randomMove(mySymbol)
    }
  }

fun BeliefsScope.alignment(name: String, dx: Int, dy: Int) {
  val first = cell(A, B, C)
  val second = cell(X, Y, Z)
  rule { name(listOf(second)).fromSelf impliedBy second }
  rule { name(listFrom(first, second, last = W)).fromSelf.impliedBy(
      first, 
      second, 
      (X - A) arithEq dx, 
      (Y - B) arithEq dy,
      name(listFrom(second, last = W)).fromSelf,
    ) 
  }
}

fun PlansScope.detectVictory(myMark: String, size: Int) =
      detect(myMark, myMark, size) { Print("I won!") }
fun PlansScope.detectDefeat(myMark: String, otherMark: String, size: Int) =
      detect(mySymbol, otherMark, size) { Print("I lost!") }
fun PlansScope.detect(me:String, oth:String, s:Int, action:BodyScope.() -> Unit) =
      +turn(me) onlyIf { aligned((1..s).map { cell(oth) }) } then(action)

fun PlansScope.winningMove(myMark:String, gridSize:Int, mark:String = myMark) =
  allPossibleCombinationsOf(cell(X, Y, e), cell(mark), size - 1).forEach {
    +turn(myMark) onlyIf { aligned(it) } then { Put(X, Y, myMark) }
  }

fun PlansScope.preventOtherFromWinning(mySymbol: String, otherSymbol: String, gridSize: Int) = makeWinningMove(mySymbol, gridSize, otherSymbol)

fun PlansScope.randomMove(mySymbol: String) = 
  +turn(mySymbol) onlyIf { cell(X, Y, e) } then { Put(X, Y, mySymbol) }

object Put : AbstractExternalAction(Signature("put", 3)) {
  override fun action(request: ExternalRequest) {
      val x = request.argument(0)
      val y = request.argument(1)
      val mark = request.argument(2)
      updateData(mapOf("cell" to Triple(x, y, mark)))
  }
}
