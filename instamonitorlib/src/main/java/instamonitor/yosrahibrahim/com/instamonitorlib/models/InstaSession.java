package instamonitor.yosrahibrahim.com.instamonitorlib.models;

/**
 * Created by Yosrah Ibrahim on 6/9/2016.
 * <p/>
 * Model for session
 */
public class InstaSession {

    /**
     * Component is of type ACTIVITY
     */
    public static final int TYPE_ACTIVITY = 1;

    /**
     * Component is of type FRAGMENT
     */
    public static final int TYPE_FRAGMENT = 2;

    /**
     * id column
     */
    private int id;

    /**
     * component name column
     */
    private String name;

    /**
     * time duration spent in component in milliseconds
     */
    private long duration;

    /**
     * is time in component ignored column
     */
    private boolean isIgnored;

    /**
     * component type column
     */
    private int componentType;

    /**
     * default constructor
     */
    public InstaSession() {
    }

    public InstaSession(String name, int componentType, boolean isIgnored, int duration) {
        this.name = name;
        this.componentType = componentType;
        this.isIgnored = isIgnored;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isIgnored() {
        return isIgnored;
    }

    public void setIgnored(boolean ignored) {
        isIgnored = ignored;
    }

    public int getComponentType() {
        return componentType;
    }

    public void setComponentType(int componentType) {
        this.componentType = componentType;
    }
}
