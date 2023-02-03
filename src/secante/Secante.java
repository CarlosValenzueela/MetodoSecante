/**
 * Paquete secante.
 */
package secante;

/**
 * Importación del Scanner, que sirve para los valores de entrada
 */
import java.util.Scanner;

/*Nombre del archivo: asignacion03_00000207256
Nombre de alumno: Carlos Antonio Valenzuela Valdez
Matricula: 00000207256
Fecha de creación: 16/09/2020 */
/**
 * El programa Secante ayuda a encontrar las raices de una función ya
 * establecida. Al ingresar datos de entrada iniciales y finales, ademas de un
 * error que se usara para aproximarse a la respuesta. Este nos ayuda a mejorar
 * la velocidad al momento de querer encontrar raices, ya que es muy rapido y
 * facil hacerlo funcionar. Nos brinda todos los datos con los que opera para
 * dar el resultado así como las iteraciones que necesito para dar con dicha
 * respuesta.
 */

/*
Pasos a seguir en el programa.
1.- Escójanse dos valores iniciales
xi y xf de forma que tal que la
función cambie de signo en el
intervalo.
2.- Con los datos ingresados se trabajara en la proxima aproximacion
3.- Se comparara los resultados para obtener un resultado 
4.- Se calcula una nueva aproximación a la raíz.
5.- Decídase si la nueva aproximación es tan exacta como se
desea. Si es así los cálculos terminan, de otra manera
regrese al paso 3.
 */
/**
 * Clase donde se mandaran a llamar los métodos así como se registraran los
 * datos.
 *
 * @author CarlosValenzuela
 */
public class Secante {

    /**
     * Método main de la clase Secante para dar ejecución al programa
     *
     * @param args Argumentos del parametro
     */
    public static void main(String[] args) {
        //Declaracion del método Scanner
        Scanner teclado = new Scanner(System.in);

        //Variables para los datos de entrada
        double valorInicial = 0.0, valorFinal = 0.0, errorAprox = 0.0;

        //Funcionalidad del programa.
        System.out.println("El programa Secante ayuda a encontrar las raices de una función ya\n"
                + "establecida. Al ingresar datos de entrada iniciales y finales, ademas de un\n"
                + "error que se usara para aproximarse a la respuesta. Este nos ayuda a mejorar\n"
                + "la velocidad al momento de querer encontrar raices, ya que es muy rapido y\n"
                + "facil hacerlo funcionar. Nos brinda todos los datos con los que opera para\n"
                + "dar el resultado así como las iteraciones que necesito para dar con dicha\n"
                + "respuesta.");

        System.out.println("\n F(x) (0.8 - 0.3 * x) / x");

        //Solicitud de datos
        System.out.println("\nSolicitud de los datos de entrada");
        System.out.print("Ingrese el valor inicial: ");
        valorInicial = teclado.nextDouble();

        System.out.print("Ingrese el valor final: ");
        valorFinal = teclado.nextDouble();

        System.out.print("Ingrese el error aproximado máximo: ");
        errorAprox = teclado.nextDouble();

        //Llamada al método de secante
        Secante main = new Secante();
        main.secante(valorInicial, valorFinal, errorAprox);
    }

    /**
     * Método que hara el proceso de cambio de variables mediante el uso de otro
     * método para calcular la ordenada de Y
     *
     * @param valorInicial Valor X izquierdo
     * @param valorFinal Valor X derecho
     * @param errorAproxMax Error aproximado
     */
    public void secante(double valorInicial, double valorFinal, double errorAproxMax) {

        //Declaración de variables
        int iteraciones = 1;
        double funcionXi = 0.0, funcionXf = 0.0, funcionXr = 0.0, xR = 0.0, errorAprox = 0.0;
        boolean continuar = true;

        System.out.println("\n\n \t\t\t\t\t Tabla de valores ");
        System.out.print("\n\nIteración     x1\t   x2\t\t  f(x1)\t\tf(x2)\t\txr\t\tea");
        //Ciclo para saber iteraciones, así como para proseguir con los pasos del algoritmo

        while (continuar) {
            funcionXf = f(valorFinal);
            funcionXi = f(valorInicial);
            xR = valorInicial - ((valorFinal - valorInicial) / (funcionXf - funcionXi)) * funcionXi;
            funcionXr = f(xR);

            //Imprimir sin ea
            if (iteraciones == 1) {
                imprimirValores2(iteraciones, valorInicial, valorFinal, funcionXi, funcionXf, xR);
                iteraciones++;
            }

            //Haga x1 = x2 y x2 = xr
            valorInicial = valorFinal;
            valorFinal = xR;
            xR = valorInicial - ((valorFinal - valorInicial) / (funcionXf - funcionXi)) * funcionXi;
            funcionXr = f(xR);

            errorAprox = ((valorFinal - valorInicial) / valorFinal) * 100;

            //Si el error aproximado es menor al error aproximado maximo, se finaliza el programa y se despliega la tabla.
            if (errorAproxMax > Math.abs(errorAprox)) {
                imprimirValores(iteraciones, valorInicial, valorFinal, funcionXi, funcionXf, xR, errorAprox);
                break;
            }
            if (errorAproxMax > Math.abs(funcionXr)) {
                imprimirValores(iteraciones, valorInicial, valorFinal, funcionXi, funcionXf, xR, errorAprox);
                break;
            }

            //Reasignar valores
            funcionXf = f(valorFinal);
            funcionXi = f(valorInicial);
            xR = valorInicial - ((valorFinal - valorInicial) / (funcionXf - funcionXi)) * funcionXi;

            //imprimir valores
            imprimirValores(iteraciones, valorInicial, valorFinal, funcionXi, funcionXf, xR, errorAprox);

            //Sumar al contador
            iteraciones++;

        }

        System.out.println("\n\nResultados obtenidos: ");
        System.out.println("Número de iteraciones requeridas para encontrar raíz: " + (iteraciones - 1));
        System.out.printf("Valor de la raíz: %.6f \n", xR);
        System.out.printf("Valor de la función para la raíz: %.6f \n", funcionXr);

    }

    /**
     * Método que regresa la ordenada de Y recibiendo X como parametro
     *
     * @param x Valor al que se le sacara la ordenada
     * @return Valor de la función
     */
    public double f(double x) {
        double funcion = 0.0;

        funcion = (2 * Math.sin(x)) - x;
        return funcion;

    }

    /**
     * Método con el cual se imprimiran los valores según un formtado
     * establecido.
     *
     * @param iteraciones Número de iteraciones necesarias para el problema
     * @param valor1 Valor inicial o valor primario
     * @param valor2 Valor secundario
     * @param fx1 Funcion del valor inicial o bien valor1
     * @param fx2 Funcion del valor secundario o bien valor2
     * @param xr Resultado obtenido del uso de la formula con los valores
     * anteriores
     * @param ea Error aproximado maximo
     */
    public void imprimirValores(int iteraciones, double valor1, double valor2, double fx1, double fx2, double xr, double ea) {
        System.out.printf("\n%d     \t    %8.6f    %10.6f    %10.6f    %10.6f    %10.6f      %10.6f", iteraciones, valor1,
                valor2, fx1, fx2, xr, ea);

    }

    /**
     * Método con el cual se imprimiran los valores según un formtado
     * establecido.
     *
     * @param iteraciones Número de iteraciones necesarias para el problema
     * @param valor1 Valor inicial o valor primario
     * @param valor2 Valor secundario
     * @param fx1 Funcion del valor inicial o bien valor1
     * @param fx2 Funcion del valor secundario o bien valor2
     * @param xr Resultado obtenido del uso de la formula con los valores
     * anteriores
     */
    public void imprimirValores2(int iteraciones, double valor1, double valor2, double fx1, double fx2, double xr) {
        System.out.printf("\n%d     \t    %8.6f    %10.6f    %10.6f    %10.6f    %10.6f ", iteraciones, valor1,
                valor2, fx1, fx2, xr);

    }
}
