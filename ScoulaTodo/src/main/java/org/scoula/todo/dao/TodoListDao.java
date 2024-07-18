package org.scoula.todo.dao;

import org.scoula.todo.domain.Todo;
import org.scoula.todo.domain.TodoVO;
import org.scoula.todo.dto.PageRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoListDao implements TodoDao {
    private static TodoListDao instance = new TodoListDao();

    public static TodoDao getInstance() {
        return instance;
    }

    private List<Todo> list;

    private TodoListDao() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) { // 임시 테스트 데이터 구성
            Todo todo = Todo.builder()
                    .title("Todo " + i)
                    .description("Description " + i)
                    .done(false)
                    .build();
            list.add
                    (todo);
        }
    }

    @Override
    public List<Todo> getList() {
        return list;
    }

    @Override
    public Todo getTodo
            (int id) {
        for (Todo todo : list) {
            if (todo.getId() == id) {
                return todo
                        ;
            }
        }
        return null;
    }

    @Override
    public void add(Todo todo) {
        list.add
                (todo);
    }

    @Override
    public void update(Todo todo) {
        for (int
             i = 0;
             i
                     < list.size();
             i++) {
            if (todo.getId() == list.get
                    (
                            i).getId()) {
                list.set
                        (
                                i, todo);
            }
        }
    }

    @Override
    public void delete(int id) {
        for (int
             i = 0;
             i
                     < list.size();
             i++) {
            if (list.get
                    (
                            i).getId() == id) {
                list.remove
                        (
                                i);
                return;
            }
        }
    }

    @Override
    public int getTotalCount(String userId) throws SQLException {
        return 0;
    }

    @Override
    public int create(TodoVO todo) throws SQLException {
        return 0;
    }

    @Override
    public List<TodoVO> getList(String userId) throws SQLException {
        return List.of();
    }

    @Override
    public Optional<TodoVO> get(String userId, Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<TodoVO> search(String userId, String keyword) throws SQLException {
        return List.of();
    }

    @Override
    public int update(String userId, TodoVO todo) throws SQLException {
        return 0;
    }

    @Override
    public int delete(String userId, Long id) throws SQLException {
        return 0;
    }

    @Override
    public List<TodoVO> getPage(String userId, PageRequest request) throws SQLException {
        return List.of();
    }
}