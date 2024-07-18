package org.scoula.todo.dao;

import org.scoula.todo.common.JDBCUtil;
import org.scoula.todo.domain.Todo;
import org.scoula.todo.domain.TodoVO;
import org.scoula.todo.dto.PageRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoDaoImpl implements TodoDao {
    Connection conn = JDBCUtil.getConnection();

    @Override
    public List<Todo> getList() {
        return List.of();
    }

    @Override
    public Todo getTodo(int id) {
        return null;
    }

    @Override
    public void add(Todo todo) {

    }

    @Override
    public void update(Todo todo) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int getTotalCount(String userId) throws SQLException {
        String sql = "select count(*) from todo where userid=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    @Override
    public int create(TodoVO todo) throws SQLException {
        String sql = "insert into todo(title, description, done, userid) values(?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getDescription());
            stmt.setBoolean(3, todo.getDone());
            stmt.setString(4, todo.getUserId());
            return stmt.executeUpdate();
        }
    }

    private TodoVO map(ResultSet rs) throws SQLException {
        TodoVO todo = new TodoVO();
        todo.setId(rs.getLong("id"));
        todo.setTitle(rs.getString("title"));
        todo.setDescription(rs.getString("description"));
        todo.setDone(rs.getBoolean("done"));
        todo.setUserId(rs.getString("userid"));
        return todo;
    }

    private List<TodoVO> mapList(ResultSet rs) throws SQLException {
        List<TodoVO> todoList = new ArrayList<TodoVO>();
        while (rs.next()) {
            TodoVO todo = map(rs);
            todoList.add(todo);
        }
        return todoList;
    }

    @Override
    public List<TodoVO> getList(String user) throws SQLException {
        String sql = "select * from todo where userId = ?";
        try (PreparedStatement stmt = conn.prepareStatement
                (sql)) {
            stmt.setString(1, user);
            try (ResultSet rs = stmt.executeQuery()) {
                return mapList
                        (rs);
            }
        }
    }

    @Override
    public Optional<TodoVO> get(String userId, Long id) throws SQLException {
        String sql = "select * from todo where userId = ? and id = ?";
        try (PreparedStatement stmt = conn.prepareStatement
                (sql)) {
            stmt.setString(1, userId);
            stmt.setLong(2, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<TodoVO> search(String userId, String keyword) throws SQLException {
        String sql = "select * from todo where userId = ? and (title like ? or description like ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setString(2, keyword);
            stmt.setString(3, keyword);
            try (ResultSet rs = stmt.executeQuery()) {
                return mapList(rs);
            }
        }
    }

    @Override
    public int update(String userId, TodoVO todo) throws SQLException {
        String sql = "update todo set title = ?, description = ?, done = ? where userId =? and id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getDescription());
            stmt.setBoolean(3, todo.getDone());
            stmt.setString(4, userId);
            stmt.setLong(5, todo.getId());
            stmt.executeUpdate();
        }
        return 0;
    }

    @Override
    public int delete(String userId, Long id) throws SQLException {
        String sql = "delete from todo where userId = ? and id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
        return 0;
    }

    @Override
    public List<TodoVO> getPage(String userId, PageRequest request) throws SQLException {
        String sql = "select * from todo where userId = ? limit ?, ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setInt(2, request.getOffset());
            stmt.setInt(3, request.getSize());
            try (ResultSet rs = stmt.executeQuery()) {
                return mapList(rs);
            }
        }
    }
}
