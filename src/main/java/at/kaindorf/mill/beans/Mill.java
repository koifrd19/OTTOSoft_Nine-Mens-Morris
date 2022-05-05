package at.kaindorf.mill.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Mill {
    private Position pos1;
    private Position pos2;
    private Position pos3;
}
