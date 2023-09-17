import java.util.Scanner;

public class VPF2 {
    private int Vec[], Du;
    Scanner Basura = new Scanner(System.in);
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
        int pos = 1;

            
        
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

    public void Evaluar() {
        System.out.println("Ingrese el valor a evaluar:");
        int j=2;
        int x = Basura.nextInt();
        int s = 0;
        for (int i = 1; i < Vec.length; i+=2) {
            s+= (Vec[i] * ((Math.pow(x, Vec[j]))));
            j+=2;
        }

        System.out.println("El valor del Polinomio es: "+s);
    }

    public void Eliminar() {
        System.out.println("Ingresa el monomio a eliminar: ");
        String monomio = Basura.nextLine();
        VPF2 Vi = new VPF2(monomio);
        Boolean bandera = false;
        
        for(int i=2; i < Vec.length; i+=2){
            if(Vi.Vec[2]==Vec[i]){
                Vec[i]=0;
                Vec[i-1]=0;
                bandera=true;
            }
        }
        Redimensionar();
        MostrarConsola();

        if(bandera==false){
            System.out.println("El monomio ingresado no existe");
        }
    }



    public void Sumar() {
        System.out.println("Ingresa el polinomio a sumar: ");
        String cadena = Basura.nextLine();
        
        VPF2 B = new VPF2(cadena);
        VPF2 C = new VPF2();
        C.Vec = new int[((Vec[0] + B.Vec[0]) * 2) + 1];
        int i = 2, j = 2, k = 1;
        while (i <= Du || j <= B.Du) {
            if ((i <= Du && j <= B.Du)) {
                if((Vec[i] == B.Vec[j])){
                    C.Vec[k] = Vec[i - 1] + B.Vec[j - 1];
                C.Vec[k + 1] = Vec[i];
                k += 2;
                i += 2;
                j += 2;
                }else if((Vec[i] > B.Vec[j])){
                    C.Vec[k] = Vec[i - 1];
                C.Vec[k + 1] = Vec[i];
                k += 2;
                i += 2;
                }else if((B.Vec[j] > Vec[i])){
                    C.Vec[k] = B.Vec[j - 1];
                C.Vec[k + 1] = B.Vec[j];
                k += 2;
                j += 2;
                }
            } else if ((j > B.Du)) {
                C.Vec[k] = Vec[i - 1];
                C.Vec[k + 1] = Vec[i];
                k += 2;
                i += 2;
            } else if ((i > Du)) {
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


    public void Multiplicar() {
        System.out.println("Ingresa el polinomio a Multiplicar: ");
        String cadena = Basura.nextLine();
        
        VPF2 Vi = new VPF2(cadena);
        int[] A, B;

        if (Vec[0] > Vi.Vec[0]) {
            A = Vec;
            B = Vi.Vec;
        } else {
            A = Vi.Vec;
            B = Vec;
        }

        int C[] = new int[(A[0] + B[0])*2 + 1];
        C[0] = A[0] + B[0];
        int k=1, y,z;
        Boolean Bo=false;
        for (int j = 1; j < B.length; j+=2) {
                for (int i = 1; i < A.length; i+=2) {
                    Bo=false;
                    y=A[i+1] + B[j+1];
                    z=A[i] * B[j];
                    Bo=Juntar(C,y,z,Bo);

                    if(!Bo){
                        C[k]= z;
                        C[k+1]= y;
                        k+=2;
                    }    
            }
        }
        Vec=C;
        Du=(Vec[0]*2) + 1;
        Orden();
        MostrarConsola();
    }

    public Boolean Juntar(int C[], int y, int z, boolean B){

        for (int a=2; a < C.length; a+=2){
            if(C[a]==y){
                C[a-1]= C[a-1] + z;
                B=true;
            }
        }
        return B;
    }

    public void Orden(){
        for(int i=2; i< Vec.length;i+=2){
            for(int j=2; j < Vec.length-2;j+=2){
                if(Vec[j] < Vec[j+2]){
                    int aux;
                    aux = Vec[j];
                    Vec[j]= Vec[j+2];
                    Vec[j+2]=aux;
                }
            }
        }
    }


    public void Insertar() {
        System.out.println("Ingresa el monomio a insertar: ");
        String cadena = Basura.nextLine();
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

    public void Reconstruir(){
        String cadena = "";
        for (int i = 1; i < Vec.length; i+=2) {
            
                if(Vec[i] != 1 && Vec[i] != -1){
                    cadena += Vec[i];
                }else if(Vec[i] == -1){
                    if(i == Vec.length-2){
                        cadena += Vec[i];    
                    }else{
                        cadena += "-";
                    }
                }else if(Vec[i] == 1){
                    if(i == Vec.length-2){
                        cadena += Vec[i];    
                    }
                }
                if(Vec[i+1] == 1){
                    cadena += "x";
                }else if(Vec[i+1] > 1){
                    cadena += "x^"+Vec[i+1];
                }
                if(i+2 < Vec.length && Vec[i+2] > 0){
                    cadena += "+";
                }
            
        }
        System.out.println(cadena);
    }
}
