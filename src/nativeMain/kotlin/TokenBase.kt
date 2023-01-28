data class TokenBase(val text: String,
                     val cost: Int,
                     val kind: TokenKind) {
  override fun toString(): String {
    return when(kind) {
      TokenKind.NOUN -> "N"
      TokenKind.VERB -> "V"
      TokenKind.CONJUNCTION -> "C"
      TokenKind.STOP_WORD -> "!"
      TokenKind.UNKNOWN -> "UN"
      else -> "?"
    } + ": " + text + "(" + cost + ")"
  }
}
