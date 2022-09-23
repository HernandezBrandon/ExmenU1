package Cliente;

import Server.Methods;
import controller.BeanRFC;
import controller.DaoRFC;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cliente {
    public Cliente(){

    }
    private static Scanner leer = new Scanner(System.in);

    public static void main(String[] args) throws MalformedURLException, SQLException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://localhost:1200"));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        DaoRFC dao = new DaoRFC();
        Methods met = new Methods();
        int opc=0,id=0;
        String nombre,apellido1,apellido2,curp,fecha,rfc,rfc2;
        do{
            Random ran= new Random();
            int ran1= ran.nextInt(9);
            String num1=ran1+"";

            int num=ran.nextInt(26);
            char letra=(char)(((int)'A')+num);
            String letra2=letra+"";

            int ran3=ran.nextInt(9);
            String num2=ran3+"";

            String numeros=num1.concat(letra2);
            String numeros2=numeros.concat(num2);

            System.out.println("Ingresa una opcion \n1.-Insertar \n2.-Modificar \n3.-Consultar \n4.-Eliminar \n5.-Salir");
            opc=leer.nextInt();
            switch (opc){
                case 1:
                    System.out.println("Ingresa el nombre");
                    nombre=leer.next();
                    System.out.println("Ingresa el primer apellido");
                    apellido1= leer.next();
                    System.out.println("Ingresa el segundo apellido");
                    apellido2=leer.next();
                    System.out.println("Ingresa el curp");
                    curp= leer.next();
                    System.out.println("Ingresa la fecha de nacimiento");
                    fecha= leer.next();

                    rfc=met.RFC(apellido1,apellido2,nombre,fecha,numeros2);
                    System.out.println("RFC = "+rfc);
                    dao.insertar(id,nombre,apellido1,apellido2,curp,fecha,rfc);
                    break;
                case 2:
                    System.out.println("Ingresa el RFC a modificar");
                    rfc2=leer.next();
                    if(dao.mostrarRFC(rfc2)){
                        System.out.println("Ingresa el nuevo nombre");
                        nombre=leer.next();
                        System.out.println("Ingresa el nuevo primer apellido");
                        apellido1= leer.next();
                        System.out.println("Ingresa el nuevo segundo apellido");
                        apellido2=leer.next();
                        System.out.println("Ingresa el nuevo curp");
                        curp= leer.next();
                        System.out.println("Ingresa la nueva fecha de nacimiento");
                        fecha= leer.next();

                        rfc=met.RFC(apellido1,apellido2,nombre,fecha,numeros2);
                        System.out.println("RFC nuevo= "+rfc);
                        dao.modificar(nombre,apellido1,apellido2,curp,fecha,rfc,rfc2);

                    }else {
                        System.out.println("RFC no encontrado");
                    }
                    break;
                case 3:
                    System.out.println("Ingresa el RFC");
                    rfc= leer.next();
                    System.out.println(dao.consultar(rfc));
                    break;
                case 4:
                    System.out.println("Ingresa el RFC a eliminar");
                    rfc2=leer.next();
                    if(dao.mostrarRFC(rfc2)){
                        dao.elminar(rfc2);
                        System.out.println("RFC: "+rfc2+" eliminado");
                    }else {
                        System.out.println("RFC no encontrado");
                    }

                    break;
                case 5:
                    System.out.println("Adios");
                    break;
                default:
                    System.out.println("Ingresa una opcion valida");
            }

        }while (opc!=5);

    }
}
