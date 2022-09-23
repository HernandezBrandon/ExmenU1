package controller;

public class BeanRFC {

    public BeanRFC(){

    }
    //MOVM980911 random
    //Miguel MOreno Velaszquez fechadenacimiento + 3numeros randoms;
    //4 caracteres del curp y 6 numeros de año fecha y dia
private String nombre,apellido1,apellido2,curp,fecha,rfc;
private int id;
public BeanRFC(int id,String nombre,String apellido1,String apellido2,String curp,String fecha,String rfc){
    this.id=id;
    this.nombre=nombre;
    this.apellido1=apellido1;
    this.apellido2=apellido2;
    this.curp=curp;
    this.fecha=fecha;
    this.rfc=rfc;
}

    public String getRfc() {
        return rfc;
    }

    public String setRfc(String rfc) {
        this.rfc = rfc;
        return rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public String setNombre(String nombre) {
        this.nombre = nombre;
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String setApellido1(String apellido1) {
        this.apellido1 = apellido1;
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String setApellido2(String apellido2) {
        this.apellido2 = apellido2;
        return apellido2;
    }

    public String getCurp() {
        return curp;
    }

    public String setCurp(String curp) {
        this.curp = curp;
        return curp;
    }

    public String getFecha() {
        return fecha;
    }

    public String setFecha(String fecha) {
        this.fecha = fecha;
        return fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
