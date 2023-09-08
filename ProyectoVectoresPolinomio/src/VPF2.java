import java.util.Scanner;

public class VPF2 {
    private int Vec[], Du;

    public VPF2(String cadena) {// no terminado
        Vec = CrearVectori(CrearVectorString(cadena));
        Du = Vec[0] * 2;
    }

    public VPF2() {

    }

    public void setVec(int[] vec) {
        Vec = vec;
    }

    public void setDu(int du) {
        Du = du;
    }

    public int[] getVec() {
        return Vec;
    }

    public int getDu() {
        return Du;
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

    public int[] CrearVectori(String Vs[]) {// no terminado
        int Mayor = 0, cont = 0;
        int pos = 0;
        for (int i = 1; i < Vs.length && !(Vs[i] == null); i += 2) {
            if (Mayor < Integer.parseInt(Vs[i])) {
                Mayor = Integer.parseInt(Vs[i]);
                pos = i;
            }
            cont++;
        }
        int[] Vi = new int[(cont) * 2 + 1];
        Vi[0] = cont;
        Boolean bandera = true;
        for (int j = 1; j < Vs.length && !(Vs[j] == null); j += 2) {
            if (bandera) {
                Vi[j] = Integer.parseInt(Vs[pos - 1]);
                Vi[j + 1] = Integer.parseInt(Vs[pos]);
                bandera = false;
            } else {
                j -= 2;
            }
            Mayor--;
            for (int i = 1; i < Vs.length && !(Vs[i] == null) && !bandera; i += 2) {

                if (Mayor == Integer.parseInt(Vs[i])) {
                    Mayor = Integer.parseInt(Vs[i]);
                    pos = i;
                    bandera = true;
                } else {
                    bandera = false;
                }
            }
        }
        return Vi;
    }

    public void MostrarConsola() {
        for (int i = 0; i < Vec.length; i++) {
            System.out.print("[" + Vec[i] + "]");
        }
        System.out.println("");
    }

    public void Redimensionar() {
        for (int i = 1; i <= Du; i += 2) {
            if (Vec[i] == 0) {
                Du -= 2;
                Vec[0] -= 1;
                for (int j = i; j <= Du; j++) {
                    Vec[j] = Vec[j + 2];
                }
            }
        }

        if (!(Du + 1 == Vec.length)) {
            int[] nuevo = new int[Du + 1];
            for (int i = 0; i < nuevo.length; i++) {
                nuevo[i] = Vec[i];
            }
            Vec = nuevo;
        }
    }

    public void Sumar() {
        Scanner Basura = new Scanner(System.in);
        System.out.println("Ingresa el polinomio a sumar: ");
        String cadena = Basura.nextLine();
        Basura.close();
        VPF2 B = new VPF2(cadena);
        VPF2 C = new VPF2();
        C.Vec = new int[((Vec[0] + B.Vec[0]) * 2) + 1];
        int i = 2, j = 2, k = 1;
        while (i <= Du || j <= B.Du) {
            if ((i <= Du && j <= B.Du) && (Vec[i] == B.Vec[j])) {
                C.Vec[k] = Vec[i - 1] + B.Vec[j - 1];
                C.Vec[k + 1] = Vec[i];
                k += 2;
                i += 2;
                j += 2;
            } else if ((j > B.Du) || (Vec[i] > B.Vec[j])) {
                C.Vec[k] = Vec[i - 1];
                C.Vec[k + 1] = Vec[i];
                k += 2;
                i += 2;
            } else if ((i > Du) || (B.Vec[j] > Vec[i])) {
                C.Vec[k] = B.Vec[j - 1];
                C.Vec[k + 1] = B.Vec[j];
                k += 2;
                j += 2;
            }
        }
        C.Vec[0] = (k - 1) / 2;
        C.Du = C.Vec[0] * 2;
        C.Redimensionar();
        Vec = C.Vec;
        MostrarConsola();
    }

    public void Insertar() {
        Scanner Basura = new Scanner(System.in);
        System.out.println("Ingresa el monomio a insertar: ");
        String cadena = Basura.nextLine();
        Basura.close();
        VPF2 B = new VPF2(cadena);
        if (B.Vec[2] > Vec[2]) {
            int[] nuevo = new int[(Vec[0] + 1) * 2 + 1];
            nuevo[0] = Vec[0] + 1;
            nuevo[1] = B.Vec[1];
            nuevo[2] = B.Vec[2];
            int k = 3;
            for (int i = 1; i <= Du; i++) {
                nuevo[k] = Vec[i];
                k++;
            }
            Vec = nuevo;
            Du = Vec[0] * 2;
        } else if (B.Vec[2] < Vec[Vec.length - 1]) {
            int[] nuevo = new int[(Vec[0] + 1) * 2 + 1];
            nuevo[0] = Vec[0] + 1;
            int i = 1;
            for (; i <= Du; i++) {
                nuevo[i] = Vec[i];
            }
            nuevo[i] = B.Vec[1];
            nuevo[i + 1] = B.Vec[2];
            Vec = nuevo;
            Du = Vec[0] * 2;
        } else {
            Boolean banderaigual = false;
            for (int i = 2; i < Vec.length; i += 2) {
                if (B.Vec[2] == Vec[i]) {
                    Vec[i - 1] += B.Vec[1];
                    banderaigual = true;
                    Redimensionar();
                }
            }
            if (!banderaigual) {
                int[] nuevo = new int[(Vec[0] + 1) * 2 + 1];
                nuevo[0] = Vec[0] + 1;
                int i = 2;
                for (; i < Vec.length && B.Vec[2] < Vec[i]; i += 2) {
                }
                for (int j = 1; j < nuevo.length; j++) {
                    if(j<i){
                        nuevo[j] = Vec[j];
                    }else if(j==i){
                        nuevo[j-1] = B.Vec[1];
                        nuevo[j] = B.Vec[2];
                    }else {
                        nuevo[j] = Vec[j-2];
                    }
                }
                Vec = nuevo;
                Du = Vec[0] * 2;
            }
        }
        MostrarConsola();
    }
}
