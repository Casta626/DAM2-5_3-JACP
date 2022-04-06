fun main() {

    //val reset = "\u001B[0m"
    val rojo = "\u001B[31m"
    val azul = "\u001B[34m"
    //val cian = "\u001B[36m"
    val verde = "\u001B[32m"
    val amarillo = "\u001B[33m"
    val purpura = "\u001B[35m"

    val a = Agenda()
    while (true){
        println("$purpura¿Que desea hacer?")
        println(verde+"1.Comprobar datos de contacto")
        println(azul+"2.Lista de Contactos existente")
        println(amarillo+"3.Buscar contactos por filtrado")
        println(rojo+"4.Salir")
        when (readLine().toString()){
            "1"->{
                a.comprobarDatosContacto()
            }
            "2"->{
                a.listadoContactos()
            }
            "3"->{
                a.buscarPorTexto()
            }
            "4"->{
                println("$cian¡Hasta pronto!")
                a.salir()
            }
            else -> {
                println("Introduzca uno de los numeros anteriores")
            }
        }
    }

}