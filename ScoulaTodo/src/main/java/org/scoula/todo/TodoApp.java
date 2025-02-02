package org.scoula.todo;

import org.scoula.lib.cli.App;
import org.scoula.lib.cli.ui.Input;
import org.scoula.lib.cli.ui.Menu;
import org.scoula.lib.cli.ui.MenuItem;
import org.scoula.todo.context.Context;
import org.scoula.todo.exception.LoginFailException;
import org.scoula.todo.service.AccountService;
import org.scoula.todo.service.LoginService;
import org.scoula.todo.service.TodoService;

import java.sql.SQLException;

public class TodoApp extends App {

    Menu userMenu; // 로그인한 상태의 메뉴
    Menu anonymousMenu; // 로그아웃한 상태의 메뉴

//    LoginService loginService = new LoginService();
//    AccountService accountService = new AccountService();
//    TodoService todoService = new TodoService();

    LoginService loginService = Context.getBean(LoginService.class);
    AccountService accountService = Context.getBean(AccountService.class);
    TodoService todoService = Context.getBean(TodoService.class);


//    TodoService service = new TodoService();

    @Override
    public void createMenu(Menu menu) {
        super.createMenu(menu);

//        menu.add(new MenuItem("목록", new PrintTodoCommand()));
//        menu.add(new MenuItem("상세", new DetailTodoCommand()));
//        menu.add(new MenuItem("추가", new AddTodoCommand()));
//        menu.add(new MenuItem("수정", new UpdateTodoCommand()));
//        menu.add(new MenuItem("삭제", new DeleteTodoCommand()));

//        menu.add(new MenuItem("목록", service::printTodoList));
//        menu.add(new MenuItem("상세", service::deleteTodo));
//        menu.add(new MenuItem("추가", service::addTodo));
//        menu.add(new MenuItem("수정", service::updateTodo));
//        menu.add(new MenuItem("삭제", service::deleteTodo));

    }

    @Override
    public void init() {
        anonymousMenu = new Menu();
        anonymousMenu.add(new MenuItem("로그인", this::login));
        anonymousMenu.add(new MenuItem("가입 (사용자 ID: guest / 비밀번호: guest123", this::join));
//        anonymousMenu.add(new MenuItem("종료", this::exit));

        userMenu = new Menu();
//        userMenu.add(new MenuItem("목록", todoService::print));
        userMenu.add(new MenuItem("목록", todoService::printPage));
        userMenu.add(new MenuItem("검색", todoService::search));
        userMenu.add(new MenuItem("상세", todoService::detail));
        userMenu.add(new MenuItem("추가", todoService::create));
        userMenu.add(new MenuItem("수정", todoService::update));
        userMenu.add(new MenuItem("삭제", todoService::delete));


        userMenu.add(new MenuItem("로그아웃", this::logout));
//        userMenu.add(new MenuItem("종료", this::exit));

        setMenu(anonymousMenu); // 시작은 annonymousMenu로
    }

    public void join() {
        accountService.join();
    }

    public void login() {
        try {
            loginService.login();
            setMenu(userMenu);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (LoginFailException e) {
            System.out.println(e.getMessage());
        }
    }

    public void logout() {
        if (Input.confirm("로그아웃 할까요?")) {
            Context.getContext().setUser(null);
            setMenu(anonymousMenu);
        }
    }

    public static void main(String[] args) {
        App app = new TodoApp();
        app.run();
    }


}