package Server;

public class Methods {
public String RFC(String apellido1,String apellido2,String nombre,String fecha,String numeros2){
    String nom=nombre.substring(0,1);
    String ap1=apellido1.substring(0,2);
    String ap2=apellido2.substring(0,1);

    String rfc=ap1.concat(ap2);
    String rfc2=rfc.concat(nom);
    String rfc3=rfc2.concat(fecha);
    String rfc4=rfc3.concat(numeros2);

    return rfc4;
}
}
