package org.servlet.project.model.entity;

import java.util.Objects;

public class UserActivity {
    private long userId;
    private long activityId;
    private ActivityState state;

    public UserActivity() {}

    public long getUserId() {
        return userId;
    }

    public long getActivityId() {
        return activityId;
    }

    public ActivityState getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserActivity that = (UserActivity) o;
        return userId == that.userId &&
                activityId == that.activityId &&
                state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, activityId, state);
    }

    private UserActivity(UserActivityBuilder builder) {
        this.userId = builder.userId;
        this.activityId = builder.activityId;
        this.state = builder.state;
    }

    public static UserActivityBuilder builder() {
        return new UserActivityBuilder();
    }

    public static class UserActivityBuilder {
        private long userId;
        private long activityId;
        private ActivityState state;

        public UserActivityBuilder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public UserActivityBuilder activityId(long activityId) {
            this.activityId = activityId;
            return this;
        }

        public UserActivityBuilder state(ActivityState state) {
            this.state = state;
            return this;
        }

        public UserActivity build() {
            return new UserActivity(this);
        }
    }
}
