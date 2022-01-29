package com.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.in28minutes.data.api.TodoService;

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectMocksTest {
	@Mock
	TodoService todoService;

	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;

	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;

	@Test
	public void usingMockito() {
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		when(todoService.retrieveTodos("Utsav")).thenReturn(allTodos);

		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Utsav");
		assertEquals(2, todos.size());
	}

	@Test
	public void usingMockito_UsingBDD() {
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		//given
		given(todoService.retrieveTodos("Utsav")).willReturn(allTodos);

		//when
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Utsav");

		//then
		assertThat(todos.size(), is(2));
	}

	@Test
	public void letsTestDeleteNow() {

		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		when(todoService.retrieveTodos("Utsav")).thenReturn(allTodos);

		todoBusinessImpl.deleteTodosNotRelatedToSpring("Utsav");

		verify(todoService).deleteTodos("Learn to Dance");

		verify(todoService, Mockito.never()).deleteTodos("Learn Spring MVC");

		verify(todoService, Mockito.never()).deleteTodos("Learn Spring");

		verify(todoService, Mockito.times(1)).deleteTodos("Learn to Dance");
		// atLeastOnce, atLeast

	}

	@Test
	public void captureArgument() {
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		Mockito.when(todoService.retrieveTodos("Utsav")).thenReturn(allTodos);

		todoBusinessImpl.deleteTodosNotRelatedToSpring("Utsav");
		Mockito.verify(todoService).deleteTodos(stringArgumentCaptor.capture());

		assertEquals("Learn to Dance", stringArgumentCaptor.getValue());
	}
}