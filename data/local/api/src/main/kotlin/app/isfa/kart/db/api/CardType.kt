package app.isfa.kart.db.api

enum class CardType(val type: String) {
    QRCode("qrcode"),
    Barcode("barcode"),
    Numeric("numeric"),
}