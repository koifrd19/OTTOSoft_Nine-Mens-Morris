package at.kaindorf.logic.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Move {
    private int start;
    private int destination;
}
