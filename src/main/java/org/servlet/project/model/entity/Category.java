package org.servlet.project.model.entity;

public class Category {
    private long id;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private Category(CategoryBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static CategoryBuilder builder() {
        return new CategoryBuilder();
    }

    private static class CategoryBuilder {
        private long id;
        private String name;

        public CategoryBuilder id(long id) {
            this.id = id;
            return this;
        }

        public CategoryBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}
