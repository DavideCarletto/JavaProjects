import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.TreeMap;
import org.junit.jupiter.api.*;


public class Test {
	private Studente[] studenti1;
	private ArrayList<Studente> studenti2;
	private LinkedList<Studente> studenti3;
	private Hashtable<String, Studente> studenti4;
	private HashMap<String, Studente> studenti5;
	private TreeMap<String, Studente> studenti6;
	private Studente studenteRiferimento;
	private static final int NUM_MAX_STUDENTI = 2000;

	
	public void creaArrays() {
		studenti1 = new Studente[NUM_MAX_STUDENTI + 1];
		studenti2 = new ArrayList<>();
		studenti3 = new LinkedList<>();
		studenti4 = new Hashtable<>();
		studenti5 = new HashMap<>();
		studenti6 = new TreeMap<>();
		
		long startTime1 = System.currentTimeMillis();
		for (int i = 0; i < NUM_MAX_STUDENTI; i++) {
			Studente s = new Studente((int) Math.random() * 10);

			studenti1[i] = s;

		}
		System.out.println(System.currentTimeMillis()-startTime1);
		long startTime2 = System.currentTimeMillis();
		for (int i = 0; i < NUM_MAX_STUDENTI; i++) {
			Studente s = new Studente((int) Math.random() * 10);

			studenti2.add(s);

		}
		System.out.println(System.currentTimeMillis()-startTime2);
		long startTime3 = System.currentTimeMillis();
		for (int i = 0; i < NUM_MAX_STUDENTI; i++) {
			Studente s = new Studente((int) Math.random() * 10);

			studenti3.add(s);

		}
		System.out.println(System.currentTimeMillis()-startTime3);
		long startTime4 = System.currentTimeMillis();
		for (int i = 0; i < NUM_MAX_STUDENTI; i++) {
			Studente s = new Studente((int) Math.random() * 10);

			studenti4.put("sos"+Math.random()*10, s);

		}
		System.out.println(System.currentTimeMillis()-startTime4);
		long startTime5 = System.currentTimeMillis();
		for (int i = 0; i < NUM_MAX_STUDENTI; i++) {
			Studente s = new Studente((int) Math.random() * 10);

			studenti5.put("sos"+Math.random()*10, s);

		}
		System.out.println(System.currentTimeMillis()-startTime5);
		long startTime6 = System.currentTimeMillis();
		for (int i = 0; i < NUM_MAX_STUDENTI; i++) {
			Studente s = new Studente((int) Math.random() * 10);

			studenti6.put("sos"+Math.random()*10, s);

		}
		System.out.println(System.currentTimeMillis()-startTime6);
		

		studenteRiferimento = new Studente(-2);
		studenti1[NUM_MAX_STUDENTI] = studenteRiferimento;
		studenti2.add(studenteRiferimento);
		studenti3.add(studenteRiferimento);
		studenti4.put("sosa", studenteRiferimento);
		studenti5.put("sosa", studenteRiferimento);
		studenti6.put("sosa", studenteRiferimento);
		
		
	}
	@org.junit.Test
	public void testaArrayStaico() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i <= NUM_MAX_STUDENTI; i++) {
			if (studenti1[i].equals(studenteRiferimento)) {
				long elapsedTime = System.currentTimeMillis() - startTime;
				System.out.println("tempo trascorso arrayStatico:" + elapsedTime);
				break;
			}
			if(NUM_MAX_STUDENTI>0)
			assertEquals("expected 0",	12);
		}
	}

	public void testaArrayDinamico() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i <= NUM_MAX_STUDENTI; i++) {
			if (studenti2.get(i).equals(studenteRiferimento)) {
				long elapsedTime = System.currentTimeMillis() - startTime;
				System.out.println("tempo trascorso ArrayDinamico:" + elapsedTime);
				break;
			}
		}

	}

	public void testaArrayConcatenato() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i <= NUM_MAX_STUDENTI; i++) {
			if (studenti3.get(i).equals(studenteRiferimento)) {
				long elapsedTime = System.currentTimeMillis() - startTime;
				System.out.println("tempo trascorso ArrayConcatenato:" + elapsedTime);
				break;
			}
		}

	}

	public void testaHashTable() {
		long startTime = System.currentTimeMillis();
		studenti4.get("sosa");
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("tempo trascorso testaHashTable:" + elapsedTime);

	}

	public void testaHashMap() {
		long startTime = System.currentTimeMillis();
		studenti5.get("sosa");
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("tempo trascorso testaHashMap:" + elapsedTime);

	}

	public void testaTreeMap() {
		long startTime = System.currentTimeMillis();
		studenti6.get("sosa");
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("tempo trascorso testaTreeMap:" + elapsedTime);

	}

	public static void main(String[] args) {
		Test test = new Test();
		test.creaArrays();
		test.testaArrayStaico();
		test.testaArrayDinamico();
//		 test.testaArrayConcatenato();
		test.testaHashTable();
		test.testaHashMap();
		test.testaTreeMap();
	}

}
