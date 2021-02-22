package org.servlet.project.model.entity;

import java.util.Objects;

public class Activity {
    private long id;
    private String name;

    public Activity(String name) {
        this.name = name;
    }

    public Activity() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return id == activity.id &&
                name.equals(activity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    private Activity(ActivityBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static ActivityBuilder builder() {
        return new ActivityBuilder();
    }

    public static class ActivityBuilder {
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
