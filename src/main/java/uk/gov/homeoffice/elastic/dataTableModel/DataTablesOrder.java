package uk.gov.homeoffice.elastic.dataTableModel;

public class DataTablesOrder {

    public enum Direction {
        asc, desc
    }

    private int column;
    private Direction dir;


    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
}
