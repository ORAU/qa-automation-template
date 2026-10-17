package automatizacion;
public class TC001CondicionalesEnJava {
    public static void main (String[] args){
    //Declarar Variables
    String nombre="Orlando";
    int edad= 35;
    boolean estudiante=true;
    //Mostrar variables en consola
    //System.out.println(x:"===DATOS DE VARIBALES===");
     System.out.println("Nombre: "+nombre);
     System.out.println("Edad: "+edad);
     System.out.println("Estudiante : "+estudiante);

   System.out.println("===CONDICIONALES===");
    if (edad>=18){
        System.out.println("Nombre : "+nombre +" es mayor de edad");
    }
    else
    {
         System.out.println("Nombre : "+nombre +" es menor de edad");
    }

    if(estudiante){
        System.out.println("Nombre : "+nombre +" es estudiante de este curso.");
    }
    else
    {
         System.out.println("Nombre : "+nombre +" NO es estudiante de este curso.");
    }

    }

   

}