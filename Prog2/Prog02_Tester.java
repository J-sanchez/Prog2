package Prog2;

import java.util.NoSuchElementException;

import java.util.ListIterator;

public class Prog02_Tester {

	public static void main(String[] args) {
		
		System.out.println( "Hello world, Program 02 Tester" );
		
		int testNum = 1;
		int numFailedTests = 0;
		String s = "";

		var roster = new LucasArrayList<String>();
		
		roster.add("alice");
		roster.add("bob");
		roster.add("chad");
		roster.add("dan");
		roster.add("ed");	
		
		/* TEST CASE */
		try {
			s = roster.toString();
			if ( ! s.equals("[alice, bob, chad, dan, ed]") )
				System.out.println( "Failed test " + testNum++ + 
						             " of adding to the ArrayList");  numFailedTests++;
		} catch ( RuntimeException trouble ) {
			System.out.println( "Failed test " + testNum++ + 
		             " of adding to the ArrayList");  numFailedTests++;
		}
		
		/* TEST CASE */
		ListIterator<String> bookmark = null;
		try {
			bookmark = roster.listIterator();
		} catch ( RuntimeException trouble ) {
			System.out.println( "Failed test " + testNum++ + 
		             " of creating the ListIterator");  numFailedTests++;
		}
		
		/* TEST CASE */
		try { 
			s = bookmark.next();
			s = bookmark.next();
			s = bookmark.next();

			if ( ! s.equals("chad") )
				throw new RuntimeException();
			if ( bookmark.nextIndex() != 3 )
				throw new RuntimeException();
			if ( bookmark.previousIndex() != 2 )
				throw new RuntimeException();
			if ( ! bookmark.hasNext() )
				throw new RuntimeException();
			if ( ! bookmark.hasPrevious() )
				throw new RuntimeException();

		} catch ( RuntimeException trouble ) {
			System.out.println( "Failed test " + testNum++ + 
		             " of using next()");  numFailedTests++;
		}
		
		/* TEST CASE */
		try {
			s = bookmark.next();
			s = bookmark.next();

			if ( ! s.equals("ed") )
				throw new RuntimeException();
			if ( bookmark.nextIndex() != 5 )
				throw new RuntimeException();
			if ( bookmark.previousIndex() != 4 )
				throw new RuntimeException();
			if ( bookmark.hasNext() )
				throw new RuntimeException();
			if ( ! bookmark.hasPrevious() )
				throw new RuntimeException();
			try {
				s = bookmark.next();
				throw new RuntimeException();
			} catch ( NoSuchElementException woe ) {
				; // this is the correct behavior
			}
		} catch ( RuntimeException trouble ) {
			System.out.println( "Failed test " + testNum++ + 
		             " of using next() to the end of List");  numFailedTests++;
		}

		/* TEST CASE */
		try { 
			s = bookmark.previous();
			s = bookmark.previous();
			s = bookmark.previous();
			s = bookmark.previous();

			if ( ! s.equals("bob") )
				throw new RuntimeException();
			if ( bookmark.nextIndex() != 1 )
				throw new RuntimeException();
			if ( bookmark.previousIndex() != 0 )
				throw new RuntimeException();
			if ( ! bookmark.hasNext() )
				throw new RuntimeException();
			if ( ! bookmark.hasPrevious() )
				throw new RuntimeException();
		} catch ( RuntimeException trouble ) {
			System.out.println( "Failed test " + testNum++ + 
		             " of using prev()");  numFailedTests++;
		}

		/* TEST CASE */
		try { 
			s = bookmark.previous();

			if ( ! s.equals("alice") )
				throw new RuntimeException();
			if ( bookmark.nextIndex() != 0 )
				throw new RuntimeException();
			if ( bookmark.previousIndex() != -1 )
				throw new RuntimeException();
			if ( ! bookmark.hasNext() )
				throw new RuntimeException();
			if ( bookmark.hasPrevious() )
				throw new RuntimeException();
			try {
				s = bookmark.previous();
				throw new RuntimeException();
			} catch ( NoSuchElementException woe ) {
				; // this is the correct behavior
			}
		} catch ( RuntimeException trouble ) {
			System.out.println( "Failed test " + testNum++ + 
		             " of using prev() at the front of List");  numFailedTests++;
		}

		/* TEST CASE - Test the set method */
		try { 
			s = bookmark.next();
			s = bookmark.next();
			s = bookmark.next();
			bookmark.set( "tom" );
			
			s = roster.toString();
			if ( ! s.equals("[alice, bob, tom, dan, ed]") )
				throw new RuntimeException();
			if ( bookmark.nextIndex() != 3 )
				throw new RuntimeException();
			if ( bookmark.previousIndex() != 2 )
				throw new RuntimeException();
			if ( ! bookmark.hasNext() )
				throw new RuntimeException();
			if ( ! bookmark.hasPrevious() )
				throw new RuntimeException();
			s = bookmark.next();
			if ( ! s.equals("dan") )
				throw new RuntimeException();
		} catch ( RuntimeException trouble ) {
			System.out.println( "Failed test " + testNum++ + 
		             " of using set()");  numFailedTests++;
		}
		
		/* TEST CASE - Test the add method */
		try { 
			bookmark.add( "dave" );
			
			s = roster.toString();
			if ( ! s.equals("[alice, bob, tom, dan, dave, ed]") )
				throw new RuntimeException();
			if ( bookmark.nextIndex() != 5 )
				throw new RuntimeException();
			if ( bookmark.previousIndex() != 4 )
				throw new RuntimeException();
			if ( ! bookmark.hasNext() )
				throw new RuntimeException();
			if ( ! bookmark.hasPrevious() )
				throw new RuntimeException();
			
			bookmark.next();
			bookmark.add( "fran" );   // add at end of list
			
			s = roster.toString();
			if ( ! s.equals("[alice, bob, tom, dan, dave, ed, fran]") )
				throw new RuntimeException();
			if ( bookmark.nextIndex() != 7 )
				throw new RuntimeException();
			if ( bookmark.previousIndex() != 6 )
				throw new RuntimeException();
			if ( bookmark.hasNext() )
				throw new RuntimeException();
			if ( ! bookmark.hasPrevious() )
				throw new RuntimeException();
		} catch ( RuntimeException trouble ) {
			System.out.println( "Failed test " + testNum++ + 
		             " of using add()");  numFailedTests++;
		}
		
		/* TEST CASE - Test the remove method */
		try { 
			bookmark.previous();
			bookmark.previous();
			bookmark.previous();
			bookmark.remove();
			
			s = roster.toString();
			if ( ! s.equals("[alice, bob, tom, dan, ed, fran]") )
				throw new RuntimeException();
			if ( bookmark.nextIndex() != 4 )
				throw new RuntimeException();
			if ( bookmark.previousIndex() != 3 )
				throw new RuntimeException();
			if ( ! bookmark.hasNext() )
				throw new RuntimeException();
			if ( ! bookmark.hasPrevious() )
				throw new RuntimeException();
			
			bookmark.previous();
			bookmark.previous();
			bookmark.previous();
			bookmark.previous();
			bookmark.remove();     // remove first item in List
			
			s = roster.toString();
			if ( ! s.equals("[bob, tom, dan, ed, fran]") )
				throw new RuntimeException();
			if ( bookmark.nextIndex() != 0 )
				throw new RuntimeException();
			if ( bookmark.previousIndex() != -1 )
				throw new RuntimeException();
			if ( ! bookmark.hasNext() )
				throw new RuntimeException();
			if ( bookmark.hasPrevious() )
				throw new RuntimeException();
		} catch ( RuntimeException trouble ) {
			System.out.println( "Failed test " + testNum++ + 
		             " of using remove()");  numFailedTests++;
		}	
		
		
		var rollcall = new LucasArrayList<String>();
		
		rollcall.add("alice");
		rollcall.add("bob");
		rollcall.add("chad");
		rollcall.add("dan");
		rollcall.add("ed");	
		
		/* TEST CASE */
		try {
			s = rollcall.toString();
			if ( ! s.equals("[alice, bob, chad, dan, ed]") )
				throw new RuntimeException();
		} catch ( RuntimeException trouble ) {
			System.out.println( "Failed test " + testNum++ + 
		             " of creating list");  numFailedTests++;
		}
		
		/* TEST CASE */
		bookmark = null;
		try {
			bookmark = rollcall.listIterator();
		} catch ( RuntimeException trouble ) {
			System.out.println( "Failed test " + testNum++ + 
		             " of creating ListIterator");  numFailedTests++;
		}
		
		/* TEST CASE */
		// Test the set operation - illegal if no prior movement
		try { 
			bookmark.set( "zoe" );
			throw new RuntimeException();	
		} catch ( IllegalStateException trouble ) {
			;  // this is the correct behavior
		}catch ( RuntimeException trouble ) {
			System.out.println( "Failed test " + testNum++ + 
		             " of set(), no prior movement");  numFailedTests++;
		}
		
		/* TEST CASE */
		// Test the remove operation - illegal if no prior movement
		try { 
			bookmark.remove();
			throw new RuntimeException();		
		} catch ( IllegalStateException trouble ) {
			;  // this is the correct behavior
		} catch ( RuntimeException trouble ) {
			System.out.println( "Failed test " + testNum++ + 
		             " of remove(), no prior movement");  numFailedTests++; 
		}
		
		/* TEST CASE */
		// Test the add operation - if no prior movement
		try { 
			bookmark.add( "abby" );
			
			s = rollcall.toString();
			if ( ! s.equals("[abby, alice, bob, chad, dan, ed]") )
				throw new RuntimeException();
			if ( bookmark.nextIndex() != 1 )
				throw new RuntimeException();
			if ( bookmark.previousIndex() != 0 )
				throw new RuntimeException();
			if ( ! bookmark.hasNext() )
				throw new RuntimeException();
			if ( ! bookmark.hasPrevious() )
				throw new RuntimeException();
		} catch ( RuntimeException trouble ) {
			System.out.println( "Failed test " + testNum++ + 
		             " of add(), no prior movement");  numFailedTests++; 
		}
		
		System.out.println( "Good-bye world" );
	}
}
