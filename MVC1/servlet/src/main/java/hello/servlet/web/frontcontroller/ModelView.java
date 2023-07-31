package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

public class ModelView {
    private String name;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(final Map<String, Object> model) {
        this.model = model;
    }
}
