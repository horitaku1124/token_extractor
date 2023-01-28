
var library = listOf(
  TokenBase("ウィキペディア", TokenKind.NOUN),
  TokenBase("は", TokenKind.CONJUNCTION),
  TokenBase("、", TokenKind.STOP_WORD),
  TokenBase("共同", TokenKind.NOUN),
  TokenBase("で", TokenKind.CONJUNCTION),
  TokenBase("編纂", TokenKind.NOUN),
  TokenBase("している", TokenKind.VERB),
  TokenBase("プロジェクト", TokenKind.NOUN),
  TokenBase("です", TokenKind.VERB),
  TokenBase("。", TokenKind.STOP_WORD),
)

fun main() {
  val text = "ウィキペディアは、共同で編纂しているプロジェクトです。 "
  val result = arrayListOf<TokenBase>()

  var i = 0
  while (i < text.length) {
    val sub = text.subSequence(IntRange(i, text.length - 1))
    var found = false
    for (token in library) {
      if (sub.startsWith(token.text)) {
        result.add(token)
        i += token.text.length
        found = true
        break
      }
    }
    if (found) {
      continue
    }
    result.add(TokenBase(sub.toString(), TokenKind.UNKNOWN))
    break
  }
  println(result)
}
