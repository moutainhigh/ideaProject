package hry.util.excel;

import java.util.ArrayList;
import java.util.List;

public class Label {
    private String label;
    private List<Label> children = new ArrayList<>();

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Label> getChildren() {
        return children;
    }

    public void setChildren(List<Label> children) {
        this.children = children;
    }
}
