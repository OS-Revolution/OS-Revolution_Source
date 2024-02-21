package ethos.scaperune.action;

public interface Action extends Runnable {

    /**
     * executes when an action is scheduled
     */
    void onScheduled();

    /**
     * executes if an action is cancelled
     */
    void onCancelled();

    /**
     * executed upon successful completion of an action
     */
    void onSuccess();

    /**
     * Stops the action
     */
    void stop();

    /**
     * Get the delay (in milliseconds) to introduce between actions.
     * @return The delay in milliseconds.
     */
    default long getActionDelay() {
        return 0; // Default to no delay
    }

    /**
     * Determines if an action is repeated in-definitely
     * @return the repeatable status
     */
    default boolean isRepeatable() {
        return false;
    }
}
