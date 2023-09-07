import java.util.Scanner;

import javax.swing.JOptionPane;

public class VPF1 {
    private int Vec[];
    private int Du;

    public VPF1(String Cadena) {
        Vec = CrearVectori(CrearVectorString(Cadena));

        Du = Vec[0] + 1;
    }

    public int[] getVec() {
        return Vec;
    }

    public void setVec(int[] vec) {
        Vec = vec;
    }

    public int getDu() {
        return Du;
    }

    public void setDu(int du) {
        Du = du;
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

    public void MayorCoeficiente(String[] Vec, int du) {// Cuando Du este, cambiar
        int Mayor = 0;
        for (int i = 1; i <= du; i++) {// Cuando Du este, cambiar
            if (Integer.parseInt(Vec[i]) > Mayor) {
                Mayor = Integer.parseInt(Vec[i]);
            }
        }
        for (int i = 1; i <= du; i++) {
            if (Integer.parseInt(Vec[i]) == Mayor) {
                int Exp = du - i;
                System.out.println("El exponente " + Exp + " tiene el exponente mayor");
            }
        }
    }

    public void MostrarConsola() {
        for (int i = 0; i < Vec.length; i++) {
            System.out.print("[" + Vec[i] + "]");
        }
        System.out.println("");
    }

    public void Redimensionar() {
        int i = 1, c = 0;
        while (i <= Du && Vec[i] == 0) {
            c++;
            i++;
        }
        Vec[0] = Du - 1 - c;
        for (; i <= Du; i++) {
            Vec[i - c] = Vec[i];
        }
        Du = Vec[0] + 1;
        int[] Vi = new int[Du + 1];
        for (int j = 0; j <= Du; j++) {
            Vi[j] = Vec[j];
        }
        Vec = Vi;
    }

    public int[] Redimensionar(int Vec[]) {
        int i = 1, c = 0;
        int Du = Vec[0] + 1;
        while (i <= Du && Vec[i] == 0) {
            c++;
            i++;
        }
        Vec[0] = Du - 1 - c;
        for (; i <= Du; i++) {
            Vec[i - c] = Vec[i];
        }
        Du = Vec[0] + 1;
        int[] Vi = new int[Du + 1];
        for (int j = 0; j <= Du; j++) {
            Vi[j] = Vec[j];
        }
        Vec = Vi;
        return Vec;
    }

    public void Evaluar() {
        System.out.println("Ingrese el valor a evaluar:");
        Scanner Basura = new Scanner(System.in);
        int x = Basura.nextInt();
        int s = 0;
        int exp = Du - 1;
        for (int i = 1; i <= Du; i++) {
            s += (Vec[i] * ((Math.pow(x, exp))));
            exp--;
        }
        System.out.println(s);
    }

    public int[] CrearVectori(String[] Vs) {
        int Mayor = 0;
        for (int i = 1; i < Vs.length && !(Vs[i] == null); i += 2) {
            if (Mayor < Integer.parseInt(Vs[i])) {
                Mayor = Integer.parseInt(Vs[i]);
            }
        }
        int du = Mayor + 1;
        int[] Vi = new int[du + 1];
        for (int i = 1; i < Vs.length && !(Vs[i] == null); i += 2) {
            int Pos = du - Integer.parseInt(Vs[i]);
            Vi[Pos] = Integer.parseInt(Vs[i - 1]);
        }
        Vi[0] = Mayor;
        Vi = this.Redimensionar(Vi);
        return Vi;
    }

    public void Insertar_Monomio() {
        Scanner Basura = new Scanner(System.in);
        System.out.println("Ingresa el monomio a insertar: ");
        String cadena = Basura.nextLine();
        String[] Vs = CrearVectorString(cadena);
        int[] Vi = CrearVectori(Vs);

        int exp_N = Vi[0];
        int exp_vec = Vec[0];
        if (exp_N > exp_vec) {
            for (int i = 1; i < Du + 1; i++) {
                int Pos_G = Vi.length - 1 - exp_vec;
                Vi[Pos_G] = Vec[i];
                exp_vec--;
            }
            Vec = Vi;
            Du = Vec[0] + 2;

        } else if (exp_N < exp_vec) {
            int Pos_G = Du - exp_N;
            Vec[Pos_G] += Vi[1];
        } else {
            Vec[1] += Vi[1];
            if (Vec[1] == 0) {
                Redimensionar();
            }
        }
        MostrarConsola();
    }

    public void Eliminar() {
        Scanner Basura = new Scanner(System.in);
        System.out.println("Ingresa el monomio a eliminar: ");
        String cadena = Basura.nextLine();
        VPF1 Vi = new VPF1(cadena);
        Boolean bandera = true;
        if (Vec[0] >= Vi.Vec[0]) {
            int pos = Du - Vi.Vec[0];
            if (Vec[pos] == Vi.Vec[1]) {
                Vec[pos] = 0;
                Redimensionar();
                MostrarConsola();
            } else {
                bandera = false;
            }
        } else {
            bandera = false;
        }
        if (!bandera) {
            System.out.println("El valor no se a encontrado");
        }
    }

    public void Suma_PolinomiosVPF1() {
        Scanner Basura = new Scanner(System.in);
        System.out.println("Ingresa el polinomio a sumar: ");
        String cadena = Basura.nextLine();
        VPF1 Vi = new VPF1(cadena);

        int i = 1, j = 1, expA = Vec[0], expB = Vi.Vec[0];
        int Mayor;
        if (expA > expB) {
            Mayor = expA;
        } else {
            Mayor = expB;
        }
        int[] C = new int[Mayor + 2];
        C[0] = Mayor;
        int k = 1;
        while (i < Vec.length && j < Vi.Vec.length) {

            if (expA > expB) {
                C[k] = Vec[i];
                i++;
            } else if (expB > expA) {
                C[k] = Vi.Vec[j];
                j++;
            } else {
                C[k] = Vec[i] + Vi.Vec[j];
                i++;
                j++;
            }
            expA = Du - i;
            expB = Vi.Vec[0] + 1 - j;
            k++;
        }
        C = Redimensionar(C);
        Vec = C;
        Du = Vec[0] + 1;
        MostrarConsola();
    }

    public void Multiplicar_PolinomiosVPF1() {
        Scanner Basura = new Scanner(System.in);
        System.out.println("Ingresa el polinomio a sumar: ");
        String cadena = Basura.nextLine();
        VPF1 Vi = new VPF1(cadena);
        int[] A, B;
        if (Du > Vi.Du) {
            A = Vec;
            B = Vi.Vec;
        } else {
            B = Vec;
            A = Vi.Vec;
        }
        int C[] = new int[A[0] + B[0] + 2];
        C[0] = A[0] + B[0];
        for (int j = 1; j < B.length; j++) {
            if (B[j] != 0) {
                for (int i = 1; i < A.length; i++) {
                    if (A[i] != 0) {
                        C[C[0] + 1 - ((A[0] + 1 - i) + (B[0] + 1 - j))] += A[i] * B[j];
                    }
                }
            }
        }
        C = Redimensionar(C);
        Vec = C;
        Du = Vec[0] + 1;
        MostrarConsola();
    }

    public void ReconstruirVPF1() {
        int exp;
        String cadena = "";
        for (int i = 1; i < Vec.length; i++) {
            if (Vec[i] != 0) {
                if (Vec[i] > 0 && !(cadena.equals(""))) {
                    cadena += "+";
                }
                switch (Vec[i]) {
                    case -1:
                        cadena += "-";
                        break;
                    case 1:
                        break;
                    default:
                        cadena += Integer.toString(Vec[i]);
                        break;
                }
                exp = Du - i;
                switch (exp) {
                    case 0:
                        break;
                    case 1:
                        cadena += "x";
                        break;
                    default:
                        cadena += "x^" + Integer.toString(exp);
                        break;
                }
            }
        }
        System.out.println(cadena);
    }
}
