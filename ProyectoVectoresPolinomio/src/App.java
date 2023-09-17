import java.util.Scanner;

public class App {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int opc,b;
		String Polinomio;
		System.out.println("Ingrese por favor el valor del polinomio");
		Polinomio = sc.nextLine();

		VPF1 Prueba = new VPF1(Polinomio);
		VPF2 Prueba2 = new VPF2(Polinomio);
		VPF3 Prueba3 = new VPF3(Polinomio);
		do {
			System.out.println("Ingrese sobre que nivel de polinomio desea trabajar (1, 2, 3): ");
			b= sc.nextInt();
			if(b==1){
				System.out.println("********************************");
				System.out.println("   MENU");
				System.out.println(" VECTORES POLINOMIOS FORMA 1:");
				System.out.println("1. (VPF1) Mostrar vector actual");
				System.out.println("2. (VPF1) Redimensionar");
				System.out.println("3. (VPF1) Insertar Monomio");
				System.out.println("4. (VPF1) Eliminar");
				System.out.println("5. (VPF1) Suma de Polinomios");
				System.out.println("6. (VPF1) Multiplicar Polinomios");
				System.out.println("7. (VPF1) Evaluar Polinomio");
				System.out.println("8. (VPF1) Reconstruir");
				System.out.println("*******************************");
				System.out.println("0. Salir del Programa");
			}
			else if(b==2){
				System.out.println("********************************");
				System.out.println(" VECTORES POLINOMIOS FORMA 2:");
				System.out.println("9. (VPF2) Mostrar vector actual");
				System.out.println("10. (VPF2) Redimensionar");
				System.out.println("11. (VPF2) Insertar Monomio");
				System.out.println("12. (VPF2) Eliminar");
				System.out.println("13. (VPF2) Suma de Polinomios");
				System.out.println("14. (VPF2) Multiplicar Polinomios");
				System.out.println("15. (VPF3) Evaluar Polinomio");
				System.out.println("16. (VPF2) Reconstruir");
				System.out.println("*******************************");
				System.out.println("0. Salir del Programa");
			}
			else if(b==3){
				System.out.println("*******************************");
				System.out.println(" VECTORES POLINOMIOS FORMA 3:");
				System.out.println("17. (VPF3) Mostrar vector actual");
				System.out.println("18. (VPF3) Redimensionar");
				System.out.println("19. (VPF3) Insertar Monomio");
				System.out.println("20. (VPF3) Eliminar");
				System.out.println("21. (VPF3) Suma de Polinomios");
				System.out.println("22. (VPF3) Multiplicar Polinomios");
				System.out.println("23. (VPF3) Evaluar Polinomio");
				System.out.println("24. (VPF3) Reconstruir");
				System.out.println("*******************************");
				System.out.println("0. Salir del Programa");
			}
			else{
				System.out.println("Opcion invalida!");
			}
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
					Prueba.Evaluar();
					break;

				case 8:
					Prueba.ReconstruirVPF1();
					break;
				// VPF2
				case 9:
					Prueba2.MostrarConsola();
					break;

				case 10:
					Prueba2.Redimensionar();
					break;

				case 11:
					Prueba2.Insertar();
					break;

				case 12:
					Prueba2.Eliminar();
					break;

				case 13:
					Prueba2.Sumar();
					break;

				case 14:
					Prueba2.Multiplicar();
					break;

				case 15:
					Prueba2.Evaluar();
					break;

				case 16:
					Prueba2.Reconstruir();
					break;
				//FORMA 3
				case 17:
					Prueba3.Mostrar();
					break;
					
				case 18:
					Prueba3.Redimensionar();
					break;

				case 19:
					Prueba3.Insertar();
					break;

				case 20:
					Prueba3.Eliminar();
					break;

				case 21:
					Prueba3.Sumar();
					break;

				case 22:
					Prueba3.Multiplicar();
					break;

				case 23:
					Prueba3.Evaluar();
					break;

				case 24:
					Prueba3.Reconstruir();
					break;

				default:
					System.out.println("OPCION NO VALIDA!!!");
					break;
			}

		} while (opc != 0);
		sc.close();
	}
}
