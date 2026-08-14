# **Control de Parqueo**

**Estudiante** 

Nombre: Gerson Ezequiel López Enriquez
Carné: 9941-25-22144

## **Descrición**

###### Programa desarrollado en Java para registrar vehículos en un parqueo, calcular el pago según el tipo de vehículo y las horas estacionadas, aplicar descuento y recargo por ticket perdido, y mostrar un resumen de la jornada.



## **Métodos utilizados**

* ObtenerTarifa(): obtiene la tarifa según el vehículo.
* ObtenerNombreVehiculo(): obtiene el nombre del tipo de vehículo.
* CalcularDescuento(): calcula el descuento del 15 %.
* CalcularPago(): calcula el total a pagar.
* MostrarComprobante(): muestra el comprobante del vehículo.
* MostrarResumen(): muestra el resumen de la jornada.

## **Sobrecarga**

La sobrecarga se aplicó en CalcularPago(). Una versión recibe Horas y Tarifa, y la otra recibe Horas, Tarifa y Recargo. La segunda se utiliza cuando se pierde el ticket.

## **Casos de prueba**

* Automóvil, 10 horas, ticket perdido → Q118.00
* Motocicleta, 4 horas, sin ticket perdido → Q20.00
* Pickup, 10 horas, sin ticket perdido → Q102.00

## **Reto opcional**

No realizado.