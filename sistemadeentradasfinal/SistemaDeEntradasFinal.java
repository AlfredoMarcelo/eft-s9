/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemadeentradasfinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaDeEntradasFinal {

    static public Scanner lecturaEntrada = new Scanner(System.in);
    static public List<Integer> idsVenta = new ArrayList<>();
    static public List<String> usuarios = new ArrayList<>();
    static public List<Integer> valoresDeVenta = new ArrayList<>();
    static public int idCorrelativo;
    static public int indiceParaBoleta = 0;
    //static public int[] asientosVip = new int[]{1, 2, 3, 4, 5};
    static public int[] asientosVip = new int[]{1, 2};
    //static public int[] asientosPalco = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    static public int[] asientosPalco = new int[]{1, 2, 3,};
    //static public int[] asientosGeneral = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    static public int[] asientosGeneral = new int[]{1, 2, 3, 4};
    static public int[] listaDescuento = new int[]{10, 15};
    static public int valorVip = 15000;
    static public int valorPalco = 10000;
    static public int valorGeneral = 5000;

    // Eliminar asiento comprado de lista
    static int[] eliminarAsientoComprado(int[] asientos, Integer valorEntrada) {
        int asientoSeleccionado;
        int indexAsientoSelecionado = 0;
        Boolean asientoEstaSeleccionado = false;
        Boolean asientoIngresadoEsCorrecto = false;
        String msjSeleccionAsiento = "\nSeleccione su número de asiento:";
        // Creacion de nuevo arreglo para que sea copia del original segun su ubicacion
        int[] copyArreglo = new int[asientos.length - 1];

        // Selección de asiento
        while (!asientoEstaSeleccionado) {
            mostrarAsientos(asientos);
            System.out.println(msjSeleccionAsiento);
            // Solicitud de asiento
            asientoSeleccionado = lecturaEntrada.nextInt();
            // * Limpieza de buffer Scanner
            lecturaEntrada.nextLine();

            // Busqueda de asiento ingresado disponible
            for (int i = 0; i < asientos.length; i++) {
                if (asientos[i] == asientoSeleccionado) {
                    indexAsientoSelecionado = i;
                    asientoIngresadoEsCorrecto = true;
                }
            }

            // Validación para seleccion de asiento 
            if (asientoIngresadoEsCorrecto == false) {
                System.out.println("****WARNING: Debe ingresar un número de asiento valido****\n");
            } else {
                // Copiar arreglo y quitar asiento reservado
                for (int i = 0, j = 0; i < asientos.length; i++) {
                    if (i != indexAsientoSelecionado) {
                        copyArreglo[j++] = asientos[i];
                    }
                }

                // Agregar valor de entrada y realizar descuento
                String seleccionTipoCliente;
                int valorConDescuento = 0;
                Double descuentoEstudiante = (100 - Double.valueOf(listaDescuento[0])) / 100;
                Double descuentoTerceraEdad = (100 - Double.valueOf(listaDescuento[1])) / 100;
                System.out.println("Seleccione una opción para realizar descuento\n"
                        + "El cliente es: \n"
                        + "1 => Estudiante\n"
                        + "2 => Tercera Edad\n"
                        + "3 => Ninguno de los 2");
                seleccionTipoCliente = lecturaEntrada.nextLine();

                if (seleccionTipoCliente.equalsIgnoreCase("1")) {
                    valorConDescuento = (int) (valorEntrada * descuentoEstudiante);
                    valorEntrada = valorConDescuento;
                }
                if (seleccionTipoCliente.equalsIgnoreCase("2")) {
                    valorConDescuento = (int) (valorEntrada * descuentoTerceraEdad);
                    valorEntrada = valorConDescuento;
                }
                valoresDeVenta.add(valorEntrada);

                // agregar ID de venta
                if (idsVenta.isEmpty()) {
                    //Agrega primer Id
                    idCorrelativo = 1;
                    idsVenta.add(idCorrelativo);
                } else {
                    // agrega un correlativo de ID, entendiendo que no se puede eliminar un correlativo de venta por historial
                    idCorrelativo++;
                    idsVenta.add(idCorrelativo);
                }
                // * Limpieza de buffer Scanner
                agregarDatosCliente();
                asientoEstaSeleccionado = true;
            }

        }
        System.out.println("");
        return copyArreglo;

    }

    // Mostrar Asientos Disponibles
    static void mostrarAsientos(int[] asientos) {
        System.out.println("Asientos Disponibles: ");
        for (int i = 0; i < asientos.length; i++) {
            System.out.print("[" + asientos[i] + "]" + " ");
        }
    }

    // Mostrar correlativos ID
    static void mostrarIds() {
        for (int i = 0; i < idsVenta.size(); i++) {
            System.out.println("ID: " + idsVenta.get(i) + " ");
        }
    }

    // Mostrar Usuarios registrados
    static void mostrarUsuarios() {
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println(usuarios.get(i) + " ");
        }
    }

    // Mostrar ventas segun indice
    static void mostrarValorVentas() {
        for (int i = 0; i < valoresDeVenta.size(); i++) {
            System.out.println(valoresDeVenta.get(i) + " ");
        }
    }

    // Mostrar boleta
    static void mostrarBoleta(int indice) {
        System.out.println("\n-----------------------------------");
        System.out.println("Boleta N " + idsVenta.get(indice));
        System.out.println("Nombre comprador: " + usuarios.get(indice));
        System.out.println("Valor $" + valoresDeVenta.get(indice));
        System.out.println("-----------------------------------\n");

    }

    // Aumentar correlativo boleta
    static void aumentarIndiceParaBoleta() {
        indiceParaBoleta++;
    }

    // Mostrar ventas segun indice
    static void agregarDatosCliente() {
        String nombre;
        String apellido;
        String spacer = " ";
        String nombreCompleto;

        // Agregar nombre y apellido a lista usuarios
        System.out.println(" Ingrese su nombre");
        nombre = lecturaEntrada.nextLine();
        System.out.println(" Ingrese su apellido");
        apellido = lecturaEntrada.nextLine();
        nombreCompleto = nombre + spacer + apellido;
        usuarios.add(nombreCompleto);
    }

    // Realizar Venta
    static void venderEntrada() {
        System.out.println("");
        Boolean salirDelSubmenu = false;
        int id;
        String ubicacionSeleccionada;

        while (!salirDelSubmenu) {
            String opcionSeleccionada;

            System.out.println("Estas son las ubicaciones disponibles y valores:\n"
                    + "  => VIP - $15.000\n"
                    + "  => Palco - $10.000\n"
                    + "  => Galería - $5.000\n");

            // Seleccion ubicacion, numero asiento y datos cliente
            Boolean asientoFueSeleccionado = false;
            while (!asientoFueSeleccionado) {

                System.out.println("Seleccioné su ubicación:\n"
                        + "  1 => Vip\n"
                        + "  2 => Palco\n"
                        + "  3 => General");
                ubicacionSeleccionada = lecturaEntrada.nextLine();
                if (ubicacionSeleccionada.equalsIgnoreCase("1") || ubicacionSeleccionada.equalsIgnoreCase("2") || ubicacionSeleccionada.equalsIgnoreCase("3")) {
                    if (ubicacionSeleccionada.equalsIgnoreCase("1")) {
                        try {
                            if (asientosVip.length == 0) {
                                System.out.println("****************************\n"
                                        + "  Asientos VIP agotados !!!\n"
                                        + "****************************\n");
                                asientoFueSeleccionado = true;
                                salirDelSubmenu = true;
                            } else {
                                //mostrarAsientos(asientosVip);
                                asientosVip = eliminarAsientoComprado(asientosVip, valorVip);
                                //agregarDatosCliente();
                                mostrarBoleta(indiceParaBoleta);
                                System.out.println("Gracias por su compra :)");
                                asientoFueSeleccionado = true;
                                aumentarIndiceParaBoleta();
                                System.out.println(indiceParaBoleta);

                            }

                        } catch (Exception e) {
                            System.out.println("Datos ingresados incorrectamente, se reiniciara el programa");
                            // * Limpieza de buffer Scanner
                            lecturaEntrada.nextLine();
                        }
                    }
                    if (ubicacionSeleccionada.equalsIgnoreCase("2")) {
                        try {
                            if (asientosPalco.length == 0) {
                                System.out.println("****************************\n"
                                        + "  Asientos PALCO agotados !!!\n"
                                        + "****************************\n");
                                asientoFueSeleccionado = true;
                                salirDelSubmenu = true;
                            } else {
                                //mostrarAsientos(asientosPalco);
                                asientosPalco = eliminarAsientoComprado(asientosPalco, valorPalco);
                                //agregarDatosCliente();
                                mostrarBoleta(indiceParaBoleta);
                                System.out.println("gracias por tu compra");
                                asientoFueSeleccionado = true;
                                aumentarIndiceParaBoleta();
                                System.out.println(indiceParaBoleta);
                            }
                        } catch (Exception e) {
                            System.out.println("Datos ingresados incorrectamente, se reiniciara el programa");
                            // * Limpieza de buffer Scanner
                            lecturaEntrada.nextLine();
                        }
                    }
                    if (ubicacionSeleccionada.equalsIgnoreCase("3")) {
                        try {
                            if (asientosGeneral.length == 0) {
                                System.out.println("****************************\n"
                                        + "  Asientos GENERAL agotados !!!\n"
                                        + "****************************\n");
                                asientoFueSeleccionado = true;
                                salirDelSubmenu = true;
                            } else {
                                //mostrarAsientos(asientosGeneral);
                                asientosGeneral = eliminarAsientoComprado(asientosGeneral, valorGeneral);
                                //agregarDatosCliente();
                                mostrarBoleta(indiceParaBoleta);
                                System.out.println("gracias por tu compra");
                                asientoFueSeleccionado = true;
                                aumentarIndiceParaBoleta();
                            }
                        } catch (Exception e) {
                            System.out.println("Datos ingresados incorrectamente, se reiniciara el programa");
                            // * Limpieza de buffer Scanner
                            lecturaEntrada.nextLine();
                        }
                    }
                } else {
                    System.out.println("Debes ingresar una opcion disponible en el menú\n");
                }
            }

            salirDelSubmenu = true;
        }

    }

    // Ver promociones
    static void verPromociones() {
        System.out.println("-----------------------------------------------");
        System.out.println("Las promociones disponibles son:\n"
                + listaDescuento[0] + "% de descuento para estudiantes\n"
                + listaDescuento[1] + "% de descuento para clientes de tercera edad");
        System.out.println("-----------------------------------------------\n");

    }

    // Cambiar Descuento
    static void cambiarPorcentajeDescuento() {
        System.out.println("Ingrega el nuevo valor para el descuento a Estudiantes (ej: 20 es igual a 20% dcto) ");
        listaDescuento[0] = lecturaEntrada.nextInt();
        System.out.println("Ingrega el nuevo valor para el descuento a Tercera Edad (ej: 30 es igual a 30% dcto)");
        listaDescuento[1] = lecturaEntrada.nextInt();
        System.out.println("Se actualizaron los valores de descuento");
        // * Limpieza de buffer Scanner
        lecturaEntrada.nextLine();
    }

    // Ver total ventas
    static void verHistorialDeVentas() {
        if (idsVenta.size() == 0) {
            System.out.println("\n-----------------------------------");
            System.out.println("NO EXISTEN VENTAS :(");
            System.out.println("-----------------------------------\n");
        } else {
            System.out.println("\nHISTORIAL DE VENTAS");
            for (int i = 0; i < idsVenta.size(); i++) {
                System.out.println("-----------------------------------");
                System.out.println("Boleta N " + idsVenta.get(i));
                System.out.println("Nombre comprador: " + usuarios.get(i));
                System.out.println("Valor $" + valoresDeVenta.get(i));
                System.out.println("\n-----------------------------------");
            }
        }
    }

    // Editar Venta
    static void editarVenta() {
        int ventaSeleccionada;
        String nombreActual;
        Integer valorActual;
        String nuevoNombre;
        Integer nuevoValor;

        if (idsVenta.size() == 0) {
            System.out.println("\n-----------------------------------");
            System.out.println("NO EXISTEN VENTAS :(");
            System.out.println("-----------------------------------\n");
        } else {
            System.out.println("\nHISTORIAL DE VENTAS");
            for (int i = 0; i < idsVenta.size(); i++) {
                System.out.println("-----------------------------------");
                System.out.println("Boleta N " + idsVenta.get(i));
                System.out.println("Nombre comprador: " + usuarios.get(i));
                System.out.println("Valor $" + valoresDeVenta.get(i));
                System.out.println("\n-----------------------------------");
            }
            System.out.println("Ingresa el numero de Venta que deseas editar según el numero de boleta:");
            ventaSeleccionada = lecturaEntrada.nextInt();
            // * Limpieza de buffer Scanner
            lecturaEntrada.nextLine();

            nombreActual = usuarios.get(ventaSeleccionada - 1);
            valorActual = valoresDeVenta.get(ventaSeleccionada - 1);

            System.out.println("\n-----------------------------------");
            System.out.println("Datos de Venta a editar:\n");
            System.out.println(" Boleta N " + idsVenta.get(ventaSeleccionada - 1));
            System.out.println(" Nombre comprador: " + nombreActual);
            System.out.println(" Valor $" + valorActual);
            System.out.println("-----------------------------------\n");

            System.out.println("Ingrese el nuevo Nombre y Apellido del comprador:");
            nuevoNombre = lecturaEntrada.nextLine();
            usuarios.set(ventaSeleccionada - 1, nuevoNombre);

            System.out.println("Ingrese el nuevo Valor de Venta");
            nuevoValor = lecturaEntrada.nextInt();
            valoresDeVenta.set(ventaSeleccionada - 1, nuevoValor);

            System.out.println("\n-----------------------------------");
            System.out.println("*****Datos de Venta actualizados con exito :)*****");
            System.out.println("-----------------------------------\n");

            // * Limpieza de buffer Scanner
            lecturaEntrada.nextLine();
        }
    }

    // Eliminar Venta
    static void eliminarVenta() {
        int ventaAEliminarSeleccionada;

        if (idsVenta.size() == 0) {
            System.out.println("\n-----------------------------------");
            System.out.println("NO EXISTEN VENTAS :(");
            System.out.println("-----------------------------------\n");
        } else {
            System.out.println("\nHISTORIAL DE VENTAS");
            for (int i = 0; i < idsVenta.size(); i++) {
                System.out.println("-----------------------------------");
                System.out.println("Boleta N " + idsVenta.get(i));
                System.out.println("Nombre comprador: " + usuarios.get(i));
                System.out.println("Valor $" + valoresDeVenta.get(i));
                System.out.println("\n-----------------------------------");
            }
            System.out.println("Ingresa el numero de Venta que deseas eliminar según el numero de boleta:");
            ventaAEliminarSeleccionada = lecturaEntrada.nextInt();
            // * Limpieza de buffer Scanner
            lecturaEntrada.nextLine();

            idsVenta.remove(ventaAEliminarSeleccionada - 1);
            usuarios.remove(ventaAEliminarSeleccionada - 1);
            valoresDeVenta.remove(ventaAEliminarSeleccionada - 1);

            System.out.println("\n-----------------------------------");
            System.out.println("***** Venta eliminada con exito :)*****");
            System.out.println("-----------------------------------\n");
            indiceParaBoleta--;
            // * Limpieza de buffer Scanner
            lecturaEntrada.nextLine();
        }
    }

    public static void main(String[] args) {
        Boolean salirDelSistema = false;
        String opcionSeleccionada;

        while (!salirDelSistema) {
            System.out.println("Bienvenido al sistema de entradas de teatro Moro\n"
                    + "Seleccione una opción\n"
                    + "  1 => Vender entrada\n"
                    + "  2 => Ver promociones\n"
                    + "  3 => Cambiar Descuento\n"
                    + "  4 => Ver Historial De Ventas\n"
                    + "  5 => Editar Venta\n"
                    + "  6 => Eliminar Venta\n"
                    + "  7 => Salir");

            opcionSeleccionada = lecturaEntrada.nextLine();

            if (opcionSeleccionada.equalsIgnoreCase("1") || opcionSeleccionada.equalsIgnoreCase("2") || opcionSeleccionada.equalsIgnoreCase("3") || opcionSeleccionada.equalsIgnoreCase("4") || opcionSeleccionada.equalsIgnoreCase("5") || opcionSeleccionada.equalsIgnoreCase("6") || opcionSeleccionada.equalsIgnoreCase("7")) {
                if (opcionSeleccionada.equalsIgnoreCase("1")) {
                    System.out.println("seleccionaste vender");
                    venderEntrada();
                    System.out.println("");
                }
                if (opcionSeleccionada.equalsIgnoreCase("2")) {
                    verPromociones();
                    System.out.println("");
                }
                if (opcionSeleccionada.equalsIgnoreCase("3")) {
                    cambiarPorcentajeDescuento();
                    System.out.println("");
                }
                if (opcionSeleccionada.equalsIgnoreCase("4")) {
                    verHistorialDeVentas();
                    System.out.println("");
                }
                if (opcionSeleccionada.equalsIgnoreCase("5")) {
                    System.out.println("Seleccionaste Editar Venta");
                    editarVenta();
                }
                if (opcionSeleccionada.equalsIgnoreCase("6")) {
                    System.out.println("Seleccionaste Eliminar Venta");
                    eliminarVenta();
                }
                if (opcionSeleccionada.equalsIgnoreCase("7")) {
                    System.out.println("Hasta luego :)");
                    System.out.println("");
                    salirDelSistema = true;
                }
            } else {
                System.out.println("Debes ingresar una opcion disponible en el menú");
            }
        }
    }
}
