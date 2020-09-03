//package com.puebla.monitoralertas;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.function.BiFunction;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;
//
//public class Main {
//
//	@FunctionalInterface
//	interface Suma {
//		public Integer sumaDosNumeros(Integer a, Integer b);
//	}
//	
//	public void ordenar() {
//		List<String> lista = new ArrayList<>();
//		lista.add("Juan");
//		lista.add("Pedro");
//		lista.add("Maria");
//		lista.add("Alex");
//		
//		Collections.sort(lista, (p1, p2) -> p1.compareTo(p2));
//		for(String elemento : lista) {
//			System.out.println(elemento);
//		}
//	}
//	
//	public static void main(String[] args) {
//
//
//		//IMPLEMENTANDO UNA INTERFAZ FUCIONAL CON FUNCION LAMBDA
////		Suma suma = (a, b) -> a + b;
////		System.out.println("La suma es: " + suma.sumaDosNumeros(1,2));
//		
//		//PASANDO FUNCION ANOMINA (LAMBDA)
////		Main main = new Main();
////		main.ordenar();
//		
//		//FUNCION DE ORDEN SUPERIOR
//		List<Integer> numeros = Arrays.asList(5, -5, 2, -3, 7, 0, 12, 32, -65, 128);
////		BiFunction<
////			List<Integer>, 
////			Predicate<Integer>, 
////			List<Integer>
////			> filtrar;
////		
////		filtrar = (list,predicado) -> {
////			List<Integer> resultado = new ArrayList<>();
////			for(Integer i : list ) {
////				if(predicado.test(i)) 
////				{ 
////					resultado.add(i);
////				}
////			
////			}
////			return resultado;
////		};
////		
////		System.out.println(filtrar.apply(numeros, x -> x % 2 == 0));
////		
//		
//		//USO DE STREAM
//		List<Integer> resultados = new ArrayList<>();
//		resultados = numeros.stream()
////				.filter(Main::esPar)
//				.filter(n -> n % 2 == 0)
//				.collect(Collectors.toList());
//		System.out.println(resultados);
//	}
//	
//	static boolean esPar(Integer n) {
//		return n%2==0;
//	}
//
//}
