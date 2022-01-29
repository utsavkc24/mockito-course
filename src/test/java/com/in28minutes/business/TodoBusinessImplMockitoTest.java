package com.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.in28minutes.data.api.TodoService;

public class TodoBusinessImplMockitoTest {

	@Test
	public void usingMockito() {
		TodoService todoService = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		when(todoService.retrieveTodos("Utsav")).thenReturn(allTodos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Utsav");
		assertEquals(2, todos.size());
	}
	
	@Test
	public void usingMockitoToRetrieveTodosRelatedToSpring_usingBDD() {
		
		//Given
		TodoService todoService = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("Utsav")).willReturn(allTodos);
		
		//When
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Utsav");
		
		//Then
		assertThat(todos.size(), is(2));
	}
	
	@Test
	public void usingMockitoToDeleteTodosNotRelatedToSpring_usingBDD() {
		
		//Given
		TodoService todoService = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("Utsav")).willReturn(allTodos);
		
		//When
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		 todoBusinessImpl.deleteTodosNotRelatedToSpring("Utsav");
		
		//Then
		 
		//verify(todoService).deleteTodos("Learn to Dance"); // to verify method deleteTodos has executed
		then(todoService).should().deleteTodos("Learn to Dance");
		
		//verify(todoService, never()).deleteTodos("Learn Spring"); // to verify method deleteTodos has not executed
		then(todoService).should(never()).deleteTodos("Learn Spring");
		
		//verify(todoService, atLeastOnce()).deleteTodos("Learn to Dance"); // to verify method deleteTodos has executed atleast once
		then(todoService).should(atLeastOnce()).deleteTodos("Learn to Dance");
		
		//verify(todoService, atLeast(1)).deleteTodos("Learn to Dance"); // to verify method deleteTodos has executed atleast {number of arg pass}
		then(todoService).should(atLeast(1)).deleteTodos("Learn to Dance");
		
		//verify(todoService, times(1)).deleteTodos("Learn to Dance"); // to verify method deleteTodos has executed {number of arg pass}
		then(todoService).should(times(1)).deleteTodos("Learn to Dance");

	}
}