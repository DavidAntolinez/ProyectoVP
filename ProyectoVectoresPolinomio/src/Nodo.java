public class Nodo {
    private int Coeficiente;
    private int Grado;
    private Nodo Liga;

    public Nodo(int coeficiente, int grado) {
        Coeficiente = coeficiente;
        Grado = grado;
        Liga = null;
    }

    public int getCoeficiente() {
        return Coeficiente;
    }

    public void setCoeficiente(int coeficiente) {
        Coeficiente = coeficiente;
    }

    public int getGrado() {
        return Grado;
    }

    public void setGrado(int grado) {
        Grado = grado;
    }

    public Nodo getLiga() {
        return Liga;
    }

    public void setLiga(Nodo liga) {
        Liga = liga;
    }

    
    
}
