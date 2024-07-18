package org.scoula.lib.cli.command;

import org.scoula.lib.cli.domain.StudentScores;
import org.scoula.lib.cli.ui.Input;

public class GetScoresCommand implements Command{
    StudentScores studentScores = StudentScores.getInstance();
    @Override
    public void execute() {
        int [] scores = studentScores.getScores();
        for(int i = 0; i< scores.length; i++) {
            scores[i] = Input.getInt("scores[" + i + "]> ");
        }
    }
}