import java.awt.font.NumericShaper.Range
import kotlin.system.exitProcess

/*

* Crear un programa para gestionar una agenda de contactos. El funcionamiento será el siguiente:

*** - El programa pedirá introducir algo por teclado:
*** o Si se introduce un número de teléfono comprobará si existe, si es así mostrará a quién pertenece. Si no existe pedirá el nombre del contacto para darlo de alta.

*** o Si se introduce un contacto debe comprobar si existe, si es así mostrará el número de teléfono. Si no existe pedirá el número de teléfono para darlo de alta.

?- Debe reconocer números de teléfono con espacios entre los dígitos o no y con el símbolo + al principio para indicar prefijo de país.
- Los nombres deben comenzar por letras y pueden contener números y caracteres especiales imprimibles.

- Comandos especiales:
*** o adios: Sale del programa
*** o listado: Muestra el listado completo de contactos ordenados por nombre
*** o filtra texto_a_buscar: Muestra el listado de los contactos que contengan texto_a_buscar o “Ningún
contacto” si no hubiera ninguno.

- Se deben crear funciones cuando sea adecuado (por employee, comprobar si existe un contacto en la agenda, comprobar si un texto puede ser un número de teléfono, etc).
*
* */

//const val reset = "\u001B[0m"
const val rojo = "\u001B[31m"
const val azul = "\u001B[34m"
const val cian = "\u001B[36m"
const val verde = "\u001B[32m"
const val amarillo = "\u001B[33m"
//const val purpura = "\u001B[35m"

class Agenda {
    private var contactos:  MutableMap<String, String> = mutableMapOf(
                    Pair("Paco",      "654234957"),
                    Pair("Claudia",   "654928375"),
                    Pair("Marco",     "654192846") )

    fun comprobarNombre(nombre : String): String {

        //(nombre[0] != 65-90 && nombre[0] != 97-122)
        //val rangoMinus = 'a'..'z'
        //val rangoMayus = 'A'..'Z'

        var nombre = nombre
        if (nombre[0].isLetter()){
            nombre[0].uppercase()
            println(nombre)
        }else{
            println(rojo+"El primer caracter no es una letra así será cambiada por una 'A'")
            nombre = "A$nombre"
            println(nombre)
        }
        return nombre
    }

    fun listadoContactos () {
        println(azul+"Listado de Contactos por orden alfabetico:")
        for (key in contactos.keys.sorted()){
            println(cian+key+"  "+contactos[key])
        }
    }

    fun salir () {
        exitProcess(0)
    }

    fun buscarPorTexto(){
        //https://programmerclick.com/article/10661775360/
        //Hacer una lista de los nombres y otro de los numeros con un for y pasar cada una.

        //para el telefono estaria bien hacer saber si se puede guardar las posiciones de una
        // lista y cuando entregue el telefono de la posicion x te devuelva el nombre de la posicion x.

        val listaContactos : MutableList<String> = mutableListOf()
        val listaTelefonos : MutableList<String> = mutableListOf()

        for (key in contactos.keys.sorted()){
            listaContactos.add(key)
            listaTelefonos.add(contactos[key].toString())
        }

        //Falta Identificar que la primera letra la identifique tambien como mayuscula.
        val lista = listaContactos.toList()
        println(amarillo+"Escribe el texto del contacto que quiere buscar:")
        val texto = readLine().toString()
        println(lista.filter { it.contains(texto) })

    }

    //Esta function es para gestionar el dato que se manda desde el main, si es un dato de contacto o teléfono y para ell lo buscará por ejemplo en un mapa que tengo que crear de antes.
    fun comprobarDatosContacto(){
        println("$verde¿Desea buscar por Teléfono o por Nombre?")
        print("Pulse T/N    ")
        val repuestaTN = readLine().toString()

        if (repuestaTN=="t"|| repuestaTN=="T"){
            println("¿Que teléfono desea buscar?")
            val telefono = readLine().toString()
            contactos.containsValue(telefono)
            if (!contactos.containsValue(telefono)) {
                println(rojo + "Ese teléfono no se encuentra disponible, ¿desea crear el contacto?")
                print(amarillo + "S/N   ")
                val respuesta = readLine().toString()
                if (respuesta == "s" || respuesta == "S") {
                    println(verde + "Introduce el nombre del contacto:")
                    val contactoCreado = readLine().toString()
                    comprobarNombre(contactoCreado) //seguir por aqui.
                    //contactos[comprobarNombre(contactoCreado)] = telefono
                    contactos[contactoCreado] = telefono

                }
            }else {
                println(cian + "Su nombre es "+ contactos[telefono])
            }

        }else if (repuestaTN =="n" || repuestaTN=="N") {
            println("¿Que contacto desea buscar?")
            println("El primer caracter debe ser una letra")
            val contacto = readLine().toString()
            // val contactoLocalizado: String? = contactos[comprobarNombre(contacto)]
            comprobarNombre(contacto)
            val contactoLocalizado: String? = contactos[contacto]

            if (contactoLocalizado == null) {
                println(rojo + "Ese contacto no se encuentra disponible, ¿desea crearlo?")
                print(amarillo + "S/N   ")
                val respuesta = readLine().toString()
                if (respuesta == "s" || respuesta == "S") {
                    println(verde + "Introduce el número de teléfono:")
                    val telefonoCreado = readLine().toString()

                    contactos[comprobarNombre(contacto)] = telefonoCreado
                    //contactos[contacto] = telefonoCreado

                } else {
                    println(cian + "Que tenga un buen día")
                }
            } else {
                println(cian + "Su telefono es $contactoLocalizado")
            }
        }else{
            println("Se ha producido un error")
        }
    }
}
