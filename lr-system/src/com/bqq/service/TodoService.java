package com.bqq.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqq.domain.Todo;
import com.bqq.util.TodoPage;
import com.bqq.util.UpdateNickname;

@Service
public class TodoService {
	@Autowired
	UpdateDAO updateDao;
	
	@Autowired
	QueryDAO queryDao;
	
	public List<Todo> initTodos(TodoPage todoPage) {
		return queryDao.executeForObjectList("Todo.initTodos", todoPage);
	}
	
	public int addTodo(Todo todo) {
		return updateDao.execute("Todo.addTodo", todo);
	}
	
	public int findAllNumber(String whoCreate) {
		return queryDao.executeForObject("Todo.findAllNumber", whoCreate, Integer.class);
	}
	
	public Todo findOneById(int id) {
		return queryDao.executeForObject("Todo.findOneById", id, Todo.class);
	}
	
	public int updateComplete(Todo todo) {
		return updateDao.execute("Todo.updateComplete", todo);
	}
	
	public int updatePriority(Todo todo) {
		return updateDao.execute("Todo.updatePriority", todo);
	}
	
	public int delTodoById(int id) {
		return updateDao.execute("Todo.delTodoById", id);
	}

	public void updateWhocreate(UpdateNickname updateNickname) {
		updateDao.execute("Todo.updateWhocreate", updateNickname);
	}

	public List<Todo> findNew15() {
		return queryDao.executeForObjectList("Todo.findNew15", null);
	}

	public int findAllNumber() {
		return queryDao.executeForObject("Todo.findAllNumber2", null, Integer.class);
	}

}
