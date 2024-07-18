package org.scoula.todo.dao;

import org.scoula.todo.domain.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoListDao implements TodoDao {
    private static TodoListDao instance = new TodoListDao();
    private List<Todo> todos = new ArrayList<>();

    private TodoListDao() {
        // private constructor to prevent instantiation
    }

    public static TodoDao getInstance() {
        return instance;
    }

    @Override
    public List<Todo> getList() {
        return new ArrayList<>(todos);
    }

    @Override
    public Todo getTodo(int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }

    @Override
    public void add(Todo todo) {
        todos.add(todo);
    }

    @Override
    public void update(Todo todo) {
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getId() == todo.getId()) {
                todos.set(i, todo);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        todos.removeIf(todo -> todo.getId() == id);
    }
}
