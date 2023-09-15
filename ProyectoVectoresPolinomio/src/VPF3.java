import java.util.Scanner;

public class VPF3 {
    private Nodo Punta;

    public VPF3(String cadena) {
        String[] Vs = CrearVectorString(cadena);
        Punta = null;
        for (int i = 0; i < Vs.length && Vs[i] != null; i += 2) {
            InsertarDescendente(Integer.parseInt(Vs[i]), Integer.parseInt(Vs[i + 1]));
        }
    }

    public String[] CrearVectorString(String Cadena) {
        String S = "";
        int J = 0;

        Cadena.toLowerCase();

        char Vc[] = Cadena.toCharArray();
        String Vs[] = new String[Vc.length + 1];

        for (int i = 0; i < Vc.length; i++) {
            if (Character.isDigit(Vc[i])) {
                S += Character.toString(Vc[i]);
                if (i == Vc.length - 1) {
                    Vs[J] = S;
                    Vs[J + 1] = "0";
                }
            } else if (Vc[i] == '-' || Vc[i] == '+') {
                if (S.equals("")) {
                    if (Vc[i] == '-') {
                        S += Character.toString(Vc[i]);
                    }
                } else {
                    Vs[J] = S;
                    Vs[J + 1] = "0";
                    J += 2;
                    S = "";
                    if (Vc[i] == '-') {
                        S += Character.toString(Vc[i]);
                    }
                }
            } else if (Vc[i] == 'x') {
                if (S.equals("") || Vc[i - 1] == '-') {
                    S += "1";
                    Vs[J] = S;
                } else {
                    Vs[J] = S;
                }
                J++;
                S = "";
                if (i != Vc.length - 1 && Vc[i + 1] == '^') {
                    Vs[J] = Character.toString(Vc[i + 2]);
                    i += 2;
                    J++;
                } else {
                    Vs[J] = "1";
                    J++;
                }

            }
        }

        return Vs;
    }

    public void InsertarDescendente(int d, int c) {
        Nodo p = Punta, /* a, */x = new Nodo(d, c), q = Punta;
        if (Punta == null) {
            Punta = x;
            Punta.setLiga(null);
        } else {
            if (x.getGrado() < p.getGrado()) {
                do {
                    p = p.getLiga();
                } while (p != null && x.getGrado() < p.getGrado());
                if (p != null) {
                    x.setLiga(p);
                    while (q.getLiga() != p) {
                        q = q.getLiga();
                    }
                    q.setLiga(x);
                } else {
                    while (q.getLiga() != p) {
                        q = q.getLiga();
                    }
                    q.setLiga(x);
                }
            } else {
                x.setLiga(Punta);
                Punta = x;
            }
        }
    }

    public void Mostrar() {
        Nodo x = Punta;
        do {
            if (x.getLiga() == null) {
                System.out.print("[" + x.getCoeficiente() + "][" + x.getGrado() + "]\n");
            } else {
                System.out.print("[" + x.getCoeficiente() + "][" + x.getGrado() + "]-->");
            }
            x = x.getLiga();
        } while (x != null);
    }

    public void Insertar() {
        Scanner Basura = new Scanner(System.in);
        System.out.println("Ingresa el monomio a insertar: ");
        String cadena = Basura.nextLine();

        VPF3 Monomio = new VPF3(cadena);
        Nodo x = Punta;
        while (x != null && x.getGrado() != Monomio.Punta.getGrado()) {
            x = x.getLiga();
        }
        if (x == null) {
            InsertarDescendente(Monomio.Punta.getCoeficiente(), Monomio.Punta.getGrado());
        } else {
            x.setCoeficiente(x.getCoeficiente() + Monomio.Punta.getCoeficiente());
        }
        Redimensionar();
        Mostrar();
    }

    public void Redimensionar() {
        Nodo x = Punta, q = Punta;
        while (x != null) {
            if (x.getCoeficiente() == 0) {
                if (x == Punta) {
                    Punta = x.getLiga();
                    x.setLiga(null);
                } else {
                    q = Punta;
                    while (q.getLiga() != x) {
                        q = q.getLiga();
                    }
                    q.setLiga(x.getLiga());
                    x.setLiga(null);
                }
            }
            if(x.getLiga() != null && x.getGrado() == x.getLiga().getGrado()){
                if (x == Punta) {
                    x.setCoeficiente(x.getCoeficiente()+x.getLiga().getCoeficiente());
                } else {
                    q = Punta;
                    while (q.getLiga() != x) {
                        q = q.getLiga();
                    }
                    x.setCoeficiente(x.getCoeficiente()+x.getLiga().getCoeficiente());
                }
            }
            x = x.getLiga();
        }
    }

    public void Eliminar() {
        Scanner Basura = new Scanner(System.in);
        System.out.println("Ingresa el monomio a eliminar: ");
        String cadena = Basura.nextLine();

        VPF3 Monomio = new VPF3(cadena);
        Nodo x = Punta;
        while (x != null
                && (x.getGrado() != Monomio.Punta.getGrado() || x.getCoeficiente() != Monomio.Punta.getCoeficiente())) {
            x = x.getLiga();
        }
        if (x == null) {
            System.out.println("No se encuentra el monomio a eliminar");
        } else {
            x.setCoeficiente(-x.getCoeficiente() + Monomio.Punta.getCoeficiente());
            Redimensionar();
            Mostrar();
        }
    }

    public void Reconstruir() {
        String cadena = "";
        Nodo x = Punta;
        while (x != null) {
            int coe = x.getCoeficiente();
            int gra = x.getGrado();
            if (x != Punta && coe > 0) {
                cadena += "+";
            }
            if (coe != 1 && coe != -1 || gra == 0) {
                cadena += coe;
            } else if (coe == -1) {
                cadena += "-";
            }
            if (gra != 0) {
                cadena += "x";
                if (gra != 1) {
                    cadena += "^" + gra;
                }
            }
            x = x.getLiga();
        }
        System.out.println(cadena);
    }

    public void Evaluar() {
        Nodo x = Punta;
        Scanner Basura = new Scanner(System.in);
        System.out.println("Ingresa el valor a evaluar: ");
        int cadena = Basura.nextInt();

        int resultado = 0;
        while (x != null) {
            resultado += x.getCoeficiente() * (Math.pow(cadena, x.getGrado()));
            x = x.getLiga();
        }
        System.out.println(resultado);
    }

    public void Sumar() {
        Scanner Basura = new Scanner(System.in);
        System.out.println("Ingresa el polinomio a sumar: ");
        String cadena = Basura.nextLine();
        VPF3 polinomio = new VPF3(cadena), resultado = new VPF3();
        Nodo x = Punta, q = polinomio.Punta;
        while (x != null || q != null) {
            if (x != null && q != null) {
                if (x.getGrado() > q.getGrado()) {
                    resultado.InsertarDescendente(x.getCoeficiente(), x.getGrado());
                    x = x.getLiga();
                } else if (x.getGrado() < q.getGrado()) {
                    resultado.InsertarDescendente(q.getCoeficiente(), q.getGrado());
                    q = q.getLiga();
                } else if (x.getGrado() == q.getGrado()) {
                    resultado.InsertarDescendente((q.getCoeficiente() + x.getCoeficiente()), q.getGrado());
                    q = q.getLiga();
                    x = x.getLiga();
                }
            } else if (x != null) {
                resultado.InsertarDescendente(x.getCoeficiente(), x.getGrado());
                x = x.getLiga();
            } else {
                resultado.InsertarDescendente(q.getCoeficiente(), q.getGrado());
                q = q.getLiga();
            }
        }
        resultado.Redimensionar();
        Punta = resultado.Punta;
        Mostrar();
    }

    public VPF3() {
        Punta = null;
    }

    public void Multiplicar(){
	    Scanner Basura = new Scanner(System.in);
        System.out.println("Ingresa el polinomio a multiplicar: ");
        String cadena = Basura.nextLine();
        VPF3 B = new VPF3(cadena);
        Nodo p = Punta;
        
        Nodo q = B.Punta;
        
        Nodo  aux ;
        
        int coe =0;
        int grado = 0;
        
        VPF3 R = new VPF3();
        
        while(q!=null){
            
            aux = p;
            
            while(p!=null){
            
                grado = p.getGrado()+q.getGrado();
                coe = p.getCoeficiente()*q.getCoeficiente();
                R.InsertarDescendente(coe, grado);
                p = p.getLiga();
                
            }
           
            p = aux;
            
            q = q.getLiga();

        }
        R.Redimensionar();
        Punta = R.Punta;
        Mostrar();
    }
}
