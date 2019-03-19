package models;

public class Storage {

    public Storage() {
    }

    private Long id;
    private String name;
    private Source source;

    public Storage(Long id, String name, Source source) {
        this.id = id;
        this.name = name;
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
