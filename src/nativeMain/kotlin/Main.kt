
var library = listOf(
  TokenBase("ウィキペディア", 10, TokenKind.NOUN),
  TokenBase("は",10, TokenKind.CONJUNCTION),
  TokenBase("、",10, TokenKind.STOP_WORD),
  TokenBase("共同",10, TokenKind.NOUN),
  TokenBase("で",50, TokenKind.CONJUNCTION),
  TokenBase("編纂",10, TokenKind.NOUN),
  TokenBase("している",10, TokenKind.VERB),
  TokenBase("プロジェクト",10, TokenKind.NOUN),
  TokenBase("です",10, TokenKind.VERB),
  TokenBase("。", 10,TokenKind.STOP_WORD),
)

fun main() {
  val text = "ウィキペディアは、共同で編纂しているプロジェクトです。"

  val plans = arrayListOf(
    SearchPlan(0, arrayListOf(), text),
  )

  var j = 0
  while(j < plans.size) {
    val plan = plans[j]

    val localText = plan.restOfString
    val result = plan.tokens
    var i = 0
    while (i < localText.length) {
      val sub = localText.subSequence(IntRange(i, localText.length - 1))
      val candidates = arrayListOf<TokenBase>()
      for (token in library) {
        if (sub.startsWith(token.text)) {
          println("Found:" + i + "=" + token.text)
          candidates.add(token)
        }
      }
      if (candidates.size > 0) {
        i += candidates[0].text.length
        for (k in 1 until candidates.size) {
          val subsub = sub.substring(candidates[k].text.length)
          val newList = arrayListOf<TokenBase>()
          result.forEach {
            newList.add(it)
          }
          newList.add(candidates[k])
          plans.add(SearchPlan(
            plan.cost, newList, subsub
          ))
        }
        plan.cost += candidates[0].cost
        result.add(candidates[0])
        continue
      }
      val unknown = TokenBase(sub.toString(), 99999, TokenKind.UNKNOWN)
      result.add(unknown)
      plan.cost += unknown.cost
      break
    }

    j += 1
  }

  for (plan in plans) {
    println("----- Plan Detail ----")
    println("TotalCost=" + plan.cost)
    for (token in plan.tokens) {
      println(token.text)
    }
    println("----------------------")
  }
  plans.sortBy { it.cost }

  println(plans[0])

}
