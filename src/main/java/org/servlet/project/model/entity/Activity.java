package org.servlet.project.model.entity;

public class Activity {
    private long id;
    private String name;

    public Activity(String name) {
        this.name = name;
    }

    public Activity() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private Activity(ActivityBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static ActivityBuilder builder() {
        return new ActivityBuilder();
    }

    private static class ActivityBuilder {
        private long id;
        private String name;

        public ActivityBuilder id(long id) {
            this.id = id;
            return this;
        }

        public ActivityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Activity build() {
            return new Activity(this);
        }
    }
}
