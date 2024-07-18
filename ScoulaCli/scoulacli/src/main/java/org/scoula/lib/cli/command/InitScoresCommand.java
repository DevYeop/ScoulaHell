package org.scoula.lib.cli.command;

import org.scoula.lib.cli.domain.StudentScores;
import org.scoula.lib.cli.ui.Input;

public class InitScoresCommand implements Command{
    StudentScores studentScores = StudentScores.getInstance();
    @Override
    public void execute() {
        int studentNum = Input.getInt("학생수> ");
        studentScores.setStudentNum(studentNum);
    }
}