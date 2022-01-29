package com.in28minutes.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void letMockListSizeMethod() {
		List list = mock(List.class);
		when(list.size()).thenReturn(2);
		
		assertEquals(2, list.size());
	}
	
	@Test
	public void letMockListSizeMethodWithMultipleReturnValue() {
		List list = mock(List.class);
		when(list.size()).thenReturn(2).thenReturn(10).thenReturn(12);
		
		assertEquals(2, list.size());
		assertEquals(10, list.size());
		assertEquals(12, list.size());
	}

	@Test
	public void letMockListGetMethod() {
		List list = mock(List.class);
		when(list.get(0)).thenReturn("Bruce Wayne");
		
		assertEquals("Bruce Wayne", list.get(0));
		
		//Nice mock behavior
		assertEquals(null, list.get(1));
	}
	
	@Test
	public void letMockListGetMethodWithArguementMatcher() {
		List list = mock(List.class);
		when(list.get(anyInt())).thenReturn("Bruce Wayne");
		
		assertEquals("Bruce Wayne", list.get(0));
		
		assertEquals("Bruce Wayne", list.get(1));
	}
	
	@Test(expected = RuntimeException.class)
	public void letMockList_throwAnException() {
		List list = mock(List.class);
		when(list.get(anyInt())).thenThrow(new RuntimeException("Something went wrong"));
		
		list.get(0);
	}
}
