//package com.puebla.monitoralertas;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.TimeZone;
//import java.util.stream.Collectors;
//
//import lombok.extern.log4j.Log4j2;
//
//@Log4j2
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
//	public static void main(String[] args) throws ParseException {
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
////		System.out.println(resultados);
//		
//		
//		
//		
//		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
//		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
//
//		//Local time zone   
//		SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
//
//		//Time in GMT
//		
//		System.err.println("Hora local: " + dateFormatLocal.parse( dateFormatGmt.format(new Date())));
//		System.err.println("Hora GMT: " + dateFormatGmt.format(new Date()));
//		System.err.println("Hora DATE: " + new Date());
//		Instant instant = Instant.now();
//		System.err.println("Hora INSTANT: " + instant);
//	
//		
//		log.info("prieba mensaje info");
//		log.warn("prieba mensaje warn");
//		log.error("prieba mensaje error");
//		log.debug("prieba mensaje error");
//		
//		
//	}
//	
//	static boolean esPar(Integer n) {
//		return n%2==0;
//	}
//
//}
