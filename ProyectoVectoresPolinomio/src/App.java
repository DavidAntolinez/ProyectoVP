import java.util.Scanner;

public class App {
	public static void main(String[] args) throws Exception {
		VPF1 Prueba = new VPF1("9x^6-23x^5+3x^4-x^3+1");
		VPF2 Prueba2 = new VPF2("9x^6-23x^5+3x^4-x^3+1");
		Prueba.MostrarConsola();
		Scanner sc = new Scanner(System.in);
		int opc;
		do {
			System.out.println("********************************");
			System.out.println("   MENU");
			System.out.println(" Vectores Polinomios Formula 1:");
			System.out.println("1. (VPF1) Mostrar vector actual");
			System.out.println("2. (VPF1) Redimensionar");
			System.out.println("3. (VPF1) Insertar Monomio");
			System.out.println("4. (VPF1) Eliminar");
			System.out.println("5. (VPF1) Suma de Polinomios");
			System.out.println("6. (VPF1) Multiplicar Polinomios");
			System.out.println("7. (VPF1) Reconstruir");
			System.out.println("********************************");
			System.out.println(" Vectores Polinomios Formula 2:");
			System.out.println("8. (VPF2) Mostrar vector actual");
			System.out.println("9. (VPF2) Redimensionar");
			System.out.println("10. (VPF2) Insertar Monomio");
			System.out.println("11. (VPF2) Eliminar");
			System.out.println("12. (VPF2) Suma de Polinomios");
			System.out.println("13. (VPF2) Multiplicar Polinomios");
			System.out.println("14. (VPF2) Reconstruir");
			System.out.println("*******************************");
			System.out.println("0. Salir del Programa");
			System.out.println("*******************************");
			opc = sc.nextInt();
			switch (opc) {
				case 0:
					System.out.println("Saliendo del Menu...");
					return;

				case 1:
					Prueba.MostrarConsola();
					break;

				case 2:
					Prueba.Redimensionar();
					break;

				case 3:
					Prueba.Insertar_Monomio();
					break;

				case 4:
					Prueba.Eliminar();
					break;

				case 5:
					Prueba.Suma_PolinomiosVPF1();
					break;

				case 6:
					Prueba.Multiplicar_PolinomiosVPF1();
					break;

				case 7:
					Prueba.ReconstruirVPF1();
					break;
				// VPF2
				case 8:
					Prueba2.MostrarConsola();
					break;

				case 9:
					Prueba2.Redimensionar();
					break;

				case 10:
					Prueba2.Insertar();
					break;

				case 11:

					break;

				case 12:
					Prueba2.Sumar();
					break;

				case 13:

					break;

				case 14:
					Prueba2.Reconstruir();
					break;

				default:
					System.out.println("OPCION NO VALIDA!!!");
					break;
			}

		} while (opc != 0);
		sc.close();
	}
}
