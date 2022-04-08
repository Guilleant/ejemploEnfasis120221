fun main( args: Array<String> ){
    var mensaje:String
    mensaje = "hola Guillermo"

    var mensaje2 = "¡Bienvenido!"
    var n1: Int
    var n2: Int

    mostrarMensaje( mensaje, mensaje2 )
    n1 = 4
    n2 = 56
    suma( n1, n2 )
}

fun suma( n1: Int, n2: Int ){
    println( "El resultado de la suma es ${ n1 + n2 }")
}

fun mostrarMensaje( m: String, m2: String ){
    println( "mensaje: $m" )
    println( "Este fue el mensaje enviado: $m $m2" )
}